package robotparts.hardware;

import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;

// robotparts -> hardware -> drive
public class Drive extends RobotPart {
    /**
     * DRIVE: This is the drive RobotPart. This is how the program will move the hardware in the robot
     *
     * For example, to define the motors for a mecanum drive, first create 4 CMotor objects
     *
     * private CMotor name1, name2, name3, name4;
     *
     * Then in the init() method,
     *
     * name1 = create("name", ElectronicType.CMOTOR_REVERSE); [repeat for 4 motors]
     *
     * Note that the two right motors should be reversed and the two left ones forward
     */

    // TASK 2: Create 4 uninitialized public CMotor objects named (rb, lb, rf, lf)
    // Note that rb stands for right back and lf is left front

    // < motors definitions go here >


    @Override
    public void init() {

        // TASK 3: Use the method create(String name, ElectronicType type) to initialize the motors
        // For example to initialize the CMotor (rb)
        // rb = create("rb",  ElectronicType.CMOTOR_REVERSE); [Note that different motors have different directions (see above)]

        // < motors initializations go here >



    }

    @Override
    public void move(double f, double s, double t) {

        // TASK 4: Program the movement of the mecanum wheels. The input to this function is three doubles
        // which are all a values between -1 and 1
        // f -> forward: this should move the robot forward (+) and backward (-)
        // s -> strafe: should move the robot sideways right (+) and left (-)
        // t -> turn: should turn the robot clockwise (+) and counterclockwise (-)

        // For example for the rb motor
        //  1. Needs to go forward when the robot moves forward so +f
        //  2. Needs to go backward when the robot turns to the right so -t
        //  3. Needs to go forward when the robot strafes to the right so +s
        //  rb.setPower(f-t+s);
        // Do this for all motors

        // < motor movement code goes here>


    }

































































//    public CMotor rb, lb, rf, lf;



//        rb = create("rb", ElectronicType.CMOTOR_REVERSE);
//        lb = create("lb", ElectronicType.CMOTOR_FORWARD);
//        rf = create("rf", ElectronicType.CMOTOR_REVERSE);
//        lf = create("lf", ElectronicType.CMOTOR_FORWARD);






    //        rb.setPower(f-t+s);
//        lb.setPower(f+t-s);
//        rf.setPower(f-t-s);
//        lf.setPower(f+t+s);



}
