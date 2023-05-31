package robot;

import robotparts.hardware.Drive;
import robotparts.hardware.Intake;
import robotparts.hardware.Lift;
import robotparts.hardware.Outtake;
import robotparts.sensors.Cameras;
import robotparts.sensors.ColorSensors;
import robotparts.sensors.GyroSensors;
import robotparts.sensors.odometry.ThreeOdometry;
import robotparts.unused.CustomTestPart;
import robotparts.unused.DistanceSensors;
import robotparts.unused.TouchSensors;
import unittests.tele.framework.movement.AutoModuleTest;

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
    DistanceSensors distanceSensors = new DistanceSensors();
    GyroSensors gyro = new GyroSensors();
    Cameras camera = new Cameras();

//    Odometry odometry = new TwoOdometry();
    ThreeOdometry odometry = new ThreeOdometry(); // TOD 5 EXTEND THIS CONCEPT TO ALL ROBOT PART
    /**
     * UNUSED
     */

    Intake intake = new Intake();
    TouchSensors touchSensors = new TouchSensors();

    ColorSensors color = new ColorSensors();

    /**
     * Test Part
     */

    CustomTestPart customTestPart = new CustomTestPart();
    AutoModuleTest.TestPart2 testPart2 = new AutoModuleTest.TestPart2();



}
