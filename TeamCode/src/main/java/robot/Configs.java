package robot;

import static robot.RobotConfig.setConfig;

public class Configs implements RobotUser{
    RobotConfig IntoTheDeep = new RobotConfig(intake, outtake, lift, extendo, drive, odometry, gyro);
    public void setCurrentConfig(){setConfig(IntoTheDeep);}
}