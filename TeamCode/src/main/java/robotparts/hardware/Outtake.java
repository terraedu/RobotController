package robotparts.hardware;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.robotStatus;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CServo;
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

        arml.changePosition("start", .8);
        armr.changePosition("start", .8);
        arml.changePosition("transfer", .97);
        armr.changePosition("transfer", .97);

        arml.changePosition("grab", 0.4);
        armr.changePosition("grab", 0.4);

        arml.changePosition("specimen", 0.3);
        armr.changePosition("specimen", 0.3);


        pivot.changePosition("start", 0.05);
        pivot.changePosition("transfer", 0.05);



        claw.changePosition("start", 0);
        claw.changePosition("grab", 0.5);
        claw.changePosition("specimen", 0.2);


//


        robotStatus.set(DRIVING);
    }



    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); claw.setPosition("grab");pivot.setPosition("start");}
    public void moveGrab(){ armr.setPosition("grab");arml.setPosition("grab");}
    public void moveOpen(){ claw.setPosition("start");}
    public void moveClose(){ claw.setPosition("grab");}
    public void moveTransfer(){armr.setPosition("transfer"); armr.setPosition("transfer"); pivot.setPosition("transfer"); claw.setPosition("start");}

    public void moveSpecimen(){armr.setPosition("specimen"); arml.setPosition("specimen"); }

    //public void moveTest(){ armr.setPower(.3);}
public Stage stageGrab(double t){return super.customTime(this::moveGrab, t);}
    public Stage stageClose(double t){return super.customTime(this::moveClose, t);}
    public Stage stageSpecimen(double t){return super.customTime(this::moveSpecimen, t);}

    public Stage stageOpen(double t){return super.customTime(this::moveOpen, t);}

    public Stage stageStart(double t) {
        return super.customTime(this::moveStart, t);
    }
    public Stage stageTransfer(double t){return super.customTime(this::moveTransfer, t);}













}

