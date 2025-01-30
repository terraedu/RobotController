package robotparts.sensors;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import autoutil.vision.old.Scanner;
import robotparts.RobotPart;

import static global.General.hardwareMap;
import static global.General.log;

public class Cameras extends RobotPart {
    public OpenCvCamera camera;

    @Override
    public void init() {}
    public void start(boolean view) {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "ecam");
        log.show("got cam");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        if (view) {
            camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
            camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){
            @Override
            public void onOpened() {
                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
                log.show("camera opened");
            }
            @Override
            public void onError(int errorCode) {
                log.show("CAN'T BE OPENED");
            }
            });
        }
    }

    public void pause(){
        camera.pauseViewport();}
    public void resume(){
        camera.resumeViewport();}
    public void setScanner(Scanner scanner){
        camera.setPipeline(scanner);}

    /**
     * Used to get the monitor view Id (To view what the camera is seeing)
     * @return monitor id
     */
    public static int getCameraMonitorViewId(){ return hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()); }
}
