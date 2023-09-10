package teleop;

import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import util.Timer;

@TeleOp(name = "er", group = "TeleOp")
public class basicerop extends OpMode {

    DcMotor lift;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void init() {
        lift = hardwareMap.get(DcMotor.class, "lf");

        timer.reset();

    }
    @Override
    public void loop() {
        timer.reset();
       lift.setPower(0);
        lift.setPower(0);
        lift.setPower(0);
        lift.setPower(0);
       // log.show(timer.seconds()*1000);
        telemetry.addData("",timer.seconds()*1000 );
        telemetry.update();
    }
}

