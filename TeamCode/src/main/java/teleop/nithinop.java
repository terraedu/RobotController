package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.INTAKING;
import static global.Modes.TeleStatus.REDA;
import static teleutil.button.Button.LEFT_BUMPER;
import static teleutil.button.Button.LEFT_STICK_BUTTON;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;

@TeleOp(name = "\uD83D\uDE08", group = "TeleOp")
public class nithinop extends Tele {

    @Override
    public void initTele() {
        teleStatus.set(REDA);
        voltageScale = 1;


        gph1.linkWithCancel(RIGHT_TRIGGER, robotStatus.isMode(DRIVING), intakeOut, robotStatus.isMode(INTAKING), intakeIn, DRIVINGMODE);
//        gph1.linkWithCancel(RIGHT_BUMPER, robotStatus.isMode(SPECIMEN), upSpecimen, robotStatus.isMode(DRIVING), high, down);
        gph1.linkWithCancel(LEFT_TRIGGER, robotStatus.isMode(DRIVING), high, robotStatus.isMode(INTAKING), resetturret, down);
//        gph1.linkWithCancel(LEFT_BUMPER, robotStatus.isMode(DRIVING), specimenReady, grabSpecimen);
//        gph1.link(Y, SAMPLEMODE);
//        gph1.link(A, SPECIMENMODE);
        gph1.link(LEFT_STICK_BUTTON, ()->{extendo.liftAdjust(10);});
        gph1.link(LEFT_BUMPER, intake::turretLeft);
        gph1.link(RIGHT_BUMPER, intake::turretRight);
//        gph2.link(RIGHT_TRIGGER, high);
//        gph2.link(RIGHT_BUMPER, clearIntake);
//        gph2.link(LEFT_TRIGGER, zestyFlick);
//        gph2.link(LEFT_BUMPER, zestiestFlick);
//        gph2.link(A, switcharoo);

        robotStatus.set(DRIVING);
    }

    @Override
    public void startTele() {

        intake.moveInit();
        outtake .moveInit();

    }

    @Override
    public void loopTele() {

        drive.newMove(gph1.ly,gph1.rx,gph1.lx);

        log.show("right:", lift.motorRight.getPosition());
        log.show("left:", lift.motorLeft.getPosition());
        log.show("intake:", extendo.motorLeft.getPosition());
        log.show("arml:", intake.iarml.getPosition());
//        if (teleStatus.modeIs(INTAKING)) {extendo.move(gph1.ry);}
        log.show("odo", odometry.getPose());

        lift.move(gph2.ly);
        extendo.move(gph2.ry);
    }
}