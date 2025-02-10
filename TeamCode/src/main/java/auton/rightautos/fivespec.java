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
        bot.saveLocationOnField();
        outtake.moveInit();
        intake.moveInit();
    }

    AutoModule extend = new AutoModule(
//            outtake.stageSpecimen(.1).attach(lift.stageLift(1,10)  )
    );

    AutoModule lock = new AutoModule(
            lift.stageLift(1,2)
    );

    AutoModule retract = new AutoModule(
//            outtake.stageOpen(.1),

//            lift.stageLift(1,0).attach(outtake.stageStart(.1))
    );

    AutoModule doIntake = new AutoModule(

    );

    AutoModule grab = new AutoModule(

    );

    AutoModule open = new AutoModule(

    );

    AutoModule sampleAlign = new AutoModule (
            drive.alignSampleRight(0, -0.5, 0),
            drive.alignSampleLeft(0, 0.5, 0)
    );

    @Override
    public void define() {

    }
}
