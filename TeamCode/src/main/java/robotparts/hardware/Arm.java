package robotparts.hardware;


import automodules.StageBuilder;
import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import global.Constants;
import math.trigonmetry.Trig;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.positional.PMotor;
import robotparts.electronics.positional.PServo;
import teleop.Tele;
import util.Timer;
import util.codeseg.CodeSeg;
import util.codeseg.ParameterCodeSeg;
import util.codeseg.ReturnParameterCodeSeg;

import static global.General.log;

public class Arm extends RobotPart {
    PServo cr;
    PServo cl;
    PMotor arm;
    double startAngle = 45;
    final double armRestPow = 0.3;




    @Override
    public void init() {
        cr = create("cr", ElectronicType.PSERVO_FORWARD);
        cl = create("cl", ElectronicType.PSERVO_REVERSE);
        arm = create("arm",ElectronicType.PMOTOR_FORWARD);

        double startPos = 0.2;
        cr.changePosition("start",startPos);
        cl.changePosition("start",startPos);
        double halfPos = (startPos+1.0)/2.0;
        cr.addPosition("half",halfPos);
        cl.addPosition("half",halfPos);



        arm.setToRotational(Constants.ORBITAL_TICKS_PER_REV,1);
        arm.resetPosition();
        arm.usePositionHolder(() -> armRestPow*Trig.cos(getArm()), 0.01);




        log.show("\uD83D\uDC80 \uD83D\uDC80 \uD83D\uDC80");
    }

    public double getArm(){
        return startAngle + arm.getPosition();
    }



    public void openClaw(){
        cr.setPosition("start");
        cl.setPosition("start");

    }
    public void closeClaw(){
        cr.setPosition("end");
        cl.setPosition("end");

    }
    public void halfClaw(){
        cr.setPosition("half");
        cl.setPosition("half");

    }


    @Override
    public void move(double p) {
        arm.move(p);
    }


    /**
     * Here we will be creating some automodules. What is an automodule?
     * An AutoModule is a series of tasks the robot takes to achieve a goal
     * For example, consider the AutoModule that
     *     1. Grabs a block (closeClaw)
     *     2. Lift it up (move arm 90 degrees up)
     * We could call this automodule PickUp and it would be composed of two "stages"
     * A stage is just a specific step that needs to be run (that controls one RobotPart)
     * Then when we want to run the automodule, we add the stages to the RobotFunctions Thread
     * This is a separate thread that has a Queue of Stages in it
     * So if we add the above AutoModule to the Queue twice it would look like this,
     *     1. Grab
     *     2. Lift
     *     3. Grab
     *     4. Lift
     * These would all get executed in the order they were put in (FIFO, First In First Out)
     * Now the question is how we can create Stages
     * Each Stage is composed of four parts
     *     1. Initial - Runs once when the stage starts (ex: reset position)
     *     2. Main - Runs on loop while the stage is running (ex: update power of motor)
     *     3. Exit - Is the condition for the Stage to stop (ex: arm angle > 90)
     *     4. Stop - Runs once after the stage has exited (ex: halt motor)
     * The workflow for creating AutoModules is
     *     1. Create necessary stages
     *     2. Create the automodules using the stages
     * Note that most of the times you dont need to create stages yoursefl, but instead can use StageBuilder
     * which can help create all the most common stages for you
     * Task 23: Teaches how to create a new stage
     * Task 24: Teaches how to create a new stage with StageBuilder [What you will do almost all of the time]
     * Task 25: Teaches how to create a lift stage
     * Task 26: Teaches how to create a new AutoModule (in AutoModuleUser)
     * Task 27: Make the PutDown Automodule
     * Task 28: Use the PickUp and PutDown AutoModules in Teleop
     */

