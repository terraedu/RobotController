package robotparts.hardware;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.outtakeStatus;

import java.util.EventListener;

public class Outtake extends RobotPart {

    public PServo pivot,R_CLAW,L_CLAW, launch;
    //    public PServo forklift;

//    public boolean cycleMachine = false;
//    public boolean pauseMachine = false;
//    public boolean skipMachine = false;

    @Override
    public void init() {
        pivot = create("pivot", ElectronicType.PSERVO_REVERSE);
        R_CLAW = create("Rclaw", ElectronicType.PSERVO_FORWARD);
        launch = create("launch", ElectronicType.PSERVO_REVERSE);
        L_CLAW = create("Lclaw", ElectronicType.PSERVO_FORWARD);

        pivot.changePosition("start", 0.05);
        L_CLAW.changePosition("start", 0);
        launch.changePosition("start", 0);
        R_CLAW.changePosition("start",0);
        L_CLAW.addPosition("close", 0.2);
        pivot.addPosition("place", 1);
        launch.addPosition("release", 0.7);
       R_CLAW.addPosition("close",0.2);
//        arml.addPosition("s", 0.09);
//        armr.addPosition("s", 0.3);
//
//        arml.addPosition("startHalf", 0.38);
//        armr.addPosition("startHalf", 0.38);
//
//        arml.addPosition("middle", 0.28);
//        armr.addPosition("middle", 0.49);
//
//        arml.addPosition("endHalf", 0.71);
//        armr.addPosition("endHalf", 0.71);
//
//        arml.changePosition("end", 0.54);
//        armr.changePosition("end", 0.75);
//
//        armr.addPosition("e", 0.1);
//        arml.addPosition("e", 0.1);


        outtakeStatus.set(DRIVING);
    }

//    public void changeArmPosition(String name, double pos){ armr.changePosition(name, pos); arml.changePosition(name, pos); }

//    public void setToTeleop(){ changeArmPosition("start", 0.06);}

//    public void arm(double pos){ armr.setPosition(pos); arml.setPosition(pos); }


    public void moveStart(){
        pivot.setPosition("start");
        L_CLAW.setPosition("start");
        R_CLAW.setPosition("start");
        launch.setPosition("start");
    }
    public void moveEnd(){
        pivot.setPosition("end");
        L_CLAW.setPosition("end");
        R_CLAW.setPosition("end");
    }
    public void openClawFull(){
        L_CLAW.setPosition("openfull");
        R_CLAW.setPosition("openfull");
    }
    public void openClawHalf(){
        L_CLAW.setPosition("openhalf");
        R_CLAW.setPosition("openhalf");
    }
//    public void openClawCap() { claw.setPosition("cap"); }
    public void
    closeClaw(){
        L_CLAW.setPosition("close");
        R_CLAW.setPosition("close");
    }
    public void openClaw(){
        L_CLAW.setPosition("start");
        R_CLAW.setPosition("start");
    }
    public void placePivot() {
        pivot.setPosition("place");
    }
    public void grabPivot() {
        pivot.setPosition("start");
    }
    public void release() {
        launch.setPosition("release");
    }

//    public void readyStart(){ pivot.setPosition("startHalf"); claw.setPosition("startHalf"); }
//    public void readyEnd(){ armr.setPosition("endHalf"); arml.setPosition("endHalf"); }

    public void moveMiddle(){ pivot.setPosition("middle"); claw.setPosition("middle"); }

//    public Stage stageReadyStart(double t){return super.customTime(this::readyStart, t);}
    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageEnd(double t){ return super.customTime(this::moveEnd, t); }
    public Stage stageOpen(double t){ return super.customTime(this::openClawFull, t); }
    public Stage stageOpenHalf(double t){ return super.customTime(this::openClawHalf, t); }
    public Stage stageClose(double t){ return super.customTime(this::closeClaw, t); }

    public Stage stageCloseAfter(double t){ return super.customTimeAfter(this::closeClaw, t);}

    public Stage stageEndAfter(double t){ return super.customTimeAfter(this::moveEnd, t); }
    public Stage stageOpenAfter(double t){ return super.customTimeAfter(this::openClawHalf, t); }

    public Stage stageMiddle(double t){ return super.customTime(this::moveMiddle, t);}
    public Stage stageCloseClaw(double t) {return super.customTime(this::closeClaw, t);}
    public Stage stageOpenClaw(double t) { return super.customTime(this::openClaw, t);}
    public Stage stageGrabPivot(double t) { return super.customTime(this::grabPivot, t);}
    public Stage stageDrivePivot(double t) { return super.customTime(this::placePivot, t);}
    public Stage stageRelease(double t) { return super.customTime(this::release, t);}
//    public Stage stageReadyEnd(double t){ return super.customTime(this::readyEnd, t); }
//    public Stage stageReadyEndAfter(double t){ return super.customTimeAfter(this::readyEnd, t); }
//    public Stage stageStartAfter(double t){ return super.customTimeAfter(this::moveStart, t); }
//    public Stage stageReadyStartAfter(double t){ return super.customTimeAfter(this::readyStart, t); }
//
//    public Stage stage(double pos, double t){ return super.customTime(() -> { arm(pos);},  t); }
//
//    public Stage stageWithFlip(double pos, double t){ return super.customTime(() -> { arm(pos);},  t); }
//    public Stage stageAfter(double pos, double t){ return super.customTimeAfter(() -> { arm(pos);},  t); }
//    public Stage stageWithFlipAfter(double pos, double t){ return super.customTimeAfter(() -> { arm(pos);},  t); }
//
//    public Stage stageEndContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "end", t); }

//    public Stage stageReadyEndContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "endHalf", t); }
//
//
//
//    public Stage stageStartContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "e", t); }





//    public Stage stageStartContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "start", t); }
//
//    public Stage stageReadyEndContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "endHalf", t); }
//
//
//
//
//    public Stage stageEndContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "end", t); }



//    public Stage stageMiddleWithoutFlip(double t){ return super.customTime(() -> {armr.setPosition("middle"); arml.setPosition("middle");}, t);}
//    public Stage stageReadyEndWithoutFlip(double t){ return super.customTime(() -> {armr.setPosition("endHalf"); arml.setPosition("endHalf");}, t); }
//
//
//    public double getArmPos(){ return armr.getPosition(); }
//    public boolean isClawClosed(){ return claw.getPosition() > 0.2; }

//    public Stage stageBack(double start){
//        return super.customTime(new StageBuilderTime(this)
//                .addSubStage(start, () -> {})
//                .addSubStage(0.1, () -> arm(0.0))
//
//        );
//    }


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




    public Stage stageStartAndSignal(){
        return super.customTime(new StageBuilderTime(this)
                .addSubStage(0.1, this::startReadySignal)
                .addSubStage(0.2, this::moveStart)
                .addSubStage(0.1, this::endSignal)
                .addSubStage(0.1, this::startSignal)
        );
    }


    public Stage stageStartAndSignal2(){
        return super.customTime(new StageBuilderTime(this)
                .addSubStage(0.15, this::startReadySignal)
                .addSubStage(0.2, this::moveStart)
                .addSubStage(0.1, this::endSignal)
                .addSubStage(0.1, this::startSignal)
        );
    }

//    public void readyStartCond(){
//        if(armr.getPosition() < 0.4){
//            readyStart();
//        }
//    }

//    public Stage stageReadyStartCond(double time){
//        return super.customTime(this::readyStartCond, time);
//    }





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
