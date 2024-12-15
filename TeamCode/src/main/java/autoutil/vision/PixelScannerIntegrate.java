package autoutil.vision;

import static global.General.log;
import static global.General.telemetry;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;

public class PixelScannerIntegrate extends Scanner{

    double firstLeft;
    double firstRight;
    public static String location;
    public static ArrayList<String> locations = new ArrayList<>(Arrays.asList("left","center","right"));
    int pixelCount;
    boolean first = true;
    Mat mat = new Mat();

    public double[] scan (Rect rect1, Rect rect2, Rect rect3, Rect rect4, Rect rect5) {
        Mat matRect1 = mat.submat(rect1);
        Mat matRect2 = mat.submat(rect2);
        Mat matRect3 = mat.submat(rect3);
        Mat matRect4 = mat.submat(rect4);
        Mat matRect5 = mat.submat(rect5);

//            Core.extractChannel(matRect1, matRect1, 0);
//            Core.extractChannel(matRect2, matRect2, 0);
//            Core.extractChannel(matRect3, matRect3, 0);
//            Core.extractChannel(matRect4, matRect4, 0);
//            Core.extractChannel(matRect5, matRect5, 0);
//            Core.extractChannel(matRect6, matRect6, 0);

        Scalar v1 = Core.mean(matRect1);
        Scalar v2 = Core.mean(matRect2);
        Scalar v3 = Core.mean(matRect3);
        Scalar v4 = Core.mean(matRect4);
        Scalar v5 = Core.mean(matRect5);

        double v1Avg = v1.val[2];
        double v2Avg = v2.val[2];
        double v3Avg = v3.val[2];
        double v4Avg = v4.val[2];
        double v5Avg = v5.val[2];

        return new double[] {v1Avg, v2Avg, v3Avg, v4Avg, v5Avg};
    }

    public double[] scan (Rect rect1, Rect rect2, Rect rect3) {
        Mat matRect1 = mat.submat(rect1);
        Mat matRect2 = mat.submat(rect2);
        Mat matRect3 = mat.submat(rect3);

        Scalar v1 = Core.mean(matRect1);
        Scalar v2 = Core.mean(matRect2);
        Scalar v3 = Core.mean(matRect3);

        double v1Avg = v1.val[2];
        double v2Avg = v2.val[2];
        double v3Avg = v3.val[2];

        return new double[] {v1Avg, v2Avg, v3Avg};
    }

    public double scanBig(Rect rect1) {
        Mat matRect = mat.submat(rect1);
        Scalar avg = Core.mean(matRect);
        double finAvg = avg.val[2];

        return finAvg;
    }

    public void getPixelCount(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV); // Convert to HSV
        int lowerValue = 150; // Threshold
        int space = 20; // Space between center and right/left rectangles
        int topx = 750; int topy = 585; int width = 150; int height = 25;

        // 5 Rectangles for center
        Rect rect1 = new Rect(topx, topy, width, height);
        Rect rect2 = new Rect(topx, topy + height, width, height);
        Rect rect3 = new Rect(topx, topy + height * 2, width, height);
        Rect rect4 = new Rect(topx, topy + height * 3, width, height);
        Rect rect5 = new Rect(topx, topy + height * 4, width, height);

        // Right rectangle to check if centered
        Rect rightRect = new Rect(topx - width / 2, topy, width / 2, height * 5);

        // Left rectangle to check if centered
        Rect leftRect = new Rect(topx + width + space, topy, width / 2, height * 5);

        // Draw center rectangles on screen
        Imgproc.rectangle(input, rect1, GREEN, 3);
        Imgproc.rectangle(input, rect2, GREEN, 3);
        Imgproc.rectangle(input, rect3, GREEN, 3);
        Imgproc.rectangle(input, rect4, GREEN, 3);
        Imgproc.rectangle(input, rect5, GREEN, 3);

        // Draw left and right rectangles on screen
        Imgproc.rectangle(input, rightRect, RED, 3);
        Imgproc.rectangle(input, leftRect, BLUE, 3);

        double[] centerValues = scan(rect1, rect2, rect3, rect4, rect5); // Get values for every center rectangle

        double leftValue = scanBig(leftRect);
        double rightValue = scanBig(rightRect);

        for (int i = 0; i < centerValues.length; i++) {
            telemetry.addData("Center " + i, centerValues[i]);
        }

        telemetry.addData("Left", leftValue);
        telemetry.addData("Right", rightValue);

        for (double val: centerValues) {
            if (val >= lowerValue) {
                pixelCount += 1;
            }
        }

        if (first) {
            firstLeft = leftValue;
            firstRight = rightValue;
        } else {
            if (leftValue >= firstLeft + 15) {
                location = "left";
            } else if (rightValue >= firstRight + 15) {
                location = "right";
            } else {
                location = "center";
            }
        }

        telemetry.update();
    }

    public void setLeftOrRight(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        int topx = 750; int topy = 585; int width = 200; int height = 125;
        int threshold = 20;
        Rect center = new Rect(750, 585, 200, 125);
        Rect left = new Rect(450, 585, 300, 125);
        Rect right = new Rect(950, 585, 200, 125);

        Imgproc.rectangle(input, left, BLUE, 3);
        Imgproc.rectangle(input, center, RED, 3);
        Imgproc.rectangle(input, right, GREEN, 3);

        double[] values = scan(left, center, right);

        double lValue = values[0]; double cValue = values[1]; double rValue = values[2];

        telemetry.addData("left val", lValue);
        telemetry.addData("center val", cValue);
        telemetry.addData("right val", rValue);


        if (lValue > rValue && lValue > cValue) {
            if (lValue <= rValue + threshold) {
                locations = new ArrayList<>(
                        Arrays.asList("right", "left", "center")
                );
            } else if (lValue <= cValue + threshold) {
                locations = new ArrayList<>(
                        Arrays.asList("center", "left", "right")
                );
            } else {
                double m = Math.max(cValue, rValue);
                if (m == cValue) {
                    locations = new ArrayList<>(
                            Arrays.asList("left", "center", "right")
                    );
                } else {
                    locations = new ArrayList<>(
                            Arrays.asList("left", "right", "center")
                    );
                }
            }
        } else if (rValue > lValue && rValue > cValue) {
            double m = Math.max(cValue, lValue);
            if (m == cValue) {
                locations = new ArrayList<>(
                        Arrays.asList("right", "center", "left")
                );
            } else {
                locations = new ArrayList<>(
                        Arrays.asList("right", "left", "center")
                );
            }
        } else {
            if (cValue <= rValue + threshold) {
                locations = new ArrayList<>(
                        Arrays.asList("right", "center", "left")
                );
            } else {
                double m = Math.max(rValue, lValue);
                if (m == rValue) {
                    locations = new ArrayList<>(
                            Arrays.asList("center", "right", "left")
                    );
                } else {
                    locations = new ArrayList<>(
                            Arrays.asList("center", "left", "right")
                    );
                }
            }
        }
        telemetry.addData("location", locations.get(0));
    }

//    public static String locatnio = locations.get(0);



    public void setFirst(boolean f) {
        first = f;
    }

    public String getLocation() {
        return locations.get(0);
    }
    @Override
    public void run(Mat input) {
        setLeftOrRight(input);
    }

    @Override
    public void preProcess(Mat input) {

    }

    @Override
    public void postProcess(Mat input) {

    }

    @Override
    public void message() {
        log.show("Scanning Pixels...");
        log.show("Rect location", location);
        log.show("Pixel count", pixelCount);
    }
}
