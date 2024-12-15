//package autoutil.vision;
//
//import static global.General.log;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfPoint;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvPipeline;
//
//import java.awt.font.ImageGraphicAttribute;
//import java.util.ArrayList;
//import java.util.List;
//
//@Autonomous(name = "PixelDummy", group = "auto")
//public class PixelScannerTest extends LinearOpMode {
//    @Override
//    public void runOpMode() {
//        WebcamName webcamName = hardwareMap.get(WebcamName.class, "ecam");
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        // With live preview
//        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
//        {
//            @Override
//            public void onOpened()
//            {
//                camera.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
//            }
//            @Override
//            public void onError(int errorCode)
//            {
//                log.show("CAN'T BE OPENED");
//            }
//
//
//        });
//        telemetry.addLine("Starting");
//        camera.setPipeline(new Scan());
//        telemetry.update();
////        ScannerPipeline scannerPipeline = new ScannerPipeline();
////        scannerPipeline.message();
//        waitForStart();
//    }
//
//    class Scan extends OpenCvPipeline {
//        Mat mat = new Mat();
//        @Override
//        public Mat processFrame(Mat input) {
//            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
//            Rect rect_crop = new Rect(50, 0, 150, 300);
//            Mat crop = mat.submat(rect_crop);
//////            Scalar lowerBound = new Scalar(0, 0, 255 - 15);
//////            Scalar upperBound = new Scalar(255, 15, 255);
//////            Scalar lowerBound = new Scalar(0, 0, 0);
//////            Scalar upperBound = new Scalar(0, 0, 255);
//////            Scalar lowerBound = new Scalar(75, 0, 99);
//////            Scalar upperBound = new Scalar(179, 62, 255);
//            Scalar lowerBound = new Scalar(0, 0, 168);
//            Scalar upperBound = new Scalar(172, 111, 255);
//            Mat edges = new Mat();
//            Imgproc.rectangle(input, rect_crop, new Scalar(255, 0, 0),5);
////
////            Mat thresh = new Mat();
////            Core.inRange(mat, lowerBound, upperBound, thresh);
//////            Rect bbox = Imgproc.boundingRect(thresh);
////
////            Mat masked = new Mat();
////            Core.bitwise_and(mat, mat, masked, thresh);
////
//////            Scalar average = Core.mean(masked, thresh);
//////
//////            Mat scaledMask = new Mat();
//////            masked.convertTo(scaledMask, -1, 150/average.val[1], 0);
////
////            Mat scaledThresh = new Mat();
////            Scalar strictLowerBound = new Scalar(0, 0, 179);
////            Scalar strictUpperBound = new Scalar(172, 40, 255);
////            Core.inRange(masked, strictLowerBound, strictUpperBound, scaledThresh);
//////
////            Mat finalMask = new Mat();
//////            Core.bitwise_and(mat, mat, finalMask, scaledThresh);
////
////            Mat blur = new Mat();
////            Imgproc.blur(scaledThresh, blur, new Size(3, 3));
////            Mat edges = new Mat();
////            Imgproc.Canny(blur, edges, 100, 200);
//
//            Core.inRange(crop, lowerBound, upperBound, edges);
//
//            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
//            Imgproc.morphologyEx(edges, edges, Imgproc.MORPH_OPEN, kernel);
//            Imgproc.morphologyEx(edges, edges, Imgproc.MORPH_CLOSE, kernel);
////
//            List<MatOfPoint> contours = new ArrayList<>();
//            Mat hierarchy = new Mat();
//            Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//            double maxArea = 0;
//            MatOfPoint largestContour = null;
//            for (MatOfPoint contour : contours) {
//                double area = Imgproc.contourArea(contour);
//                if (area > maxArea) {
//                    maxArea = area;
//                    largestContour = contour;
//                }
//            }
//
//            if (largestContour != null) {
//                telemetry.addLine("detected stack");
//                telemetry.update();
//                Rect rect = Imgproc.boundingRect(largestContour);
//                Imgproc.drawContours(crop, contours, contours.indexOf(largestContour), new Scalar(0, 0, 255), 3);
//                Imgproc.rectangle(crop, rect, new Scalar(0, 255, 0), 2);
//            }
//
//            Imgproc.cvtColor(crop, input, Imgproc.COLOR_HSV2RGB);
////            edges.copyTo(input);
//            edges.release();
////            crop.copyTo(input);
//            crop.release();
//            return input;
//        }
//    }
//}
