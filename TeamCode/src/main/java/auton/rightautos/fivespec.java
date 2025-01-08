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
        lift.maintain();
        bot.saveLocationOnField();
        outtake.moveLinkStart();
        outtake.moveStart();

    }

    AutoModule specimenout = new AutoModule(
            outtake.stageLinkEnd(.1),
            lift.stagePivot(.3,-8.1),
            lift.stageLift(1,30  )


    );
    AutoModule specimenin = new AutoModule(
            lift.stageLift(.7,26),
            outtake.stageOpen(.1),
            lift.stageLift(1,0),
            lift.stagePivot(.8,0).attach(            outtake.stageStart(.1)
            )



    );
    @Override
    public void define() {
        addSegment(1,1, DefaultSP, 0,20,0);
//        addAutoModule(specimenout);
        addSegment(.2,1, DefaultSP, -4,28,0);

//        addAutoModule(specimenin);
        addSegment(1,1, DefaultWP, 40,20,90);
        addSegment(1,1, DefaultSP, 50 ,55,180);








        addPause(30);

    }

    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
