package robotparts.hardware;

import automodules.AutoModule;
import automodules.stage.Stage;
import autoutil.vision.SampleScanner;
import geometry.position.Vector;
import math.misc.Logistic;
import math.polynomial.Linear;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import util.Timer;
import util.codeseg.ReturnCodeSeg;
import util.template.Precision;

import static global.General.bot;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.driveMode;
import static global.Modes.robotStatus;

public class Drive extends RobotPart {

    public CMotor fr, br, fl, bl;

    private final Precision precision = new Precision();
    private final Precision precision2 = new Precision();

    private final Timer timer = new Timer();

    public boolean noStrafeLock = false;
    public boolean turnFast = false;
    public boolean using = false;

    public double[] currentPower = new double[3];
    public double[] deltaPower = new double[3];

    @Override
    public void init() {
        fr = create("fr", ElectronicType.CMOTOR_FORWARD);//
        br = create("br", ElectronicType.CMOTOR_REVERSE);
        fl = create("fl", ElectronicType.CMOTOR_FORWARD);//
        bl = create("bl", ElectronicType.CMOTOR_REVERSE);

        noStrafeLock = false;

        driveMode.set(FAST);
        precision.reset();
        precision2.reset();

        currentPower = new double[3];
        deltaPower = new double[3];

        turnFast = false;
        using = false;

        timer.reset();
    }

    @Override
    public void move(double f, double s, double t) {
        fl.setPower(f + s - t);
        bl.setPower(f - s + t);
        fr.setPower(f - s - t);
        br.setPower(f + s + t);
    }

    public void newMove(double f, double s, double t) {
        if (robotStatus.get() == DRIVING) {
            fl.setPower(f - .75 * s + t);
            bl.setPower(f + .75 * s - t);
            fr.setPower(f - .75 * s - t);
            br.setPower(f + .75 * s + t);
        } else {
            fl.setPower(.3 * f - .3 * s + .3 * t);
            bl.setPower(.3 * f + .3 * s - .3 * t);
            fr.setPower(.3 * f - .3 * s - .3 * t);
            br.setPower(.3 * f + .3 * s + .3 * t);
        }

    }

//    public Stage alignSampleRight(double f, double s, double t) {
//        return super.moveCustomExit(f, s, t, intake.sampleScanner.centerRight());
//    }
//
//    public Stage alignSampleLeft(double f, double s, double t) {
//        return super.moveCustomExit(f, s, t, intake.sampleScanner.centerLeft());
//    }

    @Override
    public Stage moveTime(double fp, double sp, double tp, double t) {
        return super.moveTime(fp, sp, tp, t);
    }

    @Override
    public Stage moveTime(double fp, double sp, double tp, ReturnCodeSeg<Double> t) {
        return super.moveTime(fp, sp, tp, t);
    }

    @Override
    public AutoModule MoveTime(double fp, double sp, double tp, double t) {
        return super.MoveTime(fp, sp, tp, t);
    }

    public double getAntiTippingPower(){
        return 0;
    }
}