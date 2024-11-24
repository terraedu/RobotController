package robot;

import static robot.RobotConfig.setConfig;

public class Configs implements RobotUser{


    /**
     * Used for PowerPlay robot
     */
//    RobotConfig PowerPlay = new RobotConfig(drive, lift, outtake, gyro, camera, odometry, distanceSensors);
//    RobotConfig PowerPlay = new RobotConfig(drive, lift, outtake, gyro, camera, odometry);

    /**
     * Used for CenterStage robot
     */
//    RobotConfig CenterStage = new RobotConfig(gyro, odometry, distanceSensorsNew,colorSensorsNew); //drive, intake, lift, outtake, camera

    /**
     * Used for Into The Deep robot
     */
    RobotConfig IntoTheDeep = new RobotConfig(gyro, odometry, outtake, intake, extendo, drive); //drive, intake, lift, outtake, camera

    /**
     * Current Config
     */
    public void setCurrentConfig(){
        setConfig(IntoTheDeep);
    }

}
