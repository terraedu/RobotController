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


public class Extendo extends RobotPart {

    public PMotor motorLeft;

    public static final double maxPosition = 50;
    public final double defaultCutoffPosition = 0;
    public volatile double currentCutoffPosition = defaultCutoffPosition;
    public int adjust = 0;
    public double globalOffset = 0;

    @Override
    public void init() {
        motorLeft = create("eil", ElectronicType.PMOTOR_REVERSE);
        // 0.25
        motorLeft.setToLinear(Constants.ORBITAL_TICKS_PER_REV, 1.79, 1, 30);
        motorLeft.usePositionHolder(0.1, .1);
        adjust = 0;
        globalOffset = 0;
    }


    @Override
    public CodeSeg move(double p) {
        motorLeft.moveWithPositionHolder(p, currentCutoffPosition, 0.05);
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
        motorLeft.holdPositionExact();
        motorLeft.setPositionHolderTarget(motorLeft.getPositionHolder().getTarget() + delta);
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
        return moveTarget(() -> motorLeft, power, () -> {
            double Lasttarget = target;

                return target;

        }).combine(new Initial(() -> currentCutoffPosition = target < 1 ? defaultCutoffPosition : 0));
    }


//    public Stage adjustLift(double power, double adjust){
//        return moveTarget(() -> motorRight, () -> motorLeft, power, power, () -> {
//            if (lastTarget == heightMode.getValue(LOW) + 2 || target == heightMode.getValue(MIDDLE) + 2 || target == heightMode.getValue(HIGH) + 2) {
//                return target + globalOffset;
//            } else {
//                return target;
//            }
//        }).combine(new Initial(() -> currentCutoffPosition = target < 1 ? defaultCutoffPosition : 0));
//    }

    @Override
    public void maintain() {
        super.maintain();
    }


    public void reset(){ motorLeft.softReset(); }

}

