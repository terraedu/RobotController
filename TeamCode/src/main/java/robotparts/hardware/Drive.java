package robotparts.hardware;

import static java.lang.Math.abs;

import automodules.AutoModule;
import automodules.stage.Stage;
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
//import static global.Modes.driveMode;

public class Drive extends RobotPart {

    public CMotor fr, br, fl, bl;

    private final Precision precision = new Precision();
    private final Precision precision2 = new Precision();

    private final Timer timer = new Timer();

    public boolean noStrafeLock = false;

    public double[] currentPower = new double[3];
    public double[] deltaPower = new double[3];

    public boolean turnFast = false;

    public boolean using = false;


//    public boolean slow = false;
//    private PServo retract;


    @Override
    public void init() {
//        fr = create("fr", ElectronicType.CMOTOR_REVERSE);
//        br = create("br", ElectronicType.CMOTOR_REVERSE);
//        fl = create("fl", ElectronicType.CMOTOR_FORWARD);
//        bl = create("bl", ElectronicType.CMOTOR_FORWARD);


        fr = create("fr", ElectronicType.CMOTOR_FORWARD);//
        br = create("br", ElectronicType.CMOTOR_REVERSE);
        fl = create("fl", ElectronicType.CMOTOR_FORWARD);//
        bl = create("bl", ElectronicType.CMOTOR_REVERSE);

//
//        retract = create("ret", ElectronicType.PSERVO_FORWARD);
//
//        retract.changePosition("start", 0.03);
//        retract.changePosition("end", 1.0);

//        engage();

        noStrafeLock = false;

        driveMode.set(FAST);
        precision.reset();
        precision2.reset();

        currentPower = new double[3];
        deltaPower = new double[3];

        turnFast = false;
        using = false;

        timer.reset();

        //        throw new RuntimeException("HA HA YOU NOOB VIRUS VIRUS VIRUS");
    }




//    public void retract(){ retract.setPosition("end"); }
//    public void engage(){ retract.setPosition("start"); }

//    public Stage stageRetract(){ return customTime(this::retract, 0.0); }
//    public Stage stageEngage(){ return customTime(this::engage, 0.0); }

    @Override
    public void move(double f, double s, double t) {
        Vector power = new Vector(Precision.clip(s, 1), Precision.clip(f, 1));
        power.scaleX(1.2);
        power.limitLength(1);
        f = power.getY(); s = power.getX(); t = Precision.clip(t, 1);
        fr.setPower(f + s - t);
        br.setPower(f - s - t);
        fl.setPower(f - s + t);
        bl.setPower(f + s + t);
    }

    public void moveWithoutVS(double f, double s, double t) {
        Vector power = new Vector(Precision.clip(s, 1), Precision.clip(f, 1));
        power.scaleX(1.2);
        power.limitLength(1);
        f = power.getY(); s = power.getX(); t = Precision.clip(t, 1);
        fr.setPowerRaw(f - s - t);
        br.setPowerRaw(f + s - t);
        fl.setPowerRaw(f + s + t);
        bl.setPowerRaw(f - s + t);
    }

    public void help(double[] power, int i, double cutoff, double accel, double decel){
        if(Math.abs(power[i]) > cutoff){
//            deltaPower[i] += Math.abs(accel*power[i]);
//            currentPower[i] = Math.signum(power[i]) * (deltaPower[i] + cutoff);
            currentPower[i] = Math.signum(power[i])*cutoff;
        }else{
            currentPower[i] = power[i];
//            deltaPower[i] = Math.max(0, deltaPower[i] - decel);
        }
    }

    public void newMove(double f, double s, double t) {
        if(robotStatus.get()== DRIVING) {


            fl.setPower(f - .75 * s + t); //fr
            bl.setPower(f + .75 * s - t);
            fr.setPower(f - .75 * s - t); //br
            br.setPower(f + .75 * s + t); //fl


        }else{

            fl.setPower(.3 * f - .3 * s + .3 * t);
            bl.setPower(.3 * f + .3 * s - .3 * t);
            fr.setPower(.3 * f - .3 * s - .3 * t);
            br.setPower(.3 * f + .3 * s + .3 * t);
        }


    }


