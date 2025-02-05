package robotparts.hardware;

import static autoutil.vision.SampleScanner.servoPos;

import automodules.AutoModule;
import automodules.stage.Exit;
import automodules.stage.Stage;
import autoutil.vision.SampleScanner;
import autoutil.vision.yolovision.YoloScanner;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.input.IColor;
import robotparts.electronics.positional.PServo;
import util.User;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.template.Precision;

public class Intake extends RobotPart {

    public PServo iarmr, iarml, ipivot, iturret, iclaw, linkager, linkagel;
    public SampleScanner sampleScanner;
//    public YoloScanner yoloScanner;

    @Override
    public void init() {
        iarmr = create("iarmr", ElectronicType.PSERVO_REVERSE);
        iarml = create("iarml", ElectronicType.PSERVO_FORWARD);
        ipivot = create("ipivot", ElectronicType.PSERVO_FORWARD);
        iturret = create("iturret", ElectronicType.PSERVO_FORWARD);

        iclaw = create("iclaw", ElectronicType.PSERVO_FORWARD);
        linkager = create("linkager", ElectronicType.PSERVO_REVERSE);
        linkagel = create("linkagel", ElectronicType.PSERVO_FORWARD);

        iarml.changePosition("start", .67);
        iarmr.changePosition("start", .67);
        iarml.changePosition("startrun", .65);
        iarmr.changePosition("startrun", .65);
        iarml.changePosition("transfer1", 0.57);
        iarmr.changePosition("transfer1", 0.57);
        iarml.changePosition("transferspecimen", 0.7);
        iarmr.changePosition("transferspecimen", 0.7);
        iarml.changePosition("upspecimen", 0.74);
        iarmr.changePosition("upspecimen", 0.74);
        iarml.changePosition("specimen1", 0.59);
        iarmr.changePosition("specimen1", 0.59);
        iarml.changePosition("specimen", 0.57);
        iarmr.changePosition("specimen", 0.60);
        iarml.changePosition("transfer", 0.60);
        iarmr.changePosition("transfer", 0.2);
        iarml.changePosition("end", 0.06);
        iarmr.changePosition("end", 0.06);
        iarml.changePosition("end1", 0.26);
        iarmr.changePosition("end1", 0.26);
        iarml.changePosition("removespecimen", 0.5);
        iarmr.changePosition("removespecimen", 0.5);

        ipivot.changePosition("start", .58);
        ipivot.changePosition("specimen2", 1);

        ipivot.changePosition("end", .57);
        ipivot.changePosition("end1", .9);

        ipivot.changePosition("transfer", .85);
        ipivot.changePosition("transferspecimen", .57);

        ipivot.changePosition("specimen", 0.0);


        ipivot.changePosition("middle", 0.2);

        iclaw.changePosition("start", .38);
        iclaw.changePosition("adjust", 0.42);

        iclaw.changePosition("close", .28);
        iclaw.changePosition("wider", .62);


        iturret.changePosition("start", 0.02);
        iturret.changePosition("middle",.73);
        iturret.changePosition("middler",.25);
        iturret.changePosition("middlest",.8);
        iturret.changePosition("middlestest",.67);
        iturret.changePosition("sideways",.5);




        linkager.changePosition("start", 0.3);
        linkagel.changePosition("start", 0.3);
        linkager.changePosition("transfer", .15);
        linkagel.changePosition("transfer", .15);
        linkager.changePosition("smallend", .02);
        linkagel.changePosition("smallend", .02);
        linkager.changePosition("end", 0);
        linkagel.changePosition("end", 0);

                linkagel = create("linkagel", ElectronicType.CSERVO_FORWARD);

        // new positions
        iturret.changePosition("0", 0);
        iturret.changePosition("straight", 0.25);
        iturret.changePosition("left", 0.125);
        iturret.changePosition("right", 0.375);

        camera.checkAccess(User.ROFU);
    }
    public void moveStart(){ ipivot.setPosition("start"); iturret.setPosition("start");iclaw.setPosition("start");iarmr.setPosition("start"); iarml.setPosition("start"); linkager.setPosition("start"); linkagel.setPosition("start"); }
    public void moveStartAuto(){ ipivot.setPosition("specimen2"); iturret.setPosition("start");iclaw.setPosition("start");iarmr.setPosition("start"); iarml.setPosition("start"); linkager.setPosition("start"); linkagel.setPosition("start"); }
    public void moveLinkEnd(){ linkager.setPosition("smallend"); linkagel.setPosition("smallend");}
    public void moveLinkStart(){ linkager.setPosition("start"); linkagel.setPosition("start");}
    public void moveEnd(){linkager.setPosition("end"); linkagel.setPosition("end"); iarmr.setPosition("end"); iarml.setPosition("end"); ipivot.setPosition("end");}
    public void adjustPivot(){ ipivot.setPosition("end1");}

