package autoutil.vision;

import org.firstinspires.ftc.vision.apriltag.*;

public class AprilTag {
    public static void main(String[] args) {
//        AprilTagProcessor myAprilTagProcessor;
//        // Create the AprilTag processor and assign it to a variable.
//        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        // TODO5 Not efficient code
//        AprilTagProcessor.Builder myAprilTagProcessorBuilder;
//        AprilTagProcessor myAprilTagProcessor;
//
//// Create a new AprilTag Processor Builder object.
//        myAprilTagProcessorBuilder = new AprilTagProcessor.Builder();
//        myAprilTagProcessorBuilder.setDrawTagID(true);       // Default: true, for all detections.
//        myAprilTagProcessorBuilder.setDrawTagOutline(true);  // Default: true, when tag size was provided (thus eligible for pose estimation).
//        myAprilTagProcessorBuilder.setDrawAxes(true);        // Default: false.
//        myAprilTagProcessorBuilder.setDrawCubeProjection(true);        // Default: false.
//
//        //Create AprilTagProcessor
//        myAprilTagProcessor = myAprilTagProcessorBuilder.build();


        //Creating an AprilTagProcessor
        AprilTagProcessor myAprilTagProcessor;

        myAprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .build();
    }


    //Creating FTC Vision Portal
}
