package auton.unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import autoutil.AutoFramework;

@Disabled
@Autonomous(name = "TerraAutoTest", group = "auto")
public class TerraAutoTest extends AutoFramework {

    @Override
    public void initialize() {
        setConfig(nStopNewConfig);
    }

    @Override
    public void define() {

//        addTimedSetpoint(0.5, 0.3, 4.0, 0, 90, 0);


        addSetpoint(1.0, 0.5, 0, 100, 0);

//        addSegment();


//        addCustomCode(() -> {
//            log.show("Odometry Pose", odometry.getPose());
//        }, 10);

    }


//    @Override
//    public void postProcess() { autoPlane.reflectY(); autoPlane.reflectX(); }

}
