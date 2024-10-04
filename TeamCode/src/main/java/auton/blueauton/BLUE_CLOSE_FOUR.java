package auton.blueauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import automodules.stage.Exit;
import autoutil.AutoFramework;
import util.Timer;

@Autonomous(name = "BLUECLOSE STAGE (4)", group = "auto", preselectTeleOp = "TerraOp")

public class BLUE_CLOSE_FOUR extends AutoFramework {
    Timer time = new Timer();
    public boolean timeout(){return time.seconds() > 14;}
    public boolean timeout2(){return time.seconds() > 25.5;}


    public Exit exitIntake(){return new Exit(this::timeout);}
    public Exit exitIntake2(){return new Exit(this::timeout2);}






    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.moveLock();
        outtake.closeClaw();
        intake.moveInit();
//        propCaseDetected = TeamProp.THIRD;
        AutoFramework auto = this;
        auto.scan(true, "blue", "left");

    }
    AutoModule ExtakeandLift = new AutoModule(

            intake.stageStart(.2),
            intake.stageInit(.2),
            lift.stageLift(1, 14.7).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))

    );
    AutoModule ReadyIntake = new AutoModule(
            outtake.stageLock(.1).attach(outtake.stageStartPivot(.1)),
            outtake.stageOpen(.3)
    ).setStartCode(() ->{}
    );
    AutoModule StackIntake = new AutoModule(


//            intake.stageStart(.2),
//            drive.moveTime(-.3,0,0,.5),
//            drive.moveTime(0,-0.02,0,.3),

            intake.moveTime(.8,.3),

            intake.moveSmart(-.6, exitIntake()).attach(drive.intakeSmart2(0.15,0,0.02, exitIntake())),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)

    ).setStartCode(()->{
//        time.reset();
            }
    );

    AutoModule StackIntakefirst2 = new AutoModule(
            intake.stageStart(.1),
            intake.moveSmart2(-.6).attach(drive.intakeSmart(0,0.15,0)),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.8,.3)

    ).setStartCode(()->{
//        time.reset();
            }
    );
    AutoModule StackIntake3 = new AutoModule(

            intake.stageStart(.1),

            intake.moveSmart(-.6, exitIntake()).attach(drive.intakeSmart2(0.08,0,-0.04, exitIntake())),
            intake.moveTime(-0.8, .4),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.5,.3)

    ).setStartCode(() -> {
//        time.reset();
    });
    AutoModule StackIntake2 = new AutoModule(



            intake.stageStart(.2),
            drive.moveTime(0,0.0,0,.5),


            intake.moveSmart(-.58, exitIntake2()).attach(drive.intakeSmart2(0.0,0.13,0.0, exitIntake2())),
            intake.moveTime(-0.8, .4),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
            intake.moveTime(.5,.3)
    ).setStartCode(() -> {
//        time.reset();
    });

    AutoModule StackIntakeNext = new AutoModule(
            drive.moveTime(0,0,-.15,.2),

            intake.moveSmart2(-.6).attach(drive.intakeSmart(0,-.1,0)),
            intake.moveTime(-0.8, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3))

    ).setStartCode(() -> {

    });


    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 5).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4))




    );

    AutoModule PreExtend1 = new AutoModule(
            lift.stageLift(1, 22).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4)),
            outtake.stageStackRotate(.1)



    );
    AutoModule PreExtend3 = new AutoModule(
            lift.stageLift(1, 22).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4))



    );
    AutoModule whitePixel = new AutoModule(
            lift.stageLift(1, 25).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.2).attach(outtake.stageEndPivot(.2))




    );

    AutoModule whitePixelFinal = new AutoModule(
            lift.stageLift(1, 25).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.2).attach(outtake.stageEndPivot(.2)),
            outtake.stageStackRotate(.1)
