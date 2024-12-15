package robot;

import robotparts.hardware.Extendo;
import robotparts.sensors.ColorSensors;
import robotparts.sensors.DistanceSensors;
import robotparts.sensors.odometry.NewOdometry;
import unittests.tele.framework.movement.AutoModuleTest;
import robotparts.hardware.Drive;
import robotparts.hardware.Intake;
import robotparts.hardware.Lift;
import robotparts.hardware.Outtake;
import robotparts.sensors.Cameras;
import robotparts.sensors.GyroSensors;
//import robotparts.unused.DistanceSensorsOld;
import robotparts.unused.CustomTestPart;
import robotparts.unused.TouchSensors;

public interface RobotUser {
    /**
     * Implement this in any class that needs to use the robot
      */



    /**
     * USED
     */
    Drive drive = new Drive();
    Lift lift = new Lift();
    Outtake outtake = new Outtake();
//    Leds leds = new Leds();
//    DistanceSensorsOld distanceSensors = new DistanceSensorsOld();
    GyroSensors gyro = new GyroSensors();
    Cameras camera = new Cameras();
    DistanceSensors distanceSensorsNew = new DistanceSensors();
    ColorSensors colorSensorsNew = new ColorSensors();

//    Odometry odometry = new TwoOdometry();
//    ThreeOdometry odometry = new ThreeOdometry(); // TOD 5 EXTEND THIS CONCEPT TO ALL ROBOT PARTS
    NewOdometry odometry = new NewOdometry();
    /**
     * UNUSED
     */

    Intake intake = new Intake();
    Extendo extendo = new Extendo();

    TouchSensors touchSensors = new TouchSensors();


    /**
     * Test Part
     */

    CustomTestPart customTestPart = new CustomTestPart();
    AutoModuleTest.TestPart2 testPart2 = new AutoModuleTest.TestPart2();



}
