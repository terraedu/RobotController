package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.PLACING;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import auton.Auto;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name= "FiveSpecimen", group = "auto")
public class fivespec extends AutoFramework {


    @Override
    public void initialize() {
        voltageScale = 1;
        this.setConfig(NonstopConfig);
        lift.maintain();
        bot.saveLocationOnField();
        outtake.moveLinkStart();
        outtake.moveStart();

    }

    AutoModule specimenout = new AutoModule(
            outtake.stageLinkEnd(.1),
            lift.stagePivot(.3,-7.9).attach(outtake.stagePlaceSpecimen(.1)),
            lift.stageLift(1,45)


    );
    AutoModule specimenin = new AutoModule(
            lift.stageLift(1,33),
            outtake.stageStartLink(.1),
            RobotPart.pause(.2),
            outtake.stageOpen(.1),
            outtake.stageLinkEnd(.1).attach(outtake.stageFull(.1)),
            RobotPart.pause(.2),
            lift.stageLift(1,6),
            lift.stagePivot(.6,0),
            lift.stageLift(1,0),

            outtake.stageStart(.1)




    );
    AutoModule Intake = new AutoModule(
            lift.stageLift(1, 15).attach(outtake.stageLinkEnd(.1))



    );
    AutoModule Grab = new AutoModule(
            outtake.stageGrab(.1),
            outtake.stageOpen(.1).attach(outtake.stageFull(.1)),
            outtake.stageCloseArm(.1),
            RobotPart.pause(.2),
            outtake.stageClose(.1),
            outtake.stageStart(.1),
            lift.stageLift(1,0),
            lift.stageLift(1,-1.5)




    );
    @Override
    public void define() {
        addConcurrentAutoModule(specimenout);

        addSegment(1,1, DefaultSP, -2,22,0);

        addAutoModule(specimenin);
        addSegment(.5,1, DefaultWP, 40,20,90);
        addConcurrentAutoModule(Intake);
        addSegment(.5,1, DefaultSP, 48 ,52,180);
        addAutoModule(Grab);
        addConcurrentAutoModule(Grab);








        addPause(30);

    }

    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
