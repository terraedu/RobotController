package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.PLACING;
import static global.Modes.TeleStatus.RED;
import static teleutil.button.Button.A;
import static teleutil.button.Button.B;


@TeleOp(name = "TerraOpRed", group = "TeleOp")
public class TerraOpRed extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;
gph1.link(B, PlaceHigh);
gph1.link(A, Place);

        teleStatus.set(RED);
    outtake.moveStart();
    robotStatus.set(DRIVING);

    }

    @Override
    public void startTele() {
        /**
         * Start code
         */


    }

    @Override
    public void loopTele() {

drive.newMove(gph1.ly, gph1.lx, gph1.ry);
lift.move(gph2.ly);
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


