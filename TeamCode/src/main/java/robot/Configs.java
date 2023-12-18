package robot;

import static robot.RobotConfig.setConfig;

import elements.Robot;

public class Configs implements RobotUser{

    /**
     * Used to test only the test part
     */
    RobotConfig TestConfig = new RobotConfig(customTestPart);
    RobotConfig TestConfig2 = new RobotConfig(testPart2);
    RobotConfig TestConfig3 = new RobotConfig(drive);
    RobotConfig test = new RobotConfig(camera);

    /**
     * Used for PowerPlay robot
     */
//    RobotConfig PowerPlay = new RobotConfig(drive, lift, outtake, gyro, camera, odometry, distanceSensors);
//    RobotConfig PowerPlay = new RobotConfig(drive, lift, outtake, gyro, camera, odometry);

    /**
     * Used for CenterStage robot
     */
    RobotConfig CenterStage = new RobotConfig(drive, gyro, intake, lift, odometry, outtake, camera);

    /**
     * Current Config
     */
    RobotConfig NewConfig = new RobotConfig(drive);

    public void setCurrentConfig(){
        setConfig(NewConfig);

    }

}
