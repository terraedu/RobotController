package auton;

import static global.General.bot;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import autoutil.AutoFramework;

@Autonomous(name = "FourBucket", group = "Autonomous")
public class FourBucketAuto extends AutoFramework {


    @Override
    public void initialize() {
        voltageScale = 1;
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        intake.moveStart();
        outtake.moveStart();
    }
    @Override
    public void define() {

        addSegment(.5,1,DefaultSP,36,-23,-45);
        addSegment(.5,1,DefaultSP,23,-67,0);
        addSegment(.5,1,DefaultSP,36,-23,-45);
        addSegment(.5,1,DefaultSP,48,-1,0);
        addSegment(.5,1,DefaultSP,36,-23,-45);
        addSegment(.5,1,DefaultSP,47,-103,92);
        addSegment(.5,1,DefaultSP,42,-103,92);
        addSegment(.5,1,DefaultSP,36,-23,-45);


//        addSegment(.5,1,DefaultSP,33,-22,-45);
//        addSegment(.5,1,DefaultSP,30,-31,-4);
//        addSegment(.5,1,DefaultSP,33,-22,-45);
//        addSegment(.5,1,DefaultSP,32,-40,18);
//        addSegment(.5,1,DefaultSP,33,-22,-45);
//        addSegment(.5,1,DefaultSP,26,-38,40);
//        addSegment(.5,1,DefaultSP,33,-22,-45);
//        addSegment(.5,1,DefaultSP,20,-35,42);
//        addSegment(.5,1,DefaultSP,33,-22,-45);
        //addSegment(.5,1,DefaultSP,10,-108,94);


    }

}


