package global;

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

    enum RobotStatus implements Mode.ModeType { DRIVING, PLACING, INTAKING, PLACING2, SPECIMEN, SAMPLE, GRAB}
    Mode robotStatus = new Mode(RobotStatus.class);

    enum TeleStatus implements Mode.ModeType {REDA, BLUEA}
    Mode teleStatus = new Mode(TeleStatus.class);

    enum Height implements Mode.ModeType {HIGH, MIDDLE, LOW, GROUND}
    Mode heightMode = new Mode(Height.class)
            .set(Height.HIGH, Lift.maxPosition-11)
            .set(Height.MIDDLE, 28)
            .set(Height.LOW, 6)
            .set(Height.GROUND, 3);



    // TOD5 MULTI DIMENSIONAL MODE
    enum Drive implements Mode.ModeType {FAST, MEDIUM, SLOW, SUPERSLOW}
    Mode driveMode = new Mode(Drive.class);

}
//