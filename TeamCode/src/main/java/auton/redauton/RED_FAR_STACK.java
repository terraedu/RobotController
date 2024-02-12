package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name = "REDFAR STACK", group = "auto", preselectTeleOp = "TerraOp")
public class RED_FAR_STACK extends AutoFramework {


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
        auto.scan(true, "red", "left");

    }
    AutoModule Extake = new AutoModule(
            intake.stageStart(.2),
            RobotPart.pause(.2),
            intake.stageInit(.2)

    );



    AutoModule align = new AutoModule(
            drive.strafeSmart(0,-0.6,0),
//            drive.turnRightSmart(0, 0, .05),
//            drive.turnLeftSmart(0, 0, -.05),

            drive.driveSmart(-.23 ,0,0)
    );

    AutoModule ReadyIntake = new AutoModule(
            outtake.stageLock(.1).attach(outtake.stageStartPivot(.1)),
            intake.stageStart(.1).attach(outtake.stageOpen(.3))
    );
    AutoModule StackIntake = new AutoModule(


            intake.moveSmart(-.7).attach(drive.intakeSmart(0 ,.15,0))
//            intake.moveTime(-0.7, .2),
//            outtake.stageClose(.3).attach(outtake.stageBetterLock(.2))
    ).setStartCode(() -> {

    });
    AutoModule StackIntake2 = new AutoModule(


            intake.moveSmart(-.65).attach(drive.intakeSmart(0,-.2,-0.02))

    ).setStartCode(() -> {

    });

    AutoModule StackIntake3 = new AutoModule(


            intake.moveSmart(-.7).attach(drive.intakeSmart(0,-.17,-0.02))
//            intake.moveTime(-0.5, .2),
//            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3))
    ).setStartCode(() -> {

    });
    AutoModule Drop = new AutoModule(
            outtake.stageEnd(.2).attach(outtake.stageTransferPivot(.2)),

            outtake.stageEndPivot(.2).attach(outtake.stageStackRotate(.2))
    );

    AutoModule PreExtend = new AutoModule(
            lift.stageLift(1, 24).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.4))



    );
    AutoModule whitePixel = new AutoModule(
            lift.stageLift(1, 20).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.5).attach(outtake.stageEndPivot(.2)),
            outtake.stageStackRotate(.1)




    );

    AutoModule whitePixelFinal = new AutoModule(
            lift.stageLift(1, 25).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.2).attach(outtake.stageEndPivot(.2)),
            outtake.stageStackRotate(.1)
//            outtake.stageStackRotate(.1)




    );
    AutoModule RemovePixels = new AutoModule(
            intake.moveTime(-0.53, .2),
            outtake.stageClose(.3).attach(outtake.stageBetterLock(.3)),
           intake.moveTime(.5,.3)
    );
    AutoModule intakereset = new AutoModule(
            intake.stageInit(.1)
    );

    AutoModule Reset = new AutoModule(
            outtake.stageOpen(.1),
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.05).attach(outtake.stageLock(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageStart(.2)),

            outtake.stageStartRotate(.05),
            outtake.stageDownPivot(.3).attach(lift.stageLift(1,0))

    );
    //TODO MAKE DISTANCE SENSOR ADJUST Y COORDINATE
    @Override
    public void define() {
        customCase(() -> {
            addPause(19);
            addTimedSetpoint(1.0,.5,1,-3,-45,0);

            addTimedSetpoint(1.0,.5,1,-17.5,-70,90);
            addAutoModule(Extake);
//            addWaypoint(-5,-20,0);
            addTimedSetpoint(1.0,1,.8,-5,-130,90);
            addWaypoint(-155, -130,90);
            addAutoModule(PreExtend);
            addWaypoint(-180, -130,90);
            addWaypoint(-180,-95.5,90);
            addTimedSetpoint(1.0,1,2,-210,-95.5,91);

            addAutoModule(align);

            addAutoModule(Reset);












        }, () -> {
            addPause(21);
            addWaypoint(30,-30,0);

            addTimedSetpoint(1.0,.5,1,30,-100,-95);


            addAutoModule(Extake);
            addWaypoint(30, -65,-90);

            addWaypoint(0, -65,90);
            addWaypoint(-150, -68,93);
            addConcurrentAutoModule(PreExtend);
            addTimedSetpoint(1,1,.8,-180, -82,91);

            addAutoModule(align);

            addAutoModule(Reset);




        }, () -> {
            addWaypoint(0,-30,0);
            addWaypoint(0,-35,-20);

            addTimedSetpoint(1.0,4,.5,-5,-60,-95);

            addAutoModule(Extake);
            addWaypoint(10,-60,-95);
            addWaypoint(0,-140,90);


            addConcurrentAutoModule(ReadyIntake);

            addTimedSetpoint(1,4,.8,24,-113.5,91);
//            addPause(1.2);

            addAutoModule(StackIntake);
            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,0,-120,90);
            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,-145,-130,80);

            addConcurrentAutoModule(PreExtend);



            addSegment(1,DefaultWP,-180,-90,30);



            addSegment(1,DefaultWP,-200,-35,75);




            addTimedSetpoint(1.0,.5,.5,-205,-35,75);

            addAutoModule(align);
            addPause(.2);


            addConcurrentAutoModule(Reset);

            //GOES FOR NEXT CYCLE
            addSegment(1,DefaultWP,-219,-30,90);


            addSegment(1,DefaultWP,-175,-90,40);
            addSegment(1,DefaultWP,-120,-115,90);


            addConcurrentAutoModule(ReadyIntake);
            addSegment(.7,DefaultWP,-45,-120,90);



            addSegment(.2,DefaultWP,35,-142,90);


            addAutoModule(StackIntake2);
            addConcurrentAutoModule(RemovePixels);
//            addPause(1.5);


            addSegment(1,DefaultWP,0,-120,90);

            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,-136,-125,80);

            addConcurrentAutoModule(whitePixel);

            addSegment(1,DefaultWP,-170,-90,50);
            addSegment(1,DefaultWP,-210,-65,70);

            addTimedSetpoint(1,.2,.5,-210,-65,70);


            addAutoModule(align);
            addPause(.2);
            addConcurrentAutoModule(Reset);
            addSegment(1,DefaultWP,-170,-90,30);
            addSegment(1,DefaultWP,-120,-125,90);
            addConcurrentAutoModule(ReadyIntake);

            addSegment(.7,DefaultWP,-45,-115,90);



            addSegment(.3,DefaultWP,33,-140,90);




            addAutoModule(StackIntake3);
            addConcurrentAutoModule(RemovePixels);

            addSegment(1,DefaultWP,0,-120,90);
            addSegment(1,DefaultWP,-100,-120,90);


            addConcurrentAutoModule(intakereset);

            addSegment(1,DefaultWP,-136,-125,80);

            addConcurrentAutoModule(whitePixelFinal);

            addSegment(1,DefaultWP,-170,-90,50);
            addSegment(1,DefaultWP,-210,-65,70);

            addTimedSetpoint(1,.2,.5,-210,-65,70);

            addAutoModule(align);

            addAutoModule(Reset);









        });
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}