package automodules;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.INTAKING;
import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

import auton.Auto;
import robot.RobotUser;
import robotparts.RobotPart;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {

    AutoModule SpecimenReady = new AutoModule(
            intake.stageSpecimen(.1)

    );
    AutoModule Specimen = new AutoModule(
            intake.stageOpen(.1),
            intake.removeSpecimen(.1),
            intake.stageTransfer(.1),
            intake.stagePivotStart(.1),
            intake.stageStartTurret(.1),
            intake.stageAdjustClaw(.3),
            intake.stageDownTurret(.1),
            RobotPart.pause(.2),
            intake.stageOpen(.1).attach(outtake.stageOpen(.1))


    );
AutoModule PlaceHigh = new AutoModule(
        outtake.stageOpen(.1),
outtake.stageTransfer(.1),
  intake.stageTransfer4(.1),
        RobotPart.pause(.2),
        outtake.stageClose(.1),
        intake.stageClose(.1),
  outtake.stageGrab(.1).attach(lift.stageLift(1,40)  )
).setStartCode(()->
        robotStatus.set(PLACING)
        );
AutoModule Place = new AutoModule(
  outtake.stageOpen(.1),
        intake.stageStart(.1),
        outtake.stageStart(.1).attach(lift.stageLift(1,0))
).setStartCode(()->
        robotStatus.set(DRIVING));;



AutoModule Intake = new AutoModule(
        intake.stageTransfer(.1),
        extendo.stageLift(1,25),
        intake.stageLinkEnd(.1),
        intake.stagePivotStart(.1),
        intake.stageTurret(.1),
        intake.stageEnd1(.1),
        intake.stageClose(.1)


        ).setStartCode(()->
        robotStatus.set(INTAKING));
    AutoModule Grab = new AutoModule(
            intake.stageEnd(.1),
            intake.stageOpen(.1),
            intake.stageTransfer2(.1),
            intake.stageTransfer3(.1),
            intake.stageTransfer(.1),
            intake.stageLinkStart(.1),
            extendo.stageLift(1,0),
            intake.stagePivotStart(.1),
            intake.stageStartTurret(.1),
            intake.stageAdjustClaw(.3),
            RobotPart.pause(.4),
            intake.stageOpen(.1).attach(outtake.stageOpen(.1))





    ).setStartCode(()->
            robotStatus.set(DRIVING));;






}


