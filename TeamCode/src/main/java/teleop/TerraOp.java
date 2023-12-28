package teleop;

import automodules.AutoModuleUser;
import teleutil.button.Button;

import static global.General.bot;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.GamepadMode.*;
import static global.Modes.OuttakeStatus.*;
import static global.Modes.Height.*;
import static teleutil.button.Button.*;

public class TerraOp extends Tele {
    @Override
    public void initTele() {
        outtake.moveStart();
        lift.reset();
    }


    @Override
    public void loopTele() {
    drive.move(gph1.ry, gph1.rx, gph1.lx);

    }
}

