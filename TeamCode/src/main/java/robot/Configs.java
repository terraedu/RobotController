package robot;

import static robot.RobotConfig.setConfig;

public class Configs implements RobotUser{
    RobotConfig IntoTheDeep = new RobotConfig(camera);
    public void setCurrentConfig(){
        setConfig(IntoTheDeep);
    }
}