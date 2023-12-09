//package teleop;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import javax.crypto.Mac;
//
//import automodules.AutoModule;
//import automodules.AutoModuleUser;
//import autoutil.vision.JunctionScannerAll;
//import elements.FieldPlacement;
//import geometry.framework.Point;
//import geometry.position.Pose;
//import geometry.position.Vector;
//import math.polynomial.Linear;
//import robotparts.electronics.output.OLed;
//import teleutil.TeleTrack;
//import teleutil.button.Button;
//import util.Timer;
//import util.codeseg.CodeSeg;
//import util.template.Precision;
//
//import static global.General.bot;
//import static global.General.cameraMonitorViewId;
//import static global.General.fault;
//import static global.General.fieldPlacement;
//import static global.General.gph1;
//import static global.General.gph2;
//import static global.General.log;
//import static global.General.voltageScale;
//import static global.Modes.Drive.MEDIUM;
//import static global.Modes.Drive.SLOW;
//import static global.Modes.GamepadMode.*;
//import static global.Modes.OuttakeStatus.*;
//import static global.Modes.Height.*;
//import static teleutil.button.Button.*;
//import static teleutil.TeleTrack.*;
//
////@TeleOp(name = "NithinOp", group = "TeleOp")
//public class NithinOp extends Tele {
//
//    @Override
//    public void initTele() {
//        voltageScale = 1;
//        gph2.link(RIGHT_BUMPER, () -> lift.adjustHolderTarget(2.5));
//        gph2.link(LEFT_BUMPER, () -> lift.adjustHolderTarget(-2.5));
//        gph1.link(DPAD_DOWN, () -> intake.move(-1));
//        gph1.link(DPAD_UP, () -> intake.move(1));
//        gph1.link(Y, PlaceHigh);
//        gph1.link(B, PlaceMid);
//        gph1.link(X, Place);
//        gph1.link(RIGHT_TRIGGER, PlaceReady);
//        gph1.link(A, PlaceLow);
//        gph2.link(Y, ManualOpenFull);
//        gph2.link(A, ManualClose);
//        gph2.link(B, ManualOpenHalf);
//        /**
//         * Start code
//         */
//        outtake.moveEnd();
//        outtake.closeClaw();
//
//    }
//
//    @Override
//    public void startTele() {
//        lift.reset();
//    }
//
//    @Override
//    public void loopTele() {
//
//        drive.newMove(gph1.ry, gph1.rx, gph1.lx);
//        lift.move(gph2.ry);
//        log.show("pose", odometry.getPose());
////        log.show("DriveMode", driveMode.get());
////        log.show("StackedMode", lift.stackedMode == 0 ? "N/A" : 6-lift.stackedMode);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////        log.show("TrackStatus", kappaBefore.isEnabled() ? "Kappa" : "None");
////        log.show("OuttakeStatus", outtakeStatus.get());
//
////        log.show("Kappa Size", kappaBefore.steps.size());
////        log.show("Kappa #", kappaBefore.stepNumber);
//
////        log.show("GamepadMode", gph1.isBackPressed() ? AUTOMATED : GamepadMode.NORMAL);
//
//
////        log.show("Is", bot.indHandler.isIndependentRunning());
////
////        log.show("heading", gyro.getHeading());
//
////        junctionScannerAll.message();
////        log.show("Right", lift.motorRight.getPosition());
////        log.show("Left", lift.motorLeft.getPosition());
////        log.show("TargetRight", lift.motorRight.getPositionHolder().getTarget());
////        log.show("TargetLeft", lift.motorLeft.getPositionHolder().getTarget());
////        log.show("Pose", odometry.getPose());
////        log.show("SavedPose", bot.getSavedPose());
////        log.show("Voltage", bot.getVoltage());
////        log.show("Pitch", gyro.getPitch());
//    }
//
//}
//
//
