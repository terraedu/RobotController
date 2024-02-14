package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name = "Park_BR", group = "auto")
public class Park_Auto_BR extends Auto {
    @Override
    public void initAuto() {

    }

    @Override
    public void runAuto() {
        drive.move(1,0, 0);
        pause(0.2);
        drive.move(0, 1, 0);
        pause(0.7);
        drive.move(-1,0, 0);
        pause(0.2);
    }
}
