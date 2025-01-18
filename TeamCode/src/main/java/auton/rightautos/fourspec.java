package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;

import android.os.DeadObjectException;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;
import util.Timer;

@Autonomous(name= "FourSpecimen", group = "auto")
public class fourspec extends AutoFramework {
    Timer timer2 = new Timer();


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
            outtake.stageLinkEnd(.1).attach(outtake.stagePlaceSpecimen(.1)),
            lift.stagePivot(.3,-7.9).attach(outtake.stageSpecimenTurret(.1)),
            lift.stageLift(1,23)



    );
    AutoModule specimenin = new AutoModule(
            lift.stageLift(1,16).attach(            outtake.stageStartLink(.1)
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

//        addSegment(1,1, DefaultSP,-5,0,0);
//
        timer2.set(0);
        addSegment(1, 1, DefaultSP, 0, 10, 0);

        addAutoModule(specimenout);

       addSegment(1, 1, DefaultSP, 1, 21, 0);



        addAutoModule(specimenin);

        addConcurrentAutoModule(specimenleave);


        addSegment(1, 1, DefaultWP, -26, 5, 0);

        addConcurrentAutoModule(Intake);
        addSegment(1,1, DefaultWP, -26 ,47,0);

             addSegment(1,1, DefaultSP, -37 ,47,0);

          addSegment(1,1, DefaultWP, -37 ,0,0);

           addSegment(1,1, DefaultWP, -38 ,45,0);

          addSegment(1,1, DefaultWP, -46 ,45,0);


          addSegment(1,1, DefaultWP, -46 ,0,0);
          addConcurrentAutoModule(SpecimenReady);

        addSegment(1,1, DefaultSP, -30 ,8,0);


          addSegment(1,1, DefaultSP, -30 ,.5,0);
          addAutoModule(SpecimenLocked);


        addConcurrentAutoModule(specimenout);

        addSegment(1,1, DefaultWP, 4,10,0);


        addSegment(1,1, DefaultSP, 4,12,0);


        addSegment(1,1, DefaultSP, 4,17.5,0);



        addAutoModule(specimenin);
        addConcurrentAutoModule(specimenleave);


        addSegment(1,1, DefaultSP, -60 ,10,90);














        addAutoModule(Grab);
        addConcurrentAutoModule(SpecimenDrop);
        addSegment(1,1, DefaultWP, 52 ,40,180);
        addConcurrentAutoModule(Intake);
        addSegment(1,1, DefaultWP, 55 ,50,180);
        addSegment(1,1, DefaultSP, 55 ,62,180);
        addAutoModule(Grab);
        addConcurrentAutoModule(SpecimenDrop);
        addSegment(1,1, DefaultSP, 58 ,35,180);

        addSegment(1,1, DefaultSP, 64 ,45,180);


    }


        @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
