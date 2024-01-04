package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.log;
import static global.Modes.Drive.FAST;
import static teleutil.button.Button.*;

import global.General;

@TeleOp(name = "FireOp", group = "TeleOp")
public class FireOp extends Tele {

    @Override
    public void initTele() {
        outtake.start();

        driveMode.set(FAST);

    gph1.link(X, PLACELOW);
    gph1.link(A, LOW);
        gph1.link(B, MIDDLE);
    gph1.link(DPAD_LEFT, openClaw1);
        gph1.link(DPAD_RIGHT, openClaw2);



        gph1.link(RIGHT_BUMPER, Reset);
    gph1.link(RIGHT_TRIGGER, PLACE);
    gph1.link(LEFT_TRIGGER, GRAB);





    }


    @Override
    public void startTele() {


    }

    @Override
    public void loopTele() {

        drive.newMove(gph1.ry, gph1.rx, gph1.lx);

//        log.show("pose", odometry.getPose());
//        log.show("DriveMode", driveMode.get());



















//        log.show("StackedMode", lift.stackedMode == 0 ? "N/A" : 6-lift.stackedMode);




















//        log.show("TrackStatus", kappaBefore.isEnabled() ? "Kappa" : "None");
//        log.show("OuttakeStatus", outtakeStatus.get());

//        log.show("Kappa Size", kappaBefore.steps.size());
//        log.show("Kappa #", kappaBefore.stepNumber);

//        log.show("GamepadMode", gph1.isBackPressed() ? AUTOMATED : GamepadMode.NORMAL);


//        log.show("Is", bot.indHandler.isIndependentRunning());
//
//        log.show("heading", gyro.getHeading());

//        junctionScannerAll.message();
        log.show("pivot", lift.pivot.getPosition());
//        log.show("Left", lift.motorLeft.getPosition());
//        log.show("TargetRight", lift.motorRight.getPositionHolder().getTarget());
//        log.show("TargetLeft", lift.motorLeft.getPositionHolder().getTarget());
//        log.show("Pose", odometry.getPose());
//        log.show("SavedPose", bot.getSavedPose());
//        log.show("Voltage", bot.getVoltage());
//        log.show("Pitch", gyro.getPitch());
    }

}


