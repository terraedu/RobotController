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
    private CServo drone;
    private PServo lock;

    private PServo link;
    private PServo link2;
    private CMotor hang;

    public PServo farmR, farmL, pivot, claw;
    public CServo intake;

    boolean Pixel;

    @Override
    public void init() {
        in = create("in", ElectronicType.CMOTOR_FORWARD_FLOAT);
        drone = create("drone", ElectronicType.CSERVO_FORWARD);
        link = create("link", ElectronicType.PSERVO_REVERSE);
        link2 = create("link2", ElectronicType.PSERVO_FORWARD);

        lock = create("lock", ElectronicType.PSERVO_FORWARD);








        farmR = create("farmR", ElectronicType.PSERVO_FORWARD);
        farmL = create("farmL", ElectronicType.PSERVO_REVERSE);

        farmR.addPosition("down", 0.42);
        farmL.addPosition("down", 0.42);

        farmR.addPosition("flip", 0.42);
        farmL.addPosition("flip", 0.42);

        pivot.addPosition("notflipped", 0);
        pivot.addPosition("flip", 0.5);

        claw.addPosition("close", 0);
        claw.addPosition("open", 0.8);






//        hang = create("hang", ElectronicType.CMOTOR_FORWARD);
        lock.changePosition("purple", 0.75);

        lock.changePosition("init", 0);
        lock.changePosition("ready", 0.1);
        lock.changePosition("open", 0.54);


        link.changePosition("init", .33);
        link.changePosition("start", .62);
        link.changePosition("middle", .60);
        link.changePosition("middler", .54);

        link2.changePosition("init", .33);
        link2.changePosition("start", .61);
        link2.changePosition("middle", .58);
        link2.changePosition("middler", .54);

    }





    public void extendFarm() {
        farmR.setPosition("flip");
        farmL.setPosition("flip");
    }

    public void resetFarm() {
        farmR.setPosition("down");
        farmL.setPosition("down");
    }

    public void pivotClaw() {
        pivot.setPosition("flip");
    }

    public void resetClaw() {
        pivot.setPosition("notflipped");
    }

    public void openClaw() {
        claw.setPosition("open");
    }

    public void closeClaw() {
        claw.setPosition("close");
    }

    public void runIntake() {
        intake.moveTime(5, 3000);
    }







    public void moveInit() {
        link.setPosition("init");
        link2.setPosition("init");


    }

    public void lockClose() {
            lock.setPosition("open");


    }
    public void purple() {
        lock.setPosition("purple");


    }
    public void lockInit() {
        lock.setPosition("init");


    }
    public void lockReady(){
        lock.setPosition("ready");
    }


    public void moveStart() {
        link.setPosition("start");
        link2.setPosition("start");


    }
    public void moveMiddler() {
        link.setPosition("middler");
        link2.setPosition("middler");


    }



    public void moveMiddle() {
        link.setPosition("middle");
        link2.setPosition("middle");


    }

    public void chubramani(){

        drone.setPower(-1);
//        log.show("mission failed");

    }
//    public void moveHang(double pow) {
//        hang.setPower(pow);
//    }

    @Override
    public CodeSeg move(double pow) {
        in.setPower(pow);
//        ins.setPower(pow);
        return null;
    }





    public Stage extendFarm(double t) {return super.customTime(this::extendFarm, t);}
    public Stage resetFarm(double t) {return super.customTime(this::resetFarm, t);}
    public Stage pivotClaw(double t) {return super.customTime(this::pivotClaw, t);}
    public Stage resetClaw(double t) {return super.customTime(this::resetClaw, t);}
    public Stage openClaw(double t) {return super.customTime(this::openClaw, t);}
    public Stage closeClaw(double t) {return super.customTime(this::closeClaw, t);}
    public Stage runIntake(double t) {return super.customTime(this::runIntake, t);}







    public Stage moveSmart(double p, Exit exit2){
            return super.customTwoExit(p, exit2, colorSensorsNew.exitIntake());





    }
    public Stage moveSmart2(double p){
        return super.customExit(p, colorSensorsNew.exitIntake());





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



















    public Stage moveOneSmart2(double p, Exit exit){
        return super.customExit(p, colorSensorsNew.exitIntake2());





    }

    public Stage moveOneSmart(double p){
        return super.customExit(p, colorSensorsNew.exitIntake2());





    }


//    public Stage stopSmart(){
//        return
//    }

    public Stage removeSmart(double p){

        return super.customExit(p, colorSensorsNew.exitExtake());

    }

    public Stage stageLockInit(double t){ return super.customTime(this::lockInit, t); }
    public Stage stageLockReady(double t){ return super.customTime(this::lockReady, t); }
    public Stage stageLockClose(double t){ return super.customTime(this::lockClose, t); }



    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageInit(double t){ return super.customTime(this::moveInit, t); }

    public Stage chubramani(double t){ return super.customTime(this::chubramani, t); }


    public Stage stageMiddle(double t){ return super.customTime(this::moveMiddle, t); }

    public Stage stageMiddler(double t){ return super.customTime(this::moveMiddler, t); }

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


