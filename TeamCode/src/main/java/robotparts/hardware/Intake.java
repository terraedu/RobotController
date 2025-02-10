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
        iturret = create("iturret", ElectronicType.PSERVO_FORWARD);
        iclaw = create("iclaw", ElectronicType.PSERVO_FORWARD);
        linkager = create("linkager", ElectronicType.PSERVO_REVERSE);
        linkagel = create("linkagel", ElectronicType.PSERVO_FORWARD);

        iarmr.changePosition("init", 1);
        iarml.changePosition("init", 1);
        iarmr.changePosition("specimenready", 0.79);
        iarml.changePosition("specimenready", 0.79);
        iarmr.changePosition("specimen", 0.93);
        iarml.changePosition("specimen", 0.93);
        iarmr.changePosition("transferspecimen", 0.86);
        iarml.changePosition("transferspecimen", 0.86);
        iarmr.changePosition("stagetransfer", 0.9);
        iarml.changePosition("stagetransfer", 0.9);
        iarmr.changePosition("seek", 0.52);
        iarml.changePosition("seek", 0.52);
        iarmr.changePosition("grab", 0.41);
        iarml.changePosition("grab", 0.41);

        ipivot.changePosition("init", 0.8);
        ipivot.changePosition("specimenready", 0);
        ipivot.changePosition("transferspecimen", 0.3);
        ipivot.changePosition("seek", 0.68);
        ipivot.changePosition("grab", 0.79);
        ipivot.changePosition("drop", 0.15);

        iturret.changePosition("start", 0.8);
        iturret.changePosition("horizontal", 0.43);
        iturret.changePosition("left", 0.125);
        iturret.changePosition("right", 0.375);
        iturret.changePosition("switcharoo", 0.05);

        iclaw.changePosition("close", 0.5);
        iclaw.changePosition("start", 0.45);
        iclaw.changePosition("adjust", 0.38);
        iclaw.changePosition("open", 0.28);

        linkager.changePosition("start", 0.31);
        linkagel.changePosition("start", 0.31);
        linkager.changePosition("init", 0.25);
        linkagel.changePosition("init", 0.25);
        linkager.changePosition("end", 0.06);
        linkagel.changePosition("end", 0.06);
        linkager.changePosition("specimen", 0.21);
        linkagel.changePosition("specimen", 0.21);
        linkager.changePosition("transferspecimen", 0.2);
        linkagel.changePosition("transferspecimen", 0.2);
        linkager.changePosition("seek", 0.23);
        linkagel.changePosition("seek", 0.23);
        linkager.changePosition("switcharoo", 0.14);
        linkagel.changePosition("switcharoo", 0.14);

        camera.checkAccess(User.ROFU);
    }

    public void moveInit() {
        iarmr.setPosition("init");
        iarml.setPosition("init");
        ipivot.setPosition("init");
        iturret.setPosition("start");
        iclaw.setPosition("start");
        linkager.setPosition("init");
        linkagel.setPosition("init");
    }

    public void iSpecimenReady() {
        iarmr.setPosition("specimenready");
        iarml.setPosition("specimenready");
        ipivot.setPosition("specimenready");
        iturret.setPosition("start");
        iclaw.setPosition("open");
        linkager.setPosition("start");
        linkagel.setPosition("start");
    }

    public void yoinkSpecimen() {
        iarmr.setPosition("specimen");
        iarml.setPosition("specimen");
        linkager.setPosition("specimen");
        linkagel.setPosition("specimen");
    }

    public void transferSpecimen() {
        iarmr.setPosition("transferspecimen");
        iarml.setPosition("transferspecimen");
        ipivot.setPosition("transferspecimen");
        linkager.setPosition("transferspecimen");
        linkagel.setPosition("transferspecimen");
    }

    public void stageTransfer() {
        iarmr.setPosition("stagetransfer");
        iarml.setPosition("stagetransfer");
        ipivot.setPosition("specimenready");
        iclaw.setPosition("adjust");
    }

    public void intakeSeek() {
        iarmr.setPosition("seek");
        iarml.setPosition("seek");
        ipivot.setPosition("seek");
        iturret.setPosition("start");
        iclaw.setPosition("start");
        linkager.setPosition("seek");
        linkagel.setPosition("seek");
    }

    public void intake() {
        linkager.setPosition("end");
        linkagel.setPosition("end");
        ipivot.setPosition("grab");
        iarmr.setPosition("grab");
        iarml.setPosition("grab");
    }

    public void drop() {
        ipivot.setPosition("drop");
    }

    public void switcharooReady() {
        linkager.setPosition("start");
        linkagel.setPosition("start");
        iarmr.setPosition("specimen");
        iarml.setPosition("specimen");
    }

    public void clawGrab() {iclaw.setPosition("close");}
    public void clawLightGrab() {iclaw.setPosition("start");}
    public void clawAdjust() {iclaw.setPosition("adjust");}
    public void clawRelease() {iclaw.setPosition("open");}

    public void turretReset() {iturret.setPosition("start");}
    public void turretHorizontal () {iturret.setPosition("horizontal");}
    public void turretLeft() {iturret.setPosition("left");}
    public void turretRight() {iturret.setPosition("right");}
    public void turretSwitcharoo() {iturret.setPosition("switcharoo");}

    public void linkEnd() {linkager.setPosition("end"); linkagel.setPosition("end");}
    public void linkStart() {linkager.setPosition("start"); linkagel.setPosition("start");}



    public Stage init(double t) {return super.customTime(this::moveInit, t);}
    public Stage specimenReady(double t) {return super.customTime(this::iSpecimenReady, t);}
    public Stage yoinkSpecimen(double t) {return super.customTime(this::yoinkSpecimen, t);}
    public Stage transferSpecimen(double t) {return super.customTime(this::transferSpecimen, t);}
    public Stage stageTransfer(double t) {return super.customTime(this::stageTransfer, t);}
    public Stage intakeSeek(double t) {return super.customTime(this::intakeSeek, t);}
    public Stage intake(double t) {return super.customTime(this::intake, t);}
    public Stage drop(double t) {return super.customTime(this::drop, t);}
    public Stage switcharooReady(double t) {return super.customTime(this::switcharooReady, t);}

    public Stage clawGrab(double t) {return super.customTime(this::clawGrab, t);}
    public Stage clawLightGrab(double t) {return super.customTime(this::clawLightGrab, t);}
    public Stage clawAdjust(double t) {return super.customTime(this::clawAdjust, t);}
    public Stage clawRelease(double t) {return super.customTime(this::clawRelease, t);}

    public Stage sTurretReset(double t) {return super.customTime(this::turretReset, t);}
    public Stage sTurretHorizontal(double t) {return super.customTime(this::turretReset, t);}
    public Stage sTurretLeft(double t) {return super.customTime(this::turretLeft, t);}
    public Stage sTurretRight(double t) {return super.customTime(this::turretRight, t);}
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