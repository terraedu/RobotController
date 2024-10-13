package automodules;

import automodules.stage.Stage;
import auton.Auto;
import robot.RobotUser;
import robotparts.RobotPart;
import robotparts.hardware.Intake;

import static global.Modes.*;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.Drive.SUPERSLOW;
import static global.Modes.Height.GROUND;
//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;
import static global.Modes.Height.currentHeight;
import static global.Modes.Height.five;
import static global.Modes.Height.four;
import static global.Modes.Height.one;
import static global.Modes.Height.seven;
import static global.Modes.Height.six;
import static global.Modes.Height.three;
import static global.Modes.Height.two;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.INTAKING;
import static global.Modes.OuttakeStatus.PLACING;
import static global.Modes.OuttakeStatus.PLACING2;

import org.firstinspires.ftc.robotcore.internal.camera.libuvc.constants.UvcAutoExposureMode;


public interface AutoModuleUser extends RobotUser {

    /**
     * Forward
     */

    AutoModule L_BUMPER = new AutoModule(
        outtake.stageOpen1(.1)
    ).setStartCode(() ->{

    });

    AutoModule R_BUMPER = new AutoModule(
        outtake.stageOpen2(.1)
    ).setStartCode(() ->{

    });

    AutoModule chubramani = new AutoModule(
            intake.chubramani(.5)
    ).setStartCode(() ->{

    });


    AutoModule L_TRIGGER = new AutoModule(
        outtake.stageOpen(.1),
            outtake.stageUp(.1).attach(outtake.stageHiPivot(.1)),
        outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.05).attach(outtake.stageLock(.1)),
            outtake.stageDownPivot(.1),
            outtake.stageStart(.1).attach(lift.stageLift(1,0)),
            outtake.stageDownPivot(.1).attach(outtake.stageLock(.1))

    ).setStartCode(() ->{
        heightMode.set(GROUND);
        driveMode.set(SLOW);
        outtakeStatus.set(DRIVING);


    });

        AutoModule R_TRIGGER = new AutoModule(
                outtake.stageStart(.1),
                outtake.stageOpen(.4),
                outtake.stageLock(.5).attach(outtake.stageDownPivot(.5)),
                intake.stageStart(.4),
                intake.moveSmart2(-.65),

                intake.moveTime(-.35,.5),
                outtake.stageClose(.1).attach(outtake.stageBetterLock(.2)),
                RobotPart.pause(.3),

                intake.moveTime(1,.2),
                intake.stageInit(.2)


        ).setStartCode(() -> {
            driveMode.set(SUPERSLOW);

        });

    AutoModule A_BUTTON = new AutoModule(
            lift.stageLift(1, 35)

            ).setStartCode(() ->{
        heightMode.set(MEDIUM);


    });

    AutoModule IntakeMid = new AutoModule(
            outtake.stageLock(.3).attach(outtake.stageDownPivot(.5)),
            intake.stageMiddle(.2).attach(outtake.stageOpen(.1)),
            intake.moveSmart2(-.45),
            outtake.stageClose(.5),
            intake.moveTime(1,.2),
            intake.stageInit(.2)

    ).setStartCode(() -> {
        driveMode.set(SUPERSLOW);

    });


    AutoModule IntakeMider = new AutoModule(
            outtake.stageLock(.3).attach(outtake.stageDownPivot(.5)),
            intake.stageMiddle(.2).attach(outtake.stageOpen(.1)),
            intake.moveSmart2(-1),
            outtake.stageClose(.5),
            intake.moveTime(1,.2),
            intake.stageInit(.2)

    ).setStartCode(() -> {
        driveMode.set(SUPERSLOW);

    });


