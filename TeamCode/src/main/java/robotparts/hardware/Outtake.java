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

        arml.changePosition("start", 0.75);
        armr.changePosition("start", 0.75);
        arml.changePosition("dropspecimen", 1);
        armr.changePosition("dropspecimen", 1);
        arml.changePosition("placespecimen", .7);
        armr.changePosition("placespecimen", .7);
        arml.changePosition("grabspecimen", 0.88);
        armr.changePosition("grabspecimen", 0.88);
        arml.changePosition("liftspecimen", 0.96);
        armr.changePosition("liftspecimen", 0.96);

        arml.changePosition("place", 0.71);
        armr.changePosition("place", 0.71);
        arml.changePosition("grab", 0.8    );
        armr.changePosition("grab", 0.8);
        arml.changePosition("intake", 0.7);
        armr.changePosition("intake", 0.7);
        arml.changePosition("specimen", 0.8);
        armr.changePosition("specimen", 0.8);
//        arml.changePosition("transfer", .8);
//        armr.changePosition("transfer", .8);
//
//        arml.changePosition("grab", 0.4);
//        armr.changePosition("grab", 0.4);
//
//
        pivot.changePosition("start", 0.26);
        pivot.changePosition("place", 0.62);
        pivot.changePosition("specimen", 0.82);

        pivot.changePosition("grab", 1);

//        pivot.changePosition("transfer", 0.1);
//
        turret.changePosition("start", 0.0);
        turret.changePosition("specimen", 0.83);
        turret.changePosition("half", 0.5);
        turret.changePosition("angle", 0.25);



        claw.changePosition("start", 0.2);
        claw.changePosition("half", 0.4);
        claw.changePosition("open", 0);
//        claw.changePosition("grab", 0.4);

        linkl.changePosition("start", 0.1);
        linkr.changePosition("start", 0.1);
        linkl.changePosition("end", 0.25);
        linkr.changePosition("end", 0.25);

//


        robotStatus.set(DRIVING);
    }



    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); pivot.setPosition("start"); turret.setPosition("start"); claw.setPosition("start");linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveStartLink(){ linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveSpecimenTurret(){turret.setPosition("specimen");}
    public void moveLiftSpecimen(){armr.setPosition("liftspecimen");arml.setPosition("liftspecimen");}
        public void moveGrab(){ armr.setPosition("intake");arml.setPosition("intake");}
    public void moveOpen(){ claw.setPosition("open");}
    public void movePlace(){armr.setPosition("place"); arml.setPosition("place"); pivot.setPosition("start"); }

    public void movePlacePivot(){pivot.setPosition("place"); }
    public void moveClose(){ claw.setPosition("start");}
    public void moveTransfer(){armr.setPosition("transfer"); armr.setPosition("transfer"); pivot.setPosition("transfer");}
    public void moveLinkStart(){ linkr.setPosition("start"); linkl.setPosition("start");}
    public void moveLinkEnd(){ linkr.setPosition("end"); linkl.setPosition("end");}
    public void moveIntake(){ pivot.setPosition("grab"); armr.setPosition("grab"); arml.setPosition("grab");}
    public void moveSpecimen(){armr.setPosition("dropspecimen");arml.setPosition("dropspecimen");}
    public void moveSpecimen2(){armr.setPosition("placespecimen");arml.setPosition("placespecimen");}

    public void moveSpecimen3(){armr.setPosition("start");arml.setPosition("start");pivot.setPosition("start");}

    public void moveHalf(){turret.setPosition("half");}
    public void moveFull(){turret.setPosition("start");}
    public void moveAngle(){turret.setPosition("angle");}
    public void moveGrabSpecimen(){claw.setPosition("open");pivot.setPosition("specimen");armr.setPosition("grabspecimen");arml.setPosition("grabspecimen");}

    public Stage stageSpecimenTurret(double t){return super.customTime(this::moveSpecimenTurret, t);}
    public Stage stagePlacePivot(double t){return super.customTime(this::movePlacePivot, t);}
    public Stage stageHalf(double t){return super.customTime(this::moveHalf, t);}
    public Stage stageGrabSpecimen(double t){return super.customTime(this::moveGrabSpecimen,t);}
    public Stage stageFull(double t){return super.customTime(this::moveFull, t);}
    public Stage stageAngle(double t){return super.customTime(this::moveAngle, t);}

    public void moveSpecimenPivot(){arml.setPosition("liftspecimen"); armr.setPosition("liftspecimen");pivot.setPosition("place");}

    public Stage stageLiftSpecimen(double t){return super.customTime(this::moveSpecimenPivot,t);}
    public Stage stageSpecimenPivot(double t){return super.customTime(this::moveSpecimenPivot, t);}
    public Stage stageSpecimen(double t){return super.customTime(this::moveSpecimen,t);}
    public Stage stagePlaceSpecimen(double t){return super.customTime(this::moveSpecimen3,t);}

    public Stage stageSpecimen2(double t){return super.customTime(this::moveSpecimen2,t);}

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
    public Stage stageStartLink(double t) {
        return super.customTime(this::moveStartLink, t);
    }

    public Stage stageTransfer(double t){return super.customTime(this::moveTransfer, t);}













}

