package robotparts.hardware;

import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.positional.PMotor;

public class Intake extends RobotPart {

    public CMotor intake;


    @Override
    public void init() {
        intake = create("intake", ElectronicType.CMOTOR_REVERSE);

    }
    @Override
    public void move(double p) {
        intake.setPower(p);
    }
}
