package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.controllers.control1D.Controller1D;
import autoutil.controllers.control1D.RP;

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
        drive.move(f,s,t);
        pause(time);
        drive.halt();
        // TASK 7 Create code that moves the robot for a set amount of time and then stops it, here are the steps
        // 1. Move the robot using the drive.move method with inputs f, s, and t
        // 2. Do nothing for the amount of time given using the pause method
        // 3. Stop the robot using the drive.halt method

        // < Code goes here >

    }

    public void moveForwardTime(double f, double time){
        moveTime(f,0,0,time);

        // TASK 8 Call the above method moveTime in this method
        // Call the method with inputs f and time so put 0 for the inputs s and t

        // < Code goes here >
    }

    public void moveStrafeTime(double s, double time){
        // TASK 9 Call the above method moveTime in this method
        // Call the method with inputs s and time so put 0 for the inputs f and t
        moveTime(0,s,0,time);
        // < Code goes here >

    }

    // TASK 10 Create your own method called moveTurnTime that has two inputs double t, and double time
    // Then inside the method call moveTime with inputs t and time so put 0 for f and s
    public void moveTurnTime(double t, double time) {
        moveTime(0,0,t,time);

    }


        // < Code goes here >

    // TASK 29:
    // Make method to turn with gyro
    // First go to configs and add gyro
    // Then fill in the method below [hint absolute value is Math.abs, and signum is Math.signum, gyro heading = gyro.getHeading()]
    // Steps
    // 1. Turn the robot (using drive.move) at power in the direction of degrees (i.e. if degrees is +, power is +)
    // 2. Create a double for the start heading
    // 3. Create a final double for the target heading (start + degrees)
    // 4. Use the while active method (why?). The first argument is <difference between current heading and target> > 5 (why?), the second is ()->{}
    // 5. Halt the drive (drive.halt)
    public void moveTurnGyro(double power, double degrees){
        drive.move(0,0,Math.abs(power)*Math.signum(degrees));
        double sheading = gyro.getHeading();
        final double target = (sheading+degrees);
        whileActive(() -> Math.abs(gyro.getHeading()-target)>5,() -> {});
        drive.halt();
    }


    // TASK 30:
    // Fill out the method below which turns but smoother (not set power, reduces overshoot)
    // Steps:
    // 1. Copy/Paste the following line: Controller1D con = new RP(0.008, 0.06);
    //    - This creates a controller1D of type RP (rest power, proportional coefficent)
    //    - This is a controller that moves the robot to a target (1D becuase turn is 1 dimensional)
    // 2. Use .setProcessVariable(<method reference (::) to gyro.getHeading))
    // 3. Use .setAccuracy and set it to 2 degrees
    // 4. Set the minimum time to 0.2 seconds (this is the minimum time at the target (otherwise could overshoot))
    // 5. Set the target to degrees
    // 6. Create a whileActive loop, while the controller is not at the target, update it and move at the output power (.getOutput())
    // 7. Halt the drive
    // 8. Reset the controller
    public void moveTurnGyroRP(double degrees) {
        Controller1D con = new RP(0.008, 0.06);
        con.setProcessVariable(gyro::getHeading);
        con.setAccuracy(2);
        con.setMinimumTime(0.2);

        con.setTarget(degrees);
        whileActive(con::isNotAtTarget, ()->{
            con.update();
            drive.move(0,0,con.getOutput());
        });
        drive.halt();
        con.reset();
    }




    @Override
    public void runAuto() {
        // TASK 11 Make the robot move in the following steps (call the appropriate methods)
        // 1. Move forward for 1 second at 0.5 speed
        // 2. Turn to the left for 1.5 seconds at 0.4 speed
        // 4. Strafe to the right (local robot view) for 0.7 seconds at 0.7 speed
        moveForwardTime(0.5,1);
        moveTurnTime(-0.4,1.5);
        moveStrafeTime(0.7,0.7);
        // < Code goes here >


        // TASK 31:
        // Make the robot move in a hexagon
        // For moving forward, move at 0.5 power for 1 s
        // For turning alternate between using moveTurnGyro and moveTurnGyroRP [use moveTurnGyro first, power = 0.5]
        // [be careful of the difference between the two methods, hint: one is global and one is local]

        moveForwardTime(0.5,1);
        moveTurnGyro(0.5,60);

        moveForwardTime(0.5,1);
        moveTurnGyroRP(120);

        moveForwardTime(0.5,1);
        moveTurnGyro(0.5,60);

        moveForwardTime(0.5,1);
        moveTurnGyroRP(240);

        moveForwardTime(0.5,1);
        moveTurnGyro(0.5,60);

        moveForwardTime(0.5,1);
        moveTurnGyroRP(360);

    }
































    @Override
    public void initAuto() {}

//        drive.move(0,0,Math.abs(power)*Math.signum(degrees));
//        double startHeading = gyro.getHeading();
//        final double targetHeading = startHeading + degrees;
//        whileActive(()->Math.abs(gyro.getHeading()-targetHeading)>5,()->{});
//        drive.halt();

//    Controller1D con = new RP(0.008, 0.06);
//        con.setProcessVariable(gyro::getHeading);
//        con.setAccuracy(2);
//        con.setMinimumTime(0.2);
//        con.setTarget(degrees);
//        whileActive(con::isNotAtTarget,()->{
//            con.update();
//            drive.move(con.getOutput(),0,0);
//        });
//        con.reset();
//        drive.halt();

    //        moveForwardTime(0.5, 1);
//        moveTurnGyro(0.5, 60);
//
//        moveForwardTime(0.5, 1);
//        moveTurnGyroRP(120);
//
//        moveForwardTime(0.5, 1);
//        moveTurnGyro(0.5, 60);
//
//        moveForwardTime(0.5, 1);
//        moveTurnGyroRP(240);
//
//        moveForwardTime(0.5, 1);
//        moveTurnGyro(0.5, 60);
//
//        moveForwardTime(0.5, 1);
//        moveTurnGyroRP(360);


}
