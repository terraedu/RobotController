package autoutil.reactors;

import autoutil.controllers.control1D.RP;
import autoutil.controllers.control2D.NoStop;
import autoutil.generators.PoseGen;

public class NonstopReact extends MecanumReactor {

    public NoStop nostop = new NoStop(0.2, 0.1, 100.0);
    public RP hRP = new RP(0.006    , 0.08);
//    public PID hPID = new PID(PID.PIDParameterType.STANDARD_FORM_ALL, 0.01, 6.0, 0.2, 50.0, 20.0);

    public NonstopReact(){
        hRP.setMinimumTime(0.1); hRP.setAccuracy(2.0); setControllers(nostop, hRP);
//        hPID.setMinimumTime(0.1); hPID.setAccuracy(2.0); hPID.setRestOutput(0.06); setControllers(nonstop, hPID);
    }


    @Override
    public void scale(double scale) {
        movementController.scale(scale);
    }

    @Override
    public void firstTarget() { movementController.reset(); }

    @Override
    public boolean isAtTarget() { return movementController.isAtTarget(); }

    @Override
    public void moveToTarget(PoseGen generator) {
        movementController.update(getPose(), generator); headingController.update(getPose(), generator);
//        drive.move(0,0,0);
        drive.move(movementController.getOutputY(), 1.2*movementController.getOutputX(), -1.05*headingController.getOutput());
    }


    public static class NonstopReactSP extends NonstopReact {
        public NonstopReactSP(){ super(); nostop.setpoint(); movementController.setAccuracy(2.0);  }
    }

    public static class NonstopReactTurnSP extends NonstopReact {
        public NonstopReactTurnSP(){ super(); nostop.setpoint(); movementController.setAccuracy(2.0);}

        @Override
        public void scale(double scale) {
            movementController.scale(scale);
            headingController.scale(scale);
        }
    }


    public static class NonstopReactSPslow extends NonstopReact {
        public NonstopReactSPslow(){ super(); nostop.setpoint(); nostop.setEndTp(0.05); movementController.setAccuracy(2.0);  }
    }

}
