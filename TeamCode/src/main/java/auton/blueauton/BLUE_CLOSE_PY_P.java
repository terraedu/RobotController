package auton.blueauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "BLUECLOSE PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class BLUE_CLOSE_PY_P extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
    }
    @Override
    public void define() {
        addWaypoint(30, -30, 0);
        addTimedSetpoint(1.0, .5, 1, 40, -75, -90);
        addTimedSetpoint(1.0, .5, 1, 60, -55, -90);
        addTimedSetpoint(1.0, .5, .4, 70, -55, -95);
        addPause(.1);
        addWaypoint(60, -20, 0);
        addTimedSetpoint(1.0, .5, 1, 100, -10, -90);
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
