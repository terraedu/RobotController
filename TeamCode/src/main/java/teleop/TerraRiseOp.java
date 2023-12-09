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

        outtake.moveStart();
        outtake.closeClaw();

    }

    @Override
    public void loopTele() {
        drive.move(gph1.ry, gph1.rx, gph2.lx);


    }
}
