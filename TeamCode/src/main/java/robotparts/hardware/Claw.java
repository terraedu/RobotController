package robotparts.hardware;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Claw extends RobotPart {
  public PServo pivot;
   public PServo claw;
    @Override
    public void init() {
        claw = create("claw", ElectronicType.PSERVO_REVERSE);
claw.addPosition("open",0.1);
claw.addPosition("closed",0);
pivot = create("pivot",ElectronicType.PSERVO_FORWARD);
pivot.addPosition("grab",0);
pivot.addPosition("backdrop",0.7);
    }
    public void openClaw(){
        claw.setPosition("open");
    }
    public void closeClaw(){

        claw.setPosition("closed");
    }
    public Stage stageOpenClaw(double t){
return super.customTime(this::openClaw,t);
    }
    public Stage stageCloseClaw(double t){
        return super.customTime(this::closeClaw,t);
    }
    public Stage stagePivotGrab(double t){
        return super.customTime(this::pivotGrab,t);
    }
    public Stage stagePivotBackdrop(double t){
        return super.customTime(this::pivotBackdrop,t);
    }
    public void pivotGrab(){
    pivot.setPosition("grab");
    }
    public void pivotBackdrop(){
        pivot.setPosition("backdrop");
    }
}


