package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;
import static global.Modes.RobotStatus.PLACING;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import auton.Auto;
import autoutil.AutoFramework;
import robotparts.RobotPart;

@Autonomous(name= "FiveSpecimen", group = "auto")
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

    AutoModule specimenout = new AutoModule(
            outtake.stageLinkEnd(.1),
            lift.stagePivot(.3,-7.9).attach(outtake.stagePlaceSpecimen(.1)),
            lift.stageLift(1,50)


    );
    AutoModule specimenin = new AutoModule(
            lift.stageLift(1,27).attach(            outtake.stageStartLink(.1)
                    ),
            outtake.stageOpen(.1)





    );
    AutoModule SpecimenReady = new AutoModule(
            outtake.stageGrabSpecimen(.1),
            outtake.stageOpen(.1),
            outtake.stageGrabSpecimen(.1),
            outtake.stageLinkEnd(.1)






    );
    AutoModule SpecimenLocked = new AutoModule(

            outtake.stageClose(.1),
            outtake.stageLiftSpecimen(.1)






    );
    AutoModule specimenleave = new AutoModule(
            outtake.stageLinkEnd(.1).attach(outtake.stageFull(.1)),
            RobotPart.pause(.2),
            lift.stageLift(1,6),
            lift.stagePivot(.6,0),
            lift.stageLift(1,0).attach( outtake.stageStart(.1))







            );
    AutoModule Intake = new AutoModule(
            outtake.stageLinkEnd(.1)



    );
    AutoModule SpecimenDrop = new AutoModule(
            outtake.stageSpecimen(.1).attach(outtake.stageStartLink(.1)),
            RobotPart.pause(.4),
            outtake.stageOpen(.1)



    );
    AutoModule Grab = new AutoModule(
            outtake.stageGrab(.1),
            outtake.stageOpen(.1).attach(outtake.stageFull(.1)),
            outtake.stageCloseArm(.1),
            RobotPart.pause(.2),
            outtake.stageClose(.1),
            outtake.stageStart(.1),
            lift.stageLift(1,0),
            lift.stageLift(1,-1.5)




    );
    @Override
    public void define() {
        addConcurrentAutoModule(specimenout);

        addSegment(.5,1, DefaultSP, 0,10,0);
        addSegment(.5,1, DefaultSP, 0,22,0);

        addAutoModule(specimenin);
        addConcurrentAutoModule(specimenleave);

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
        addAutoModule(SpecimenLocked);
        addConcurrentAutoModule(specimenout);
        addSegment(.5,1, DefaultSP, 4,5,0);
        addSegment(.5,1, DefaultSP, 4,18,0);

        addAutoModule(specimenin);
        addConcurrentAutoModule(specimenleave);
        addSegment(.5,1, DefaultWP, 35 ,7,0);
        addConcurrentAutoModule(SpecimenReady);

        addSegment(.5,1, DefaultSP, 30 ,-2,0);
        addAutoModule(SpecimenLocked);
        addConcurrentAutoModule(specimenout);
        addSegment(.5,1, DefaultSP, 15,5,0);
        addSegment(.5,1, DefaultSP, 15,18,0);

        addAutoModule(specimenin);
        addConcurrentAutoModule(specimenleave);
        addSegment(.5,1, DefaultWP, 35 ,7,0);
        addConcurrentAutoModule(SpecimenReady);

        addSegment(.5,1, DefaultSP, 30 ,-2,0);
        addAutoModule(SpecimenLocked);
        addConcurrentAutoModule(specimenout);
        addSegment(.5,1, DefaultSP, 25,5,0);
        addSegment(.5,1, DefaultSP, 25,18,0);

        addAutoModule(specimenin);
        addConcurrentAutoModule(specimenleave);
        addSegment(.5,1, DefaultWP, 35 ,7,0);
        addConcurrentAutoModule(SpecimenReady);

        addSegment(.5,1, DefaultSP, 30 ,-2,0);
        addAutoModule(SpecimenLocked);
        addConcurrentAutoModule(specimenout);
        addSegment(.5,1, DefaultSP, 35,5,0);
        addSegment(.5,1, DefaultSP, 35,18,0);

       addAutoModule(specimenin);
        addConcurrentAutoModule(specimenleave);
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
