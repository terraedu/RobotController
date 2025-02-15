package robotparts.hardware;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.robotStatus;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Outtake extends RobotPart {

    public PServo armr, arml, claw, pivot;
//    public CServo armr;

    @Override
    public void init() {
        armr = create("armr", ElectronicType.PSERVO_FORWARD);
        arml = create("arml", ElectronicType.PSERVO_REVERSE);
        pivot = create("pivot", ElectronicType.PSERVO_FORWARD);
        claw = create("claw", ElectronicType.PSERVO_REVERSE);

        armr.changePosition("init", 0);
        arml.changePosition("init", 0);
        armr.changePosition("specimenready", 0.2);
        arml.changePosition("specimenready", 0.2);
        armr.changePosition("place", 0.83);
        arml.changePosition("place", 0.83);
        armr.changePosition("switcharoo", 0.055);
        arml.changePosition("switcharoo", 0.055);
        armr.changePosition("basket", 0.5);
        arml.changePosition("basket", 0.5);

        pivot.changePosition("init", 0);
        pivot.changePosition("specimenready", 0.82);
        pivot.changePosition("place", 0.44);
        pivot.changePosition("switcharoo", 0.55);
        pivot.changePosition("basket", 0.28);

        claw.changePosition("start", 1);
        claw.changePosition("open", 0.6);

        robotStatus.set(DRIVING);
    }

    public void moveInit() {
        armr.setPosition("init");
        arml.setPosition("init");
        pivot.setPosition("init");
        claw.setPosition("start");
    }

    void specimenReady() {
        armr.setPosition("specimenready");
        arml.setPosition("specimenready");
        pivot.setPosition("specimenready");
        claw.setPosition("open");
    }

    void upSpecimen() {
        armr.setPosition("place");
        arml.setPosition("place");
        pivot.setPosition("place");
    }

    void switcharooReady() {
        armr.setPosition("switcharoo");
        arml.setPosition("switcharoo");
        pivot.setPosition("switcharoo");
        claw.setPosition("open");
    }

    void placeHigh() {
        armr.setPosition("basket");
        arml.setPosition("basket");
        pivot.setPosition("basket");
    }

    public void clawGrab() {claw.setPosition("start");}
    public void clawRelease() {claw.setPosition("open");}



    public Stage init(double t) {return super.customTime(this::moveInit, t);}
    public Stage specimenReady(double t) {return super.customTime(this::specimenReady, t);}
    public Stage upSpecimen(double t) {return super.customTime(this::upSpecimen, t);}
    public Stage switcharooReady(double t) {return super.customTime(this::switcharooReady, t);}
    public Stage placeHigh(double t) {return super.customTime(this::placeHigh, t);}

    public Stage clawGrab(double t) {return super.customTime(this::clawGrab, t);}
    public Stage clawRelease(double t) {return super.customTime(this::clawRelease, t);}
}