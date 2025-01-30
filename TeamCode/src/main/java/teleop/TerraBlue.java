package teleop;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.TeleStatus.BLUEA;
import static global.Modes.TeleStatus.REDA;
import static teleutil.button.Button.A;
import static teleutil.button.Button.B;
import static teleutil.button.Button.DPAD_DOWN;
import static teleutil.button.Button.LEFT_BUMPER;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;
import static teleutil.button.Button.Y;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TerraBlue", group = "TeleOp")
public class TerraBlue extends Tele {

    @Override
    public void initTele() {
        teleStatus.set(BLUEA);
        voltageScale = 1;

        Tele auto = this;
        auto.scan(true);

//        intake.yoloScanner = (YoloScanner) yoloScanner;
        intake.sampleScanner = sampleScanner;

        gph1.link(LEFT_BUMPER, () -> intake.turretLeft());
        gph1.link(RIGHT_BUMPER, () -> intake.turretRight());
        gph1.link(DPAD_DOWN, () -> intake.updatePipeline(20));

        gph2.link(B, PlaceHigh);
        gph2.link(A, Place);
        gph2.link(RIGHT_BUMPER, OutSpecimen);
        gph2.link(LEFT_BUMPER, InSpecimen);
        gph2.link(DPAD_DOWN, Drop);
        gph2.link(RIGHT_TRIGGER, Intake);
        gph2.link(LEFT_TRIGGER, Grab);
        gph2.link(Y, SpecimenReady);
        gph2.link(X, Specimen);

        robotStatus.set(DRIVING);
    }

    @Override
    public void startTele() {
//        intake.turretReset();
//        outtake.moveStart();
    }

    @Override
    public void loopTele() {

//        drive.newMove(-gph1.ly, gph1.rx, gph1.lx);
//        lift.move(gph2.ly*.7);
//        extendo.move(gph2.lx*.7);

        /**
         * odo pose
         */
//        log.show("pose", odometry.getPose());

        /**
         * Outtake Status
         */
//        log.show("OuttakeStatus", outtakeStatus.get());

        /**
         * Heading
         */
//        log.show("heading", gyro.getHeading());


        /**
         * lift encoder positions
         */
//        log.show("Right", lift.motorRight.getPosition());


        /**
         * extendo encoder positions
         */
//        log.show("left", extendo.motorLeft.getPosition());

        /**
         * drive mode
         */
//        log.show("DriveMode", driveMode.get());

        /**
         * outtake status
         */
//                log.show("outske", outtakeStatus.get());
        /**
         * heights
         */
//        log.show("current height", current.getValue(currentHeight));

    }

}


