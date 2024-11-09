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

    enum OuttakeStatus implements Mode.ModeType { DRIVING, PLACING, INTAKING, PLACING2}
    Mode outtakeStatus = new Mode(OuttakeStatus.class);

    enum TeleStatus implements Mode.ModeType {RED, BLUE}
    Mode teleStatus = new Mode(TeleStatus.class);


    enum Height implements Mode.ModeType {GROUND, one, two, three, four, five, six, seven, currentHeight}

    Mode heightMode = new Mode(Height.class)


            .set(Height.GROUND, 0)
            .set(Height.one, 15)
            .set(Height.two, 20)
            .set(Height.three, 30)
            .set(Height.four, 40)
            .set(Height.five, 45)
            .set(Height.six, 50)
            .set(Height.seven, 57);



    Mode current = new Mode(Height.class)
            .set(Height.currentHeight, heightMode.getValue(heightMode.get()));




    // TOD5 MULTI DIMENSIONAL MODE
    enum Drive implements Mode.ModeType {FAST, MEDIUM, SLOW, SUPERSLOW}
    Mode driveMode = new Mode(Drive.class);

}
//