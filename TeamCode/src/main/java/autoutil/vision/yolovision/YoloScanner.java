package autoutil.vision.yolovision;

import autoutil.vision.filters.MovingAverageFilter;
import org.opencv.core.*;
import org.opencv.dnn.Dnn;
//import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.*;

import static java.lang.Math.*;
import static global.General.log;
import static global.Modes.TeleStatus.BLUEA;
import static global.Modes.teleStatus;

// step 1: get input image
// step 2: get yolo bboxes
// step 3: nms + enlarge through padding using toRect
// step 4: iterate through boxes
// step 5: figure out color
// step 6: find contours in box
// step 7: rotatedRect + angle

public class YoloScanner extends OpenCvPipeline {

    Yolo yolo;
    Size frameSize;
    int counter;
    static Size reSized = new Size(448, 336);

    public final MovingAverageFilter angleFilter = new MovingAverageFilter(35);
    public double rotRectArea;

    static final Scalar RED = new Scalar(0, 0, 255);
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar YELLOW = new Scalar(0, 255, 255);

    public static int YELLOW_MASK_THRESHOLD = 90;
    public static int BLUE_MASK_THRESHOLD = 150;
    public static int RED_MASK_THRESHOLD = 150;
    public static double BIG_THRESH = 200000;
    public static double SMALL_THRESH = 0; // ask baba abt static

    static class AnalyzedStone {
        double angle;
        String color;
        Point center;
    }

    ArrayList<AnalyzedStone> internalStoneList = new ArrayList<>();
    volatile ArrayList<AnalyzedStone> clientStoneList = new ArrayList<>();


    public YoloScanner() {
        this.yolo = new Yolo();
    }

    @Override
    public Mat processFrame(Mat input) {
        counter++;
//        System.out.println("processing frame: " + counter);
        this.frameSize = input.size();
        Mat frame = new Mat();

        Size size = new Size(416, 416);
        Mat input3 = new Mat();
        Imgproc.cvtColor(frame, input3, Imgproc.COLOR_BGRA2BGR);
        Mat blob = Dnn.blobFromImage(input3, 0.00392, size, new Scalar(0), true, false);

        Map<BoundingBox, Double> objects = yolo.forward(input3, blob);
        List<BoundingBox> boxes = nonMaxSuppression(objects, 0.5);
        Point origin = new Point(frameSize.width / 2, frameSize.height);

        objectIterator(input, boxes, origin, 40, 50, true, true);
        return input;
    }

    public void objectIterator(Mat frame, List<BoundingBox> boxes, Point origin, int padX, int padY, boolean onlyClosest, boolean drawYolo) {
        int size = boxes.size();
        int cnt = 0;
        double minDist = 0;
        double dist;
        Point box_center;

        BoundingBox smallDistBox = null;
        Rect bbox_rect = new Rect();
        Mat bboxMat;

        for (BoundingBox box : boxes) {
//            System.out.println("# of boxes: " + boxes.size());
            box_center = new Point(box.x, box.y);
            dist = pow((pow((origin.x - box_center.x), 2) + pow((origin.y - box_center.y), 2)), 0.5);

            if (onlyClosest) {
                if (minDist == 0 || dist < minDist) {
                    minDist = dist;
                    smallDistBox = box;
                }
                if (++cnt == size) {
                    bbox_rect = smallDistBox.toRect(padX, padY, frameSize);
                    try {
                        bboxMat = new Mat(frame, bbox_rect);
                        findContours(bboxMat);
                    } catch (Exception e) {
                        log.show("ERROR w box: " + smallDistBox);
                    }
                }
            } else {
                bbox_rect = box.toRect(padX, padY, frameSize);
                try {
                    bboxMat = new Mat(frame, bbox_rect);
                    findContours(bboxMat);
                } catch (Exception e) {
                    log.show("ERROR w box: " + box);
                }
            }
            if (drawYolo) {
                Imgproc.rectangle(frame, bbox_rect, new Scalar(255, 255, 255), 1); // Draws yolo bounding boxes
            }
        }
//        HighGui.imshow("Image", frame);
//        HighGui.waitKey(1);
    }

