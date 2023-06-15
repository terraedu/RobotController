package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

// auton -> TerraAuto
@Autonomous(name="TerraAuto")
public class TerraAuto extends Auto {
    /**
     * TERRA AUTO: This the autonomous class. This is like teleop except there are no joystick controllers
     * and the robot has to move completely on its own.
     * The init method of this class can be empty for this example
     * All the code will be in runAuto (note this is not a loop like loopTele)
     * Methods like moveTime can be created that will make it easy to move the robot
     */


    public void moveTime(double f, double s, double t,  double time){
        // TASK 7 Create code that moves the robot for a set amount of time and then stops it, here are the steps
        // 1. Move the robot using the drive.move method with inputs f, s, and t
        // 2. Do nothing for the amount of time given using the pause method
        // 3. Stop the robot using the drive.halt method

        // < Code goes here >

    }

    public void moveForwardTime(double f, double time){
        // TASK 8 Call the above method moveTime in this method
        // Call the method with inputs f and time so put 0 for the inputs s and t

        // < Code goes here >
    }

    public void moveStrafeTime(double s, double time){
        // TASK 8 Call the above method moveTime in this method
        // Call the method with inputs s and time so put 0 for the inputs f and t

        // < Code goes here >

    }

    // TASK 9 Create your own method called moveTurnTime that has two inputs double t, and double time
    // Then inside the method call moveTime with inputs t and time so put 0 for f and s

    // < Code goes here >




    @Override
    public void runAuto() {
        // TASK 10 Make the robot move in the following steps (call the appropriate methods)
        // 1. Move forward for 1 second at 0.5 speed
        // 2. Turn to the left for 1.5 seconds at 0.4 speed
        // 4. Strafe to the right (local robot view) for 0.7 seconds at 0.7 speed

        // < Code goes here >

    }
































    @Override
    public void initAuto() {}



}
