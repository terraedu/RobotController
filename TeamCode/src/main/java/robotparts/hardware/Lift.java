package robotparts.hardware;

import automodules.stage.Initial;
import automodules.stage.Stage;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PMotor;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;
//import static global.Modes.Height.MIDDLE;


public class Lift extends RobotPart {

    public PMotor motorRight;

    public static final double maxPosition = 50;
    public final double defaultCutoffPosition = 0;
    public volatile double currentCutoffPosition = defaultCutoffPosition;
    public int adjust = 0;
    public double globalOffset = 0;

    @Override
    public void init() {
        motorRight = create("lir", ElectronicType.PMOTOR_REVERSE);
        // 0.25
        motorRight.setToLinear(Constants.ORBITAL_TICKS_PER_REV, 1.79, 1, 30);
        motorRight.usePositionHolder(0.5, .1);
        adjust = 0;
        globalOffset = 0;
    }


    @Override
    public CodeSeg move(double p) {
        motorRight.moveWithPositionHolder(p, currentCutoffPosition, 0.05);
        return null;
    }

//      Old holder target
//    public void adjustHolderTarget(double delta) {
//        if (outtakeStatus.modeIs(PLACING) && !heightMode.modeIs(GROUND)) {
//            globalOffset += delta;
//        }
//        currentCutoffPosition = 0;
//        motorRight.holdPositionExact();
//        motorLeft.holdPositionExact();
//        double target = Precision.clip(motorRight.getPositionHolder().getTarget() + delta, 0, maxPosition);
//        motorRight.setPositionHolderTarget(target);
//        motorLeft.setPositionHolderTarget(target);
//    }

    public void liftAdjust(double delta){
        motorRight.holdPositionExact();
        motorRight.setPositionHolderTarget(motorRight.getPositionHolder().getTarget() + delta);
    }


    @Override
    public Stage moveTime(double p, double t) {
        return super.moveTime(p, t).combine(new Initial(() -> currentCutoffPosition = p > 0 ? 0 : defaultCutoffPosition));
    }

    @Override
    public Stage moveTime(double p, ReturnCodeSeg<Double> t) {
        return super.moveTime(p, t);
    }


    public Stage stageLift(double power, double target) {
        return moveTarget(() -> motorRight, power, () -> {
            double Lasttarget = target;

                return target;

        }).combine(new Initial(() -> currentCutoffPosition = target < 1 ? defaultCutoffPosition : 0));
    }



    @Override
    public void maintain() {
        super.maintain();
    }


    public void reset(){ motorRight.softReset();}

}