//    AutoModule Y_BUTTON = new AutoModule(
//            lift.stageLift(1, adjustHeight)
//
//    ).setStartCode(() ->{
//
//
//    });

    AutoModule X_BUTTON = new AutoModule(
//            outtake.stageMiddle(.2),
            outtake.stageClose(.1),
            lift.stageLift(1, 15).attach(outtake.stageThruPivot(.2)),

            outtake.stageEnd(.2).attach(outtake.stageTransferPivot(.2)),
            outtake.stageEndPivot(.2).attach(outtake.stageStartRotate(.2))


    ).setStartCode(() ->{
        heightMode.set(one);
        outtakeStatus.set(PLACING);
        driveMode.set(SUPERSLOW);

    });


    AutoModule joyoi = new AutoModule(
            outtake.stageUp(.1).attach(outtake.stageHiPivot(.1)),
            outtake.stageTransferPivot(.2).attach(outtake.stageMiddle(.2)),
            outtake.stageStartRotate(.05).attach(outtake.stageLock(.1)),
            outtake.stageDownPivot(.1),
            outtake.stageStart(.1).attach(lift.stageLift(1,0)),
            outtake.stageDownPivot(.1).attach(outtake.stageLock(.1))
    ).setStartCode(() ->{
        heightMode.set(GROUND);
        driveMode.set(SLOW);
        outtakeStatus.set(DRIVING);


    });

    AutoModule UP_DPAD = new AutoModule(
            outtake.stageStackRotate(.2)

    ).setStartCode(() ->{

    });

    AutoModule DOWN_DPAD = new AutoModule(
        outtake.stageflipStackRotate(.2)
    ).setStartCode(() ->{

    });

    AutoModule LEFT_DPAD = new AutoModule(
        outtake.stageLeftRotate(.2)

    ).setStartCode(() ->{

    });

    AutoModule RIGHT_DPAD = new AutoModule(
            outtake.stageTransferRotate(.2)


    ).setStartCode(() ->{

    });

//
    AutoModule CancelIntake = new AutoModule(
        outtake.stageClose(.5),
        intake.moveTime(.7,.2),

        intake.moveTime(0,.1).attach(intake.stageInit(.1))



).setStartCode(() ->{
    driveMode.set(SLOW);



    });

    AutoModule levelone = new AutoModule(
                lift.stageLift(1, 20)

    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);
        heightMode.setTo(one);



    });

    AutoModule leveltwo = new AutoModule(
            lift.stageLift(1, 25)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING);
        heightMode.setTo(two);


//
    });

    AutoModule levelthree = new AutoModule(
            lift.stageLift(1, 30)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);
        heightMode.setTo(three);


//
    });

    AutoModule levelfour = new AutoModule(
            lift.stageLift(1, 37)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING);
        heightMode.setTo(four);

//
    });

    AutoModule levelfive = new AutoModule(
            lift.stageLift(1, 45)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);
        heightMode.setTo(five);


//
    });
    AutoModule levelsix = new AutoModule(

//
    ).setStartCode(() ->{



//
    });

    AutoModule levelseven = new AutoModule(
            lift.stageLift(1, 57)
//
    ).setStartCode(() ->{
        outtakeStatus.set(PLACING2);
        heightMode.setTo(seven);


//
    });




    AutoModule reset = new AutoModule(
            lift.stageLift(1, 0).attach(outtake.stageFarmDown(0.2)).attach(outtake.stageWristDown(0.3)).attach(outtake.stageClawOpen(0.1)).
                    attach(intake.resetFarm(0.2)).attach(intake.resetClaw(0.2)).attach(intake.closeClaw(0.2))
    );

    AutoModule basketlow = new AutoModule(
            lift.stageLift(1, 25).attach(outtake.stageFarmFlip(0.3)).attach(outtake.stageWristFlip(0.5)),
            outtake.stageClawOpen(0.2)
    );

   AutoModule baskethigh = new AutoModule(
           lift.stageLift(1, 50).attach(outtake.stageFarmFlip(0.2)).attach(outtake.stageWristFlip(0.2)),
           outtake.stageClawOpen(0.2)
   );

    AutoModule clipHangLow = new AutoModule(
            outtake.stageFarmFlip(0.2).attach(outtake.stageWristFlip(0.3)).attach(lift.stageLift(1, 20)),
            lift.stageLift(0.1, 23)
    );

    AutoModule clipHangHigh = new AutoModule(
            outtake.stageFarmFlip(0.2).attach(outtake.stageWristFlip(0.3)).attach(lift.stageLift(1, 35)),
            lift.stageLift(0.1, 38)
    );

    AutoModule getSample = new AutoModule(
            intake.extendFarm(0.2).attach(intake.pivotClaw(0.2)).attach(intake.openClaw(0.2)).attach(intake.runIntake(3))
    );

    AutoModule placeSample = new AutoModule(
            outtake.stageFarmFlip(0.4).attach(outtake.stageWristFlip(0.3)),
            outtake.stageClawOpen(0.1)
    );






