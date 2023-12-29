package robotparts.hardware;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.outtakeStatus;

public class Outtake extends RobotPart {

    public PServo armr, arml, claw;
//    public PServo forklift;

//    public boolean cycleMachine = false;
//    public boolean pauseMachine = false;
//    public boolean skipMachine = false;

    @Override
    public void init() {
        armr = create("armr", ElectronicType.PSERVO_REVERSE);
        arml = create("arml", ElectronicType.PSERVO_FORWARD);


        arml.changePosition("start", 0.1); //.21 difference
        armr.changePosition("start", 0.28);
        arml.addPosition("s", 0.09);
        armr.addPosition("s", 0.3);
//
//        arml.addPosition("startHalf", 0.38);
//        armr.addPosition("startHalf", 0.38);
        arml.addPosition("middle", 0.27);
        armr.addPosition("middle", 0.48);
//
//        arml.addPosition("endHalf", 0.71);
//        armr.addPosition("endHalf", 0.71);

        arml.changePosition("end", 0.62);
        armr.changePosition("end", 0.83);

        armr.addPosition("e", 0.1);
        arml.addPosition("e", 0.1);

        claw = create("claw", ElectronicType.PSERVO_REVERSE);



        claw.addPosition("close", .14);
        claw.addPosition("openfull", .06);
        claw.addPosition("openhalf", 0);

        outtakeStatus.set(DRIVING);
    }

    public void changeArmPosition(String name, double pos){ armr.changePosition(name, pos); arml.changePosition(name, pos); }

    public void setToTeleop(){ changeArmPosition("start", 0.06);}

    public void arm(double pos){ armr.setPosition(pos); arml.setPosition(pos); }

    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); }
    public void moveEnd(){ armr.setPosition("end"); arml.setPosition("end"); }
    public void openClawFull(){ claw.setPosition("openfull"); }
    public void openClawHalf(){ claw.setPosition("openhalf"); }
//    public void openClawCap() { claw.setPosition("cap"); }
    public void closeClaw(){ claw.setPosition("close"); }


    public void readyStart(){ armr.setPosition("startHalf"); arml.setPosition("startHalf"); }
    public void readyEnd(){ armr.setPosition("endHalf"); arml.setPosition("endHalf"); }

    public void moveMiddle(){ armr.setPosition("middle"); arml.setPosition("middle"); }

    public Stage stageReadyStart(double t){return super.customTime(this::readyStart, t);}
    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageEnd(double t){ return super.customTime(this::moveEnd, t); }
    public Stage stageOpen(double t){ return super.customTime(this::openClawFull, t); }
    public Stage stageOpenHalf(double t){ return super.customTime(this::openClawHalf, t); }
    public Stage stageClose(double t){ return super.customTime(this::closeClaw, t); }

    public Stage stageCloseAfter(double t){ return super.customTimeAfter(this::closeClaw, t);}

    public Stage stageEndAfter(double t){ return super.customTimeAfter(this::moveEnd, t); }
    public Stage stageOpenAfter(double t){ return super.customTimeAfter(this::openClawHalf, t); }

    public Stage stageMiddle(double t){ return super.customTime(this::moveMiddle, t);}
    public Stage stageReadyEnd(double t){ return super.customTime(this::readyEnd, t); }
    public Stage stageReadyEndAfter(double t){ return super.customTimeAfter(this::readyEnd, t); }
    public Stage stageStartAfter(double t){ return super.customTimeAfter(this::moveStart, t); }
    public Stage stageReadyStartAfter(double t){ return super.customTimeAfter(this::readyStart, t); }

    public Stage stage(double pos, double t){ return super.customTime(() -> { arm(pos);},  t); }

    public Stage stageWithFlip(double pos, double t){ return super.customTime(() -> { arm(pos);},  t); }
    public Stage stageAfter(double pos, double t){ return super.customTimeAfter(() -> { arm(pos);},  t); }
    public Stage stageWithFlipAfter(double pos, double t){ return super.customTimeAfter(() -> { arm(pos);},  t); }

    public Stage stageEndContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "end", t); }

    public Stage stageReadyEndContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "endHalf", t); }



    public Stage stageStartContinuous(double t){ return super.customContinuousTime(() -> armr, () -> arml, "e", t); }





    public Stage stageStartContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "start", t); }

    public Stage stageReadyEndContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "endHalf", t); }




    public Stage stageEndContinuousWithFlip(double t, double flipT){ return super.customContinuousTime(() -> armr, () -> arml, "end", t); }



    public Stage stageMiddleWithoutFlip(double t){ return super.customTime(() -> {armr.setPosition("middle"); arml.setPosition("middle");}, t);}
    public Stage stageReadyEndWithoutFlip(double t){ return super.customTime(() -> {armr.setPosition("endHalf"); arml.setPosition("endHalf");}, t); }


    public double getArmPos(){ return armr.getPosition(); }
    public boolean isClawClosed(){ return claw.getPosition() > 0.2; }

    public Stage stageBack(double start){
        return super.customTime(new StageBuilderTime(this)
                .addSubStage(start, () -> {})
                .addSubStage(0.1, () -> arm(0.0))

        );
    }


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

    public void readyStartCond(){
        if(armr.getPosition() < 0.4){
            readyStart();
        }
    }

    public Stage stageReadyStartCond(double time){
        return super.customTime(this::readyStartCond, time);
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
