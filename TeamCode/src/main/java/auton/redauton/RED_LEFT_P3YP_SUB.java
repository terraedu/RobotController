package auton.redauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;

@Autonomous(name = "RED LEFT P3YP SUB", group = "auto", preselectTeleOp = "TerraOp")
public class RED_LEFT_P3YP_SUB extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
    }
    @Override
    public void define() {
        addTimedSetpoint(1.0, 1, 1, -20, 55, 45);

//        addTimedSetpoint(1.0,1,1,20, 55, -45);
//        addAutoModule(checkalign);
//        addSegment(0.8, DefaultSP, 10, 62, 55);
//        addSegment(1, DefaultWP, -30, 50, 180);
//        addSegment(1, DefaultSP, -69, 55, 190);
//        addPause(1);
//        addTimedSetpoint(1.0, 1, 1, -75, 60, 220);
//        addTimedSetpoint(1.0, 1, 1, -70, 55, 180);
//
//            // sub auton cycling pathing
//
//        addSegment(1, DefaultWP, -60, 80, 280);
//        addTimedSetpoint(1.0, 1, 1.5, -25, 120, 280);
//        addAutoModule(checkSub);
    }

    AutoModule checkalign = new AutoModule(
            drive.smartDrive(-0.2,0.1,0)
    );

    AutoModule checkSub = new AutoModule(
            drive.smartSub(-0.2, 0, 0)
    );

    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
