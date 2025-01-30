package automodules;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.INTAKING;
import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

import robot.RobotUser;
import robotparts.RobotPart;

public interface AutoModuleUser extends RobotUser {

    AutoModule SpecimenReady = new AutoModule(
            intake.stageStart(.1),
            intake.stageSpecimen(.1),
            intake.stageClose(0.1)
    ).setStartCode(()->
            robotStatus.set(INTAKING));

    AutoModule Specimen = new AutoModule(
            intake.stageOpen(0.1),
            outtake.stageTransfer(0.1),
            RobotPart.pause(.1),
            intake.stageSpecimenPivot(.1),
            RobotPart.pause(.1),
            intake.removeSpecimen(.1),
            RobotPart.pause(.2),
            outtake.stageClose(.1),
            RobotPart.pause(.2),
            intake.stageClose(.1)
    ).setStartCode(()->
            robotStatus.set(DRIVING));

    AutoModule OutSpecimen = new AutoModule(
            outtake.stageSpecimen(.1).attach(lift.stageLift(1,10)  )
    );

    AutoModule InSpecimen = new AutoModule(
            lift.stageLift(1,5),
            outtake.stageOpen(.1),
            intake.stageStart(.1),
            outtake.stageStart(.1).attach(lift.stageLift(1,0))
    );

    AutoModule PlaceHigh = new AutoModule(
            intake.stageOpen(0.1),
            outtake.stageTransfer(0.1),
            intake.stageSpecimenPivot(.1),
            intake.removeSpecimen(.1),
            outtake.stageClose(.1),
            intake.stageClose(.1),
            outtake.stageGrab(.1).attach(lift.stageLift(1,42))
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
            outtake.stageStart(0.1),
            intake.stageTransfer(.1),
            extendo.stageLift(1,17),
            RobotPart.pause(0.1),
            intake.stageEnd1(.5),
            intake.stageAdjustPivot(0.1),
            intake.stageClose(.1),
            intake.stageLinkEnd(.1)
    ).setStartCode(()->
            robotStatus.set(INTAKING));

    AutoModule Grab = new AutoModule(
            intake.stageEnd(.2),
            intake.stageOpen(.2),
            RobotPart.pause(0.1),
            intake.stageTransfer2(.2),
            intake.stageTransfer3(.1),
            intake.stageTransfer(.4),
            RobotPart.pause(0.5),
            intake.stageAdjustClaw(.1),
            intake.stageOpen(.1),
            intake.stageLinkStart(.1),
            extendo.stageLift(1,-1),
            intake.stagePivotStart(.1).attach(outtake.stageOpen(.1)),
            intake.stageDownTurret(.1)
    ).setStartCode(()->
            robotStatus.set(DRIVING));;

    AutoModule Drop = new AutoModule(
            intake.stageTransfer(.1),
            intake.stageClose(.1),
            intake.stageStart(.1)
    );

//    AutoModule SampleAlign = new AutoModule (
//            drive.sampleHorCenter()
//    );
}