package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;
@Autonomous(name= "test1", group = "auto")

public class test extends AutoFramework {

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
    @Override
    public void define() {
        addSegment(1,1, NonstopSP,0,10,0);


    }

}