    // TASK 23:
    // Create a method to make a new Stage that closes the claw and waits for some amount of time (for the claw to close)
    // The method should return a new Stage, be named stageCloseBad, and have a parameter double time
    // Inside the constructor of the Stage is where you can put Initial, Main, etc.
    // The first step is to create an Initial that resets a timer
    // 1. First define a new final Timer above the return statement
    // 2. Then create an initial that resets the timer
    // 3. Then create a main that calls this::closeClaw
    // 4. The exit condition is when timer.seconds() > time, so create a new Exit for this
    // 5. We don't need a stop for this so we are done
    // Also you should definite use usePart() at the start and returnPart() at the end (otherwise you
    // wont transfer the locks of each thread on the hardware)

    public Stage stageCloseBad(double time){
        final Timer timmy = new Timer();
        return new Stage(
                usePart(),
                new Initial(timmy::reset),
                new Main(this::closeClaw),
                new Exit(() -> timmy.seconds() > time),
                returnPart()
        );
    }

    // TASK 24: Now we will create the same Stage with StageBuilder
    // Copy the method signature from the previous task (except the name is now stageClose)
    // Inside return customTime(this::closeClaw, time);
    // Now on your own, make the same type of method for openClaw
    public Stage stageClose(double time) {
            return customTime(this::closeClaw,time);
    }
    public Stage stageOpen(double time) {
        return customTime(this::openClaw,time);
    }



        // TASK 25:
    // Create a new method that returns a Stage, is named stageArm, has two parameters double power and double degrees
    // Then add a return statement that returns moveTarget(() -> arm, power, degrees)
    public Stage stageArm(double power,double degrees){
        return moveTarget(() -> arm,power,degrees);
    }









    /**
     * ARM: This is the Arm class. This will show you how to create a new RobotPart
     * Here are the steps to creating a new RobotPart:
     *     1. First create a new java file in the robotparts package (already done for you here)
     *     2. Next add 'extends RobotPart' after the name of the class
     *         - This will add the functionality of RobotPart to your new RobotPart class
     *     3. Then you will need to override the init and move method (implement methods).
     *         - The init method will run when the driver initializes the robot
     *         - The move method you override should be specific to the RobotPart you are trying
     *           to create, for example Drive.move will be move(double f, double s, double t) while
     *           Arm.move should be move(double p) [p is for power, also delete super.move()]
     *     4. Go to RobotUser and create a new Arm object, then open Configs and add it to the current config
     */
    // TASK SETUP (11.5): do all the steps above


    // TASK 12: Declare two public PServo objects (Positional Servo) [name them cr and cl for
    // claw right and claw left]
    // and one PMotor object (Positional Motor) [name it arm]




    // TASK 13: Inside the init method create the PServo and PMotor objects using the create method
    // [make sure the left servo is reversed and right forward (why?), arm should be forward]






    // TASK 14: Pservos come with two positions start (0) and end (0) by default. To change these,
    // use <name of pservo>.changePosition("start", 0.1) [this changes the position start from 0.0 to 0.1
    // to add a new position use .addPosition("name of new position", <value of new position>)
    //
    // Your task: create a double called startPos set to 0.2. Then set change the position "start"
    // of both the right and left claws to startPos.
    // Then add a new position to both claws called "half" set to [halfway between startPos and "end" (1.0)]
    // [you should make a new double called halfPos for this]




    // TASK 15: Now outside the init method make three methods called openClaw(), closeClaw(), halfCloseClaw()
    // and make them do what they are supposed to do [you can use .setPosition("name of position")






    // TASK 16: Go back into the init method. Here we will set the type of PMotor. There are two types
    //     1. Linear - This PMotor works for lifts where a linear motion is required [input: length (cm)]
    //     2. Rotational - This PMotor works for arms and places where rotation is required [input: angle (degrees)]
    //
    // To convert to linear use .setToLinear(), for rotational (this class) use .setToRotational()
    // The two arguments required are
    //     1. Ticks per revolution - Ticks is how the encoder counts rotations, the number of ticks
    //        in each turn is dependent on the type of motor (most of the time we use orbital motors)
    //        so we can use (Constants.ORBITAL_TICKS_PER_REV for this
    //     2. Gear ratio - This is the gear ratio from the motor to the output shaft. If this is > 1 then
    //        the motor turns faster than output shaft, if < 1 turns slower
    //        [basically x motor rotations : 1 output shaft rotation, so x is the ratio]
    //        [or 1 teeth : x teeth on gears]
    // For this class use .setToRotational(Constants.ORBITAL_TICKS_PER_REV, 1);