//    AutoModule Extake = new AutoModule(
//
//
//            intake.moveTime(.8, 1)
//
//
//    ).setStartCode(() ->
//    {
//
//
//
//    });
//
    AutoModule HangReady = new AutoModule(
        lift.stageLift(1, 37)
    ).setStartCode(() ->{

    });

    AutoModule HangStart = new AutoModule(
            lift.stageLift(1, 15)
    ).setStartCode(() ->{

    });
    AutoModule Hang = new AutoModule(
            outtake.stageStartPivot(.1),
            lift.stageLift(.5,heightMode.getValue(GROUND))
    ).setStartCode(() ->{

    });
//

//    AutoModule PlaceReady = new AutoModule(
//            outtake.stageOpenHalf(.2).attach(outtake.stageMiddle(.2)),
//            outtake.stageClose(.2)
//
//
//    ).setStartCode(() ->
//    {
//
//
//        intake.stageStart(.2);
//
//
//    });
    //
//    AutoModule ManualClose = new AutoModule(
//            outtake.stageClose(1)
//    ).setStartCode(() -> {
//
//
//
//
//
//
//    });
//    AutoModule ManualOpenFull = new AutoModule(
//            outtake.stageOpen(1)
//    ).setStartCode(() -> {
//
//
//
//
//
//    });
//
//    AutoModule ManualOpenHalf = new AutoModule(
//            outtake.stageOpenHalf(1)
//    ).setStartCode(() -> {
//
//
//
//
//
//    });
//    AutoModule PlaceAll = new AutoModule(
//            outtake.stageOpen(.2),
//            RobotPart.pause(.1),
//            outtake.stageMiddle(.2).attach(lift.stageLift(.8, heightMode.getValue(GROUND))),
//            outtake.stageStart(.2).attach(outtake.stageStartClaw(.2))
//
//    ).setStartCode(() -> {
//        driveMode.set(FAST);
//        heightMode.set(GROUND);
//
//
//    });

//    AutoModule PlaceOne = new AutoModule(
//            outtake.stageOpenHalf(.01)
//
//    ).setStartCode(() -> {
//
//
//
//    });


//    AutoModule PlaceLow = new AutoModule(
//            lift.stageLift(1, 4),
//            RobotPart.pause(2),
//            outtake.stageTransferRotate(.2),
//            lift.stageLift(1, 20),
//            outtake.stageEnd(.3).attach(outtake.stageTransferPivot(.3)),
//            outtake.stageStartRotate(.2)
//
//
//
//            ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(LOW);
//        outtakeStatus.set(PLACING);
//
//
//    });
//    AutoModule PlaceMid = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1.0, heightMode.getValue(MIDDLE)).attach(outtake.stageMiddler(.5)),
//            outtake.stageEnd(.1).attach(outtake.stageClose(.1))
//
//    ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(MIDDLE);
//        outtakeStatus.set(PLACING);
//
//
//
//    });

//    AutoModule PlaceHigh = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1.0, heightMode.getValue(HIGH)).attach(outtake.stageEndContinuousWithFlip(.5, 0))
//    ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(HIGH);
//    });
//
//    AutoModule ResetLift = new AutoModule(lift.moveTime(-0.3, 0.5),  lift.resetLift()).setStartCode(() -> {
//        lift.ground = false;
//        outtakeStatus.set(DRIVING);
//    });
//
}


