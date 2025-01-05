package autoutil.vision;

//import com.acmerobotics.dashboard.config.Config;
import static global.General.log;
import static global.Modes.TeleStatus.BLUEA;
import static global.Modes.teleStatus;

import autoutil.vision.filters.MovingAverageFilter;

//import org.firstinspires.ftc.teamcode.opencv.eocvtest.SamplePipeline;
//import org.firstinspires.ftc.teamcode.opmode.BaseOpMode;
//import autoutil.vision.subsystem.IntakeClawSys;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
public class SampleScanner extends OpenCvPipeline {

    public final MovingAverageFilter angleFilter = new MovingAverageFilter(35);
    public static boolean drawOnScreen = true;
    public static double sizeThreshold = 70000;
    public static int win_center_x = 320;
    public static int win_center_y = 240;
    public static double rect_center_x;
    public static double rect_center_y;
    public static double dist_x;
    public static double dist_y;
    public static double dist_x_in;
    public static double dist_y_in;
    public static int sample_length_pixels_4in = 260;
    int counter;

    Mat ycrcbMat = new Mat();
    Mat crMat = new Mat();
    Mat cbMat = new Mat();

    Mat blueThresholdMat = new Mat();
    Mat redThresholdMat = new Mat();
    Mat yellowThresholdMat = new Mat();

    Mat morphedBlueThreshold = new Mat();
    Mat morphedRedThreshold = new Mat();
    Mat morphedYellowThreshold = new Mat();

    Mat contoursOnPlainImageMat = new Mat();

    Size frameSize = new Size(320, 240);

    /*
     * Threshold values
     */
    public static int YELLOW_MASK_THRESHOLD = 80;
    public static int BLUE_MASK_THRESHOLD = 150;
    public static int RED_MASK_THRESHOLD = 170;
    public static int MAX_THRESHOLD = 6000;

