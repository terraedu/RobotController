package robotparts.hardware;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.outtakeStatus;

public class Outtake extends RobotPart {

    public PServo pivotServo, claw1, claw2,drone,slide, rotation;
//    public PServo forklift;

//    public boolean cycleMachine = false;
//    public boolean pauseMachine = false;
//    public boolean skipMachine = false;

    @Override
    public void init() {

        claw1 = create("c1", ElectronicType.PSERVO_FORWARD);

        claw1.addPosition("open", 0);
        claw1.addPosition("close", .06);

        claw2 = create("c2", ElectronicType.PSERVO_REVERSE);

        claw2.addPosition("open", 0);
        claw2.addPosition("close", .06);

        pivotServo = create("pivot", ElectronicType.PSERVO_REVERSE);

        pivotServo.addPosition("down", 0.05);

        pivotServo.addPosition("start", 0.13);
        pivotServo.addPosition("end", 0.93);
        pivotServo.addPosition("transfer", .99);

        drone = create("drone", ElectronicType.PSERVO_FORWARD);

        drone.addPosition("start",0);
        drone.addPosition("launch",0.65);









        outtakeStatus.set(DRIVING);
    }

    public void openClaw1(){ claw1.setPosition("open"); }
    public void closeClaw1(){ claw1.setPosition("close"); }

    public void openClaw2(){ claw2.setPosition("open"); }
    public void closeClaw2(){ claw2.setPosition("close"); }

    public void open(){claw2.setPosition("open"); claw1.setPosition("open");}
    public void close(){claw2.setPosition("close"); claw1.setPosition("close");}

    public void launch(){ drone.setPosition("launch"); }


    public void startPivot(){ pivotServo.setPosition("start"); }
    public void downPivot(){ pivotServo.setPosition("down"); }
    public void transferPivot(){ pivotServo.setPosition("transfer"); }



    public void endPivot(){ pivotServo.setPosition("end"); }

    public void start(){ pivotServo.setPosition("transfer"); claw2.setPosition("open"); claw1.setPosition("open"); drone.setPosition("start");}





    public Stage stageOpen1(double t){ return super.customTime(this::openClaw1, t); }
    public Stage stageClose1(double t){ return super.customTime(this::closeClaw1, t); }

    public Stage stageOpen2(double t){ return super.customTime(this::openClaw2, t); }
    public Stage stageClose2(double t){ return super.customTime(this::closeClaw2, t); }

    public Stage stageLaunch(double t){return super.customTime(this::launch, t); }

    public Stage stageEnd(double t){ return super.customTime(this::endPivot, t); }
    public Stage stageStart(double t){ return super.customTime(this::startPivot, t); }
    public Stage stageDown(double t){ return super.customTime(this::downPivot, t); }
    public Stage stageTransfer(double t){ return super.customTime(this::transferPivot, t); }








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