//            outtake.stageStackRotate(.1)




    );
    AutoModule whitePixel2 = new AutoModule(
            lift.stageLift(1, 24).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.2))




    );
    AutoModule align = new AutoModule(
            drive.strafeSmart(0,0.6,0),
            drive.driveSmart(-.25,0,0)
    );
    AutoModule lock = new AutoModule(
            intake.stageStart(.1)




    );
    AutoModule unlock = new AutoModule(
            intake.stageInit(.1)




    );
    AutoModule RemovePixels = new AutoModule(

            intake.moveTime(.8,1)
    );
    AutoModule RemovePixels2 = new AutoModule(
            intake.moveTime(0.8, .3)

    );
    AutoModule intakereset = new AutoModule(
            intake.stageInit(.1)
    );
    AutoModule open2 = new AutoModule(
            outtake.stageOpen2(.1)
    );

    AutoModule open1 = new AutoModule(
            outtake.stageOpen1(.1)
    );

    AutoModule flippy = new AutoModule(
            outtake.stageflipStackRotate(.1)
    );

    AutoModule flippy2 = new AutoModule(
            outtake.stageflipStackRotate(.1),
            lift.stageLift(1,18)

    );
    AutoModule Reset = new AutoModule(
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.1).attach(outtake.stageLock(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),

            outtake.stageStartRotate(.05),
            outtake.stageDownPivot(.3).attach(lift.stageLift(1,0))

    );

    AutoModule Reset2 = new AutoModule(
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.05).attach(outtake.stageLock(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),

            outtake.stageStartRotate(.05),
            outtake.stageDownPivot(.3).attach(lift.stageLift(1,0))

    );
    AutoModule alignCloser = new AutoModule(
            drive.drivecloseSmart(-.18,0,0)
    );

    @Override
    public void define() {
        time.reset();
        customCase(() -> {
            addWaypoint(30,-30,0);

//            addTimedhttps://youtube.com/clip/UgkxOwPF-u-odIgel4nU7PBMKa-V_hCwqRIu?si=S1pl8yl1YBQlfQpN(1.0,.5,1,40,-75,-90);


            addAutoModule(ExtakeandLift);



            addTimedSetpoint(1.0,.5,1,60,-55,-90);

            addTimedSetpoint(1.0,.5,.4,70,-57.5,-95);
            addAutoModule(alignCloser);
            addPause(.1);
            addAutoModule(Reset);
            addSegment(1,DefaultWP,80,-90,-40);
            addSegment(1,DefaultWP,60,-125,-90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-60,-120,-90);




            addSegment(.7,DefaultWP,-125,-120,-90);



            addSegment(.2,DefaultWP,-189.9,-111,-90);
            addAutoModule(lock);
            addSegment(.3,DefaultWP,-170.2,-120,-90);
            addAutoModule(unlock);

            addSegment(.3,DefaultWP,-162,-120,-90);

//
//
//
//
            addAutoModule(StackIntake3);




            addConcurrentAutoModule(RemovePixels);
            addSegment(1,DefaultWP,-140,-125,-90);
            addSegment(1,DefaultWP,0,-125,-90);


            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,30,-125,-80);

            addConcurrentAutoModule(whitePixel);

            addSegment(1,DefaultWP,60,-90,-50);

            addTimedSetpoint(1,.2,.5,60,-84,-88);


            addAutoModule(align);
            addPause(.1);

            addConcurrentAutoModule(Reset);


            addSegment(1,DefaultWP,80,-90,-40);
            addBreakpoint(()-> time.seconds() > 25.5);

            addSegment(1,DefaultWP,60,-125,-90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-60,-130,-90);




            addSegment(.7,DefaultWP,-120,-130,-90);



            addSegment(.2,DefaultWP,-189.5,-125,90);

            addAutoModule(StackIntake2);



            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,-140,-130,-90);

            addSegment(1,DefaultWP,0,-125,-90);
            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,15,-125,-80);
            addBreakpoint(()-> time.seconds() < 25.5);
            addSegment(1,DefaultWP,50,-125,-90);
            addAutoModule(PreExtend);
            addPause(.1);

            addAutoModule(Reset);
            addBreakpointReturn();

            addBreakpoint(()-> time.seconds() > 25.5);




            addConcurrentAutoModule(whitePixel2);

            addSegment(1,DefaultWP,60,-90,-50);

            addTimedSetpoint(1,.2,.5,75,-84,-88);


            addAutoModule(align);
            addPause(.1);

            addAutoModule(Reset);
            addBreakpointReturn();

            addTimedSetpoint(1.0,.5,1,90,-120,-89);

            }, () -> {

            addWaypoint(20,-80,0);
            addSegment(.3,DefaultWP, 47,-85,-91);
            addTimedSetpoint(1.0,.5,1,30,-110,-91);




            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,50,-67.5,-90);

            addTimedSetpoint(1.0,.5,.2,60,-67.5,-90    );
            addAutoModule(align);
            addPause(.1);
            addAutoModule(Reset);


            addSegment(1,DefaultWP,80,-90,-40);
            addSegment(1,DefaultWP,60,-125,-90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-60,-120,-90);




            addSegment(.7,DefaultWP,-125,-120,-90);



            addSegment(.2,DefaultWP,-189.9,-111,-90);
            addAutoModule(lock);
            addSegment(.3,DefaultWP,-170.2,-120,-90);
            addAutoModule(unlock);

            addSegment(.3,DefaultWP,-162,-120,-90);

