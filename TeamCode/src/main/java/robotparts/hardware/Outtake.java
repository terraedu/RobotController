package robotparts.hardware;

import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.PLACING;
import static global.Modes.outtakeStatus;

import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PServo;

public class Outtake extends RobotPart {

    public PServo armr, arml, claw1, claw2, pivot, rotate;


    @Override
    public void init() {
        armr = create("armr", ElectronicType.PSERVO_FORWARD);
        arml = create("arml", ElectronicType.PSERVO_REVERSE);


        arml.changePosition("start", 0.1);
        armr.changePosition("start", 0.12);


        arml.changePosition("lock", 0.015);
        armr.changePosition("lock", 0.055);

        arml.changePosition("betterlock", 0.0);
        armr.changePosition("betterlock", 0.04);


        arml.changePosition("middle", 0.11);
        armr.changePosition("middle", 0.13);

        arml.changePosition("end", 0.7);
        armr.changePosition("end", 0.72);

        arml.changePosition("uphigh", 0.56);
        armr.changePosition("uphigh", 0.58);





        claw1 = create("claw1", ElectronicType.PSERVO_REVERSE);



        claw1.addPosition("close", .7);
        claw1.addPosition("open", 0.25);

        claw2 = create("claw2", ElectronicType.PSERVO_FORWARD);



        claw2.addPosition("close", .7);
        claw2.addPosition("open", 0.25);


        pivot = create("pivot", ElectronicType.PSERVO_REVERSE);



        pivot.addPosition("start", 0.19); //0.08
        pivot.addPosition("makeitthru", 0.31);
        pivot.addPosition("makeitdown", 0.26);



        pivot.addPosition("transfer", 0.29);
        pivot.addPosition("end",0.7);
        pivot.addPosition("hi",0.76);


        rotate = create("rotate", ElectronicType.PSERVO_FORWARD);



        rotate.addPosition("start", .445);
        rotate.addPosition("transfer", .20);
        rotate.addPosition("angleleft", .69);
        rotate.addPosition("stack", 0.079);
        rotate.addPosition("flipstack", 0.79);










        outtakeStatus.set(DRIVING);
    }

    public void changeArmPosition(String name, double pos){ armr.changePosition(name, pos); arml.changePosition(name, pos); }





    public void arm(double pos){ armr.setPosition(pos); arml.setPosition(pos); }

    public void moveStart(){ armr.setPosition("start"); arml.setPosition("start"); rotate.setPosition("start"); pivot.setPosition("start"); claw1.setPosition("open"); claw2.setPosition("open");}
    public void moveStarttest(){ pivot.setPosition("start");   }

    public void moveEnd(){ armr.setPosition("end"); arml.setPosition("end"); }
    public void moveUp(){ armr.setPosition("uphigh"); arml.setPosition("uphigh"); }



    public void moveLock(){ armr.setPosition("lock"); arml.setPosition("lock");}
    public void moveBetterLock(){ armr.setPosition("betterlock"); arml.setPosition("betterlock");}

    public void moveEndPivot(){ pivot.setPosition("end");}
    public void moveDownPivot(){ pivot.setPosition("makeitdown");}

    public void moveThruPivot(){ pivot.setPosition("makeitthru");}
    public void moveHiPivot(){ pivot.setPosition("hi");}


    public void moveTransferPivot(){ pivot.setPosition("transfer");}
    public void moveStartPivot(){ pivot.setPosition("start");}

    public void moveTransferRotate(){ rotate.setPosition("transfer");}
    public void moveLeftRotate(){ rotate.setPosition("angleleft");}
    public void moveStackRotate(){ rotate.setPosition("stack");}
    public void moveflipStackRotate(){ rotate.setPosition("flipstack");}





    public void moveStartRotate(){ rotate.setPosition("start");}


    public void moveEndAuto(){ armr.setPosition("e"); arml.setPosition("e"); }

    public void moveEnderAuto(){ armr.setPosition("ee"); arml.setPosition("ee"); }


    public void openClaw(){ claw1.setPosition("open"); claw2.setPosition("open"); }
    public void openClaw1(){ claw1.setPosition("open");}
    public void openClaw2(){ claw2.setPosition("open");}


    public void closeClaw(){ claw1.setPosition("close"); claw2.setPosition("close"); }


    public void readyStart(){ armr.setPosition("startHalf"); arml.setPosition("startHalf"); }
    public void readyEnd(){ armr.setPosition("endHalf"); arml.setPosition("endHalf"); }

    public void moveMiddle(){ armr.setPosition("middle"); arml.setPosition("middle"); }

    public void moveMiddler(){ armr.setPosition("middler"); arml.setPosition("middler"); }






    public Stage stageReadyStart(double t){return super.customTime(this::readyStart, t);}
    public Stage stageStart(double t){ return super.customTime(this::moveStart, t); }
    public Stage stageEnd(double t){ return super.customTime(this::moveEnd, t); }
    public Stage stageBetterLock(double t){ return super.customTime(this::moveBetterLock, t); }

    public Stage stageUp(double t){ return super.customTime(this::moveUp, t); }


    public Stage stageTransferPivot(double t) {return super.customTime(this::moveTransferPivot, t);}
    public Stage stageStartPivot(double t) {return super.customTime(this::moveStartPivot, t);}

    public Stage stageThruPivot(double t) {return super.customTime(this::moveThruPivot, t);}
    public Stage stageDownPivot(double t) {return super.customTime(this::moveDownPivot, t);}
    public Stage stageHiPivot(double t) {return super.customTime(this::moveHiPivot, t);}




    public Stage stageEndPivot(double t) {return super.customTime(this::moveEndPivot, t);}

    public Stage stageTransferRotate(double t) {return super.customTime(this::moveTransferRotate, t);}
    public Stage stageStartRotate(double t) {return super.customTime(this::moveStartRotate, t);}
    public Stage stageStackRotate(double t) {return super.customTime(this::moveStackRotate, t);}
    public Stage stageflipStackRotate(double t) {return super.customTime(this::moveflipStackRotate, t);}

    public Stage stageLeftRotate(double t) {return super.customTime(this::moveLeftRotate, t);}




    public Stage stageEndAuto(double t){ return super.customTime(this::moveEndAuto, t); }
    public Stage stageEnderAuto(double t){ return super.customTime(this::moveEnderAuto, t); }


    public Stage stageOpen(double t){
        return super.customTime(this::openClaw, t);
    }
    public Stage stageClose(double t){ return super.customTime(this::closeClaw, t); }
    public Stage stageOpen1(double t){ return super.customTime(this::openClaw1, t); }
    public Stage stageOpen2(double t){ return super.customTime(this::openClaw2, t); }



    public Stage stageLock(double t){ return super.customTime(this::moveLock, t);}

//
//    public Stage stageCloseAfter(double t){ return super.customTimeAfter(this::closeClaw, t);}
//
//    public Stage stageEndAfter(double t){ return super.customTimeAfter(this::moveEnd, t); }
//    public Stage stageOpenAfter(double t){ return super.customTimeAfter(this::openClawHalf, t); }

    public Stage stageMiddle(double t){ return super.customTime(this::moveMiddle, t);}
//    public Stage stageMiddler(double t){ return super.customTime(this::moveMiddler, t);}

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















}

