package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.controllers.control1D.RP;

@Autonomous(name="TestAuto")
public class TestAuto extends Auto {
    private static final double MAX_FORWARD_VELOCITY = 150;   //cm per sec
    public static final RP rp = new RP(0.01,0.05);

    @Override
    public void initAuto() {

    }

    @Override
    public void runAuto() {
//        for(int i=0; i<4; i++) {
//            moveGyroTurn(0.3,-90);
//            moveTimeTurn(0.5,0.75);
        moveGyroTurnRP(90);
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
    public void moveTimeForward(double f, double time) {
        moveTime(f,0,0,time);
    }

    public void moveDistanceForward(double power, double distance) {
        moveTimeForward(power,distance/MAX_FORWARD_VELOCITY);
    }

    public void moveTimeTurn(double t, double time) {
        moveTime(0,0,t,time);
    }

    public void moveTimeStrafe(double s, double time) {
        moveTime(0,s,0,time);
    }

    public void moveTime(double f, double s, double t, double time) {
        drive.move(f,s,t);
        pause(time);
        drive.halt();
    }
    public void moveGyroTurn(double power, double degrees) {
        drive.move(0,0,Math.abs(power)*Math.signum(degrees));
        double startHeading = gyro.getHeading();
        double targetHeading = startHeading + degrees;
        whileActive(()->Math.abs(gyro.getHeading()-targetHeading)>5,()->{});
        drive.halt();
    }
    public void moveGyroTurnRP(double degrees) {
        double startHeading = gyro.getHeading();
        double targetHeading = startHeading + degrees;
        rp.setProcessVariable(gyro::getHeading);
        rp.setAccuracy(2);
        rp.setTarget(targetHeading);
        whileActive(() -> !rp.isAtTarget(),()->{
            rp.update();
            drive.move(0,0,rp.getOutput());
        });
        rp.reset();
        drive.halt();
    }
}
