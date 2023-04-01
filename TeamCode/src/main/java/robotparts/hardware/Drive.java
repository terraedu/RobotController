package robotparts.hardware;

import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;

public class Drive extends RobotPart {

    private CMotor rb, lb, rf, lf;


    @Override
    public void init() {
        rb = create("rb", ElectronicType.CMOTOR_REVERSE);
        lb = create("lb", ElectronicType.CMOTOR_FORWARD);
        rf = create("rf", ElectronicType.CMOTOR_REVERSE);
        lf = create("lf", ElectronicType.CMOTOR_FORWARD);
    }

    @Override
    public void move(double f, double s, double t) {
        rb.setPower(f-t+s);
        lb.setPower(f+t-s);
        rf.setPower(f-t-s);
        lf.setPower(f+t+s);
    }



}
