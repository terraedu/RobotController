package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="TestAuto")
public class TestAuto extends Auto{

    @Override
    public void initAuto() {

    }

    @Override
    public void runAuto() {
        for(int i=0; i<4; i++) {
            drive.move(0.5, 0, 0);
            pause(1);
            drive.halt();

            drive.move(0, 0, 0.5);
            pause(0.5);
            drive.halt();
        }


//        Timer time = new Timer();
//        time.reset();
//        whileActive(() -> time.seconds()<60, () -> {
//
//        });
//
//        whileTime(() -> {
//
//        }, 60);
    }
}
