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
        double multiplier;
        if (driveMode.modeIs(FAST)){
            multiplier = 1;
        if (driveMode.modeIs(MEDIUM)){
            multiplier = .75;
        if (driveMode.modeIs(SLOW)){
            multiplier = .25;;
        }
        }
        }



    }


}
