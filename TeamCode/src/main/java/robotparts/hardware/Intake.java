package robotparts.hardware;

import static autoutil.vision.SampleScanner.servoPos;

import automodules.stage.Stage;
import autoutil.vision.SampleScanner;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;
import util.User;

public class Intake extends RobotPart {

    public PServo iarmr, iarml, ipivot, iturret, iclaw, linkager, linkagel;
    public SampleScanner sampleScanner;
//    public YoloScanner yoloScanner;

    @Override
    public void init() {
        iarmr = create("iarmr", ElectronicType.PSERVO_FORWARD);
        iarml = create("iarml", ElectronicType.PSERVO_REVERSE);
        ipivot = create("ipivot", ElectronicType.PSERVO_FORWARD);
        iturret = create("iturret", ElectronicType.PSERVO_FORWARD   );
        iclaw = create("iclaw", ElectronicType.PSERVO_FORWARD);
        linkager = create("linkager", ElectronicType.PSERVO_REVERSE);
        linkagel = create("linkagel", ElectronicType.PSERVO_FORWARD);

        iarmr.changePosition("init", 1);
        iarml.changePosition("init", 1);
        iarmr.changePosition("specimenready", 0.81);
        iarml.changePosition("specimenready", 0.81);
        iarmr.changePosition("specimen", 0.95);
        iarml.changePosition("specimen", 0.95);
        iarmr.changePosition("transferspecimen", 0.87);
        iarml.changePosition("transferspecimen", 0.87);
        iarmr.changePosition("stagetransfer", 0.93);
        iarml.changePosition("stagetransfer", 0.93);
        iarmr.changePosition("seek", 0.54);
        iarml.changePosition("seek", 0.54);
        iarmr.changePosition("grab", 0.41);
        iarml.changePosition("grab", 0.41);
        iarmr.changePosition("slide", 0.7);
        iarml.changePosition("slide", 0.7);
        iarmr.changePosition("low", 0.22);
        iarml.changePosition("low", 0.22);

        ipivot.changePosition("init", 0.8);
        ipivot.changePosition("specimenready", 0);
        ipivot.changePosition("transferspecimen", 0.3);
        ipivot.changePosition("seek", 0.68);
        ipivot.changePosition("grab", 0.79);
        ipivot.changePosition("drop", 0.25);
        ipivot.changePosition("smallinit", 0.4);
        ipivot.changePosition("low", 0.15);

        iturret.changePosition("start", 0.97);
        iturret.changePosition("horizontal", 0.6);
        iturret.changePosition("left", 0);
        iturret.changePosition("right", 0);
        iturret.changePosition("switcharoo", 0.21);

        iclaw.changePosition("close", 0.45);
        iclaw.changePosition("start", 0.45);
        iclaw.changePosition("adjust", 0.41);
        iclaw.changePosition("open", 0.28);

        linkager.changePosition("start", 0.31);
        linkagel.changePosition("start", 0.31);
        linkager.changePosition("init", 0.22);
        linkagel.changePosition("init", 0.22);
        linkager.changePosition("end", 0.06);
        linkagel.changePosition("end", 0.06);
        linkager.changePosition("specimen", 0.21);
        linkagel.changePosition("specimen", 0.21);
        linkager.changePosition("transferspecimen", 0.13);
        linkagel.changePosition("transferspecimen", 0.13);
        linkager.changePosition("seek", 0.18);
        linkagel.changePosition("seek", 0.18);
        linkager.changePosition("switcharoo", 0.14);
        linkagel.changePosition("switcharoo", 0.14);
        linkager.changePosition("tight", 0.25);
        linkagel.changePosition("tight", 0.25);

        camera.checkAccess(User.ROFU);
    }

    public void moveInit() {
        iarmr.setPosition("init");
        iarml.setPosition("init");
        iturret.setPosition("start");
        iclaw.setPosition("start");
        linkager.setPosition("init");
        linkagel.setPosition("init");
        ipivot.setPosition("init");
    }

    void iSpecimenReady() {
        ipivot.setPosition("specimenready");
        iarmr.setPosition("specimenready");
        iarml.setPosition("specimenready");
        iturret.setPosition("start");
        iclaw.setPosition("open");
        linkager.setPosition("start");
        linkagel.setPosition("start");
    }

    void yoinkSpecimen() {
        iarmr.setPosition("specimen");
        iarml.setPosition("specimen");
        linkager.setPosition("specimen");
        linkagel.setPosition("specimen");
    }

