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
//        addSetpoint(1, 1, );
        addSegment(.5,100,NonstopSP,0,20,0);
        addSegment(.5,100,NonstopSP,0,20,90);
    }
    }
