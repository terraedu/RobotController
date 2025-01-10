package robotparts.hardware;

import automodules.stage.Initial;
import automodules.stage.Stage;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.positional.PMotor;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;
//import static global.Modes.Height.MIDDLE;


public class Lift extends RobotPart {

    public PMotor lir, lpivot;
    public PMotor hl, hr;

    public static final double maxPosition = 100;
    public final double defaultCutoffPosition = 1000;
    public volatile double currentCutoffPosition = defaultCutoffPosition;
    public int adjust = 0;
    public double globalOffset = 0;

    @Override
    public void init() {
        hl = create("hl", ElectronicType.PMOTOR_FORWARD);
        hr = create("hr", ElectronicType.PMOTOR_FORWARD);

        lir = create("lir", ElectronicType.PMOTOR_FORWARD);
        // 0.25
        lir.setToLinear(Constants.ORBITAL_TICKS_PER_REV, 1.79, 1, 1);
        lir.usePositionHolder(0.1, .1);
        lpivot = create("lpivot", ElectronicType.PMOTOR_FORWARD);
        // 0.25
        lpivot.setToLinear(Constants.ORBITAL_TICKS_PER_REV,1.79,2, 1);
        lpivot.usePositionHolder(.5, .3);

        adjust = 0;
        globalOffset = 0;
    }


    @Override
    public CodeSeg move(double p) {
        lir.moveWithPositionHolder(p, currentCutoffPosition, 0.1);
        return null;
    }

    public CodeSeg moveHang(double p) {
        hl.moveWithPositionHolder(p, currentCutoffPosition, 0.0);
        hr.moveWithPositionHolder(p, currentCutoffPosition, 0.0);
        return null;
    }
    public CodeSeg moveHang1(double p) {

        hl.moveWithPositionHolder(p, currentCutoffPosition, 0.0);
        return null;
    }
    public CodeSeg moveHang2(double p) {
        hr.moveWithPositionHolder(p, currentCutoffPosition, 0.0);
        return null;
    }


    public CodeSeg pivotmove(double p) {

                        lpivot.moveWithPositionHolder(p, currentCutoffPosition, 0.0);

//                lpivot.moveWithPositionHolder(.01*(p*Math.cos(lift.lpivot.getPosition()))+  (.05*lift.lir.getPosition()), currentCutoffPosition, 0.0);

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
        lir.holdPositionExact();
        lir.setPositionHolderTarget(lir.getPositionHolder().getTarget() + delta);
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
        return moveTarget(() -> lir, power, () -> {
            double Lasttarget = target;

                return target;

        }).combine(new Initial(() -> currentCutoffPosition = target < 1 ? defaultCutoffPosition : 0));
    }


//    public Stage stagePivot(double power, double target) {
//        return moveTarget(() -> lpivot, power, () -> {
//            double Lasttarget = target;
//
//            return target;
//
//        }).combine(new Initial(() -> currentCutoffPosition = target < 1 ? defaultCutoffPosition : 0));
//    }

    public Stage stagePivot(double power, double target) {
        return moveTarget(() -> lpivot, power, () -> target);


    }




    @Override
    public void maintain() {
        super.maintain();
    }


    public void reset(){ lir.softReset(); lpivot.softReset();}

}

