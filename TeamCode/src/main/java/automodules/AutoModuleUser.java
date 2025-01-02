package automodules;

import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

import auton.Auto;
import robot.RobotUser;
import robotparts.RobotPart;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {

    AutoModule SpecimenReady = new AutoModule(
            outtake.stageSpecimen(.1)



        );
        AutoModule Specimen = new AutoModule(
    //            outtake.stageClose(.1),
    //            outtake.stageStart(.1),
                lift.stagePivot(1,10)

        );
    AutoModule PlaceHigh = new AutoModule(
            outtake.stageLinkEnd(.1),
    lift.stagePivot(.5,7.5),
        lift.stageLift(1,30)
).setStartCode(()->
        robotStatus.set(PLACING)
        );
AutoModule Place = new AutoModule(
  outtake.stageOpen(.1).attach(outtake.stageStart(.1)),
        outtake.stageStart(.1).attach(lift.stageLift(1,0)),
        lift.stagePivot(.5,0)

        );



AutoModule Intake = new AutoModule(
        lift.stageLift(1, 20).attach(outtake.stageLinkEnd(.1)),
        outtake.stageGrab(.1),
        outtake.stageOpen(.1)


        );
    AutoModule Grab = new AutoModule(
        outtake.stageCloseArm(.1),
            RobotPart.pause(.2),
            outtake.stageClose(.1),
            outtake.stageStart(.1),
            lift.stageLift(1,0)



    );






}