    void transferSpecimen() {
        iarmr.setPosition("transferspecimen");
        iarml.setPosition("transferspecimen");
        ipivot.setPosition("transferspecimen");
        linkager.setPosition("transferspecimen");
        linkagel.setPosition("transferspecimen");
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
        ipivot.setPosition("seek");
        iturret.setPosition("start");
        iclaw.setPosition("start");
        linkager.setPosition("seek");
        linkagel.setPosition("seek");
    }

    void intake() {
        linkager.setPosition("end");
        linkagel.setPosition("end");
        ipivot.setPosition("grab");
        iarmr.setPosition("grab");
        iarml.setPosition("grab");
    }

    void zestyFlick() {
        ipivot.setPosition("drop");
    }

    void switcharoo() {
        linkager.setPosition("start");
        linkagel.setPosition("start");
        iarmr.setPosition("specimen");
        iarml.setPosition("specimen");
    }

    void smallInit() {
        iarmr.setPosition("init"); iarml.setPosition("init"); ipivot.setPosition("smallinit");
    }

    void clawDown() {
        linkager.setPosition("start");
        linkagel.setPosition("start");
        iarmr.setPosition("low");
        iarml.setPosition("low");
        ipivot.setPosition("low");
    }

    void clawUp() {
        iclaw.setPosition("close");
        iarmr.setPosition("seek");
        iarml.setPosition("seek");
    }

    public void clawGrab() {iclaw.setPosition("close");}
    public void clawLightGrab() {iclaw.setPosition("start");}
    public void clawAdjust() {iclaw.setPosition("adjust");}
    public void clawRelease() {iclaw.setPosition("open");}

    public void turretReset() {iturret.setPosition("start");}
    public void turretHorizontal () {iturret.setPosition("horizontal");}
    public void turretLeft() {iturret.setPosition("left");}
    public void turretRight() {iturret.setPosition("right");}
    public void turretSwitcharoo() {iturret.setPosition("switcharoo"); linkager.setPosition("tight"); linkagel.setPosition("tight");}

    public void linkEnd() {linkager.setPosition("end"); linkagel.setPosition("end");}
    public void linkStart() {linkager.setPosition("start"); linkagel.setPosition("start");}



    public Stage init(double t) {return super.customTime(this::moveInit, t);}
    public Stage specimenReady(double t) {return super.customTime(this::iSpecimenReady, t);}
    public Stage yoinkSpecimen(double t) {return super.customTime(this::yoinkSpecimen, t);}
    public Stage transferSpecimen(double t) {return super.customTime(this::transferSpecimen, t);}
    public Stage stageTransfer(double t) {return super.customTime(this::stageTransfer, t);}
    public Stage electricSlide(double t) {return super.customTime(this::electricSlide, t);}
    public Stage intakeSeek(double t) {return super.customTime(this::intakeSeek, t);}
    public Stage intake(double t) {return super.customTime(this::intake, t);}
    public Stage zestyFlick(double t) {return super.customTime(this::zestyFlick, t);}
    public Stage switcharoo(double t) {return super.customTime(this::switcharoo, t);}
    public Stage smallInit(double t) {return super.customTime(this::smallInit, t);}
    public Stage clawDown(double t) {return super.customTime(this::clawDown, t);}
    public Stage clawUp(double t) {return super.customTime(this::clawUp, t);}

    public Stage clawGrab(double t) {return super.customTime(this::clawGrab, t);}
    public Stage clawLightGrab(double t) {return super.customTime(this::clawLightGrab, t);}
    public Stage clawAdjust(double t) {return super.customTime(this::clawAdjust, t);}
    public Stage clawRelease(double t) {return super.customTime(this::clawRelease, t);}

    public Stage turretReset(double t) {return super.customTime(this::turretReset, t);}
    public Stage turretHorizontal(double t) {return super.customTime(this::turretHorizontal, t);}
    public Stage turretLeft(double t) {return super.customTime(this::turretLeft, t);}
    public Stage turretRight(double t) {return super.customTime(this::turretRight, t);}
    public Stage turretSwitcharoo(double t) {return super.customTime(this::turretSwitcharoo, t);}

    public Stage linkEnd(double t) {return super.customTime(this::linkEnd, t);}
    public Stage linkStart(double t) {return super.customTime(this::linkStart, t);}

    public void updatePipeline(int loopNum) {
        if (sampleScanner.getAngle() == -1) return;
        for (int i = 0; i < loopNum; i++) {
            iturret.changePosition("angle", servoPos);
            iturret.setPosition("angle");
        }
    }
}