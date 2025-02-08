package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.TeleStatus.REDA;
import static teleutil.button.Button.A;
import static teleutil.button.Button.B;
import static teleutil.button.Button.DPAD_DOWN;
import static teleutil.button.Button.LEFT_BUMPER;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;
import static teleutil.button.Button.Y;

import org.opencv.core.Size;

@TeleOp(name = "TerraRed", group = "TeleOp")
public class TerraRed extends Tele {

    @Override
    public void initTele() {
        teleStatus.set(REDA);
        voltageScale = 1;

        Tele auto = this;
        auto.scan(true);

//        intake.yoloScanner = (YoloScanner) yoloScanner;
        intake.sampleScanner = sampleScanner;

        gph1.link(LEFT_BUMPER, () -> intake.turretLeft());
        gph1.link(RIGHT_BUMPER, () -> intake.turretRight());
        gph1.link(DPAD_DOWN, () -> intake.updatePipeline(20));

        gph2.link(B, placeHigh);
        gph2.link(A, place);
        gph2.link(RIGHT_BUMPER, outSpecimen);
        gph2.link(LEFT_BUMPER, inSpecimen);
        gph2.link(DPAD_DOWN, drop);
        gph2.link(RIGHT_TRIGGER, goIntake);
        gph2.link(LEFT_TRIGGER, grab);
        gph2.link(Y, specimenReady);
        gph2.link(X, specimen);

        robotStatus.set(DRIVING);
    }

    @Override
    public void startTele() {
        intake.moveStart();
        outtake.moveStart();
    }

    @Override
    public void loopTele() {
        drive.newMove(gph1.ly,gph1.lx,gph1.rx);

    }
}


