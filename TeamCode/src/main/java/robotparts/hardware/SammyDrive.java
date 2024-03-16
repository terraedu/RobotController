package robotparts.hardware;

import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;

public class SammyDrive extends RobotPart {
    public CMotor fr,br,fl,bl;
    @Override
    public void init() {
        fr = create("fr", ElectronicType.CMOTOR_REVERSE);
        br = create("fr", ElectronicType.CMOTOR_REVERSE);
        fl = create("fl", ElectronicType.CMOTOR_FORWARD);
        bl= create("bl", ElectronicType.CMOTOR_FORWARD);

    }
    public void SammyMove(double f, double s, double t){
        fr.setPower(f-s-t);
        fl.setPower(f+s+t);
        br.setPower(f+s-t);
        bl.setPower(f-s+t);

    }

}
