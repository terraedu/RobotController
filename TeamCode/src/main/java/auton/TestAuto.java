package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.controllers.control1D.Controller1D;
import autoutil.controllers.control1D.RP;
import util.Timer;

@Autonomous(name="TestAuto")
public class TestAuto extends Auto {

//    private static final double MAX_FORWARD_VELOCITY = 150;   //cm per sec
//    public static Controller1D controller;


    @Override
    public void initAuto() {

//        controller = new RP(0.008, 0.06);
//        controller = new DRP(0.008,0.06);
//        controller = new PAR(0.008,0.5,0.06);
//        controller = new PID(PID.PIDParameterType.DEFAULT_ALL, .008,0.005,0.0005, 10.0, 5.0);


//        controller.setProcessVariable(gyro::getHeading);
//        controller.setAccuracy(0.5);
//        controller.setMinimumTime(0.2);
    }

    @Override
    public void runAuto() {
//        moveGyroTurn(90);
//        moveGyroTurnRP(-90);
//        moveGyroTurnRP(-180);
//        moveGyroTurnRP(180);
//        int speed = 0;
//        Timer time = new Timer();
//        time.reset();
//        while(time.seconds()<=2) {
//            drive.move(speed,0,0);
//            speed += 0.1;
//        }
//        while(time.seconds()<=4) {
//            drive.move(speed,0,0);
//            speed -= 0.1;
//        }
//        drive.halt();
//        FinalDouble turnPower = new FinalDouble(0.0);
//        double start = gyro.getHeading();
//        whileActive(() -> Math.abs(gyro.getHeading()-start)<0.25, () ->{
//            turnPower.increment(0.0005);
//            drive.move(0,0,turnPower.get());
//        });
//        log.show("Rest power = " + turnPower.get());
//        drive.halt();
//        pause(10);




        //        for(int i=0; i<4; i++) {
//            moveGyroTurn(0.3,-90);
//            moveTimeTurn(0.5,0.75);
//        }


 /*
        Timer time = new Timer();
        time.reset();
        whileActive(() -> time.seconds()<60, () -> {

        });

        whileTime(() -> {

        }, 60);
*/
    }
//    public void moveTimeForward(double f, double time) {
//        moveTime(f,0,0,time);
//    }
//    public void moveDistanceForward(double power, double distance) {
//        moveTimeForward(power,distance/MAX_FORWARD_VELOCITY);
//    }
//    public void moveTimeTurn(double t, double time) {
//        moveTime(0,0,t,time);
//    }
//    public void moveTimeStrafe(double s, double time) {
//        moveTime(0,s,0,time);
//    }
//    public void moveTime(double f, double s, double t, double time) {
//        drive.move(f,s,t);
//        pause(time);
//        drive.halt();
//    }
//    public void moveGyroTurn(double power, double degrees) {
//        drive.move(0,0,Math.abs(power)*Math.signum(degrees));
//        double startHeading = gyro.getHeading();
//        double targetHeading = startHeading + degrees;
//        whileActive(()->Math.abs(gyro.getHeading()-targetHeading)>5,()->{});
//        drive.halt();
//    }
//    public void moveGyroTurnRP(double degrees) {
//        double startHeading = gyro.getHeading();
//        double targetHeading = startHeading + degrees;
//        rp.setTarget(targetHeading);
//        whileActive(rp::notAtTarget,()->{
//            rp.update();
//            drive.move(0,0,rp.getOutput());
//        });
//        drive.halt();
//        rp.reset();
//    }







    /*
do these methods work?
    public void moveForwardRP(double cm) {
//      rp.setProcessVariable(gyro::getHeading); what do I do for this?
        rp.setAccuracy(2);
        rp.setTarget(cm);
        whileActive(rp::notAtTarget,()->{
            rp.update();
            drive.move(rp.getOutput(),0,0);
        });
        rp.reset();
        drive.halt();
    }
    public void moveRP(double cm,String direction) {
//      rp.setProcessVariable(gyro::getHeading); what do I do for this?
        rp.setAccuracy(2);
        rp.setTarget(cm);
        whileActive(rp::notAtTarget,()->{
            rp.update();
            switch (direction) {
                case "f":
                    drive.move(rp.getOutput(),0,0);
                    break;
                case "s":
                    drive.move(0,rp.getOutput(),0);
                    break;
                case "t":
                    drive.move(0,0,rp.getOutput());
                    break;
            }
        });
        rp.reset();
        drive.halt();
    }
*/
//    public void moveGyroTurn(double degrees){
//        double startHeading = gyro.getHeading();
//        double targetHeading = startHeading + degrees;
//        controller.setTarget(targetHeading);
//        whileActive(controller::isNotAtTarget, () -> {
//            controller.update();
//            drive.move(0, 0, controller.getOutput());
//        });
//        drive.halt();
//        controller.reset();
//    }

}