    public void moveEnd1() {
        iarmr.setPosition("end1");
        iarml.setPosition("end1");
        iclaw.setPosition("start");
    }
    public void moveSpecimen(){ipivot.setPosition("specimen"); iclaw.setPosition("close");iarmr.setPosition("specimen"); iarml.setPosition("specimen"); }
    public void removeSpecimen(){ipivot.setPosition("specimen2");iarmr.setPosition("upspecimen"); iarml.setPosition("upspecimen"); linkager.setPosition("transfer"); linkagel.setPosition("transfer");}
    public void moveLinkTransfer(){linkager.setPosition("transfer");linkagel.setPosition("transfer");}

    public void moveTurret() {iturret.setPosition("middle");}
    public void moveTurretMiddler(){iturret.setPosition("middler");}
    public void moveTurretMiddlest(){iturret.setPosition("middlest");}
    public void moveTurretSideways(){iturret.setPosition("sideways");}
    public void moveStartTurret() {iturret.setPosition("start");}

    public void moveTransfer(){iarmr.setPosition("startrun"); iarml.setPosition("startrun"); ipivot.setPosition("middle");}
    public void moveTransfer2(){iarmr.setPosition("transfer"); iarml.setPosition("transfer"); ipivot.setPosition("transfer");}
    public void moveTransfer3(){ipivot.setPosition("middle"); iturret.setPosition("start");}
    public void moveStartPivot(){ ipivot.setPosition("start");}
    public void moveTransfer4(){iarmr.setPosition("transfer1"); iarml.setPosition("transfer1");}


    public void moveOpen(){ iclaw.setPosition("start"); }
    public void moveClose(){ iclaw.setPosition("wider"); }
    public void moveAdjust(){ iclaw.setPosition("adjust"); }
    public void moveSpecimenPivot(){iturret.setPosition("middlest");}

    public Stage stageSpecimenPivot(double t){return super.customTime(this::moveSpecimenPivot,t);}
    public Stage stageLinkTransfer(double t){return super.customTime(this::moveLinkTransfer, t);}
    public Stage removeSpecimen(double t){return super.customTime(this::removeSpecimen,t);}
    public Stage stagePivotStart(double t){return super.customTime(this::moveStartPivot, t);}
    public Stage stageTurret(double t){return super.customTime(this::moveTurret, t);}
    public Stage stageStartTurret(double t){return super.customTime(this::moveStartTurret, t);}
    public Stage stageDownTurret(double t){return super.customTime(this::moveTurretMiddlest, t);}
    public Stage stageSpecimen(double t){return super.customTime(this::moveSpecimen, t);}

    public Stage stageAdjustClaw(double t){return super.customTime(this::moveAdjust, t );}
    public Stage stageAdjustPivot(double t){return super.customTime(this::adjustPivot, t );}

    public Stage stageTransfer3(double t){return super.customTime(this::moveTransfer3, t);}
    public Stage stageOpen(double t){return super.customTime(this::moveOpen, t);}
    public Stage stageTransfer2(double t){return super.customTime(this::moveTransfer2, t);}
    public Stage stageLinkEnd(double t){return super.customTime(this::moveLinkEnd, t);}
    public Stage stageLinkStart(double t){return super.customTime(this::moveLinkStart, t);}
    public Stage stageEnd(double t){return super.customTime(this::moveEnd, t);}
    public Stage stageEnd1(double t){return super.customTime(this::moveEnd1, t);}
    public Stage stageStart(double t){return super.customTime(this::moveStart,t);}
    public Stage stageClose(double t){return super.customTime(this::moveClose,t);}
    public Stage stageTransfer(double t){
       return super.customTime(this::moveTransfer, t);
    }
    public Stage stageTransfer4(double t){return super.customTime(this::moveTransfer4, t);}


    // new configs
    public void turretReset () {iturret.setPosition("0");}
    public void turretStraight () {iturret.setPosition("straight");}
    public void turretLeft () {iturret.setPosition("left");}
    public void turretRight () {iturret.setPosition("right");}

    public Stage sTurretReset (double t) {return super.customTime(this::turretReset, t);}
    public Stage sTurretStraight (double t) {return super.customTime(this::turretStraight, t);}
    public Stage sTurretLeft (double t) {return super.customTime(this::turretLeft, t);}
    public Stage sTurretRight (double t) {return super.customTime(this::turretRight, t);}

    public void updatePipeline(int loopNum) {
        if (sampleScanner.getAngle() == -1) return;
        for (int i = 0; i < loopNum; i++) {
            iturret.changePosition("angle", 0.3);
            iturret.setPosition("angle");
        }
    }
}