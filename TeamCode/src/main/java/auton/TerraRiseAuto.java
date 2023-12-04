package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;

@Autonomous(name = "TerraRiseAuto", group = "auto")

public class TerraRiseAuto extends AutoFramework {


    @Override
    public void define() {
drive.move(0.7,0,0);
sleep(200);
drive.stop();
addTimedSetpoint(1,0.7,2,0,0,90);
    }
    @Override

    public void initialize() {

    }
}
