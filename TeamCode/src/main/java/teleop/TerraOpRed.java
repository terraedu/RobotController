package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.TeleStatus.RED;
import static teleutil.button.Button.A;
import static teleutil.button.Button.B;
import static teleutil.button.Button.DPAD_DOWN;
import static teleutil.button.Button.LEFT_BUMPER;
import static teleutil.button.Button.LEFT_TRIGGER;
import static teleutil.button.Button.RIGHT_BUMPER;
import static teleutil.button.Button.RIGHT_TRIGGER;
import static teleutil.button.Button.X;
import static teleutil.button.Button.Y;


@TeleOp(name = "TerraOpRed", group = "TeleOp")
public class TerraOpRed extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;
//    gph1.link(Y, ()-> outtake.moveLinkEnd());
        gph1.link(B, PlaceHigh);
        gph1.link(A, Place);
//
        gph1.link(RIGHT_TRIGGER, Intake);
        gph1.link(LEFT_TRIGGER, Grab);
        gph1.link(RIGHT_BUMPER, SpecimenReady);
        gph1.link(LEFT_BUMPER, SpecimenDrop);
        gph1.link(Y, OutSpecimen);
        gph1.link(X, InSpecimen);
        gph1.link(DPAD_DOWN, ()-> outtake.moveHalf());
//

//gph1.link(Y, ()-> out()  );
        teleStatus.set(RED);
    robotStatus.set(DRIVING);

    }

    @Override
    public void startTele() {
        /**
         * Start code
         */

    outtake.moveLinkStart();
    outtake.moveStart();


    }

    @Override
    public void loopTele() {

drive.newMove(gph1.ry, gph1.rx, gph1.lx);
lift.pivotmove(gph2.ly);
        lift.move(gph2.lx);


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
        log.show("lift", lift.lir.getPosition());
        log.show("pivot", lift.lpivot.getPosition());


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


