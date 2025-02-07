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





    }






    @Override
    public void define() {

//        addSegment(1,1, DefaultSP,-5,0,0);
//
        timer2.set(0);
        addSegment(1, 1, DefaultSP, 0, 7, 0);



       addSegment(1, 1, DefaultSP, 1, 14, 0);
       addPause(1);
        addSegment(1, 1, DefaultSP, 1, 22, 0);






        addSegment(1, 1, DefaultWP, -26, 5, 0);

        addConcurrentAutoModule(Intake);
        addSegment(1,1, DefaultWP, -26 ,47,0);

             addSegment(1,1, DefaultSP, -37 ,45,0);

          addSegment(1,1, DefaultWP, -47 ,5,0);

           addSegment(1,1, DefaultWP, -48,45,0);

          addSegment(1,1, DefaultWP, -56 ,45,0);


          addSegment(1,1, DefaultWP, -46 ,13,0);
          addConcurrentAutoModule(SpecimenReady);


          addSegment(1,1, DefaultSP, -30 ,2.5,0);


        addSegment(1,1, DefaultWP, 4,10,0);


        addSegment(1,1, DefaultSP, 4,12,0);


        addSegment(1,1, DefaultSP, 4,17.5,0);




        addSegment(.5,1, DefaultSP, -30 ,-2,0);

        addSegment(.5,1, DefaultSP, -15,5,0);
        addSegment(.5,1, DefaultSP, -15,18,0);


        addSegment(.5,1, DefaultWP, -37 ,4,0);
        addConcurrentAutoModule(SpecimenReady);

        addSegment(.5,1, DefaultSP, -30 ,-2,0);

        addSegment(.5,1, DefaultSP, -25,5,0);
        addSegment(.5,1, DefaultSP, -25,18,0);






        addSegment(1,1, DefaultSP, -60 ,10,90);









    }


        @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
