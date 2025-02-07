package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.PLACING;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import auton.Auto;
import autoutil.AutoFramework;
import robotparts.RobotPart;

public class fivespec extends AutoFramework {


    @Override
    public void initialize() {
        voltageScale = 1;
        this.setConfig(NonstopConfig);
        lift.maintain();
        bot.saveLocationOnField();
        outtake.moveLinkStart();
        outtake.moveStart();

    }

    @Override
    public void define() {


        addSegment(.5,1, DefaultSP, 0,10,0);
        addSegment(.5,1, DefaultSP, 0,22,0);



        addSegment(.5,1, DefaultWP, 26 ,5,0);
//        addConcurrentAutoModule(Intake);
        addSegment(.5,1, DefaultWP, 26 ,47,0);
        addSegment(.5,1, DefaultSP, 35 ,47,0);
        addSegment(.5,1, DefaultWP, 35 ,0,0);
        addSegment(.5,1, DefaultWP, 38 ,45,0);
        addSegment(.5,1, DefaultWP, 46 ,45,0);
        addSegment(.5,1, DefaultWP, 46 ,0,0);
        addSegment(.5,1, DefaultWP, 48 ,43,0);
        addSegment(.5,1, DefaultWP, 50 ,43,0);

        addSegment(.5,1, DefaultWP, 50 ,0,0);
        addConcurrentAutoModule(SpecimenReady);
        addSegment(.5,1, DefaultWP, 30 ,7,0);
        addSegment(.5,1, DefaultSP, 30 ,-2,0);

        addSegment(.5,1, DefaultSP, 4,5,0);
        addSegment(.5,1, DefaultSP, 4,18,0);


        addSegment(.5,1, DefaultWP, 35 ,7,0);
        addConcurrentAutoModule(SpecimenReady);

        addSegment(.5,1, DefaultSP, 30 ,-2,0);

        addSegment(.5,1, DefaultSP, 15,5,0);
        addSegment(.5,1, DefaultSP, 15,18,0);


        addSegment(.5,1, DefaultWP, 35 ,7,0);
        addConcurrentAutoModule(SpecimenReady);

        addSegment(.5,1, DefaultSP, 30 ,-2,0);

        addSegment(.5,1, DefaultSP, 25,5,0);
        addSegment(.5,1, DefaultSP, 25,18,0);


        addSegment(.5,1, DefaultWP, 35 ,7,0);
        addConcurrentAutoModule(SpecimenReady);

        addSegment(.5,1, DefaultSP, 30 ,-2,0);

        addSegment(.5,1, DefaultSP, 35,5,0);
        addSegment(.5,1, DefaultSP, 35,18,0);


        addSegment(.5,1, DefaultSP, 30 ,15,90);













//
//        addAutoModule(Grab);
//        addConcurrentAutoModule(SpecimenDrop);
//        addSegment(.5,1, DefaultWP, 52 ,40,180);
//        addConcurrentAutoModule(Intake);
//        addSegment(.5,1, DefaultWP, 55 ,50,180);
//        addSegment(.5,1, DefaultSP, 55 ,62,180);
//        addAutoModule(Grab);
//        addConcurrentAutoModule(SpecimenDrop);
//        addSegment(.5,1, DefaultSP, 58 ,35,180);
//
////        addSegment(.5,1, DefaultSP, 64 ,45,180);
//
//
//
//
//
//
//
//
//
//

    }

    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
