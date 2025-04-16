package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name= "test3", group = "auto")

public class test3 extends AutoFramework {

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
        addSegment(1,1, NonstopSP,10,5,90);


    }

}
