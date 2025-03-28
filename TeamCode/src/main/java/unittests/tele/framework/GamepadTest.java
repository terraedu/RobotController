package unittests.tele.framework;

import teleutil.button.Button;
import teleutil.button.main.ButtonEventHandler;
import teleutil.button.main.ChangeHoldEventHandler;
import teleutil.button.main.OnPressEventHandler;
import teleutil.button.toggle.OnTurnOffEventHandler;
import teleutil.button.toggle.OnTurnOnEventHandler;
import teleutil.button.toggle.WhenOffEventHandler;
import teleutil.button.toggle.WhenOnEventHandler;
import unittests.tele.TeleUnitTest;

import static global.General.*;

public class GamepadTest extends TeleUnitTest {
    /**
     * Tests gamepad handler by using the a button
     */


    /**
     * Used so that messages are different ever time presses are logged
     */
    private int i;

    @Override
    protected void start() {
        /**
         * Should change as the a button is pressed
         */
        gph1.link(Button.A, ButtonEventHandler.class, () -> log.show("Held"));
        gph1.link(Button.A, OnPressEventHandler.class, () -> log.showAndRecord("Now Pressed" , i));
        gph1.link(Button.A, ChangeHoldEventHandler.class, () -> log.showAndRecord("Changed Hold" , i));
        gph1.link(Button.A, OnTurnOnEventHandler.class, () -> log.showAndRecord("Now turned on" , i));
        gph1.link(Button.A, OnTurnOffEventHandler.class, () -> log.showAndRecord("Now turned off" , i));
        gph1.link(Button.A, WhenOnEventHandler.class, () -> log.show("On"));
        gph1.link(Button.A, WhenOffEventHandler.class, () -> log.show("Off"));
    }

    @Override
    protected void loop() {
        log.show("Click the A button to test");
        i++;
    }
}
