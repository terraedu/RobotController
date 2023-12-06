package teleop;

import static global.General.gph1;
import static global.General.log;

import teleutil.button.Button;

public class TerraRiseOp extends Tele {
    @Override
    public void initTele() {
        gph1.link(Button.RIGHT_TRIGGER ,claw::closeClaw);
        gph1.link(Button.LEFT_TRIGGER,claw::openClaw);
        gph1.link(Button.A,arm::liftArm);
        gph1.link(Button.X,arm::resetArm);

    }

    @Override
    public void loopTele() {
log.show("heightMode is: " + heightMode);
        drive.move(gph1.ry,gph1.rx,gph1.lx);
    }
}
