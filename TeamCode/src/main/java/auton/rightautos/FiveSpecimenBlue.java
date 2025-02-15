package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name= "FiveSpecimenBlue", group = "auto")
public class FiveSpecimenBlue extends AutoFramework {

    @Override
    public void initialize() {
        voltageScale = 1;
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
        addConcurrentAutoModule(upSpecimen);
        addSegment(0.5,1, NonstopSP,-5,-26,0);
        addSegment(0.5,0.75, NonstopSP,-5,-30,0);
        addPause(0.1);
        addConcurrentAutoModule(down);

        addConcurrentAutoModule(intakeOut);
        addSegment(0.5,1, NonstopWP,0,-10,0);
        addSegment(0.5,1, NonstopWP,-20,-11,0);
        addSegment(0.5,1, NonstopSP,-26,-15,-47);
        addPause(0.1);
        addAutoModule(clawDown);
        addPause(0.1);
        addAutoModule(clawUp);

        addConcurrentAutoModule(clawRelease);
        addSegment(0.5,0.5, NonstopWP,60,40,-160);
    }
}