package robotparts.hardware;

//import static autoutil.vision.SampleScanner.servoPos;

import automodules.stage.Stage;
//import autoutil.vision.SampleScanner;
//import autoutil.vision.yolovision.YoloScanner;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;
import util.User;

public class Intake extends RobotPart {

    public PServo iarmr, iarml, ipivot, iturret, iclaw;

    @Override
    public void init() {
        iarmr = create("iarmr", ElectronicType.PSERVO_FORWARD);
        iarml = create("iarml", ElectronicType.PSERVO_REVERSE);
        ipivot = create("ipivot", ElectronicType.PSERVO_FORWARD);
        iturret = create("iturret", ElectronicType.PSERVO_FORWARD);
        iclaw = create("iclaw", ElectronicType.PSERVO_FORWARD);


        iarmr.changePosition("init", 1);
        iarml.changePosition("init", 1);
        iarmr.changePosition("smallinit", .885);
        iarml.changePosition("smallinit", .885);
        iarmr.changePosition("specimenready", 0.66); // previous 0.81
        iarml.changePosition("specimenready", 0.66);
        iarmr.changePosition("specimen", 0.96);
        iarml.changePosition("specimen", 0.96);
        iarmr.changePosition("switcharoo", 0.93);
        iarml.changePosition("switcharoo", 0.93);
        iarmr.changePosition("transferspecimen", 0.84);
        iarml.changePosition("transferspecimen", 0.84);
        iarmr.changePosition("stagetransfer", 0.89);
        iarml.changePosition("stagetransfer", 0.89);
        iarmr.changePosition("seek", 0.95);
        iarml.changePosition("seek", 0.95);
        iarmr.changePosition("grab", 0.91);
        iarml.changePosition("grab", 0.91);
        iarmr.changePosition("slide", 0.7);
        iarml.changePosition("slide", 0.7);
        iarmr.changePosition("low", 0.22);
        iarml.changePosition("low", 0.22);

        ipivot.changePosition("init", 1);
        ipivot.changePosition("specimenready", 0);
        ipivot.changePosition("transferspecimen", 0.38);
        ipivot.changePosition("seek", 0.68);
        ipivot.changePosition("grab", 0.79);
        ipivot.changePosition("drop", 0.4);
        ipivot.changePosition("smallinit", 0.55);

        ipivot.changePosition("low", 0.15);
//
        iturret.changePosition("start", 1);
        iturret.changePosition("horizontal", 0.6);
        iturret.changePosition("left", 0.4);
        iturret.changePosition("right", 0);
        iturret.changePosition("switcharoo", 0.21);

        iclaw.changePosition("close", 0.55);
        iclaw.changePosition("start", 0.4);
        iclaw.changePosition("adjust", 0.36);
        iclaw.changePosition("open", 0.25);
        iclaw.changePosition("OPENNN", 0.1);
        iclaw.changePosition("CLOSEEE", 0.41);

        camera.checkAccess(User.ROFU);
    }

    public void moveInit() {
        iarmr.setPosition("init");
        iarml.setPosition("init");
        iturret.setPosition("start");

        ipivot.setPosition("specimenready");
    }

    void armInit() {
        iarmr.setPosition("init");
        iarml.setPosition("init");
        iclaw.setPosition("start");
        ipivot.setPosition("init");
    }

    void specimenReady() {
        ipivot.setPosition("specimenready");
        iarmr.setPosition("smallinit");
        iarml.setPosition("smallinit");
        iturret.setPosition("start");
        iclaw.setPosition("open");

    }

    void yoinkSpecimen() {
        iarmr.setPosition("specimen");
        iarml.setPosition("specimen");

        iclaw.setPosition("start");
    }

    void transferSpecimen() {
        iarmr.setPosition("transferspecimen");
        iarml.setPosition("transferspecimen");
        ipivot.setPosition("transferspecimen");

    }

    void stageTransfer() {
        iarmr.setPosition("stagetransfer");
        iarml.setPosition("stagetransfer");
        ipivot.setPosition("specimenready");
        iclaw.setPosition("close");
    }

