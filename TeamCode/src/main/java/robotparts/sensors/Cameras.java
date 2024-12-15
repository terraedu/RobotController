package robotparts.sensors;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

//import auton.AutonomousDummy;
import autoutil.vision.Scanner;
import geometry.position.Pose;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.input.ICamera;
//import robotparts.electronics.input.ICameraNew;

import static global.General.hardwareMap;
import static global.General.log;

public class Cameras extends RobotPart {
    public ICamera cam;
//    public ICameraNew camNew;
    public OpenCvCamera camera1;

    //    @Override
//    public void init() {
//        cam = create("ecam", ElectronicType.ICAMERA_EXTERNAL);
//    }
    @Override
    public void init() {
    }
    //    public void start(boolean view){ cam.start(view); }
    public void start(boolean view) {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "ecam");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        // With live preview
        camera1 = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        camera1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera1.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {
                log.show("CAN'T BE OPENED");
            }
        });}
    public void startAndResume(boolean view){ start(view); resume(); }
    public void pause(){ camera1.pauseViewport(); }
    public void resume(){ cam.resume(); }
    public void setScanner(Scanner scanner){ camera1.setPipeline(scanner);}
    //    public void startVuforia(boolean view){ cam.startVuforia(view); }
//    public boolean updateVuforia(){ return cam.updateVuforia(); }
    public Pose getPoseFromVuforia(){ return cam.getPose(); }
    @Override
    public void halt(){
//        WebcamName webcamName = hardwareMap.get(WebcamName.class, "ecam");
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        // With live preview
//        camera1 = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
//        camera1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
//        {
//            @Override
//            public void onOpened()
//            {
//                camera1.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
//            }
//            @Override
//            public void onError(int errorCode)
//            {
//                log.show("CAN'T BE OPENED");
//            }
//        });
//        camera1.closeCameraDeviceAsync(new OpenCvCamera.AsyncCameraCloseListener() {
//        @Override
//        public void onClose() {}
//    });
}
    public double getFPS(){ return cam.getFramesPerSecond(); }


    /**
     * Used to get the monitor view Id (To view what the camera is seeing)
     * @return monitor id
     */
    public static int getCameraMonitorViewId(){ return hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()); }

}
