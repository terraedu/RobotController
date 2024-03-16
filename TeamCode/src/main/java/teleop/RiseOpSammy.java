package teleop;

import static global.General.gph1;
import static global.General.gph2;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.Height.LOW;
import static global.Modes.OuttakeStatus.DRIVING;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robotparts.hardware.SammyDrive;
import teleutil.button.Button;

@TeleOp( name = "RiseOpSammy", group = "TeleOp")

public class RiseOpSammy extends Tele {


    @Override

    public void initTele() {

heightMode.set(LOW);
outtakeStatus.set(DRIVING);
driveMode.set(FAST);


//gph1 is outtake, gph2 is driving
        gph2.link(Button.DPAD_DOWN, () -> driveMode.set(SLOW));
        gph2.link(Button.DPAD_DOWN, () -> driveMode.set(FAST));
        gph2.link(Button.DPAD_RIGHT, () -> driveMode.set(MEDIUM));

    }

    @Override
    public void loopTele() {
        SammyDrive.SammyMove(gph1.ry, gph1.rx, gph1.lx);
    }

    }

}