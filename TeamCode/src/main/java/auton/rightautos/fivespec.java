package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name= "FiveSpecimen", group = "auto")
public class fivespec extends AutoFramework {

    @Override
    public void initialize() {
        voltageScale = 1;
        this.setConfig(NonstopConfig);
        lift.maintain();
        extendo.maintain();
        odometry.reset();
        gyro.reset();
        bot.saveLocationOnField();
        outtake.moveStart();
        intake.moveStart();
    }

    AutoModule extend = new AutoModule(
            lift.stageLift(1,14).attach(outtake.stageSpecimen(.1))
    );

    AutoModule lock = new AutoModule(
            lift.stageLift(1,3)
    );
    AutoModule retract = new AutoModule(
            outtake.stageOpen(.1),

            lift.stageLift(1,0).attach(outtake.stageStart(.1))
    );
    AutoModule Intake = new AutoModule(
            intake.stageTransfer(.1),
            extendo.stageLift(1,15),
            intake.stagePivotStart(.1),
            intake.stageLinkEnd(.1),
            intake.stageEnd1(.1),
            intake.stageClose(.1)
    );
    AutoModule Grab = new AutoModule(
            intake.stageEnd(.1),
            intake.stageOpen(.1),
            intake.stageEnd1(.1)
    );
    AutoModule Open = new AutoModule(
            intake.stageClose(.1)

    );


    @Override
    public void define() {
        addConcurrentAutoModule(extend);


        addSegment(.5,.6,DefaultWP,0,80,0);
        addAutoModule(lock);
        addConcurrentAutoModule(retract);
        addSegment(.5,.6,DefaultWP,0,40,0);

        addSegment(.5,.6,DefaultWP,90,60,-45);





    }


}