    void electricSlide() {
        iarmr.setPosition("slide");
        iarml.setPosition("slide");
        ipivot.setPosition("specimenready");
        iclaw.setPosition("adjust");
    }

    void intakeSeek() {
        iarmr.setPosition("seek");
        iarml.setPosition("seek");
        ipivot.setPosition("init");
        iclaw.setPosition("OPENNN");
        iturret.setPosition("switcharoo");

    }

    void intake() {
        ipivot.setPosition("grab");
        iarmr.setPosition("grab");
        iarml.setPosition("grab");
    }

    void zestyFlick() {
        ipivot.setPosition("drop");
    }

    void switcharoo() {

        iarmr.setPosition("switcharoo");
        iarml.setPosition("switcharoo");
    }

    void smallInit() {
        iarmr.setPosition("init");
        iarml.setPosition("init");
        ipivot.setPosition("smallinit");
    }
    void smallInit2() {
        iarmr.setPosition("init");
        iarml.setPosition("init");
        ipivot.setPosition("smallinit");
    }


    void clawDown() {

        iarmr.setPosition("grab");
        iarml.setPosition("grab");
    }

    void clawUp() {
        iarmr.setPosition("init");
        iarml.setPosition("init");
    }

    public void clawGrab() {iclaw.setPosition("close");}
    public void clawLightGrab() {iclaw.setPosition("start");}
    public void clawAdjust() {iclaw.setPosition("adjust");}
    public void clawRelease() {iclaw.setPosition("open");}
    public void clawRELEASE() {iclaw.setPosition("OPENNN");}
    public void clawGRAB() {iclaw.setPosition("CLOSEEE");}

    public void turretReset() {iturret.setPosition("start");}
    public void turretHorizontal () {iturret.setPosition("horizontal");}
    public void turretLeft() {iturret.setPosition("left");}
    public void turretRight() {iturret.setPosition("right");}



    public Stage init(double t) {return super.customTime(this::moveInit, t);}
    public Stage armInit(double t) {return super.customTime(this::armInit, t);}
    public Stage specimenReady(double t) {return super.customTime(this::specimenReady, t);}
    public Stage yoinkSpecimen(double t) {return super.customTime(this::yoinkSpecimen, t);}
    public Stage transferSpecimen(double t) {return super.customTime(this::transferSpecimen, t);}
    public Stage stageTransfer(double t) {return super.customTime(this::stageTransfer, t);}
    public Stage electricSlide(double t) {return super.customTime(this::electricSlide, t);}
    public Stage intakeSeek(double t) {return super.customTime(this::intakeSeek, t);}
    public Stage intake(double t) {return super.customTime(this::intake, t);}
    public Stage zestyFlick(double t) {return super.customTime(this::zestyFlick, t);}
    public Stage switcharoo(double t) {return super.customTime(this::switcharoo, t);}
    public Stage smallInit(double t) {return super.customTime(this::smallInit, t);}
    public Stage smallInit2(double t) {return super.customTime(this::smallInit2, t);}

    public Stage clawDown(double t) {return super.customTime(this::clawDown, t);}
    public Stage clawUp(double t) {return super.customTime(this::clawUp, t);}

    public Stage clawGrab(double t) {return super.customTime(this::clawGrab, t);}
    public Stage clawLightGrab(double t) {return super.customTime(this::clawLightGrab, t);}
    public Stage clawAdjust(double t) {return super.customTime(this::clawAdjust, t);}
    public Stage clawRelease(double t) {return super.customTime(this::clawRelease, t);}
    public Stage clawRELEASE(double t) {return super.customTime(this::clawRELEASE, t);}
    public Stage clawGRAB(double t) {return super.customTime(this::clawGRAB, t);}

    public Stage turretReset(double t) {return super.customTime(this::turretReset, t);}
    public Stage turretHorizontal(double t) {return super.customTime(this::turretHorizontal, t);}
    public Stage turretLeft(double t) {return super.customTime(this::turretLeft, t);}
    public Stage turretRight(double t) {return super.customTime(this::turretRight, t);}


}