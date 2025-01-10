package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.TeleStatus.RED;
import static global.Modes.TurretStatus.ANGLE;
import static global.Modes.TurretStatus.FULL;
import static global.Modes.TurretStatus.HALF;
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


@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOpRed extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;
        gph2.link(B, PlaceHigh);
        gph2.link(Y, Place);
//
        gph2.link(RIGHT_TRIGGER, Intake);
        gph2.link(LEFT_TRIGGER, Grab);
        gph2.link(DPAD_RIGHT, SpecimenReady);
        gph2.link(RIGHT_BUMPER, SpecimenDrop);
        gph2.link(LEFT_BUMPER, SpecimenLocked);
        gph2.link(A, OutSpecimen);
        gph2.link(X, InSpecimen);
        gph2.link(DPAD_UP, Out);
        gph1.link(DPAD_DOWN, ()-> turretStatus.get()==HALF, moveHalf,  ()-> turretStatus.get()==ANGLE, moveAngle, moveFull);


//gph1.link(Y, ()-> out()  );
        teleStatus.set(RED);
        turretStatus.set(HALF);
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

drive.newMove(gph1.ly, gph1.lx, gph1.rx);
        lift.move(gph2.lx);
        lift.pivotmove(gph2.lx);
        lift.moveHang1(gph2.ry);
        lift.moveHang2(gph2.rx);

        log.show(lift.lpivot.getPower());



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


