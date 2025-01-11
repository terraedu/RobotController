package global;

import util.template.Mode;


public interface Modes {
    /**
     * Interface to hold modes (different types of movement/control)
     */


    /**
     * List of mode types
     */

    enum GamepadMode implements Mode.ModeType { NORMAL, AUTOMATED }

    enum RobotStatus implements Mode.ModeType { DRIVING, PLACING, INTAKING, PLACING2}
    Mode robotStatus = new Mode(RobotStatus.class);

    enum TeleStatus implements Mode.ModeType {RED, BLUE}
    Mode teleStatus = new Mode(TeleStatus.class);


    enum TurretStatus implements Mode.ModeType {ANGLE, HALF, FULL}
    Mode turretStatus = new Mode(TeleStatus.class);


    enum Height implements Mode.ModeType {out1, out2, out3, out4}
    Mode heightMode = new Mode(Height.class);




    // TOD5 MULTI DIMENSIONAL MODE
    enum Drive implements Mode.ModeType {FAST, MEDIUM, SLOW, SUPERSLOW}
    Mode driveMode = new Mode(Drive.class);

}
//