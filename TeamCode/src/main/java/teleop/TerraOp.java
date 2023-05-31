//If you see this, it works
//package teleop;
//
//import static global.General.gph1;
//import static global.General.log;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import global.Modes;
//import teleutil.button.Button;
//
//@TeleOp(name = "TerraOp", group = "TeleOp")
//public class TerraOp extends Tele {
//
//    @Override
//    public void initTele() {
//        Modes.driveMode.set(Drive.MEDIUM);
//        gph1.link(Button.DPAD_UP, () -> Modes.driveMode.set(Drive.FAST));
//        gph1.link(Button.DPAD_LEFT, () -> Modes.driveMode.set(Drive.MEDIUM));
//        gph1.link(Button.DPAD_RIGHT, () -> Modes.driveMode.set(Drive.MEDIUM));
//        gph1.link(Button.DPAD_DOWN, () -> Modes.driveMode.set(Drive.SLOW));
//    }
//
//    @Override
//    public void loopTele() {
//        if(driveMode.modeIs(Drive.FAST)){   FAST
//            drive.move(gph1.ry, gph1.rx, gph1.lx);
//        } else if (driveMode.modeIs(Drive.SLOW)){
//            drive.move(0.25*gph1.ry, 0.25*gph1.rx, 0.25*gph1.lx);
//        } else if (driveMode.modeIs(Drive.MEDIUM)) {
//            drive.move(0.5*gph1.ry, 0.5*gph1.rx, 0.5*gph1.lx);
//        }
//        log.show(driveMode.get());
//    }
//}