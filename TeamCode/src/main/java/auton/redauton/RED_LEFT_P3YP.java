package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name = "RED LEFT P3YP", group = "auto", preselectTeleOp = "TerraOp")
public class RED_LEFT_P3YP extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
    }
    @Override
    public void define() {
            addTimedSetpoint(1.0,1,1,38,46, -31);
            addPause(1);
            addTimedSetpoint(1.0, 1, .5, 27, 62, 50);
            addPause(0.25);
            addSegment(1, DefaultWP, -69, 55, 205);
            addPause(1);
            addTimedSetpoint(1.0, 1, 1, -75, 60, 220);
            addTimedSetpoint(1.0, 1, 1, -70, 55, 170);
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
