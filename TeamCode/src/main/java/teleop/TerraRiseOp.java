package teleop;

import static global.General.gph1;

import teleutil.button.Button;

public class TerraRiseOp extends Tele {
    @Override
    public void initTele() {
        gph1.link(Button.A,outtake::closeClaw);
    }

    @Override
    public void loopTele() {
drive.move(gph1.ry,gph1.rx,gph1.lx);
    }
}
