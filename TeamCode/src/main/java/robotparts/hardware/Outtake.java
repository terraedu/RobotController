package robotparts.hardware;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.outtakeStatus;

public class Outtake extends RobotPart {

    public PServo pivot, claw1, claw2;
//    public PServo forklift;

//    public boolean cycleMachine = false;
//    public boolean pauseMachine = false;
//    public boolean skipMachine = false;

    @Override
    public void init() {

        claw1 = create("c1", ElectronicType.PSERVO_FORWARD);



        claw1.addPosition("open", 0);
        claw1.addPosition("close", .1);

        claw2 = create("c2", ElectronicType.PSERVO_FORWARD);

        claw2.addPosition("open", 0);
        claw2.addPosition("close", .2);



        outtakeStatus.set(DRIVING);
    }

    public void openClaw1(){ claw1.setPosition("open"); }
    public void closeClaw1(){ claw1.setPosition("close"); }

    public void openClaw2(){ claw2.setPosition("open"); }
    public void closeClaw2(){ claw2.setPosition("close"); }





    public Stage stageOpen1(double t){ return super.customTime(this::openClaw1, t); }
    public Stage stageClose1(double t){ return super.customTime(this::closeClaw1, t); }

    public Stage stageOpen2(double t){ return super.customTime(this::openClaw2, t); }
    public Stage stageClose2(double t){ return super.customTime(this::closeClaw2, t); }




    public void startSignal(){
//        forklift.setPosition("start");
    }


    public void startReadySignal(){
//        forklift.setPosition("readyStart");
    }

    public void endSignal(){
//        forklift.setPosition("end");
    }


    public Stage stageStartSignal(double t){
        return super.customTime(this::startSignal, t);
    }

    public Stage stageEndSignal(double t){
        return super.customTime(this::endSignal, t);
    }

    public Stage stageStartReadySignal(double t){
        return super.customTime(this::startReadySignal, t);
    }

    public Stage stageStartSignalAfter(double t){
        return super.customTimeAfter(this::startSignal, t);
    }

    public Stage stageStartReadySignalAfter(double t) {
        return super.customTimeAfter(this::startReadySignal, t);
    }


    public Stage stageEndSignalAfter(double t){
        return super.customTimeAfter(this::endSignal, t);
    }










//
//    public Stage stageEnd(){
//        return super.customTime(new StageBuilderTime(this)
//                .addSubStage(0.1, () -> {closeClaw(); readyStart();})
//                .addSubStage(0.3, () -> {flip(); })
//                        //setArmTarget("end");
//                        .addSubStage(0.5, () -> moveEnd())
////                .addSubStage(0.5, () -> moveArmContinuous(0.5))
//        );
//    }
//
//    public Stage stageStart() {
//        return super.customTime(new StageBuilderTime(this)
//                .addSubStage(0.1, () -> {openClaw();readyEnd();})
//                .addSubStage(0.3, () -> {closeClaw(); unFlip();  })
//                //setArmTarget("start");
////                .addSubStage(0.5, () -> moveArmContinuous(0.5))
//                .addSubStage(0.5, () -> moveStart())
//        );
//    }

}
