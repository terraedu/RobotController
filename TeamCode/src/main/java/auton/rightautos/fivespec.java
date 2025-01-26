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
        intake.moveStartAuto();
    }

    AutoModule extend = new AutoModule(
            outtake.stageSpecimen(.1).attach(lift.stageLift(1,10)  )
    );

    AutoModule lock = new AutoModule(
            lift.stageLift(1,2)
    );

    AutoModule retract = new AutoModule(
            outtake.stageOpen(.1),

            lift.stageLift(1,0).attach(outtake.stageStart(.1))
    );

    AutoModule Intake = new AutoModule(
            outtake.stageStart(0.1),
            intake.stageTransfer(.1),
            extendo.stageLift(1,17),
            intake.stagePivotStart(.1),
            intake.stageEnd1(.1),
            RobotPart.pause(.2),
            intake.stageClose(.1),
            intake.stageLinkEnd(.1)
    );

    AutoModule grab = new AutoModule(
            intake.stageEnd(.1),
            intake.stageOpen(.1),
            intake.stageTransfer2(.1),
            intake.stageTransfer3(.1),
            intake.stageTransfer(.4),
            RobotPart.pause(0.2),
            intake.stageAdjustClaw(.1),
            intake.stageOpen(.1),
            intake.stageLinkStart(.1),
            extendo.stageLift(1,0),
            intake.stagePivotStart(.1).attach(outtake.stageOpen(.1)),
            intake.stageDownTurret(.1)
    );

    AutoModule open = new AutoModule(
            intake.stageClose(.1)
    );

    @Override
    public void define() {
        addConcurrentAutoModule(extend);

        // specimen
        addSegment(1,.5,DefaultWP,0,84,0);
        addAutoModule(lock);
        addConcurrentAutoModule(retract);
        // recede and samples
        addSegment(1,.5, DefaultWP,0, 34,215);
        addSegment(1,.5, DefaultWP,130, 5,215);
    }
}