    // TASK 17: The next aspect we can set is the position holder, which holds the position of the arm
    // when it is not supposed to move
    // Before we create the position holder call .resetPosition(). This will set the position to 0.
    // Lets say the arm starts at an angle of 45 degrees with respect to the horizontal
    // Create a global [a class field] double called startAngle set to 45.
    // Now the position of the arm is startAngle + arm.getPosition() (why?)
    // Create a method that returns the position of the arm called getArmPosition()
    // Now create another global final double called armRestPower = 0.3 (is a constant)
    // Now that all the pieces are in place we can set the position holder
    // In init, after .resetPosition(), call .usePositionHolder(() -> armRestPower*Trig.cos(getArmPosition()), 0.01);
    // The first number (lambda expression) is a function that returns the rest power dependent on the angle (why cos?)
    // The second represents the P coefficient (error*P coefficient = extraPower), [ex 10 degs off 10*0.01 = 0.1];


   // TASK 18: In the move method, simply move the arm using .move(p), and make it public then go TerraOp for the next task

}









































































//import global.Constants;
//import math.trigonmetry.Trig;
//import robotparts.RobotPart;
//import robotparts.electronics.ElectronicType;
//import robotparts.electronics.positional.PMotor;
//import robotparts.electronics.positional.PServo;
//
//public class Arm extends RobotPart {
//
//    public PServo cr, cl;
//    public PMotor arm;
//    public double startAngle = 45;
//    public final double armRestPower = 0.3;
//
//    @Override
//    public void init() {
//        cr = create("cr", ElectronicType.PSERVO_FORWARD);
//        cl = create("cl", ElectronicType.PSERVO_REVERSE);
//        arm = create("arm", ElectronicType.PMOTOR_FORWARD);
//
//        double startPos = 0.2;
//        cr.changePosition("start", startPos);
//        cl.changePosition("start", startPos);
//
//        double halfPos = (1.0 + startPos)/2.0;
//        cr.addPosition("half", halfPos);
//        cl.addPosition("half", halfPos);
//
//        arm.setToRotational(Constants.ORBITAL_TICKS_PER_REV, 1);
//        arm.resetPosition();
//        arm.usePositionHolder(() -> armRestPower* Trig.cos(getArmPosition()), 0.01);
//
//    }
//
//    public void openClaw(){
//        cr.setPosition("start");
//        cl.setPosition("start");
//    }
//
//    public void closeClaw(){
//        cr.setPosition("end");
//        cl.setPosition("end");
//    }
//
//    public void halfCloseClaw(){
//        cr.setPosition("half");
//        cl.setPosition("half");
//    }
//
//    public double getArmPosition(){
//        return startAngle + getArmPosition();
//    }
//
//    @Override
//    protected void move(double p) {
//        arm.move(p);
//    }

// public Stage stageCloseBad(double time){
//        final Timer timer = new Timer();
//        return new Stage(
//               new Initial(timer::reset),
//               new Main(this::closeClaw),
//               new Exit(() -> timer.seconds() > time)
//        );
//    }

//  public Stage stageClose(double time){
//        return customTime(this::closeClaw, time);
//    }
//
//    public Stage stageOpen(double time){
//        return customTime(this::openClaw, time);
//    }

//  public Stage stageArm(double power, double degrees){
//        return moveTarget(() -> arm, power, degrees);
//    }


































































// public void new3(){
//        ArmStages.CLAW_OPEN.customTime = time -> customTime(this::openClaw, time);
//    }
//
//    public interface class Stages{
//        default ReturnParameterCodeSeg<Double, Stage> customTime;
//        default Stage forTime(double time){
//            return customTime.run(time);
//        }
//    }
//
//    public enum ArmStages implements Stages{
//        CLAW_OPEN,
//        CLAW_CLOSE;
//
//
//    }
//}
