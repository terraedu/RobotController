package unittests.tele.framework.movement;

import teleutil.button.Button;
import unittests.tele.TeleUnitTest;

import static global.General.gph1;
import static global.General.log;

public class OdometryTest extends TeleUnitTest {
    @Override
    protected void start() {
        gph1.link(Button.Y, odometry::reset);
//        gph1.link(Button.DPAD_UP, () -> odometry.scaleRight += 0.001);
//        gph1.link(Button.DPAD_DOWN, () -> odometry.scaleRight -= 0.001);
//        gph1.link(Button.DPAD_UP, () -> odometry.angleLeft += Trig.rad(0.2));
//        gph1.link(Button.DPAD_DOWN, () -> odometry.angleLeft -= Trig.rad(0.2));
//        gph1.link(Button.DPAD_RIGHT, () -> odometry.angleRight += Trig.rad(0.2));
//        gph1.link(Button.DPAD_LEFT, () -> odometry.angleRight -= Trig.rad(0.2));
//        gph1.link(Button.RIGHT_BUMPER, () -> odometry.mode += 1);
//        gph1.link(Button.LEFT_BUMPER, () -> odometry.mode -= 1);

//        gph1.link(Button.RIGHT_BUMPER, drive::endSignal);
//        gph1.link(Button.LEFT_BUMPER, drive::startSignal);
    }

    @Override
    protected void loop() {
        log.show("Odometry Pose", odometry.getPose());
//        log.show("Y2", odometryNew.getEncY2());
        drive.move(gph1.ry*0.5, gph1.rx*0.5, gph1.lx*0.5);
//        log.show("Mode", odometry.mode);
//        log.show("Angle Left", Trig.deg(odometry.angleLeft));
//        log.show("Angle Right", Trig.deg(odometry.angleRight));
//        log.show("Scale Right", odometry.scaleRight);
    }

}
