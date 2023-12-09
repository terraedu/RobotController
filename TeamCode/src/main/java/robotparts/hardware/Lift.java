package robotparts.hardware;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import global.Constants;
import global.Modes;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PMotor;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.template.Precision;

import static global.General.bot;
import static global.Modes.Height.GROUND;
import static global.Modes.Height.HIGH;
import static global.Modes.Height.LOW;
import static global.Modes.Height.MIDDLE;
import static global.Modes.OuttakeStatus.PLACING;
import static global.Modes.heightMode;
import static global.Modes.outtakeStatus;

public class Lift extends RobotPart {

    public PMotor pivot;
    public PMotor lift;

    public static final double maxPosition = 61;
    public final double defaultCutoffPosition = 6;
    public volatile double currentCutoffPosition = defaultCutoffPosition;
    public int stackedMode = 0;
    public boolean circuitMode = false;
    public boolean ground = false;
    public boolean stacked = false;
    public boolean upright = false;
    public boolean skipping = false;
    public boolean cap = false;
    public int adjust = 0;
    public boolean adjusting = false;
    public double globalOffset = 0;

    @Override
    public void init() {
        pivot = create("arm", ElectronicType.PMOTOR_FORWARD);
        lift = create("lift", ElectronicType.PMOTOR_REVERSE);
        // 0.25
        pivot.setToLinear(Constants.ORBITAL_TICKS_PER_REV, 1.79, 1, 30);
        lift.setToLinear(Constants.ORBITAL_TICKS_PER_REV, 1.79, 1, 30);
        pivot.usePositionHolder(0.1, 0.1);
        lift.usePositionHolder(1.6, 0.1);
        heightMode.set(Modes.Height.HIGH);
        circuitMode = false;
        stacked = false;
        ground = false;
        upright = false;
        skipping = false;
        cap = false;
        adjusting = false;
        adjust = 0;
        stackedMode = 0;
        globalOffset = 0;
        resetArm();
    }

    public void setGround(boolean ground){ this.ground = ground; }

    @Override
    public void move(double p) {
        lift.moveWithPositionHolder(0.3 * p, currentCutoffPosition, 0.1);
//        pivot.moveWithPositionHolder(p, currentCutoffPosition, 0.1);
    }

    public void armMove(double p) {
        pivot.moveWithPositionHolder(p * 0.7, currentCutoffPosition, 0);
    }
    public double getPivotMotorPos(){
        return pivot.getPosition();
    }
    //
//    public void adjustHolderTarget(double delta){
//        if(outtakeStatus.modeIs(PLACING) && !heightMode.modeIs(GROUND)) {
//            globalOffset += delta;
//        }
//        currentCutoffPosition = -10;
//        motorRight.holdPositionExact();
//        motorLeft.holdPositionExact();
//        double target = Precision.clip(motorRight.getPositionHolder().getTarget()+delta, 0, maxPosition);
//        motorRight.setPositionHolderTarget(target);
//        motorLeft.setPositionHolderTarget(target);
//    }
    public void resetArm(){
        lift.move(0);
        pivot.move(0);
    }

    @Override
    public Stage moveTime(double p, double t) {
        return super.moveTime(p, t).combine(new Initial(() -> currentCutoffPosition = p > 0 ? 0 : defaultCutoffPosition));
    }

    @Override
    public Stage moveTime(double p, ReturnCodeSeg<Double> t) { return super.moveTime(p, t); }

    public Stage moveTimeBack(double fp, double p, ReturnCodeSeg<Double> t){ return moveTimeBack(() -> fp, () -> p, t, () -> {}); }

    public Stage moveTimeBack(ReturnCodeSeg<Double> fp, ReturnCodeSeg<Double> p, ReturnCodeSeg<Double> t, CodeSeg endCode){
        final Double[] val = {0.0};
        return new Stage(drive.usePart(), this.usePart(), new Initial(() -> val[0] = t.run()),
                new Main(() -> {drive.move(fp.run(), 0,0); move(p.run());}),
                new Exit(() -> { synchronized (val){ return val[0] == 0 || bot.rfsHandler.getTimer().seconds() > val[0]; }}), this.stop(), drive.stop(), new Stop(endCode), this.returnPart(), drive.returnPart());
    }

//    public Stage moveTimeBackOverride(double fp, double p, double t){
//        final Double[] val = {0.0};
//        return new Stage(drive.usePart(), this.usePart(), new Initial(() -> val[0] = t),
//                new Main(() -> {drive.move(fp, 0,0); motorLeft.setPowerRaw(p); motorRight.setPowerRaw(p);}),
//                new Exit(() -> { synchronized (val){ return bot.rfsHandler.getTimer().seconds() > val[0]; }}), stop(), drive.stop(), this.returnPart(), drive.returnPart());
//    }

    public Stage stageLift(double power, double target) {
        return moveTarget(() -> lift, power, () -> {
            return target;
        }).combine(new Initial(() -> currentCutoffPosition = target < 1 ? defaultCutoffPosition : 0)); }

    public Stage stageArm(double power, double target) {
        return moveTarget(() -> pivot, power, () -> {
            return target;
        }).combine(new Initial(() -> currentCutoffPosition = target < 1 ? defaultCutoffPosition : 0)); }

    @Override
    public void maintain() { super.maintain(); }

    public void reset(){ lift.softReset(); pivot.softReset(); }

    public Stage resetLift(){ return new Stage(usePart(), new Main(this::reset), exitTime(0.1), stop(), returnPart()); }

//    public Stage checkAndLift(){
//        return lift.moveTimeBack(
//                () -> {if(lift.stacked){ return -0.2; }else { return 0.0; }}, //fp
//                () -> 1.0, //p
////                () -> {if(lift.stacked ){ return 0.35;}else if(lift.cap){return 0.0;}else{return 0.0;}}, //t
//                () -> {if(lift.stacked ){ return 0.35;}else if(lift.cap){return 0.35;}else{return 0.0;}},
//                () -> {lift.stacked = false;
//                    lift.cap = false;
//                });
//    }

//    public Stage checkAndLift(){
//        return lift.moveTimeBack(
//                () -> {if(lift.stacked){ return -0.2; }else { return 0.0; }},
//                () -> 1.0,
//                () -> {if(lift.stacked ){ return 0.35;}else if(lift.cap){return 0.35;}else{return 0.0;}},
//                () -> {lift.stacked = false; lift.cap = false; });
//    }
}

