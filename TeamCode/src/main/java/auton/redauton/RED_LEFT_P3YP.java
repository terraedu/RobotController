//package auton.redauton;
//
//import static global.General.bot;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import automodules.AutoModule;
//import autoutil.AutoFramework;
//
//@Autonomous(name = "RED LEFT P3YP", group = "auto", preselectTeleOp = "TerraOp")
//public class RED_LEFT_P3YP extends AutoFramework {
//    @Override
//    public void initialize() {
//        this.setConfig(NonstopConfig);
//        bot.saveLocationOnField();
//    }
//    @Override
//    public void define() {
//            addTimedSetpoint(1.0,1,1,15, 55, -45);
//            addAutoModule(checkalign);
//            addPause(1);
//            addTimedSetpoint(1.0, 1, .5, 27, 62, 50);
//            addPause(0.25);
//            addSegment(1, DefaultWP, -69, 55, 205);
//            addPause(1);
//            addTimedSetpoint(1.0, 1, 1, -75, 60, 220);
//    }
//
//    AutoModule checkalign = new AutoModule(
//            drive.smartDrive(-0.2,0.1,0)
//    );
//
//    @Override
//    public void postProcess() {
//        autoPlane.reflectY();
//        autoPlane.reflectX();
//    }
//
//
//}
