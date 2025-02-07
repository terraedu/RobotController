package teleop;

import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.Height.currentHeight;
import static global.Modes.TeleStatus.REDA;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestOp TESTING ONLY", group = "TeleOp")
public class TestOp extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;
        teleStatus.set(REDA);
    }

    @Override
    public void startTele() {

    }

    @Override
    public void loopTele() {
        // odometry positions
        log.show("pose", odometry.getPose());

        // heading
        log.show("heading", gyro.getHeading());

        // lift motor encoder positions
        log.show("Right", lift.motorRight.getPosition());

        // drive mode
        log.show("DriveMode", driveMode.get());

        // heights
        log.show("current height", current.getValue(currentHeight));
    }
}