    public void moveSmooth(double f, double s, double t) {



        if(!bot.indHandler.isIndependentRunning()) {
            Logistic rt = new Logistic(Logistic.LogisticParameterType.RP_K, 0.12, 1.0);
            Logistic rm = new Logistic(Logistic.LogisticParameterType.RP_K, 0.05, 5.0);
            Linear rx = new Linear(1.0, 0.4, 1.0);

            if (!driveMode.modeIs(SLOW)) {
                if (precision.isInputTrueForTime(Math.abs(f) > 0.9, 0.5) && Math.abs(f) > 0.9) {
                    driveMode.set(FAST);
                } else {
                    driveMode.set(MEDIUM);
                }
            }

            if (driveMode.modeIs(SLOW)) {
                drive.move(rm.fodd(f * 0.4), noStrafeLock || !Precision.range(s, 0.7) ? rm.fodd(s) * 0.3 : 0.0, rt.fodd(t * 0.6));
            } else if (driveMode.modeIs(MEDIUM)) {
                if (precision2.isInputTrueForTime(Math.abs(t) > 0.9, 0.6) && Math.abs(t) > 0.9) {
                    drive.move(rm.fodd(f * 0.7) * (t != 0 ? rx.feven(t) : 1.0), !Precision.range(s, 0.7) ? rm.fodd(s * 0.7) : 0.0, rt.fodd(t * 0.85));
                } else {
                    drive.move(rm.fodd(f * 0.7) * (t != 0 ? rx.feven(t) : 1.0), !Precision.range(s, 0.7) ? rm.fodd(s * 0.7) : 0.0, 0.6 * rt.fodd(t * 0.85));
                }
//                }
//                drive.move(rm.fodd(f * 0.7) * (t != 0 ? rx.feven(t) : 1.0), !Precision.range(s, 0.7) ? rm.fodd(s * 0.7) : 0.0, 0.6 * rt.fodd(t * 0.85));
            } else {
                drive.move(rm.fodd(f) * (t != 0 ? rx.feven(t) : 1.0), 0.0, rt.fodd(t * 0.8));
            }
        }



//
//
//        if(!bot.indHandler.isIndependentRunning() && !using) {
//            if(driveMode.modeIs(SLOW)) {
//                Logistic rt = new Logistic(Logistic.LogisticParameterType.RP_K, 0.12, 1.0);
//                Logistic rm = new Logistic(Logistic.LogisticParameterType.RP_K, 0.05, 5.0);
//                drive.move(rm.fodd(f*0.4),  rm.fodd(s*0.9)*0.3, rt.fodd(t*0.6));
//            }else {
//
////                drive.move(f, s, t);
//                double xraw = s*0.4;
//                double traw = t*0.7;
//                double tscale = 0.6;
//
//                double fraw = f*0.6;
//
//
//
//                if(driveMode.modeIs(FAST)){
//                    if(abs(f) < 0.9 || timer.seconds() > 1.2){
//                        driveMode.set(MEDIUM);
//                    }
//                    fraw *= Linear.one(1.0, 1.0/0.6).fevenb(timer.seconds()/0.6, 1.0);
//                }else{
//                    if(precision.isInputTrueForTime(abs(f) > 0.9 && abs(t) < 0.9, 0.2)){
//                        driveMode.set(FAST);
//                        timer.reset();
//                    }
//                }
//
//
//                if(turnFast){
//                    if(abs(t) < 0.9 || timer2.seconds() > 0.5){
//                        turnFast = false;
//                    }
//                    traw *= Linear.one(1.0, 3.0).fevenb(timer2.seconds()/0.5, 1.0);
//                }else{
//                    if(precision2.isInputTrueForTime(abs(t) > 0.9 && abs(f) < 0.5, 0.4)){
//                        turnFast = true;
//                        timer2.reset();
//                    }
//                }
//
//                Linear linear = Linear.one(tscale, 1.0);
//                double xnew = Math.abs(f) > 0.1 ? Precision.attract(xraw, 0.0, 0.4) : xraw;
//                double tnew = traw*linear.fevenb(fraw, tscale);
//                drive.move(fraw, xnew, tnew);
//            }
//        }
    }



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
//        double pitch = gyro.getPitch();
//        double pitchDerivative = Math.abs(gyro.getPitchDerivative());
//        if(pitch > -1.5){
//            return 0;
//        }else{
//            return pitch*0.15/(pitchDerivative > 0.7 ? Math.pow(Math.abs(pitchDerivative), 0.5) : 1.0);
//        }
        return 0;
    }

}