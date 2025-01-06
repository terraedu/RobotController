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

        arml.changePosition("start", 0.28);
        armr.changePosition("start", 0.28);
        arml.changePosition("place", 0.24);
        armr.changePosition("place", 0.24);
        arml.changePosition("grab", 0.35);
        armr.changePosition("grab", 0.35);
        arml.changePosition("intake", 0.29);
        armr.changePosition("intake", 0.29);
        arml.changePosition("specimen", 0.52);
        armr.changePosition("specimen", 0.52);
//        arml.changePosition("transfer", .8);
//        armr.changePosition("transfer", .8);
//
//        arml.changePosition("grab", 0.4);
//        armr.changePosition("grab", 0.4);
//
//
        pivot.changePosition("start", 0.1);
        pivot.changePosition("place", 0.5);
        pivot.changePosition("specimen", 0.65);

        pivot.changePosition("grab", .88);

//        pivot.changePosition("transfer", 0.1);
//
        turret.changePosition("start", 0.0);
        turret.changePosition("specimen", 0.75);
        turret.changePosition("half", 0.7);
        turret.changePosition("angle", 0.25);



        claw.changePosition("start", 0.2);
        claw.changePosition("half", 0.4);
        claw.changePosition("open", 0);
//        claw.changePosition("grab", 0.4);

        linkl.changePosition("start", 0.1);
        linkr.changePosition("start", 0.12);
        linkl.changePosition("end", 0.45);
        linkr.changePosition("end", 0.47);

//


        robotStatus.set(DRIVING);
    }



    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); pivot.setPosition("start"); turret.setPosition("start"); claw.setPosition("start");linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveGrab(){ armr.setPosition("intake");arml.setPosition("intake");}
    public void moveOpen(){ claw.setPosition("open");}
    public void movePlace(){armr.setPosition("place"); arml.setPosition("place"); pivot.setPosition("place");}
    public void moveClose(){ claw.setPosition("start");}
    public void moveTransfer(){armr.setPosition("transfer"); armr.setPosition("transfer"); pivot.setPosition("transfer");}
    public void moveLinkStart(){ linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveLinkEnd(){ linkr.setPosition("end"); linkl.setPosition("end");}
    public void moveIntake(){ pivot.setPosition("grab"); armr.setPosition("grab"); arml.setPosition("grab");}
    public void moveSpecimen(){turret.setPosition("specimen");}
    public void moveHalf(){turret.setPosition("half");}
    public void moveFull(){turret.setPosition("start");}
    public void moveAngle(){turret.setPosition("angle");}

    public Stage stageHalf(double t){return super.customTime(this::moveHalf, t);}

    public Stage stageFull(double t){return super.customTime(this::moveFull, t);}
    public Stage stageAngle(double t){return super.customTime(this::moveAngle, t);}

    public void moveSpecimenPivot(){pivot.setPosition("specimen");}

    public Stage stageSpecimenPivot(double t){return super.customTime(this::moveSpecimenPivot, t);}
    public Stage stageSpecimen(double t){return super.customTime(this::moveSpecimen,t);}
    //public void moveTest(){ armr.setPower(.3);}
public Stage stageGrab(double t){return super.customTime(this::moveIntake, t);}
    public Stage stageClose(double t){return super.customTime(this::moveClose, t);}
    public Stage stageCloseArm(double t){return super.customTime(this::moveGrab, t);}

    public Stage stageOpen(double t){return super.customTime(this::moveOpen, t);}

    public Stage stagePlace(double t){return super.customTime(this::movePlace,t);}
    public Stage stageLinkEnd(double t){return super.customTime(this::moveLinkEnd, t);}
    public Stage stageStart(double t) {
        return super.customTime(this::moveStart, t);
    }
    public Stage stageTransfer(double t){return super.customTime(this::moveTransfer, t);}













}

