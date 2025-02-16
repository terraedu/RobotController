package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name= "FourSampleBlue", group = "auto")
public class FourSampleBlue extends AutoFramework {

    @Override
    public void initialize() {
        setConfig(NonstopConfig);
        lift.maintain();
        extendo.maintain();
        odometry.reset();
        bot.saveLocationOnField();
        outtake.moveInit();
        intake.moveInit();
    }

//    AutoModule sampleAlign = new AutoModule (
//            drive.alignSampleRight(0, -0.5, 0),
//            drive.alignSampleLeft(0, 0.5, 0)
//    );

    @Override
    public void define() {
        addAutoModule(high);
        addSegment(1,1,NonstopWP,0,-8,0);
        addSegment(1,1,NonstopWP,-4,-2,90);
        addAutoModule(down);


        addPause(30);






    }
}