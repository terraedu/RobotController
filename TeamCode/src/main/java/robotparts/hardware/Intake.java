package robotparts.hardware;

import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PMotor;

public class Intake extends RobotPart {

    public PMotor intake;


    @Override
    public void init() {
        intake = create("intake", ElectronicType.PMOTOR_FORWARD);

    }
    @Override
    public void move(double p) {
        intake.setPowerRaw(1);
    }
}
