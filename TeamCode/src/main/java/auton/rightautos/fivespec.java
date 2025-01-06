package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import auton.Auto;
import autoutil.AutoFramework;

@Autonomous(name= "FiveSpecimen", group = "auto")
public class fivespec extends AutoFramework {


    @Override
    public void initialize() {
        voltageScale = 1;
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveLinkStart();
        outtake.moveStart();

    }

AutoModule ReadySpecimen = new AutoModule(
        outtake.stageClose(.1),
        outtake.stageLinkEnd(.1).attach(        lift.stagePivot(.8,6)),
        lift.stageLift(1,10).attach(outtake.stageLinkEnd(.1))

);
    AutoModule PlaceSpecimen = new AutoModule(
           outtake.stageOpen(.1),
            lift.stageLift(1,0).attach(outtake.stageStart(.1)),

            lift.stagePivot(.5,0)
    );
    @Override
    public void define() {
        addSegment(1,1, DefaultSP, 0,-13,0);
        addAutoModule(ReadySpecimen);
        addSegment(.5,1, DefaultSP, 0,-23,0);
        addAutoModule(PlaceSpecimen);






    }

    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
