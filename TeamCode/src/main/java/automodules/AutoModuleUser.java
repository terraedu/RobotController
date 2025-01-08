package automodules;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.PLACING;
import static global.Modes.TurretStatus.ANGLE;
import static global.Modes.TurretStatus.FULL;
import static global.Modes.TurretStatus.HALF;
import static global.Modes.robotStatus;
import static global.Modes.turretStatus;

import robot.RobotUser;
import robotparts.RobotPart;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {

    AutoModule SpecimenReady = new AutoModule(
            outtake.stageOpen(.1),
            RobotPart.pause(1),
            outtake.stageGrabSpecimen(.1),
            outtake.stageLinkEnd(.1),
            outtake.stageClose(.1)




        );
    AutoModule OutSpecimen = new AutoModule(
            outtake.stageLinkEnd(.1),
           lift.stagePivot(.3,-8.1).attach(outtake.stageSpecimen(.1)),
           lift.stageLift(1,28  )


    );
    AutoModule Out = new AutoModule(
            lift.stageLift(1,15)



    );
    AutoModule InSpecimen = new AutoModule(
            outtake.stageSpecimen2(.1),
            outtake.stageOpen(.1),
            lift.stageLift(1,0),
            lift.stagePivot(.8,0).attach(            outtake.stageStart(.1)
            )



    );
        AutoModule SpecimenDrop = new AutoModule(
        outtake.stageSpecimen(.1),
                RobotPart.pause(.2),
                outtake.stageOpen(.1)



        );
    AutoModule PlaceHigh = new AutoModule(
            outtake.stageLinkEnd(.1),
    lift.stagePivot(.3,-8.1).attach(outtake.stagePlace(.1)),
        lift.stageLift(1,34)
).setStartCode(()->
        robotStatus.set(PLACING)
        );

    AutoModule moveHalf = new AutoModule(
outtake.stageHalf(.1)
    ).setStartCode(()-> turretStatus.set(ANGLE));
    AutoModule moveAngle = new AutoModule(
outtake.stageAngle(.1)
    ).setStartCode(()-> turretStatus.set(FULL));
    AutoModule moveFull = new AutoModule(
outtake.stageFull(.1)
    ).setStartCode(()-> turretStatus.set(HALF));

    AutoModule Place = new AutoModule(
  outtake.stageOpen(.1),
       lift.stageLift(.7,0),
        lift.stagePivot(.1,0),
                outtake.stageStart(.1)

        ).setStartCode(()->
        robotStatus.set(DRIVING)
        );



AutoModule Intake = new AutoModule(
        lift.stageLift(1, 10).attach(outtake.stageLinkEnd(.1)),
        outtake.stageGrab(.1),
        outtake.stageOpen(.1).attach(outtake.stageFull(.1))


        ).setStartCode(()->

        robotStatus.set(PLACING)
);
    AutoModule Grab = new AutoModule(
        outtake.stageCloseArm(.1),
            RobotPart.pause(.2),
            outtake.stageClose(.1),
            outtake.stageStart(.1),
            lift.stageLift(1,0),
            lift.stageLift(1,-1.5)




            ).setStartCode(()->
            robotStatus.set(DRIVING)
    );






}


