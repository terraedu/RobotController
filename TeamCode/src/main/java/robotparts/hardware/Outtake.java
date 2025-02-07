package robotparts.hardware;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.robotStatus;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.positional.PServo;

public class Outtake extends RobotPart {

    public PServo armr, arml, claw, pivot, linkr, linkl, pivot1, slides, turret;
//    public CServo armr;

    @Override
    public void init() {
        armr = create("armr", ElectronicType.PSERVO_FORWARD);
        arml = create("arml", ElectronicType.PSERVO_REVERSE);
//        pivot = create("pivot", ElectronicType.PSERVO_FORWARD);
//        claw = create("claw", ElectronicType.PSERVO_REVERSE);
        linkr = create("linkr", ElectronicType.PSERVO_FORWARD);
        linkl = create("linkl", ElectronicType.PSERVO_REVERSE);


        arml.changePosition("start", 0);
        armr.changePosition("start", 0);
//        arml.changePosition("transfer", .8);
//        armr.changePosition("transfer", .8);
//
//        arml.changePosition("grab", 0.4);
//        armr.changePosition("grab", 0.4);
//
//
//        pivot.changePosition("start", 0.05);
//        pivot.changePosition("transfer", 0.1);
//
//
//        claw.changePosition("start", 0);
//        claw.changePosition("grab", 0.4);

        linkl.changePosition("start", 0);
        linkr.changePosition("start", 0);
        linkl.changePosition("end", 0.2);
        linkr.changePosition("end", 0.2);


        arml.changePosition("down", 0.5);
;
//


        robotStatus.set(DRIVING);
    }

    public void armDown(){ arml.setPosition("down");}

    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start");}
    public void moveGrab(){ armr.setPosition("grab");arml.setPosition("grab");}
    public void moveOpen(){ claw.setPosition("start");}
    public void moveClose(){ claw.setPosition("grab");}
    public void moveTransfer(){armr.setPosition("transfer"); armr.setPosition("transfer"); pivot.setPosition("transfer");}
    public void moveLinkStart(){ linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveLinkEnd(){ linkr.setPosition("end"); linkl.setPosition("end");}
    //

    //public void moveTest(){ armr.setPower(.3);}
public Stage stageGrab(double t){return super.customTime(this::moveGrab, t);}
    public Stage stageClose(double t){return super.customTime(this::moveClose, t);}
    public Stage stageOpen(double t){return super.customTime(this::moveOpen, t);}
    public Stage stageArmDown(double t){return super.customTime(this::armDown, t);}
    public Stage stageStart(double t) {
        return super.customTime(this::moveStart, t);
    }
    public Stage stageTransfer(double t){return super.customTime(this::moveTransfer, t);}
    public Stage pivotExample(double t){return super.customTime(this::moveTransfer, t);}













}

