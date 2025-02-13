package automodules;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.INTAKING;
import static global.Modes.robotStatus;
import static global.Modes.teleStatus;

import robot.RobotUser;
import robotparts.RobotPart;

public interface AutoModuleUser extends RobotUser {
    AutoModule intakeOut = new AutoModule(
            outtake.upForIntake(0.1).attach(intake.stageTransfer(0.1)),
            RobotPart.pause(0.15),
            extendo.stageLift(1, 30),
            intake.linkEnd(0.1),
            intake.intakeSeek(0.1),
            RobotPart.pause(0.4),
            intake.clawRelease(0.1)
    ).setStartCode(()->
            robotStatus.set(INTAKING));

    AutoModule intakeIn = new AutoModule(
            intake.intake(0.1),
            intake.clawGrab(0.1),
            RobotPart.pause(0.1),
            intake.clawAdjust(0.1).attach(intake.electricSlide(0.1)),
            RobotPart.pause(0.5),
            intake.clawGrab(0.1),
            outtake.upForIntake(0.1).attach(intake.stageTransfer(0.1)),
            RobotPart.pause(0.3),
            intake.linkStart(0.1),
            RobotPart.pause(0.1),
            extendo.stageLift(1, 8),
            intake.init(0.1),
            RobotPart.pause(0.2),
            extendo.stageLift(1, 0),
            RobotPart.pause(0.2),
            outtake.switcharooReady(0.1).attach(intake.turretSwitcharoo(0.1))
    ).setStartCode(()->
            robotStatus.set(DRIVING));

    AutoModule specimenReady = new AutoModule(
            intake.specimenReady(0.1),
            outtake.specimenReady(0.1),
                // clingy claw :) - comment out
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1),
                outtake.clawGrab(0.1),
                outtake.clawRelease(0.1)
    ).setStartCode(()->
            robotStatus.set(INTAKING));

    AutoModule grabSpecimen = new AutoModule(
            intake.clawLightGrab(0.1),
            intake.yoinkSpecimen(0.2),
            RobotPart.pause(0.3),
            intake.transferSpecimen(0.1),
            RobotPart.pause(0.35),
            outtake.clawGrab(0.1),
            RobotPart.pause(0.3),
            intake.clawRelease(0.1)
    ).setStartCode(()->
            robotStatus.set(DRIVING));

    AutoModule upSpecimen = new AutoModule(
            lift.stageLift(1, 24).attach(outtake.upSpecimen(0.1))
    );

    AutoModule switcharoo = new AutoModule(
            outtake.switcharooReady(0.1).attach(intake.turretSwitcharoo(0.1)),
            RobotPart.pause(0.2),
            intake.switcharoo(0.1),
            outtake.clawGrab(0.1),
            intake.clawRelease(0.1)
    );

    AutoModule high = new AutoModule(
            lift.stageLift(1, 41).attach(outtake.placeHigh(0.1)),
            outtake.placeHigh(0.1)
    );

    AutoModule down = new AutoModule(
            outtake.clawRelease(0.1),
            RobotPart.pause(0.2),
            outtake.init(0.1).attach(lift.stageLift(1, 0)),
            intake.init(0.1)
    );

    AutoModule zestyFlick = new AutoModule(
            intake.zestyFlick(0.1).attach(intake.clawRelease(0.1)),
            intake.init(0.1)
    );

//    AutoModule sampleAlign = new AutoModule (
//            drive.alignSampleRight(0, -0.5, 0),
//            drive.alignSampleLeft(0, 0.5, 0)
//    );
}