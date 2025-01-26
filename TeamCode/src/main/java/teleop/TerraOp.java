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
import static teleutil.button.Button.LEFT_BUMPER;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;
import static teleutil.button.Button.Y;

import autoutil.vision.SampleScanner;
import autoutil.vision.yolovision.YoloScanner;


@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOp extends Tele {

    @Override
    public void initTele() {

        teleStatus.set(REDA);
        voltageScale = 1;

//        Tele auto = this;
//        auto.scan(true);

        intake.yoloScanner = (YoloScanner) yoloScanner;
        intake.sampleScanner = (SampleScanner) sampleScanner;

        gph1.link(LEFT_BUMPER, () -> intake.moveStartTurret()); // mid
        gph1.link(LEFT_TRIGGER, () -> intake.moveTurret()); // 45 left
        gph1.link(RIGHT_BUMPER, () -> intake.moveTurretSideways()); // 90
        gph1.link(RIGHT_TRIGGER, () -> intake.moveTurretMiddler()); // 45 right

        gph2.link(B, PlaceHigh);
        gph2.link(A, Place);
        gph2.link(RIGHT_BUMPER, OutSpecimen);
        gph2.link(LEFT_BUMPER, InSpecimen);
        gph2.link(DPAD_DOWN,drop);
        gph2.link(RIGHT_TRIGGER, Intake);
        gph2.link(LEFT_TRIGGER, Grab);
        gph2.link(Y, SpecimenReady);
        gph2.link(X, Specimen);

    robotStatus.set(DRIVING);
    }

    @Override
    public void startTele() {
        /**
         * Start code
         */
        intake.moveStart();
        outtake.moveStart();

    }

    @Override
    public void loopTele() {

drive.newMove(-gph1.ly, gph1.rx, gph1.lx);
lift.move(gph2.ly*.7);
        extendo.move(gph2.lx*.7);


        /**
         * Gets Distance
         */

//        log.show("right distance (cm)", distanceSensorsNew.getCMDistanceRight());
//        log.show("left distance (cm)", distanceSensorsNew.getCMDistanceLeft());

        /**
         * Gets light of color sensor
         */
//        log.show("light 1", colorSensorsNew.getLight1());
//        log.show("light 2", colorSensorsNew.getLight2());


        /**
         * odo pose
         */
        log.show("pose", odometry.getPose());

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
        log.show("Right", lift.motorRight.getPosition());


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


