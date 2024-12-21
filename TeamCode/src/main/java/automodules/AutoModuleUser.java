package automodules;

import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

import auton.Auto;
import robot.RobotUser;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {


AutoModule PlaceHigh = new AutoModule(
  outtake.stageGrab(.1).attach(lift.stageLift(1,36)  )
).setStartCode(()->
        robotStatus.set(PLACING)
        );
AutoModule Place = new AutoModule(
  outtake.stageOpen(.1),
        outtake.stageStart(.1).attach(lift.stageLift(1,0))
);






}


