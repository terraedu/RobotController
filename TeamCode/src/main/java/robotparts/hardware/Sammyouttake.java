package robotparts.hardware;





import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.outtakeStatus;

import automodules.stage.Stage;
import global.Modes;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Sammyouttake extends RobotPart {
    public PServo pivotSammy,L_clawSammy,R_clawSammy,LaunchSammy;

    @Override
    public void init() {
        pivotSammy = create("pivotSammy", ElectronicType.PSERVO_REVERSE);
        LaunchSammy = create("launchSammy", ElectronicType.PSERVO_REVERSE);
        L_clawSammy = create("L_ClawSammy",ElectronicType.PSERVO_FORWARD);
        R_clawSammy = create("R_ClawSammy",ElectronicType.PSERVO_FORWARD);


        pivotSammy.changePosition("start", 1);
        LaunchSammy.changePosition("start", 0);
        L_clawSammy.changePosition("start", 0);
        R_clawSammy.changePosition("start", 0);
        pivotSammy.addPosition("grab", 0);
        LaunchSammy.addPosition("release",0.3);
        L_clawSammy.addPosition("grabL", 0.2);
        R_clawSammy.addPosition("grabR", 0.2);
        outtakeStatus.set(DRIVING);
    }


public void openClawSammy()

        {
        L_clawSammy.setPosition("start");
        R_clawSammy.setPosition("start");

    }
public void closeClawSammy(){
        L_clawSammy.setPosition("grabL");
        R_clawSammy.setPosition("grabR");
    }
public void releaseSammy()
{
        LaunchSammy.setPosition("release");
    }
public void moveStart(){
        L_clawSammy.setPosition("start");
        R_clawSammy.setPosition("start");
        pivotSammy.setPosition("start");
        LaunchSammy.setPosition("start");
}


public void moveEnd(){
        L_clawSammy.setPosition("end");
        R_clawSammy.setPosition("end");
        pivotSammy.setPosition("end");
    }
public void placePivot () {

        pivotSammy.setPosition("start");
}
public void grabPivot() {

    pivotSammy.setPosition("grab");
}
public Stage stageSammyRelease(double t) {
        return super.customTime(this::releaseSammy, t);
    }
public Stage stageSammyOpenClaw(double t) {
        return super.customTime(this::openClawSammy, t);
    }



    }



