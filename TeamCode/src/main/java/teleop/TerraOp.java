package teleop;

import static global.General.fault;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Modes;
import teleutil.button.Button;
import util.condition.Expectation;
import util.condition.Magnitude;

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
        gph1.link(Button.A, () -> drive.br.setPower(1), () -> drive.br.setPower(0));
        gph1.link(Button.B, () -> drive.fr.setPower(1), () -> drive.fr.setPower(0));
        gph1.link(Button.X, () -> drive.bl.setPower(1), () -> drive.bl.setPower(0));
        gph1.link(Button.Y, () -> drive.fl.setPower(1), () -> drive.fl.setPower(0));
        // < linking code goes here>


        // TASK 19:
        // Link the right bumper to close the claw and left bumper to open the claw
        // Link dpad up to close halfway
        gph1.link(Button.RIGHT_BUMPER, arm::closeClaw);
        gph1.link(Button.LEFT_BUMPER, arm::openClaw);
        gph1.link(Button.DPAD_UP,arm::halfClaw);

        // TASK 28:
        // Link the PickUp automodule to the right dpad
        gph1.link(Button.DPAD_RIGHT,Pickup);

        // Link the PutDown automodule to the left dpad
        gph1.link(Button.DPAD_LEFT,PutDown);


    }

    @Override
    public void loopTele() {
        // TASK 6 Create code that moves the robot using the joysticks
        // There are three inputs from the joystick
        // 1. gph1.ry -> right joystick y component (forward and backward)
        // 2. gph1.rx -> right joystick x component (side to side)
        // 3. gph1.lx -> left joystick x component (turning)
        // These should be the inputs to
        // drive.move( [ry] , [rx] , [lx] );
        // Note that because drive.move was defined as drive.move(forward, strafe, turn)
        // right joystick y moves forward and backward
        // right joystick x strafes sideways
        // left joystick x turns clockwise and counterclockwise

        drive.move(gph1.ry,gph1.rx,gph1.lx);
        log.show("BRO THat was TOO EAASY");
        // < drive.move code goes here >


        // TASK 20:
        // Call the move method of arm and set it to the right joystick y of gamepad 2 (gph2)
        arm.move(gph2.ry);

        // TASK 21:
        // Use log.show(caption, value) with the caption being "Arm position" and the value being the arm position using arm.getArmPosition()
        log.show("Arm Position \uD83D\uDC80 \uD83D\uDC80 \uD83D\uDC80",arm.getArm());

        // Task 22:
        // Use fault.check to make sure the arm angle doesn't go over 180 degrees (message: arm broken,
        // expectation: Expected, magnitude: negligible, test: arm.getArmPosition() <something> 180, correct: false)
        fault.check("arm borken \uD83D\uDC80 \uD83D\uDC80 \uD83D\uDC80",Expectation.EXPECTED,Magnitude.NEGLIGIBLE,arm.getArm()<180,true);





        log.show("a:br go brrrrr(brb), b: for real fr, x: blob, blasphemous blunder bl, y:fll JR, female lead floruisihing flocks of flourdering flies fl");

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