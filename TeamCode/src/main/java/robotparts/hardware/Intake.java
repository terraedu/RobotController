package robotparts.hardware;

import automodules.AutoModule;
import automodules.stage.Exit;
import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.input.IColor;
import robotparts.electronics.positional.PServo;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

public class Intake extends RobotPart {



    public PServo iarmr, iarml, ipivot, iturret, iclaw, linkager, linkagel;//, linkager; //linkagel;
//    public CServo linkagel;
    @Override
    public void init() {
        iarmr = create("iarmr", ElectronicType.PSERVO_REVERSE);
        iarml = create("iarml", ElectronicType.PSERVO_FORWARD);
        ipivot = create("ipivot", ElectronicType.PSERVO_FORWARD);
        iturret = create("iturret", ElectronicType.PSERVO_FORWARD);

        iclaw = create("iclaw", ElectronicType.PSERVO_FORWARD);
        linkager = create("linkager", ElectronicType.PSERVO_REVERSE);
        linkagel = create("linkagel", ElectronicType.PSERVO_FORWARD);
//
        iarml.changePosition("start", 0.7);
        iarmr.changePosition("start", 0.7);
        iarml.changePosition("transfer1", 0.65);
        iarmr.changePosition("transfer1", 0.65);
        iarml.changePosition("specimen", 0.45);
        iarmr.changePosition("specimen", 0.45);
        iarml.changePosition("transfer", 0.2);
        iarmr.changePosition("transfer", 0.2);
        iarml.changePosition("end", 0.02);
        iarmr.changePosition("end", 0);
        iarml.changePosition("end1", 0.12);
        iarmr.changePosition("end1", 0.12);
        iarml.changePosition("removespecimen", 0.45);
        iarmr.changePosition("removespecimen", 0.45);

        ipivot.changePosition("start", 1);
        ipivot.changePosition("transfer", .7);
        ipivot.changePosition("specimen", 0);


        ipivot.changePosition("middle", .3);
//
        iclaw.changePosition("start", 0.27);
        iclaw.changePosition("adjust", 0.21);

        iclaw.changePosition("close", 0);


        iturret.changePosition("start", 0);
        iturret.changePosition("middle",.38);
        iturret.changePosition("middler",1);
        iturret.changePosition("middlest",.77);



//
        linkager.changePosition("start", 0);
        linkagel.changePosition("start", 0);
        linkager.changePosition("end", 0.25);
        linkagel.changePosition("end", 0.25);

//                linkagel = create("linkagel", ElectronicType.CSERVO_FORWARD);

//





    }
    public void moveStart(){ ipivot.setPosition("start"); iturret.setPosition("start");iclaw.setPosition("start");iarmr.setPosition("start"); iarml.setPosition("start"); linkager.setPosition("start"); linkagel.setPosition("start"); }
    public void moveLinkEnd(){ linkager.setPosition("end"); linkagel.setPosition("end");}
    public void moveLinkStart(){ linkager.setPosition("start"); linkagel.setPosition("start");}
    public void moveEnd(){iarmr.setPosition("end"); iarml.setPosition("end");}

    public void moveEnd1() {
        iarmr.setPosition("end1");
        iarml.setPosition("end1");
        iclaw.setPosition("start");
    }
    public void moveSpecimen(){ipivot.setPosition("specimen"); iclaw.setPosition("close");iarmr.setPosition("specimen"); iarml.setPosition("specimen"); }
    public void removeSpecimen(){iarmr.setPosition("removespecimen"); iarml.setPosition("removespecimen");}


    public void moveTurret() {iturret.setPosition("middle");}
    public void moveTurretMiddler(){iturret.setPosition("middler");}
    public void moveTurretMiddlest(){iturret.setPosition("middlest");}
    public void moveStartTurret() {iturret.setPosition("start");}

    public void moveTransfer(){iarmr.setPosition("start"); iarml.setPosition("start");iturret.setPosition("middle"); ipivot.setPosition("middle");}
    public void moveTransfer2(){iarmr.setPosition("transfer"); iarml.setPosition("transfer"); ipivot.setPosition("transfer");}
    public void moveTransfer3(){ipivot.setPosition("middle");}
    public void moveStartPivot(){ ipivot.setPosition("start");}
    public void moveTransfer4(){iarmr.setPosition("transfer1"); iarml.setPosition("transfer1");}


    public void moveOpen(){ iclaw.setPosition("start"); }
    public void moveClose(){ iclaw.setPosition("close"); }
    public void moveAdjust(){ iclaw.setPosition("adjust"); }


    public Stage removeSpecimen(double t){return super.customTime(this::removeSpecimen,t);}
    public Stage stagePivotStart(double t){return super.customTime(this::moveStartPivot, t);}
    public Stage stageTurret(double t){return super.customTime(this::moveTurret, t);}
    public Stage stageStartTurret(double t){return super.customTime(this::moveStartTurret, t);}
    public Stage stageDownTurret(double t){return super.customTime(this::moveTurretMiddlest, t);}
    public Stage stageSpecimen(double t){return super.customTime(this::moveSpecimen, t);}

    public Stage stageAdjustClaw(double t){return super.customTime(this::moveAdjust, t );}

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


//    public Stage moveRedSample(double p){
//        return super.customExit(p,colorSensorsNew.exitRed());
//    }
//
//    public Stage moveBlueSample(double p){
//        return super.customExit(p,colorSensorsNew.exitBlue());
//    }
//
//    public Stage moveYellowSample(double p){
//        return super.customExit(p,colorSensorsNew.exitYellow());
//    }

//    public Stage moveSampleIn(double p){
//        return super.customExit(p,colorSensorsNew.exitSample());
//    }



//    @Override
//    public Stage moveTime(double p, ReturnCodeSeg<Double> t) { return super.moveTime(p, t); }
//

//    @Override
//    public AutoModule MoveTime(double p, double t) {
//        return super.MoveTime(p, t);
    }
//}


