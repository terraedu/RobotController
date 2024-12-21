package automodules;

import autoutil.vision.SampleScanner;
import robotparts.hardware.Intake.*;
import robot.RobotUser;
import util.User;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {

    AutoModule angleIntake = new AutoModule(

           intake.stageAngle(0.1)

    ).setStartCode(()-> {
            }
    );

}


