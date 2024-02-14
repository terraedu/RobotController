package teleop;
import stat1global.General.log;

import static global.General.gph1;
import static global.General.gph2;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.Height.LOW;
import static global.Modes.OuttakeStatus.DRIVING;
import static teleutil.button.Button.A;
import static teleutil.button.Button.DPAD_DOWN;
import static teleutil.button.Button.DPAD_LEFT;
import static teleutil.button.Button.DPAD_RIGHT;
import static teleutil.button.Button.DPAD_UP;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import automodules.AutoModule;
import automodules.AutoModuleUser;
import teleutil.button.Button;

@TeleOp(name = "RiseOp", group = "TeleOp")
public class TerraRiseOp extends Tele {
    @Override
    public void initTele() {
        //gph1 is outtake controller gph2 is the driving controller

        heightMode.set(LOW);
        outtakeStatus.set(DRIVING);
        driveMode.set(FAST);

        outtake.moveStart();
        gph2.link(Button.LEFT_BUMPER, openClaw);
        gph2.link(Button.RIGHT_BUMPER , closeClaw); 
        gph1.link(Button.DPAD_DOWN, () -> driveMode.set(SLOW));
        gph1.link(Button.DPAD_UP, () -> driveMode.set(FAST));
        gph2.link(Button.LEFT_TRIGGER, extendGrab);
        gph2.link(Button.RIGHT_TRIGGER, grabAndDrive);
        gph2.link(Button.A, armMove);
        gph2.link(Button.B, liftMove);
        gph2.link(Button.X, down);
        gph2.link(Button.DPAD_DOWN, grabPivot);
        gph2.link(Button.DPAD_UP, drivePivot);
        gph2.link(Button.Y, release);
        //        outtake.closeClaw();
//        outtake.placePivot();
    }

    @Override
    public void loopTele() {
        if (driveMode.modeIs(SLOW)) {
            drive.move(gph1.ry * 0.25, gph1.rx * 0.25, gph1.lx * 0.25);
        } else if (driveMode.modeIs(FAST)) {
            drive.move(gph1.ry, gph1.rx, gph1.lx);
        }
//        lift.armMove(gph2.ry);
//        lift.move(gph2.ly);
        log.show(lift.getPivotMotorPos());
        log.show(lift.lift.getPosition());
        log.show(outtake.pivot.getPosition());
        log.show(outtake.launch.getPosition());
//        log.show(driveMode.getValue());
    }
}