//
//
//
//
            addAutoModule(StackIntake3);




            addConcurrentAutoModule(RemovePixels);
            addSegment(1,DefaultWP,-140,-125,-90);
            addSegment(1,DefaultWP,0,-125,-90);


            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,30,-125,-80);

            addConcurrentAutoModule(whitePixel);

            addSegment(1,DefaultWP,60,-90,-50);

            addTimedSetpoint(1,.2,.5,60,-75,-88);


            addAutoModule(align);
            addPause(.1);

            addConcurrentAutoModule(Reset);


            addSegment(1,DefaultWP,80,-90,-40);
            addBreakpoint(()-> time.seconds() > 25.5);

            addSegment(1,DefaultWP,60,-125,-90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-60,-130,-90);




            addSegment(.7,DefaultWP,-120,-130,-90);



            addSegment(.2,DefaultWP,-189.5,-125,-90);

            addAutoModule(StackIntake2);



            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,-140,-130,-90);

            addSegment(1,DefaultWP,0,-125,-90);
            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,15,-125,-80);
            addBreakpoint(()-> time.seconds() < 27);
            addSegment(1,DefaultWP,50,-125,-90);
            addAutoModule(PreExtend);

            addAutoModule(Reset);
            addBreakpointReturn();

            addBreakpoint(()-> time.seconds() > 25.5);




            addConcurrentAutoModule(whitePixel2);

            addSegment(1,DefaultWP,60,-90,-50);

            addTimedSetpoint(1,.2,.5,75,-75,-88);


            addAutoModule(align);
            addPause(.1);

            addAutoModule(Reset);
            addBreakpointReturn();

            addTimedSetpoint(1.0,.5,1,90,-120,-89);

        }, () -> {
            addWaypoint(0,-30,0);
            addWaypoint(0,-50,-20);

            addTimedSetpoint(1.0,.5,1,-11,-75,-90);


            addAutoModule(ExtakeandLift);
            addTimedSetpoint(1.0,.5,1,40,-74,-90);

            addTimedSetpoint(1.0,.5,.7,60,-80,-90);
            addAutoModule(alignCloser);
            addPause(.1);
            addAutoModule(Reset);

            addSegment(1,DefaultWP,80,-90,-40);
            addSegment(1,DefaultWP,60,-125,-90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-60,-120,-90);




            addSegment(.7,DefaultWP,-125,-120,-90);



            addSegment(.2,DefaultWP,-189.9,-111,-90);
            addAutoModule(lock);
            addSegment(.3,DefaultWP,-170.2,-120,-90);
            addAutoModule(unlock);

            addSegment(.3,DefaultWP,-162,-120,-90);

//
//
//
//
            addAutoModule(StackIntake3);




            addConcurrentAutoModule(RemovePixels);
            addSegment(1,DefaultWP,-140,-125,-90);
            addSegment(1,DefaultWP,0,-125,-90);


            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,30,-125,-80);

            addConcurrentAutoModule(whitePixel);

            addSegment(1,DefaultWP,60,-90,-50);

            addTimedSetpoint(1,.2,.5,60,-61,-88);


            addAutoModule(align);
            addPause(.1);

            addConcurrentAutoModule(Reset);


            addSegment(1,DefaultWP,80,-90,-40);
            addBreakpoint(()-> time.seconds() > 25.5);

            addSegment(1,DefaultWP,60,-125,-90);


            addConcurrentAutoModule(ReadyIntake);

            addSegment(1,DefaultWP,-60,-130,-90);




            addSegment(.7,DefaultWP,-120,-130,-90);



            addSegment(.2,DefaultWP,-189.5,-123,-90);

            addAutoModule(StackIntake2);



            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,-140,-130,-90);

            addSegment(1,DefaultWP,0,-125,-90);
            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,15,-125,-80);
            addBreakpoint(()-> time.seconds() < 25.5);
            addSegment(1,DefaultWP,50,-125,-90);
            addAutoModule(PreExtend);
            addPause(.1);

            addAutoModule(Reset);
            addBreakpointReturn();

            addBreakpoint(()-> time.seconds() > 25.5);




            addConcurrentAutoModule(whitePixel2);

            addSegment(1,DefaultWP,60,-90,-50);

            addTimedSetpoint(1,.2,.5,75,-75,-88);


            addAutoModule(align);
            addPause(.1);

            addAutoModule(Reset);
            addBreakpointReturn();

            addTimedSetpoint(1.0,.5,1,90,-120,-89);

        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }

}
