package automodules;

import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

import auton.Auto;
import robot.RobotUser;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {


//AutoModule PlaceHigh = new AutoModule(
//  outtake.stageGrab(.1).attach(lift.stageLift(1,36)  )
//).setStartCode(()->
//        robotStatus.set(PLACING)
//        );
//AutoModule Place = new AutoModule(
//  outtake.stageOpen(.1),
//        outtake.stageStart(.1).attach(lift.stageLift(1,0))
//);

AutoModule Transfer = new AutoModule(
  intake.stageTransfer(.1)
);

AutoModule Intake = new AutoModule(
  intake.stageLinkEnd(.1),
        intake.stageEnd(.1)

);
    AutoModule Grab = new AutoModule(
           intake.stageOpen(.1),
            intake.stageTransfer2(.1),
            intake.stageTransfer3(.1),
            intake.stageTransfer(.1),
            intake.stageLinkStart(.1)

    );






}


