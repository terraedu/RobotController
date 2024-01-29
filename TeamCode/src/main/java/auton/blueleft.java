package auton;

import static global.General.bot;
import static global.General.voltageScale;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;

@Autonomous(name = "blue left", group = "auto", preselectTeleOp = "TerraOp")
public class blueleft extends AutoFramework {

    @Override
    public void initialize() {
        voltageScale = 1;
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        AutoFramework auto = this;
        auto.scan(true, "blue", "left");
        outtake.start();
        outtake.close();
    }

    AutoModule PlacePurple = new AutoModule(
            outtake.stageStart(.9),
            outtake.stageOpen2(.1)
    );
    AutoModule Retract = new AutoModule(
            outtake.stageTransfer(.1)
    );

    AutoModule PlaceYellow = new AutoModule(
            lift.stageArm(1,47).attach(outtake.stageEnd(.1))

    );
    AutoModule Place = new AutoModule(
            outtake.stageOpen1(.1),
            lift.stageArm(1,0).attach(outtake.stageTransfer(.1))

    );

    @Override
    public void define() {
        customCase(()-> {
            addTimedSetpoint(1,1,1,25,-30,0);
            addTimedSetpoint(1,1,1,34,-33,-180);

            addAutoModule(PlacePurple);
            addAutoModule(Retract);


            addTimedSetpoint(1,1,1.5,35,-25,-180);
            addTimedSetpoint(1,1,1.5,45,-25,90);

            addPause(.5);
            addAutoModule(PlaceYellow);

            addTimedSetpoint(1,1,1.5,87,-50,90);
            addPause(.5);

            addTimedSetpoint(1,1,1.5,91,-45,93);

            addPause(.5);
            addAutoModule(Place);
            addTimedSetpoint(1,1,1.5,70,-50,90);


            addTimedSetpoint(1,1,.5,40,-17,180);
            addTimedSetpoint(1,1,.5,40,-17,90);

            addTimedSetpoint(1,1,1,90,-7,85);
            addPause(20);


        }, () -> {
            addTimedSetpoint(1,1,1.5,15,-50,0);
            addTimedSetpoint(1,1,.9,20,-50,-180);
            addTimedSetpoint(1,1,.9,20,-50,-190);

            addAutoModule(PlacePurple);
            addTimedSetpoint(1,1,.9,10,-62,-190);

            addAutoModule(Retract);
            addTimedSetpoint(1,1,1.5,20,-40,-190);


            addTimedSetpoint(1,1,1.5,30,-45,90);
            addPause(.5);
            addAutoModule(PlaceYellow);

            addTimedSetpoint(1,1,1.5,80,-70,90);
            addPause(.5);

            addTimedSetpoint(1,1,1.5,91,-55,90);

            addPause(.5);
            addAutoModule(Place);
            addTimedSetpoint(1,1,1.5,70,-55,90);

            addTimedSetpoint(1,1,.5,40,-17,-180);
            addTimedSetpoint(1,1,.5,40,-17,-90);

            addTimedSetpoint(1,1,1,90,-7,-85);
            addPause(20);





        }, () -> {
            addTimedSetpoint(1,1,1.5,10,-55,0);
            addTimedSetpoint(1,1,1.5,10,-55,90);
            addTimedSetpoint(1,1,.5,-25,-55,90);
            addTimedSetpoint(1,1,.5,2,-60,100);


            addAutoModule(PlacePurple);
            addAutoModule(Retract);

            addTimedSetpoint(1,1,1.5,20,-65,90);
            addPause(.5);
            addAutoModule(PlaceYellow);

            addTimedSetpoint(1,1,1.5,80,-70,90);
            addPause(.5);

            addTimedSetpoint(1,1,1.5,91,-70,90);

            addPause(.5);
            addAutoModule(Place);
            addTimedSetpoint(1,1,1.5,70,-80,90);




            addTimedSetpoint(1,1,.5,40,-17,-180);
            addTimedSetpoint(1,1,.5,40,-17,-90);

            addTimedSetpoint(1,1,1,90,-7,-85);
            addPause(20);
        });

    }


}
