package robotparts.hardware;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.robotStatus;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.positional.PServo;

public class Outtake extends RobotPart {

    public PServo armr, arml, claw, pivot, linkr, linkl, turret;
//    public CServo armr;

    @Override
    public void init() {
        armr = create("armr", ElectronicType.PSERVO_FORWARD);
        arml = create("arml", ElectronicType.PSERVO_REVERSE);
        pivot = create("pivot", ElectronicType.PSERVO_FORWARD);
        turret = create("turret", ElectronicType.PSERVO_FORWARD);
        claw = create("claw", ElectronicType.PSERVO_FORWARD);
        linkr = create("linkr", ElectronicType.PSERVO_FORWARD);
        linkl = create("linkl", ElectronicType.PSERVO_REVERSE);

        arml.changePosition("start", 0.2);
        armr.changePosition("start", 0.2);
        arml.changePosition("grab", 0.35);
        armr.changePosition("grab", 0.35);
        arml.changePosition("intake", 0.2);
        armr.changePosition("intake", 0.2);
        arml.changePosition("specimen", 0.5);
        armr.changePosition("specimen", 0.5);
//        arml.changePosition("transfer", .8);
//        armr.changePosition("transfer", .8);
//
//        arml.changePosition("grab", 0.4);
//        armr.changePosition("grab", 0.4);
//
//
        pivot.changePosition("start", 0.1);
        pivot.changePosition("grab", .85);

//        pivot.changePosition("transfer", 0.1);
//
        turret.changePosition("start", 0.0);


        claw.changePosition("start", 0.2);
        claw.changePosition("open", 0);
//        claw.changePosition("grab", 0.4);

        linkl.changePosition("start", 0);
        linkr.changePosition("start", 0);
        linkl.changePosition("end", 0.2);
        linkr.changePosition("end", 0.2);

//


        robotStatus.set(DRIVING);
    }



    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); pivot.setPosition("start"); turret.setPosition("start"); claw.setPosition("start");linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveGrab(){ armr.setPosition("intake");arml.setPosition("intake");}
    public void moveOpen(){ claw.setPosition("open");}
    public void moveClose(){ claw.setPosition("start");}
    public void moveTransfer(){armr.setPosition("transfer"); armr.setPosition("transfer"); pivot.setPosition("transfer");}
    public void moveLinkStart(){ linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveLinkEnd(){ linkr.setPosition("end"); linkl.setPosition("end");}
    public void moveIntake(){ pivot.setPosition("grab"); armr.setPosition("grab"); arml.setPosition("grab");}
    public void moveSpecimen(){pivot.setPosition("start"); armr.setPosition("specimen"); arml.setPosition("specimen"); claw.setPosition("open");}

    public Stage stageSpecimen(double t){return super.customTime(this::moveSpecimen,t);}
    //public void moveTest(){ armr.setPower(.3);}
public Stage stageGrab(double t){return super.customTime(this::moveIntake, t);}
    public Stage stageClose(double t){return super.customTime(this::moveClose, t);}
    public Stage stageCloseArm(double t){return super.customTime(this::moveGrab, t);}

    public Stage stageOpen(double t){return super.customTime(this::moveOpen, t);}

    public Stage stageLinkEnd(double t){return super.customTime(this::moveLinkEnd, t);}
    public Stage stageStart(double t) {
        return super.customTime(this::moveStart, t);
    }
    public Stage stageTransfer(double t){return super.customTime(this::moveTransfer, t);}













}

