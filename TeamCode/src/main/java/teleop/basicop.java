package teleop;

import static global.General.gph1;
import static global.General.log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "basic", group = "TeleOp")
public class basicop extends Tele {


    @Override
    public void loopTele() {

        drive.newMove(gph1.ry,gph1.lx);
//                lift.move(0);
//        log.show("Right", lift.motorRight.getPosition());
//        log.show("Left", lift.motorLeft.getPosition());

        log.show("odo:", odometry.getPose());
        log.show("controller:", gph1.ry);

    }
}
