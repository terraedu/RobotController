package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import robotparts.RobotPart;

import autoutil.AutoFramework;

@Autonomous(name = "blue pyp left", group = "auto")

public class pyp_blue_left extends AutoFramework {

    @Override
    public void initialize() {
        outtake.start();
        outtake.close();
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
    }
    AutoModule PlacePurple = new AutoModule(
            outtake.stageStart(.1),
            outtake.stageOpen1(.1)
    )



    ;
    @Override
    public void define() {
        addTimedSetpoint(1,1,1.2,0,-40,90);
        addTimedSetpoint(1,1,.3,15,0,90);
        addAutoModule(PlacePurple);



    }


}
