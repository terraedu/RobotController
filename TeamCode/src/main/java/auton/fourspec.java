package auton;//package auton.rightautos;

import static global.General.bot;
import static global.General.voltageScale;

import android.os.DeadObjectException;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import robotparts.RobotPart;
import util.Timer;

@Autonomous(name= "FourSpec", group = "auto")
public class fourspec extends AutoFramework {
    Timer timer2 = new Timer();


    @Override
    public void initialize() {
        voltageScale = 1;
        this.setConfig(NonstopConfig);
        lift.maintain();
        bot.saveLocationOnField();
//        outtake.moveLinkStart();
//        outtake.moveStart();




    }

    AutoModule specimenout = new AutoModule(




    );
    AutoModule specimenin = new AutoModule(





    );
    AutoModule SpecimenReady = new AutoModule(







    );
    AutoModule SpecimenLocked = new AutoModule(






    );
    AutoModule specimenleave = new AutoModule(








    );
    AutoModule Intake = new AutoModule(




    );
    AutoModule SpecimenDrop = new AutoModule(




    );
    AutoModule Grab = new AutoModule(





    );
    @Override
    public void define() {

//        addSegment(1,1, DefaultSP,-5,0,0);
//
        drive.moveTime(0.5,0,0.4,.5);






    }


    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }
}
