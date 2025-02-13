package auton.rightautos;

import static global.General.bot;
import static global.General.log;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name= "FiveSpecimen", group = "auto")
public class fivespec extends AutoFramework {

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
        addSegment(0.5,0.3,NonstopSP,0,-20,0);
        addSegment(0.5,1,NonstopSP,0,-29,0);
        addSegment(0.5,0.2,NonstopSP,0,-32,0);
        addConcurrentAutoModule(down);

        addSegment(0.5,1,NonstopWP,0,-6,0);
        addSegment(0.5,1,NonstopWP,-30,-14,0);
        addSegment(0.5,1,NonstopSP,-40,-14,-45);
    }
}