package robotparts.hardware;

import static java.lang.Math.abs;

import automodules.AutoModule;
import automodules.stage.Exit;
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
import static global.Modes.Height.GROUND;
import static global.Modes.driveMode;
import static global.Modes.heightMode;
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





    @Override
    public void init() {
//        fr = create("fr", ElectronicType.CMOTOR_REVERSE);
//        br = create("br", ElectronicType.CMOTOR_REVERSE);
//        fl = create("fl", ElectronicType.CMOTOR_FORWARD);
//        bl = create("bl", ElectronicType.CMOTOR_FORWARD);


        fr = create("fr", ElectronicType.CMOTOR_REVERSE);
        br = create("br", ElectronicType.CMOTOR_REVERSE);
        fl = create("fl", ElectronicType.CMOTOR_FORWARD);
        bl = create("bl", ElectronicType.CMOTOR_FORWARD);



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






    public void newMove(double f, double s, double t) {
            if(heightMode.get() == GROUND) {


                fl.setPower(f + s + .55 * t);
                bl.setPower(f - s + .55 * t);
                fr.setPower(f - s - .55 * t);
                br.setPower(f + s - .55 * t);


            }else{

                fl.setPower(.3 * f + .3 * s + .2 * t);
                bl.setPower(.3 * f - .3 * s + .2 * t);
                fr.setPower(.3 * f - .3 * s - .2 * t);
                br.setPower(.3 * f + .3 * s - .2 * t);
            }


        }


    @Override
    public Stage moveTime(double fp, double sp, double tp, double t) {
        return super.moveTime(fp, sp, tp, t);
    }

    @Override
    public Stage moveTime(double fp, double sp, double tp, ReturnCodeSeg<Double> t) {
        return super.moveTime(fp, sp, tp, t);
    }
    public Stage driveSmart(double fp, double sp, double tp){
        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitDrive());
    }

    public Stage drivecloseSmart(double fp, double sp, double tp){
        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitDrive());
    }
// Sample exit code
//    public Stage driveSmart2(double fp, double sp, double tp, Exit exit2){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitDrive());
//    }
//    public Stage strafeSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitStrafe());
//    }
//
//    public Stage turnRightSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitRight());
//    }
//    public Stage turnLeftSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, distanceSensorsNew.exitLeft());
//    }
//
//    public Stage intakeSmart(double fp, double sp, double tp){
//        return super.moveCustomExit(fp, sp, tp, colorSensorsNew.exitIntake());
//    }

    @Override
    public AutoModule MoveTime(double fp, double sp, double tp, double t) {
        return super.MoveTime(fp, sp, tp, t);
    }
//TODO10 Restore Antitipping power


}
