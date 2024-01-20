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

    private IColor cso;

    private CMotor in;
    private CServo ins;
    private PServo link;
    private PServo link2;
    private CMotor hang;

    boolean Pixel;

    @Override
    public void init() {
        in = create("in", ElectronicType.CMOTOR_FORWARD);
//        ins = create("ins", ElectronicType.CSERVO_FORWARD);
        link = create("link", ElectronicType.PSERVO_REVERSE);
        link2 = create("link2", ElectronicType.PSERVO_FORWARD);

//        hang = create("hang", ElectronicType.CMOTOR_FORWARD);

        link.changePosition("init", .39);
        link.changePosition("start", .72);
        link.changePosition("middle", .52);

        link2.changePosition("init", .39);
        link2.changePosition("start", .72);
        link2.changePosition("middle", .52);
    }

    public void moveInit() {
        link.setPosition("init");
        link.setPosition("init");


    }

    public void moveStart() {
        link.setPosition("start");
        link2.setPosition("start");


    }

    public void moveMiddle() {
        link.setPosition("middle");
        link2.setPosition("middle");


    }


    public void moveHang(double pow) {
        hang.setPower(pow);
    }

    @Override
    public CodeSeg move(double pow) {
        in.setPower(pow);
//        ins.setPower(pow);
        return null;
    }

//    public void isPixel() {
//
//        if (cso.getDistance() < 4) {
//            Pixel = true;
//
//        } else {
//
//            Pixel = false;
//        }
//    }
//
//    public Stage intakePixel(double p) {
//        isPixel();
//        if (Pixel = true) {
//
//            in.setPower(0);
//        } else {
//            in.setPower(p);
//        }
//        return null;
//
//    }


    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageMiddle(double t){ return super.customTime(this::moveMiddle, t); }

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