    public List<BoundingBox> nonMaxSuppression(Map<BoundingBox, Double> proposals, double overlapThresh) {
        List<BoundingBox> filtered_proposals = new ArrayList<>();
        double IOU;

        while (!proposals.isEmpty()) {
            Map.Entry<BoundingBox, Double> maxEntry = null;
            for (Map.Entry<BoundingBox, Double> entry : proposals.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }

            BoundingBox highConfBbox = maxEntry.getKey();
            proposals.remove(highConfBbox);
            filtered_proposals.add(highConfBbox);

            List<BoundingBox> removeProposals = new ArrayList<>();

            for (BoundingBox proposal : proposals.keySet()) {
                int a1 = highConfBbox.x - (highConfBbox.width / 2);
                int b1 = highConfBbox.y - (highConfBbox.height / 2);
                int c1 = highConfBbox.x + (highConfBbox.width / 2);
                int d1 = highConfBbox.y + (highConfBbox.height / 2);

                int a2 = proposal.x - (proposal.width / 2);
                int b2 = proposal.y - (proposal.height / 2);
                int c2 = proposal.x + (proposal.width / 2);
                int d2 = proposal.y + (proposal.height / 2);

                int aInter = Math.max(a1, a2);
                int bInter = Math.max(b1, b2);
                int cInter = min(c1, c2);
                int dInter = min(d1, d2);

                if (cInter > aInter && dInter > bInter) {
                    int intersection = (cInter - aInter) * (dInter - bInter);
                    int highArea = (c1 - a1) * (d1 - b1);
                    int propArea = (c2 - a2) * (d2 - b2);
                    int union = highArea + propArea - intersection;

                    IOU = (double) intersection / union;
//                    System.out.println(IOU);
                    if (IOU > overlapThresh) {
                        removeProposals.add(proposal);
                    }
                }
            }
            for (BoundingBox proposal : removeProposals) {
                proposals.remove(proposal);
            }
        }
        return filtered_proposals;
    }

    void findContours(Mat input) {
        Mat ycrcbMat = new Mat();
        Mat cbMat = new Mat();
        Mat crMat = new Mat();

//        String mode = "red";

        Imgproc.cvtColor(input, ycrcbMat, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(ycrcbMat, cbMat, 1);
        Core.extractChannel(ycrcbMat, crMat, 2);

        contours(input, cbMat, new Mat(), YELLOW_MASK_THRESHOLD, Imgproc.THRESH_BINARY_INV, "Yellow");

        if (teleStatus.modeIs(BLUEA)) {
            contours(input, cbMat, new Mat(), BLUE_MASK_THRESHOLD, Imgproc.THRESH_BINARY, "BLUE");
        } else {
            contours(input, crMat, new Mat(), RED_MASK_THRESHOLD, Imgproc.THRESH_BINARY, "RED");
        }
    }

    void contours(Mat input, Mat colorMat, Mat thresholdMat, int threshold, int type, String teamColor)
    {
        Imgproc.threshold(colorMat, thresholdMat, threshold, 255, type);

        ArrayList<MatOfPoint> contoursList = new ArrayList<>();
        Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
        for (MatOfPoint contour : contoursList) {
            analyzeContour(contour, input, teamColor);
        }
    }


    void analyzeContour(MatOfPoint contour, Mat input, String color) {
        RotatedRect rotatedRectFitToContour = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));
        rotRectArea = rotatedRectFitToContour.size.width * rotatedRectFitToContour.size.height;

        if (rotRectArea > SMALL_THRESH && rotRectArea < BIG_THRESH) {
            drawRotatedRect(rotatedRectFitToContour, input, color);
            drawRotatedRect(rotatedRectFitToContour, Mat.zeros(input.size(), input.type()), color);

            double rotRectAngle = rotatedRectFitToContour.angle;
            if (rotatedRectFitToContour.size.width < rotatedRectFitToContour.size.height) {
                rotRectAngle += 90;
            }
            double angle = -(rotRectAngle - 180);

            AnalyzedStone analyzedStone = new AnalyzedStone();
            analyzedStone.angle = Math.round(angle);
            analyzedStone.color = color;
            analyzedStone.center = rotatedRectFitToContour.center;
            internalStoneList.add(analyzedStone);
        }
    }

    static void drawRotatedRect(RotatedRect rect, Mat drawOn, String color) {
        Scalar colorScalar = getColorScalar(color);

        Point[] points = new Point[4];
        rect.points(points);

        for (int i = 0; i < 4; i++) {
            Imgproc.line(drawOn, points[i], points[(i + 1) % 4], colorScalar, 2);
        }
    }

    static Scalar getColorScalar(String color) {
        switch (color) {
            case "Blue":
                return BLUE;
            case "Yellow":
                return YELLOW;
            default:
                return RED;
        }
    }

    public double getAngle() {
        if (clientStoneList.isEmpty()) {
            return -1;
        }

        Point frameCenter = new Point(frameSize.width / 2, frameSize.height / 2);
        AnalyzedStone closestStone = null;
        double closestDistance = Double.MAX_VALUE;

        for (AnalyzedStone stone : clientStoneList) {
            double distanceToCenter = Math.hypot(stone.center.x - frameCenter.x, stone.center.y - frameCenter.y);
            if (distanceToCenter < closestDistance) {
                closestDistance = distanceToCenter;
                closestStone = stone;
            }
        }

        if (closestStone != null) {
            return angleFilter.estimate(closestStone.angle);
        } else {
            return -1;
        }
    }
}
