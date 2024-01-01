package automodules;

import automodules.stage.Stage;
import automodules.stage.Stop;
import auton.Auto;
import robot.RobotUser;
import robotparts.RobotPart;
import teleutil.independent.Independent;
import teleutil.independent.Machine;

import static global.Modes.*;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.Height.GROUND;
import static global.Modes.Height.HIGH;
import static global.Modes.Height.LOW;
import static global.Modes.Height.MIDDLE;
import static global.Modes.OuttakeStatus.DRIVING;
import static global.Modes.OuttakeStatus.PLACING;


public interface AutoModuleUser extends RobotUser {
//
//    /**
//     * Forward
//     */
//
    AutoModule PLACELOW = new AutoModule(
            lift.stageArm(1,49)
    ).setStartCode(() ->{});
//    AutoModule Intake = new AutoModule(
//
//            outtake.stageOpen(.2)
//
//
//    ).setStartCode(() ->
//    {
//        outtake.stageStart(.2);
//
//
//    });
//
//
//    AutoModule PlaceReady = new AutoModule(
//            outtake.stageOpenHalf(.2).attach(outtake.stageMiddle(.2)),
//            outtake.stageClose(.2)
//    ).setStartCode(() ->
//    {
//
//
//
//
//    });
//    //
////    AutoModule ManualClose = new AutoModule(
////            outtake.stageClose(1)
////    ).setStartCode(() -> {
////
////
////
////
////
////
////    });
////    AutoModule ManualOpenFull = new AutoModule(
////            outtake.stageOpen(1)
////    ).setStartCode(() -> {
////
////
////
////
////
////    });
////
////    AutoModule ManualOpenHalf = new AutoModule(
////            outtake.stageOpenHalf(1)
////    ).setStartCode(() -> {
////
////
////
////
////
////    });
//    AutoModule PlaceAll = new AutoModule(
//            outtake.stageOpen(.2),
//            RobotPart.pause(.2),
//            outtake.stageStart(.5).attach(lift.stageLift(.8, heightMode.getValue(GROUND)))
//    ).setStartCode(() -> {
//        driveMode.set(FAST);
//        heightMode.set(GROUND);
//
//
//    });
//
//    AutoModule PlaceOne = new AutoModule(
//            outtake.stageOpenHalf(.2)
//
//    ).setStartCode(() -> {
//
//
//
//    });
//
//
//    AutoModule PlaceLow = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1, heightMode.getValue(LOW)),
//            outtake.stageEnd(.1)
//    ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(LOW);
//
//
//    });
//    AutoModule PlaceMid = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1.0, heightMode.getValue(MIDDLE)).attach(outtake.stageEndContinuousWithFlip(.5,      0))
//    ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(MIDDLE);
//
//
//    });
//
//    AutoModule PlaceHigh = new AutoModule(
//            RobotPart.pause(0.05),
//            lift.stageLift(1.0, heightMode.getValue(HIGH)).attach(outtake.stageEndContinuousWithFlip(.5, 0))
//    ).setStartCode(() -> {
//        driveMode.set(SLOW);
//        heightMode.set(HIGH);
//    });
//
//
//
//
//
//
//
//    AutoModule ForwardTeleHigh = new AutoModule(
//            RobotPart.pause(0.05),
//            drive.moveTime(1.0, 0.0, 0.0, 0.1),
//            outtake.stageReadyStartAfter(0.1),
////            outtake.stageStart(0.0),
//            lift.stageLift(1.0,0).attach(outtake.stageStartContinuousWithFlip(0.9, 0.0))
//    ).setStartCode(() -> {
//            lift.cap = false;
//            outtakeStatus.set(DRIVING);
//            outtake.openClawHalf();
//            outtake.moveEnd();
//    });
//
//    // 0.6
//    AutoModule ForwardTeleMiddle = new AutoModule(
//            RobotPart.pause(0.05),
//            drive.moveTime(1.0, 0.0, 0.0, 0.1),
//            outtake.stageReadyStartAfter(0.1),
////            outtake.stageStart(0.0),
//            lift.stageLift(1.0,0).attach(outtake.stageStartContinuousWithFlip(0.9, 0.0))
//    ).setStartCode(() -> {
//            lift.cap = false;
//            outtakeStatus.set(DRIVING);
//            outtake.openClawHalf();
//            outtake.moveEnd();
//    });
//
//    AutoModule ForwardTeleLow = new AutoModule(
//            RobotPart.pause(0.05),
//            drive.moveTime(1.0, 0.0, 0.0, 0.15).combine(new Stop(() -> drive.using = false)),
//            outtake.stageReadyStartAfter(0.1),
////            outtake.stageStart(0.0),
//            lift.stageLift(1.0,0).attach(outtake.stageStartContinuousWithFlip(0.9, 0.0))
//    ).setStartCode(() -> {
//            lift.cap = false;
//            drive.using = true;
//            outtakeStatus.set(DRIVING);
//            outtake.moveEnd();
//            outtake.openClawHalf();
//    });
//
//
//    AutoModule ForwardTeleGround = new AutoModule(
//            RobotPart.pause(0.6),
//            outtake.stageStart(0.0),
//            lift.moveTime(-0.4, 0.4)
//    ).setStartCode(() -> {
//            lift.cap = false;
//            outtakeStatus.set(DRIVING);
//            driveMode.set(MEDIUM);
//            outtake.openClawHalf();
//    });
//
//    /**
//     * Backward
//     */
//
//
//    AutoModule BackwardGrabHighTele = new AutoModule(
//            outtake.stageClose(0.2),
//            lift.checkAndLift(),
////            outtake.stageFlip(0.0),
////            outtake.stageReadyStart(0.0),
////            outtake.stageFlip(0.0),
//            outtake.stageReadyStartCond(0.0),
//            lift.stageLift(1.0, heightMode.getValue(HIGH)+2).attach(
//                    outtake.stageReadyEndAfter(0.48)
////                    outtake.stageFlipThing()
////                    outtake.stageReadyEndContinuousWithFlip(0.7, 0.1)
////                    outtake.stageReadyEnd(0.0)
//            )
//    ).setStartCode(() -> {
//            lift.setGround(false);
//            outtakeStatus.set(PLACING);
//            heightMode.set(HIGH);
//    });
//
//    AutoModule BackwardGrabMiddleTele = new AutoModule(
//            outtake.stageClose(0.22),
//            lift.checkAndLift(),
////            outtake.stageFlip(0.0),
//            outtake.stageReadyStartCond(0.0),
//            lift.stageLift(1.0, heightMode.getValue(MIDDLE)+2).attach(
//                    outtake.stageReadyEndAfter(0.48)
////                    outtake.stageReadyEndContinuousWithFlip(0.7, 0.1)
////                    outtake.stageReadyEndContinuousWithFlip(0.8, 0.08)
////                    outtake.stageReadyEnd(0.0)
//            )
//    ).setStartCode(() -> {
//            lift.setGround(false);
//            outtakeStatus.set(PLACING);
//            heightMode.set(MIDDLE);
//    });
//
//    AutoModule BackwardGrabLowTele = new AutoModule(
//            outtake.stageClose(0.22),
//            lift.checkAndLift(),
////            outtake.stageFlip(0.0),
//            outtake.stageReadyStartCond(0.0),
//            lift.stageLift(1.0, heightMode.getValue(LOW)+2).attach(
//                    outtake.stageReadyEndAfter(0.48)
////                    outtake.stageReadyEndContinuousWithFlip(0.7, 0.1)
////                    outtake.stageReadyEndContinuousWithFlip(0.7, 0.06)
////                    outtake.stageReadyEnd(0.0)
//            )
//    ).setStartCode(() -> {
//            lift.setGround(false);
//            outtakeStatus.set(PLACING);
//            heightMode.set(LOW);
//    });
//
//    AutoModule BackwardGrabGroundTele = new AutoModule(
//            outtake.stageClose(0.22),
//            outtake.stage(0.1, 0.0),
//            lift.checkAndLift(),
//            lift.stageLift(1.0, 13)
//    ).setStartCode(() -> {
//            outtakeStatus.set(PLACING);
//            heightMode.set(GROUND);
//            lift.setGround(true);
//    });
//
//    AutoModule BackwardGrabGroundTele2 = new AutoModule(
//            outtake.stageClose(0.0),
////            outtake.stageUnFlip(0.4),
//            outtake.stageStartContinuous(0.7),
//            outtake.stage(0.1, 0.0),
//            lift.checkAndLift(),
//            lift.stageLift(1.0, 13)
//    ).setStartCode(() -> {
//        outtakeStatus.set(PLACING);
//        heightMode.set(GROUND);
//        lift.setGround(true);
//    });
//
//    AutoModule BackwardPlaceGroundTele = new AutoModule(
//            lift.moveTime(-0.5, 0.7)
//    ).setStartCode(() -> {
//            lift.setGround(false);
//            outtakeStatus.set(PLACING);
//            heightMode.set(GROUND);
//            outtake.moveStart();
//    });
//
//    AutoModule ForwardTeleBottom = new AutoModule(
//            lift.stageLift(1.0,0),
//            outtake.stageStart(0.1)
//    ).setStartCode(() -> {
//            outtakeStatus.set(DRIVING);
//            outtake.moveStart();
//            outtake.openClawHalf();
//            lift.ground = false;
//    });
//
//    AutoModule CapGrab = new AutoModule(outtake.stageClose(0.25));
//
////    AutoModule CapGrab = new AutoModule(
//////            outtake.stageClose(0.25)
//////    );
//////            ,
//////            lift.stageLift(1.0, heightMode.getValue(LOW)+2).attach(
//////                    outtake.stageReadyEndContinuousWithFlip(1.5, 0.15)
//////            )
////    ).setStartCode(() -> {
////        lift.setGround(false);
//////        outtakeStatus.set(PLACING);
//////        heightMode.set(LOW);
////    });
//
////    AutoModule CapPlace = new AutoModule(
////            outtake.stageOpenCap(0.8),
////            outtake.stageClose(0.2),
////            outtake.stageReadyEnd(0.2)
//////            ,
//////            outtake.stageClose(0.2)
////    ).setStartCode(() -> {
////            outtake.openClawCap();
////            outtake.moveEnd();
////    });
//
//    AutoModule ResetLift = new AutoModule(lift.moveTime(-0.3, 0.5),  lift.resetLift()).setStartCode(() -> {
//        lift.ground = false;
//        outtakeStatus.set(DRIVING);
//    });
//    AutoModule UprightCone = new AutoModule(outtake.stage(0.1, 0.0), lift.stageLift(1.0, 5), driveMode.ChangeMode(SLOW));
//    AutoModule FixCone = new AutoModule(lift.moveTimeBack(0.1, -0.4, () -> 0.15), outtake.stageStart(0.0), lift.moveTimeBack(-0.35, -0.3, () -> 0.3), driveMode.ChangeMode(MEDIUM)).setStartCode(()->{lift.currentCutoffPosition = lift.defaultCutoffPosition;});
////    AutoModule TakeOffCone = new AutoModule(heightMode.ChangeMode(HIGH), outtake.stageClose(0.0), lift.stageLift(1.0, heightMode.getValue(HIGH)+3.5).attach(outtake.stageReadyStartAfter(0.5)),RobotPart.pause(0.1),outtake.stageFlip(0.0));
//
//    static AutoModule ForwardStackTele(int i){return new AutoModule(
//        lift.stageLift(1.0,  Math.max(13 - ((i+1)*13/4.6), -0.5))
//    ).setStartCode(() -> {
//        outtake.openClawHalf();
//        outtake.moveStart();
//    });}
//
//
//
//    /**
//     * Cycle
//     */
//
//    AutoModule BackwardCycle = new AutoModule(
//            outtake.stageClose(0.2),
//            lift.stageLift(1.0, heightMode.getValue(HIGH)+1.5).attach(outtake.stageEndContinuousWithFlip(1.0, 0.1))
//    );
//
////    AutoModule ForwardCycle = new AutoModule(
////            lift.stageLift(1.0,0).attach(outtake.stageBack(0.3))
////    ).setStartCode(outtake::dropConeRaw);
//
//    static Independent Cycle(int i) {return new Independent() {
//        @Override
//        public void define() {
//            double x = 0.0;
//            if(i==0){ addSegment(0.3, 0.1, NonstopSP, x, 5.0, 0.0);}
//            addConcurrentAutoModuleWithCancel(BackwardCycle, 0.25);
//            addWaypoint(0.4, x, -38, 0);
//            addSegment(0.3, 0.1, NonstopSP, x+0.5, -44.0, 0.0);
////            addConcurrentAutoModuleWithCancel(ForwardCycle, 0.1);
//            addWaypoint(0.4, x+0.5, -8, 0);
//            addSegment(0.3, 0.1, NonstopSP, x, 2.0, 0.0);
//        }
//
//        @Override
//        public void flip() {
//
//        }
//    };}
//
//
//    Machine MachineCycle = new Machine()
//    .addInstruction(odometry::reset,0.1)
//    .addIndependent(8, AutoModuleUser::Cycle)
//    .addIndependent(new Independent() {
//        @Override
//        public void define() {
//            addSegment(0.4, 0.5, NonstopSP, 0, 0.0, 0);
//        }
//    });
//
//
//    AutoModule BackwardCycleMiddle = new AutoModule(
//            outtake.stageClose(0.2),
//            lift.stageLift(1.0, heightMode.getValue(MIDDLE)+3).attach(outtake.stageReadyEndContinuousWithFlip(0.7, 0.15))
//    );
//
//    AutoModule BackwardCycleLow = new AutoModule(
//            outtake.stageClose(0.2),
//            lift.stageLift(1.0, heightMode.getValue(LOW)+1.5).attach(outtake.stageReadyEndContinuousWithFlip(1.1, 0.2))
//    );
//
////
////    AutoModule ForwardCycleMiddle = new AutoModule(
////            RobotPart.pause(0.1),
////            lift.stageLift(1.0,0).attach(outtake.stageBack(0.2)}
////    ).setStartCode(outtake::dropConeRaw);
////
////
////    AutoModule ForwardCycleLow = new AutoModule(
////            RobotPart.pause(0.4),
////            lift.stageLift(1.0,0).attach(outtake.stageBack(0.2))
////    ).setStartCode(outtake::dropConeRaw);
//
//
//    AutoModule BackwardCycleLow2 = new AutoModule(
//            outtake.stageClose(0.2),
//            lift.stageLift(1.0, heightMode.getValue(LOW)+1.5).attach(outtake.stageReadyEndContinuousWithFlip(0.8, 0.2))
//    );
//
////    Machine MachineCycleExtra = new Machine()
////            .addInstruction(odometry::reset, 0.1)
////            .addIndependentWithPause(new Independent() {
////                @Override
////                public void define() {
////                    addSegment(0.2, 0.2, NonstopSP, 0.0, 6.0, 0.0);
////                    addConcurrentAutoModuleWithCancel(BackwardCycleMiddle, 0.15);
////                    addWaypoint(0.8,-2,-15,-5.0);
////                    addSegment(1.0, 0.4, NonstopSP, -31, -36.0, -50.0);
////                    addSegment(0.4, 0.4, NonstopSP, -37, -42.0, -50.0);
////                }
////            })
////            .addIndependentWithPause(new Independent() {
////                @Override
////                public void define() {
////                    addConcurrentAutoModuleWithCancel(ForwardCycleMiddle, 0.2);
////                    addWaypoint(0.6, -32.0, 0.0, -21.0);
////                    addSegment(0.5, 0.34, NonstopSP, -27.0, 26.0, -25.0);
////                    addConcurrentAutoModuleWithCancel(BackwardCycleLow, 0.15);
////                    addWaypoint(1.0, -25.0, 15.0, 0.0);
////                    addWaypoint(1.0, -30.0, -5.0, -45);
////                    addSegment(1.1, 0.4, NonstopSP, -100.0, -45.0, -50);
////                }
////            })
////            .addIndependentWithPause(new Independent() {
////                @Override
////                public void define() {
////                    addConcurrentAutoModuleWithCancel(ForwardCycleLow, 0.15);
////                    addWaypoint(1.0, -70.0, -30.0, -75);
////                    addWaypoint(1.0, -50.0, -20.0, -70);
////                    addWaypoint(0.4, -40.0, -10.0, 0.0);
////                    addWaypoint(0.4, -27.0, 6.0, 0.0);
////                    addSegment(0.6, 0.34, NonstopSP, -27.0, 26.0, -25.0);
////                    addConcurrentAutoModuleWithCancel(BackwardCycleLow2, 0.15);
////                    addSegment(0.7, 0.3, NonstopSP, -25.0, 26.0, -57.0);
////                    addSegment(0.4, 0.4, NonstopSP, -35.0, 9.0, -57.0);
////                }
////            })
////            .addIndependent(new Independent() {
////                @Override
////                public void define() {
////                    addConcurrentAutoModuleWithCancel(ForwardCycleLow,0.15);
////                    addSegment(0.4, 0.4, NonstopSP, -25.0, 26.0, -57.0);
////                    addSegment(0.8, 0.35, NonstopSP, -27.0, 10.0, 0.0);
////                }
////            });
}
//
//
