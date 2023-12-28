package robotparts.hardware;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Outtake extends RobotPart {

    public PServo claw;
    @Override
    public void init(){
        claw = create("claw", ElectronicType.PSERVO_FORWARD);
        claw.changePosition("start",0);
        claw.addPosition("closed",0.3);

    }
    public void moveStart(){
        claw.setPosition("start");


    }
    public void closedclaw(){
        claw.setPosition("closed");

    }

    public void openedclaw(){
        claw.setPosition("start");

    }

    public Stage StageClosed(double t){
        return super.customTime(this::closedclaw,t);
    }

    public Stage StageOpened(double t){
        return super.customTime(this::openedclaw,t);
    }
}


