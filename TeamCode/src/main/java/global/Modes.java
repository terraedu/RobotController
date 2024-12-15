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


    enum Height implements Mode.ModeType {GROUND, lowrung, highrung, lowbasket, highbasket, currentHeight}

    Mode heightMode = new Mode(Height.class)


            .set(Height.GROUND, 0)
            .set(Height.lowrung, 10)
            .set(Height.highrung, 20)
            .set(Height.lowbasket, 30)
            .set(Height.highbasket, 40);



    Mode current = new Mode(Height.class)
            .set(Height.currentHeight, heightMode.getValue(heightMode.get()));




    // TOD5 MULTI DIMENSIONAL MODE
    enum Drive implements Mode.ModeType {FAST, MEDIUM, SLOW, SUPERSLOW}
    Mode driveMode = new Mode(Drive.class);

}
//