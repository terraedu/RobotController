package autoutil.vision;

import static global.General.log;
import static global.Modes.TeleStatus.BLUEA;
import static global.Modes.teleStatus;

import automodules.stage.Exit;
import autoutil.vision.filters.MovingAverageFilter;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class SampleScanner extends OpenCvPipeline {

    public final MovingAverageFilter angleFilter = new MovingAverageFilter(35);

    Size frameSize = new Size(320, 240);
    Mat ycrcbMat = new Mat();
    Mat crMat = new Mat();
    Mat cbMat = new Mat();

    Mat contoursOnPlainImageMat = new Mat();
    Mat blueThresholdMat = new Mat();
    Mat redThresholdMat = new Mat();
    Mat yellowThresholdMat = new Mat();

    Mat morphedBlueThreshold = new Mat();
    Mat morphedRedThreshold = new Mat();
    Mat morphedYellowThreshold = new Mat();
    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3.5, 3.5));
    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3.5, 3.5));

    double BIGTHRESH = 60000;
    double SMALLTHRESH = 9000;

    int isCenter;
    int win_half_x = 320;
    int win_half_y = 240;
    int win_bottom_y = 480;

    double rect_center_x;
    double rect_center_y;
    double dist_x;
    double dist_y;
    public static double servoPos;

    int YELLOW_MASK_THRESHOLD = 90;
    int BLUE_MASK_THRESHOLD = 150;
    int RED_MASK_THRESHOLD = 170;

    static final Scalar RED = new Scalar(255, 0, 0);
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar YELLOW = new Scalar(255, 255, 0);

    ArrayList<AnalyzedStone> internalStoneList = new ArrayList<>();
    volatile ArrayList<AnalyzedStone> clientStoneList = new ArrayList<>();

    @Override
    public Mat processFrame(Mat input) {
        internalStoneList.clear();
        findContours(input);
        clientStoneList = new ArrayList<>(internalStoneList);
        return input;
    }

    void findContours(Mat input) {
        Imgproc.cvtColor(input, ycrcbMat, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(ycrcbMat, cbMat, 2);
        Core.extractChannel(ycrcbMat, crMat, 1);

        contoursOnPlainImageMat = Mat.zeros(input.size(), input.type());
        contours(input, cbMat, yellowThresholdMat, morphedYellowThreshold, YELLOW_MASK_THRESHOLD, Imgproc.THRESH_BINARY_INV, "Yellow");

        if (teleStatus.modeIs(BLUEA)) {
            contours(input, cbMat, blueThresholdMat, morphedBlueThreshold, BLUE_MASK_THRESHOLD, Imgproc.THRESH_BINARY, "Blue");
        } else {
            contours(input, crMat, redThresholdMat, morphedRedThreshold, RED_MASK_THRESHOLD, Imgproc.THRESH_BINARY, "Red");
        }
    }

    void contours(Mat input, Mat color, Mat thresholdMat, Mat morphedMat, int threshold, int type, String teamColor) {
        Imgproc.threshold(color, thresholdMat, threshold, 255, type);
        morphMask(thresholdMat, morphedMat);
        ArrayList<MatOfPoint> contoursList = new ArrayList<>();

        Imgproc.findContours(morphedMat, contoursList, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        int cnt = 0;
        int size = contoursList.size();
        double minDist = 0;
        double dist;
        RotatedRect smallDistBox = null;

        for (MatOfPoint contour : contoursList) {
            RotatedRect rotatedRectFitToContour = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));

            Imgproc.circle(input, new Point(win_half_x, win_bottom_y), 1, RED, 5);

            rect_center_x = rotatedRectFitToContour.center.x;
            rect_center_y = rotatedRectFitToContour.center.y;

            dist_x = win_half_x - rect_center_x;
            dist_y = win_bottom_y - rect_center_y;

            double area = rotatedRectFitToContour.size.height * rotatedRectFitToContour.size.width;
            dist = Math.pow((Math.pow(dist_x, 2) + Math.pow(dist_y, 2)), 0.5);

            if (rect_center_x > win_half_x) {
                isCenter = 1;
            } else {
                isCenter = -1;
            }

            if (minDist == 0 || dist < minDist)  {
                if (rect_center_x != 0 && rect_center_y != 0 && area < BIGTHRESH && area > SMALLTHRESH) {
                    minDist = dist;
                    smallDistBox = rotatedRectFitToContour;
                }
            }

            if (++cnt == size) {
                if (smallDistBox != null) {
                    double rotRectAngle = smallDistBox.angle;
                    if (smallDistBox.size.width < smallDistBox.size.height) {
                        rotRectAngle += 90;
                    }
                    double angle = -(rotRectAngle - 180);
                    String degrees = (int) Math.round(angle) + " deg";

                    drawItems(input, smallDistBox, degrees, teamColor);
                    Imgproc.line(input, smallDistBox.center, new Point(win_half_x, win_bottom_y), YELLOW);

                    AnalyzedStone analyzedStone = new AnalyzedStone();
                    analyzedStone.angle = Math.round(angle);
                    analyzedStone.color = teamColor;
                    analyzedStone.center = smallDistBox.center;
                    internalStoneList.add(analyzedStone);

                    servoPos = (angle * 0.5) / 180;
                    log.show("servo position ", servoPos);
                }
            }
        }
    }

    static void drawItems(Mat input, RotatedRect rect, String text, String color) {
        Point[] points = new Point[4];
        rect.points(points);
        Scalar colorScalar = getColorScalar(color);

        for (int i = 0; i < 4; ++i) {
            Imgproc.line(input, points[i], points[(i + 1) % 4], colorScalar, 2);
        }

        Imgproc.putText(
                input,
                text,
                new Point(
                        rect.center.x - 50,
                        rect.center.y + 25),
                Imgproc.FONT_HERSHEY_PLAIN,
                1,
                colorScalar,
                1);
    }

    static void drawRotatedRect(RotatedRect rect, Mat drawOn, String color) {
//        log.show("rotated rect drawn");
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

    void morphMask(Mat input, Mat output) {
        Imgproc.erode(input, output, erodeElement);
        Imgproc.erode(output, output, erodeElement);
        Imgproc.dilate(output, output, dilateElement);
        Imgproc.dilate(output, output, dilateElement);
    }

    public boolean centerLeft() {
        boolean centered = false;
        if (isCenter > 0) {
            centered = true;
        }

        return centered;
    }

    public Exit centered(){return new Exit(this::centerLeft);}
}
