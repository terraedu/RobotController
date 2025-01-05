package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
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
    AutoModule test = new AutoModule(
            drive.moveTime(1,1,1,30)
    );
    @Override
    public void define() {
        addAutoModule(test);
//        addTimedSetpoint(1,1, 1,0,-23,0);
//        addSegment(1,1, DefaultWP,-4,-37,-54);
//        addSegment(1,1, DefaultWP,-22,  -45,-27);
//        addSegment(1,1, DefaultSP,-36,  -49,0);
//        addSegment(1,1, DefaultWP,-36,  -10,0);
//        addSegment(1,1, DefaultWP,-38,  -49,0);
//        addSegment(1,1, DefaultSP,-45,  -49,0);
//        addSegment(1,1, DefaultWP,-45,  -10,0);
//        addSegment(1,1, DefaultWP,-51,  -49,0);
//        addSegment(1,1, DefaultWP,-55,  -49,0);
//        addSegment(1,1, DefaultWP,-55,  -10,0);






    }

    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