    /*
     * Elements for noise reduction
     */

    Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3.5, 3.5));
    Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3.5, 3.5));

    /*
     * Colors
     */
    static final Scalar RED = new Scalar(255, 0, 0);
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar YELLOW = new Scalar(255, 255, 0);

    static class AnalyzedStone {
        double angle;
        String color;
        Point center;
    }

    ArrayList<AnalyzedStone> internalStoneList = new ArrayList<>();
    volatile ArrayList<AnalyzedStone> clientStoneList = new ArrayList<>();

    @Override
    public Mat processFrame(Mat input) {
        internalStoneList.clear();
        counter++;
        findContours(input);
        clientStoneList = new ArrayList<>(internalStoneList);
        return input;
    }


    void findContours(Mat input) {

        Imgproc.cvtColor(input, ycrcbMat, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(ycrcbMat, cbMat, 2);
        Core.extractChannel(ycrcbMat, crMat, 1);

        contoursOnPlainImageMat = Mat.zeros(input.size(), input.type());
//        Imgproc.threshold(cbMat, yellowThresholdMat, YELLOW_MASK_THRESHOLD, 255, Imgproc.THRESH_BINARY_INV);
//        morphMask(yellowThresholdMat, morphedYellowThreshold);
//        ArrayList<MatOfPoint> yellowContoursList = new ArrayList<>();
//
//        Imgproc.findContours(morphedYellowThreshold, yellowContoursList, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
//        for (MatOfPoint contour : yellowContoursList) {
//            analyzeContour(contour, input, "Yellow");
//        }
        contours(input, cbMat, yellowThresholdMat, morphedYellowThreshold, YELLOW_MASK_THRESHOLD, Imgproc.THRESH_BINARY_INV, "Yellow");

        if (teleStatus.modeIs(BLUEA)) {
//            Imgproc.threshold(cbMat, blueThresholdMat, BLUE_MASK_THRESHOLD, 255, Imgproc.THRESH_BINARY);
//            morphMask(blueThresholdMat, morphedBlueThreshold);
//            ArrayList<MatOfPoint> blueContoursList = new ArrayList<>();
//            Imgproc.findContours(morphedBlueThreshold, blueContoursList, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
//            for (MatOfPoint contour : blueContoursList) {
//                analyzeContour(contour, input, "Blue");
//            }
            contours(input, cbMat, blueThresholdMat, morphedBlueThreshold, BLUE_MASK_THRESHOLD, Imgproc.THRESH_BINARY, "Blue");

        } else {
//            Imgproc.threshold(crMat, redThresholdMat, RED_MASK_THRESHOLD, 255, Imgproc.THRESH_BINARY);
//            morphMask(redThresholdMat, morphedRedThreshold);
//            ArrayList<MatOfPoint> redContoursList = new ArrayList<>();
//            Imgproc.findContours(morphedRedThreshold, redContoursList, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
//            for (MatOfPoint contour : redContoursList) {
//                analyzeContour(contour, input, "Red");
//            }
            contours(input, crMat, redThresholdMat, morphedRedThreshold, RED_MASK_THRESHOLD, Imgproc.THRESH_BINARY, "Red");

        }
    }

    void contours(Mat input, Mat color, Mat thresholdMat, Mat morphedMat, int threshold, int type, String teamColor)
    {
        Imgproc.threshold(color, thresholdMat, threshold, 255, type);
        morphMask(thresholdMat, morphedMat);
        ArrayList<MatOfPoint> contoursList = new ArrayList<>();

        Imgproc.findContours(morphedMat, contoursList, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);
        for (MatOfPoint contour : contoursList) {
            analyzeContour(contour, input, teamColor);
        }
    }

    void morphMask(Mat input, Mat output) {
        Imgproc.erode(input, output, erodeElement);
        Imgproc.erode(output, output, erodeElement);
        Imgproc.dilate(output, output, dilateElement);
        Imgproc.dilate(output, output, dilateElement);
    }

    void analyzeContour(MatOfPoint contour, Mat input, String color) {

        Point[] points = contour.toArray();
        MatOfPoint2f contour2f = new MatOfPoint2f(points);

        RotatedRect rotatedRectFitToContour = Imgproc.minAreaRect(contour2f);


        rect_center_x = rotatedRectFitToContour.center.x;
        rect_center_y = rotatedRectFitToContour.center.y;
        dist_x = win_center_x - rect_center_x;
        dist_y = win_center_y - rect_center_y;
        // 260 pixels divided by 8.89 cm (sample length) is 29.24
        dist_x_in = dist_x / 29.2463442; // dividing dist in pixels by pixels that are 1 cm long at 4 inches
        dist_y_in = dist_y / 29.2463442;

        if (this.counter > 30) {
            log.show("x inches", dist_x_in);
            log.show("y inches", dist_y_in);
        }

        if (rotatedRectFitToContour.size.width * rotatedRectFitToContour.size.height < sizeThreshold) {
            //if (drawOnScreen) {
            drawRotatedRect(rotatedRectFitToContour, input, color);
            drawRotatedRect(rotatedRectFitToContour, contoursOnPlainImageMat, color);
            //}
            double rotRectAngle = rotatedRectFitToContour.angle;
            if (rotatedRectFitToContour.size.width < rotatedRectFitToContour.size.height) {
                rotRectAngle += 90;
            }
            double angle = -(rotRectAngle - 180);
            if (drawOnScreen) {
                drawTagText(rotatedRectFitToContour, (int) Math.round(angle) + " deg", input, color);
            }
            AnalyzedStone analyzedStone = new AnalyzedStone();
            analyzedStone.angle = Math.round(angle);
            analyzedStone.color = color;
            analyzedStone.center = rotatedRectFitToContour.center;
//            log.show("sample angle:", analyzedStone.angle);
//            log.show("sample color:", analyzedStone.color);
            internalStoneList.add(analyzedStone);
//            log.show("analyzed stone added to internal list");
        }
    }

    static void drawTagText(RotatedRect rect, String text, Mat mat, String color) {
        Scalar colorScalar = getColorScalar(color);

        Imgproc.putText(
                mat,
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
        Point[] points = new Point[4];
        rect.points(points);

        Scalar colorScalar = getColorScalar(color);

        for (int i = 0; i < 4; ++i) {
            Imgproc.line(drawOn, points[i], points[(i + 1) % 4], colorScalar, 2);
        }
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
    public ArrayList<AnalyzedStone> getDetectedStones() {
        return clientStoneList;
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


//    @Override
//    public void onViewportTapped() {
//        toggleRecording = !toggleRecording;
//
//        if(toggleRecording)
//        {
//            /*
//             * This is all you need to do to start recording.
//             */
//            phoneCam.startRecordingPipeline(
//                    new PipelineRecordingParameters.Builder()
//                            .setBitrate(4, PipelineRecordingParameters.BitrateUnits.Mbps)
//                            .setEncoder(PipelineRecordingParameters.Encoder.H264)
//                            .setOutputFormat(PipelineRecordingParameters.OutputFormat.MPEG_4)
//                            .setFrameRate(30)
//                            .setPath("/sdcard/pipeline_rec.mp4")
//                            .build());
//        }
//        else
//        {
//            /*
//             * Note: if you don't stop recording by yourself, it will be automatically
//             * stopped for you at the end of your OpMode
//             */
//            phoneCam.stopRecordingPipeline();
//        }
//    }
}
