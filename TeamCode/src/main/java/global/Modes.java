package global;

import robotparts.hardware.Drive;
import robotparts.hardware.Lift;
import util.template.Mode;


public interface Modes {
    /**
     * Interface to hold modes (different types of movement/control)
     */


    /**
     * List of mode types
     */

    enum GamepadMode implements Mode.ModeType { NORMAL, AUTOMATED }

    enum OuttakeStatus implements Mode.ModeType { DRIVING, PLACING, INTAKING}
    Mode outtakeStatus = new Mode(OuttakeStatus.class);

    enum Height implements Mode.ModeType {HIGH, MIDDLE, LOW, GROUND}
    Mode heightMode = new Mode(Height.class)
            .set(Height.HIGH, 10)
            .set(Height.MIDDLE, 50)
            .set(Height.LOW, 35)
            .set(Height.GROUND, 0);

    // TOD5 MULTI DIMENSIONAL MODE
    enum Drive implements Mode.ModeType {FAST, MEDIUM, SLOW}
    Mode driveMode = new Mode(Drive.class);

}
//