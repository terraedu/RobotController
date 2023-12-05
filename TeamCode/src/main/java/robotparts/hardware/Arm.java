package robotparts.hardware;

import static global.Modes.Arm.DOWN;
import static global.Modes.Arm.UP;
import static global.Modes.Height.HIGH;
import static global.Modes.Height.LOW;
import static global.Modes.armMode;
import static global.Modes.heightMode;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PMotor;

public class Arm extends RobotPart {
    public PMotor pivot;
    public PMotor move;
    @Override
    public void init() {
pivot = create("pivot", ElectronicType.PMOTOR_FORWARD);
move = create("move", ElectronicType.PMOTOR_FORWARD);
    }

    public void liftArm() {
        pivot.move(0.5);
        move.move(0.7);
        heightMode.set(HIGH);
    }
    public void resetArm(){
        pivot.move(-0.5);
        move.move(-0.7);
        heightMode.set(LOW);
    }
    public Stage stageLiftArm(double power){
        return moveTarget(()->move,power, 0.7);


    }
    public Stage stageResetArm(double power){
        return moveTarget(()->move,power,-0.7);

    }
}
