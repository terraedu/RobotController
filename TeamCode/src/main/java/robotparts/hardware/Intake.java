package robotparts.hardware;

import automodules.AutoModule;
import automodules.stage.Exit;
import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import robotparts.electronics.continuous.CServo;
import robotparts.electronics.input.IColor;
import robotparts.electronics.positional.PServo;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;

public class Intake extends RobotPart {



    private PServo iarmr, iarml, ipivot, iturret, iclaw, linkager, linkagel;



    @Override
    public void init() {
        iarmr = create("iarmr", ElectronicType.PSERVO_REVERSE);
        iarml = create("iarml", ElectronicType.PSERVO_FORWARD);
        ipivot = create("ipivot", ElectronicType.PSERVO_FORWARD);
        iclaw = create("iclaw", ElectronicType.PSERVO_FORWARD);
        linkager = create("linkager", ElectronicType.PSERVO_REVERSE);
        linkagel = create("linkagel", ElectronicType.PSERVO_FORWARD);

        iarml.changePosition("start", 0);
        iarmr.changePosition("start", 0);

        ipivot.changePosition("start", 0);

        iclaw.changePosition("start", 0);

        linkager.changePosition("start", 0);
        linkagel.changePosition("start", 0);







    }



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


