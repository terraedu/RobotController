package robotparts.hardware;

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
        pivotSammy.addPosition("Pickup", 0);
        LaunchSammy.addPosition("release",0.3);
        L_clawSammy.addPosition("grabL", 0.2);
        R_clawSammy.addPosition("grabR", 0.2);
    }




}
