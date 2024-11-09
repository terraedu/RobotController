package autoutil.vision;

import static autoutil.vision.Scanner.BLUE;
import static autoutil.vision.Scanner.GREEN;
import static autoutil.vision.Scanner.RED;
import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

//import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Autonomous(name = "PixelHSV", group = "auto")
public class PixelScannerHSV extends LinearOpMode {
    @Override
    public void runOpMode() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "ecam");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        // With live preview
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                log.show("CAN'T BE OPENED");
            }


        });
        telemetry.addLine("Starting");
        camera.setPipeline(new Scan());
        telemetry.update();
//        ScannerPipeline scannerPipeline = new ScannerPipeline();
//        scannerPipeline.message();
        waitForStart();
    }

    class Scan extends OpenCvPipeline {
        Mat mat = new Mat();
        double firstLeft;
        double firstRight;
        ArrayList<String> locations;

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




//            mask1.copyTo(input);

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

//        public void setLeftOrRight(Mat input) {
//            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
//            int topx = 750; int topy = 585; int width = 150; int height = 125;
//            Rect left = new Rect(topx, topy, width, height);
//            Rect right = new Rect(topx + 50, topy, width, height);
//
//            Imgproc.rectangle(input, left, BLUE, 3);
//            Imgproc.rectangle(input, right, RED, 3);
//
//            double[] values = scan(left, right);
//
//            double lValue = values[0]; double rValue = values[1];
//
//            if (lValue > rValue) {
//                leftOrRight = "left";
//            } else {
//                leftOrRight = "right";
//            }
//        }

        public double scanBig(Rect rect1) {
            Mat matRect = mat.submat(rect1);
            Scalar avg = Core.mean(matRect);
            double finAvg = avg.val[2];

            return finAvg;
        }
        @Override
        public Mat processFrame(Mat input) {
//            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
////            Rect rect_crop = new Rect(50, 0, 150, 300);
////            Mat crop = mat.submat(rect_crop);
//////            Scalar lowerBound = new Scalar(0, 0, 255 - 15);
//////            Scalar upperBound = new Scalar(255, 15, 255);
//////            Scalar lowerBound = new Scalar(0, 0, 0);
//////            Scalar upperBound = new Scalar(0, 0, 255);
//////            Scalar lowerBound = new Scalar(75, 0, 99);
//////            Scalar upperBound = new Scalar(179, 62, 255);
//////            Scalar upperBound = new Scalar(179, 62, 255);
//            Scalar lowerBound = new Scalar(0, 0, 168);
//            Scalar upperBound = new Scalar(172, 111, 255);
//            int lowerValue = 150;
//            int space = 20;
//            String location = "";
//            boolean first = true;
//
//            int topx = 750; int topy = 585; int width = 150; int height = 25;
////            Mat edges = new Mat();
//            Rect rect1 = new Rect(topx, topy, width, height);
//            Rect rect2 = new Rect(topx, topy + height, width, height);
//            Rect rect3 = new Rect(topx, topy + height * 2, width, height);
//            Rect rect4 = new Rect(topx, topy + height * 3, width, height);
//            Rect rect5 = new Rect(topx, topy + height * 4, width, height);
//
////            Rect rightRect1 = new Rect(topx - width / 2 - space, topy, width / 2, height);
////            Rect rightRect2 = new Rect(topx - width / 2 - space, topy + height, width / 2, height);
////            Rect rightRect3 = new Rect(topx - width / 2 - space, topy + height * 2, width / 2, height);
////            Rect rightRect4 = new Rect(topx - width / 2 - space, topy + height * 3, width / 2, height);
////            Rect rightRect5 = new Rect(topx - width / 2 - space, topy + height * 4, width / 2, height);
//
//            Rect rightRect = new Rect(topx - width / 2, topy, width / 2, height * 5);
//
////            Rect leftRect1 = new Rect(topx + width + space, topy, width / 2, height);
////            Rect leftRect2 = new Rect(topx + width + space, topy + height, width / 2, height);
////            Rect leftRect3 = new Rect(topx + width + space, topy + height * 2, width / 2, height);
////            Rect leftRect4 = new Rect(topx + width + space, topy + height * 3, width / 2, height);
////            Rect leftRect5 = new Rect(topx + width + space, topy + height * 4, width / 2, height);
//
//            Rect leftRect = new Rect(topx + width + space, topy, width / 2, height * 5);
//
//            Imgproc.rectangle(input, rect1, GREEN, 3);
//            Imgproc.rectangle(input, rect2, GREEN, 3);
//            Imgproc.rectangle(input, rect3, GREEN, 3);
//            Imgproc.rectangle(input, rect4, GREEN, 3);
//            Imgproc.rectangle(input, rect5, GREEN, 3);
//
////            Imgproc.rectangle(input, rightRect1, RED, 3);
////            Imgproc.rectangle(input, rightRect2, RED, 3);
////            Imgproc.rectangle(input, rightRect3, RED, 3);
////            Imgproc.rectangle(input, rightRect4, RED, 3);
////            Imgproc.rectangle(input, rightRect5, RED, 3);
////
////            Imgproc.rectangle(input, leftRect1, BLUE, 3);
////            Imgproc.rectangle(input, leftRect2, BLUE, 3);
////            Imgproc.rectangle(input, leftRect3, BLUE, 3);
////            Imgproc.rectangle(input, leftRect4, BLUE, 3);
////            Imgproc.rectangle(input, leftRect5, BLUE, 3);
//
//            Imgproc.rectangle(input, rightRect, RED, 3);
//            Imgproc.rectangle(input, leftRect, BLUE, 3);
//
//            double[] centerValues = scan(rect1, rect2, rect3, rect4, rect5);
////            double[] leftValues = scan(leftRect1, leftRect2, leftRect3, leftRect4, leftRect5);
////            double[] rightValues = scan(rightRect1, rightRect2, rightRect3, rightRect4, rightRect5);
//            double leftValue = scanBig(leftRect);
//            double rightValue = scanBig(rightRect);
//
//            for (int i = 0; i < centerValues.length; i++) {
//                telemetry.addData("Center " + i, centerValues[i]);
//            }
//
//            int p_center_count = 0;
//
//            for (double val: centerValues) {
//                if (val >= lowerValue) {
//                    p_center_count += 1;
//                }
//            }
//
//            telemetry.addData("Pixel count", p_center_count);
//            telemetry.addLine("");
//
//            telemetry.addData("Left", leftValue);
//            telemetry.addData("Right", rightValue);
//
//            if (first) {
//                firstLeft = leftValue;
//                firstRight = rightValue;
//                first = false;
//            } else {
//                if (leftValue >= firstLeft + 15) {
//                    location = "left";
//                } else if (rightValue >= firstRight + 15) {
//                    location = "right";
//                } else {
//                    location = "center";
//                }
//            }

//            for (int i = 0; i < leftValues.length; i++) {
//                telemetry.addData("Left " + i, leftValues[i]);
//            }
//
//            int p_left_count = 0;
//
//            for (double val: centerValues) {
//                if (val >= lowerValue) {
//                    p_left_count += 1;
//                }
//            }
//
//            telemetry.addData("Pixel count", p_left_count);
//            telemetry.addLine("");
//
//            for (int i = 0; i < rightValues.length; i++) {
//                telemetry.addData("Right " + i, rightValues[i]);
//            }
//
//            int p_right_count = 0;
//
//            for (double val: centerValues) {
//                if (val >= lowerValue) {
//                    p_right_count += 1;
//                }
//            }

//            telemetry.addData("Pixel count", p_right_count);
//            telemetry.addLine("");


//            if (p_center_count >= 0) {
//                location = "center";
//            } else if (p_left_count >= 0) {
//                location = "left";
//            } else if (p_right_count >= 0) {
//                location = "right";
//            }


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

            telemetry.update();

//            edges.release();
//            crop.copyTo(input);
//            input.release();
            return input;
        }
    }
}
