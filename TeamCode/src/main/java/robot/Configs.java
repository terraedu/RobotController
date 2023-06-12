package robot;

import static robot.RobotConfig.setConfig;

public class Configs implements RobotUser{

    /**
     * Used to test only the test part
     */
    RobotConfig TestConfig = new RobotConfig(customTestPart);
    RobotConfig TestConfig2 = new RobotConfig(testPart2);
    RobotConfig TestConfig3 = new RobotConfig(drive, camera);

    /**
     * Used for Camp robot
     */
//    RobotConfig CampConfig = new RobotConfig(drive, gyro);

    RobotConfig CampConfig = new RobotConfig(drive);

    /**
     * Current Config
     */
    public void setCurrentConfig(){
        setConfig(CampConfig);
    }

}
