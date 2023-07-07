package teleop;

import static global.General.gph1;
import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Modes;
import teleutil.button.Button;

// teleop -> TerraOp
@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOp extends Tele {
    /**
     * TERRA OP: This is the main teleop class. Teleop means tele operated or operated from far away. This is how we tell
     * what the robot should do when the joysticks of the controller are moved. We can do this in two ways
     * 1. We can link a specific button to a specific action of the controller, this code goes in the init method
     *      a. gph1.link(Button b, () -> action); Normal link one button to one action
     *      b. gph1.link(Button b, () -> action1, () -> action2); Toggle link one button switches between action 1 and action 2
     * 2. We can move the robot continuously from joystick input
     *      a. drive.something(gph1.ry)
     *///i

    @Override
    public void initTele() {
        // TASK 5 Create code that links each button to one wheel
        // Use the link method of the gph1 object [gamepad (controller) handler 1]
        // For example to link the A button to the right back wheel:
        // Note that we have to use a toggle link because we have to turn the motor off with setPower(0)
        // gph1.link(Button.A, () -> drive.rb.setPower(1), () -> drive.rb.setPower(0));
        // There are 4 buttons A, B, X, Y that should be linked to 4 motors rb, lb, rf, lf

        //gph1.link(Button.A, () -> drive.rb.setPower(1), () -> drive.rb.setPower(0));
        //gph1.link(Button.B, () -> drive.lb.setPower(1), () -> drive.lb.setPower(0));
        //gph1.link(Button.X, () -> drive.rf.setPower(1), () -> drive.rf.setPower(0));
        //gph1.link(Button.Y, () -> drive.lf.setPower(1), () -> drive.lf.setPower(0));
    }

    @Override
    public void loopTele() {
        // TASK 6 Create code that moves the robot using the joysticks
        // There are three inputs from the joystick
        // 1. gph1.ry -> right joystick y component (forward and backward)
        // 2. gph1.rx -> right joystick x component (side to side)
        // 3. gph1.lx -> left joystick x component (side to side)
        // These should be the inputs to
        // drive.move( [ry] , [rx] , [lx] );
        // Note that because drive.move was defined as drive.move(forward, strafe, turn)
        // right joystick y moves forward and backward
        // right joystick x strafes sideways
        // left joystick x turns clockwise and counterclockwise

        // < drive.move code goes here >
        drive.move(gph1.ry, gph1.rx, gph1.lx);

    }











































    //        Modes.driveMode.set(Drive.MEDIUM);
//        gph1.link(Button.DPAD_UP, () -> Modes.driveMode.set(Drive.FAST));
//        gph1.link(Button.DPAD_LEFT, () -> Modes.driveMode.set(Drive.MEDIUM));
//        gph1.link(Button.DPAD_RIGHT, () -> Modes.driveMode.set(Drive.MEDIUM));
//        gph1.link(Button.DPAD_DOWN, () -> Modes.driveMode.set(Drive.SLOW));

    //        if(driveMode.modeIs(Drive.FAST)){
//            drive.move(gph1.ry, gph1.rx, gph1.lx);
//        } else if (driveMode.modeIs(Drive.SLOW)){
//            drive.move(0.25*gph1.ry, 0.25*gph1.rx, 0.25*gph1.lx);
//        } else if (driveMode.modeIs(Drive.MEDIUM)) {
//            drive.move(0.5*gph1.ry, 0.5*gph1.rx, 0.5*gph1.lx);
//        }
//        log.show(driveMode.get());
//        log.show(gyro.getHeading());
}