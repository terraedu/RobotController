package robot;

import static robot.RobotConfig.setConfig;

// robot -> Configs
public class Configs implements RobotUser{
    /**
     * CONFIGS: This class is called the configuration file. This class tells the program of the robot what
     * hardware is on the robot
     *
     * For example, if the robot has a mecanum drive train, use the 'drive' robot part and put it in the current config
     *
     * RobotConfig NewConfig = new RobotConfig(drive);
     *
     * Then in setCurrentConfig()
     *
     * setConfig(NewConfig);
     */


    // TASK 1: Remove the gyro sensor from the config

    // Now add the arm object you created to the config

    // Now add the gyro sensor
    RobotConfig CampConfig = new RobotConfig(drive, arm,gyro);



    /**
     * Current Config
     */
    public void setCurrentConfig(){
        setConfig(CampConfig);
    }

}
