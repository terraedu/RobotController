package automodules;

import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

import auton.Auto;
import robot.RobotUser;
import robotparts.RobotPart;

//import static global.Modes.Height.HIGH;
//import static global.Modes.Height.LOW;


public interface AutoModuleUser extends RobotUser {

    AutoModule SpecimenReady = new AutoModule(


    );
    AutoModule Specimen = new AutoModule(



    );
AutoModule PlaceHigh = new AutoModule(

).setStartCode(()->
        robotStatus.set(PLACING)
        );
AutoModule Place = new AutoModule(
  outtake.stageOpen(.1),
        outtake.stageStart(.1).attach(lift.stageLift(1,0))
);



AutoModule Intake = new AutoModule(



        );
    AutoModule Grab = new AutoModule(


    );

    AutoModule Armdown = new AutoModule(
            outtake.stageArmDown(.1)

    );


AutoModule PivotUp = new AutoModule(
        outtake.pivotExample(.1)
);
AutoModule ReadyForDrop = new AutoModule(
        lift.stageLift(1, 20)
);

}


