package teleop;

import static global.General.gph1;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Modes;
import teleutil.button.Button;
import teleutil.button.OnTurnOnEventHandler;

@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOp extends Tele {

    @Override
    public void initTele() {

    }

    @Override
    public void loopTele() {
        gph1.link(Button.RIGHT_STICK_X, OnTurnOnEventHandler.class, () -> drive.move(0,1,0));
        gph1.link(Button.RIGHT_STICK_Y, OnTurnOnEventHandler.class, () -> drive.move(1,0,0));
        gph1.link(Button.LEFT_STICK_X, OnTurnOnEventHandler.class, () -> drive.move(0,0,1));


        gph1.link(Button.DPAD_UP, OnTurnOnEventHandler.class, () -> Modes.driveMode.set(Drive.FAST));
        gph1.link(Button.DPAD_LEFT, OnTurnOnEventHandler.class, () -> Modes.driveMode.set(Drive.MEDIUM));
        gph1.link(Button.DPAD_RIGHT, OnTurnOnEventHandler.class, () -> Modes.driveMode.set(Drive.MEDIUM));
        gph1.link(Button.DPAD_DOWN, OnTurnOnEventHandler.class, () -> Modes.driveMode.set(Drive.SLOW));
    }

}


