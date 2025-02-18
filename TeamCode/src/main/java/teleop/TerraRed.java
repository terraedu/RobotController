package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.TeleStatus.REDA;
import static teleutil.button.Button.A;
import static teleutil.button.Button.B;
import static teleutil.button.Button.DPAD_DOWN;
import static teleutil.button.Button.DPAD_LEFT;
import static teleutil.button.Button.DPAD_RIGHT;
import static teleutil.button.Button.DPAD_UP;
import static teleutil.button.Button.LEFT_BUMPER;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;
import static teleutil.button.Button.Y;

@TeleOp(name = "TerraRed", group = "TeleOp")
public class TerraRed extends Tele {

    @Override
    public void initTele() {
        teleStatus.set(REDA);
        voltageScale = 1;

        Tele auto = this;
        auto.scan(true);

//        intake.yoloScanner = (YoloScanner) yoloScanner;
//        intake.sampleScanner = sampleScanner;
//
//        gph1.link(LEFT_BUMPER, () -> intake.turretLeft());
//        gph1.link(RIGHT_BUMPER, () -> intake.turretRight());
//        gph1.link(DPAD_DOWN, () -> intake.updatePipeline(20));++
//
        gph2.link(RIGHT_BUMPER, upSpecimen);
        gph2.link(LEFT_BUMPER, down);
        gph2.link(RIGHT_TRIGGER, intakeOut);
        gph2.link(LEFT_TRIGGER, intakeIn);
        gph2.link(DPAD_DOWN, zestyFlick);
        gph2.link(Y, switcharoo);
        gph2.link(B, high);
        gph2.link(A, specimenReady);
        gph2.link(X, grabSpecimen);
        gph2.link(DPAD_UP, intake::turretHorizontal);
        gph2.link(DPAD_LEFT, clawDown);
        gph2.link(DPAD_RIGHT, clawUp);
//
        robotStatus.set(DRIVING);
    }

    @Override
    public void startTele() {intake.moveInit(); outtake.moveInit();}

    @Override
    public void loopTele() {
        drive.newMove(gph1.ly,gph1.rx,gph1.lx);
        lift.move(gph2.ly);
        extendo.move(gph2.ry);
        log.show("x encoder", odometry.getX());
        log.show("y encoder", odometry.getY());
        log.show("heading", odometry.getHeading());
        log.show("extendo position", extendo.motorLeft.getPosition());
        log.show("lift position", lift.motorRight.getPosition());
    }
}


