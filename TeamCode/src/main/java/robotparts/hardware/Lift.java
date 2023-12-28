package robotparts.hardware;

import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PMotor;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

public class Lift extends RobotPart{
    public PMotor lift;
    public final double defaultCutoffPosition = 1000;
    public volatile double currentCutoffPosition = defaultCutoffPosition;

    @Override
    public void init() {
        lift = create("lift", ElectronicType.PMOTOR_FORWARD);
        lift.setToLinear(Constants.ORBITAL_TICKS_PER_REV, 1.79, 1, 30);
        lift.usePositionHolder(0.1, .1);

    }

    @Override
    public CodeSeg move(double p){
        lift.moveWithPositionHolder(p, currentCutoffPosition, 0.05);
        return null;
    }
    @Override
    public Stage moveTime(double p, double t) {
        return super.moveTime(p, t).combine(new Initial(() -> currentCutoffPosition = p > 0 ? 0 : defaultCutoffPosition));
    }

    @Override
    public Stage moveTime(double p, ReturnCodeSeg<Double> t) {
        return super.moveTime(p, t);
    }

    public void reset(){ lift.softReset();  }

    public Stage resetLift(){ return new Stage(usePart(), new Main(this::reset), exitTime(0.1), stop(), returnPart()); }
}