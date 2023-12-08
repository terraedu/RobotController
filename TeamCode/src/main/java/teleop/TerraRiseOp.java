package teleop;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
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
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teleutil.button.Button;

@TeleOp(name = "RiseOp", group = "TeleOp")
public class TerraRiseOp extends Tele {
    @Override
    public void initTele() {
        //gph1 is outtake controller gph2 is the driving controller
        heightMode.set(LOW);
        outtakeStatus.set(DRIVING);
        driveMode.set(FAST);
        gph1.link(RIGHT_TRIGGER ,claw::closeClaw);
        gph1.link(LEFT_TRIGGER,claw::openClaw);
        gph1.link(A,arm::liftArm);
        gph1.link(X,arm::resetArm);
        gph1.link(DPAD_UP,claw::openClaw);
        gph1.link(DPAD_DOWN, claw::closeClaw);
        gph2.link(X,claw::droneLaunch);
        gph2.link(DPAD_UP,()->driveMode.set(FAST));
        gph2.link(DPAD_LEFT,()->driveMode.set(MEDIUM));
        gph2.link(DPAD_RIGHT,()->driveMode.set(SLOW));



    }

    @Override
    public void loopTele() {
        double multiplier;
        if(driveMode.modeIs(FAST)){
            multiplier = 1;

        }else if(driveMode.modeIs(MEDIUM)){
            multiplier = 0.75;
        }else{
          multiplier = 0.5;
        }
        drive.move(multiplier*gph2.ry,multiplier*gph2.rx,multiplier*gph2.lx);
    }
}
