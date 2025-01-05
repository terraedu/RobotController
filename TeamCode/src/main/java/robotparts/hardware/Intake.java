package robotparts.hardware;

import org.openftc.easyopencv.OpenCvPipeline;

import automodules.AutoModule;
import automodules.stage.Exit;
import automodules.stage.Stage;
import autoutil.vision.SampleScanner;
import autoutil.vision.Scanner;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.input.IColor;
import robotparts.electronics.positional.PServo;
import util.User;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.template.Precision;

public class Intake extends RobotPart {



    private PServo iarmr, iarml, ipivot, iturret, iclaw, linkager, linkagel;

    public SampleScanner scanner;

    @Override
    public void init() {
//        iarmr = create("iarmr", ElectronicType.PSERVO_FORWARD);
//        iarml = create("iarml", ElectronicType.PSERVO_REVERSE);
//        ipivot = create("ipivot", ElectronicType.PSERVO_REVERSE);
//        iclaw = create("iclaw", ElectronicType.PSERVO_REVERSE);
//        linkager = create("linkager", ElectronicType.PSERVO_FORWARD);
//        linkagel = create("linkagel", ElectronicType.PSERVO_REVERSE);
        iturret = create("iturret", ElectronicType.PSERVO_REVERSE);

//        iarml.changePosition("start", 0);
//        iarmr.changePosition("start", 0);
//
//        ipivot.changePosition("start", 0);
//
//        iclaw.changePosition("start", 0);
//
//        linkager.changePosition("start", 0);
//        linkagel.changePosition("start", 0);

        //camera.setUser(User.ROFU); ERROR IS IN THIS LINE
        camera.checkAccess(User.ROFU);

    }

    public void updatePipeline() {
        if (scanner.getAngle() == -1) return;
        iturret.changePosition("angle", Math.round(Precision.calculateWeightedValue(0, 1, (scanner.getAngle() % 179) / 180) * 10) / 10.0);
        iturret.setPosition("angle");
    }

    public Stage stageAngle(double t){return super.customTime(this::updatePipeline, t);}

    public Stage moveRedSample(double p){
        return super.customExit(p,colorSensorsNew.exitRed());
    }

    public Stage moveBlueSample(double p){
        return super.customExit(p,colorSensorsNew.exitBlue());
    }

    public Stage moveYellowSample(double p){
        return super.customExit(p,colorSensorsNew.exitYellow());
    }

    public Stage moveSampleIn(double p){
        return super.customExit(p,colorSensorsNew.exitSample());
    }



    @Override
    public Stage moveTime(double p, ReturnCodeSeg<Double> t) { return super.moveTime(p, t); }

    @Override
    public Stage moveFull(double p){
        return super.moveFull(p);
    }

    @Override
    public AutoModule MoveTime(double p, double t) {
        return super.MoveTime(p, t);
    }
}


