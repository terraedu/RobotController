package robotparts.hardware;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Claw extends RobotPart {
   public PServo claw;
    @Override
    public void init() {
        claw = create("claw", ElectronicType.PSERVO_REVERSE);
claw.addPosition("open",0.1);
claw.addPosition("closed",0);
    }
    public void openClaw(){
        claw.setPosition("open");

    }
    public void closeClaw(){
        claw.setPosition("closed");
    }
    public Stage stageOpenClaw(){
        
    }
}
