package teleop;

import static global.General.gph1;
import static global.General.log;
import static teleutil.button.Button.A;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teleutil.button.Button;

@TeleOp(name = "RiseOp", group = "TeleOp")
public class TerraRiseOp extends Tele {
    @Override
    public void initTele() {
//        gph1.link(RIGHT_TRIGGER ,claw::closeClaw);
//        gph1.link(LEFT_TRIGGER,claw::openClaw);
//        gph1.link(A,arm::liftArm);
//        gph1.link(X,arm::resetArm);

    }

    @Override
    public void loopTele() {
        drive.move(gph1.ry,gph1.rx,gph1.lx);
    }
}
