package teleutil.button.main;

import teleutil.GamepadHandler;
import teleutil.button.Button;
import teleutil.button.main.ChangeHoldEventHandler;
import util.codeseg.CodeSeg;


public class OnPressEventHandler extends ChangeHoldEventHandler {
    /**
     * Occurs (once) when the user presses the button
     */


    /**
     * Call the super constructor
     * @param button
     * @param codeSeg
     * @param gph
     */
    public OnPressEventHandler(Button button, CodeSeg codeSeg, GamepadHandler gph) { super(button, codeSeg, gph); }

    /**
     * Did an event occur and was the button pressed?
     * @return wasPressed
     */
    @Override
    protected boolean eventOccurred() { return super.eventOccurred() && this.pressed(); }
}
