package auton;

import static global.General.bot;
import static global.Modes.Height.HIGH;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;

@Autonomous(name = "TerraAutoOdometry" +
        "", group = "auto", preselectTeleOp = "TerraOp")
public class TerraAutoOdometry extends AutoFramework {
    @Override
    public void define() {
        addTimedSetpoint(1.0, 0.8, 0.6, 0, 115, 0);
        addSegment(0.6, DefaultSP, 5, 115, 0);
    }

    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
    }
}
