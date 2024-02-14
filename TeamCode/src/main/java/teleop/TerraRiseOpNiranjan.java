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
@TeleOp(name = "NiranjanOp", group = "Teleop")
public class TerraRiseOpNiranjan extends Tele {

    @Override
    public void loopTele() {
        if (driveMode.modeIs(SLOW)) {
            drive.move(gph1.ry * 0.25, gph1.rx * 0.25, gph1.lx * 0.25);
        } else if (driveMode.modeIs(FAST)) {
            drive.move(gph1.ry, gph1.rx, gph1.lx);
        }
        }
        }



    }


}
