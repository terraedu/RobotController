package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;

@Autonomous(name = "red right", group = "auto", preselectTeleOp = "TerraOp")
public class redright extends AutoFramework {

    @Override
    public void initialize() {

        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        AutoFramework auto = this;
        auto.scan(true, "red", "right");
        outtake.start();
        outtake.close();
    }

    AutoModule PlacePurple = new AutoModule(
            outtake.stageStart(.9),
            outtake.stageOpen1(.1)
    );
    AutoModule Retract = new AutoModule(
            outtake.stageTransfer(.1)
    );

    AutoModule PlaceYellow = new AutoModule(
            lift.stageArm(1,45).attach(outtake.stageEnd(.1))

    );
    AutoModule Place = new AutoModule(
            outtake.stageOpen2(.1),
            lift.stageArm(1,0).attach(outtake.stageTransfer(.1))

    );


    @Override
    public void define() {
        customCase(()-> {
            addTimedSetpoint(1,1,1.5,-10,-55,0);
            addTimedSetpoint(1,1,1.5,-10,-55,-90);
            addTimedSetpoint(1,1,.5,25,-55,-90);
            addTimedSetpoint(1,1,.5,5,-60,-100);


            addAutoModule(PlacePurple);
            addAutoModule(Retract);

            addTimedSetpoint(1,1,1.5,-20,-65,-90);
            addPause(1);
            addAutoModule(PlaceYellow);

            addTimedSetpoint(1,1,1.5,-80,-80,-90);
            addPause(1);

            addTimedSetpoint(1,1,1.5,-84.5,-80,-90);

            addPause(1);
            addAutoModule(Place);
            addTimedSetpoint(1,1,1.5,-70,-80,-90);







            addTimedSetpoint(1,1,.5,-40,-17,180);
            addTimedSetpoint(1,1,.5,-40,-17,90);

            addTimedSetpoint(1,1,1,-75,-13,95);
            addPause(20);



        }, () -> {
            addTimedSetpoint(1,1,1.5,-15,-50,0);
            addTimedSetpoint(1,1,.9,-20,-50,180);
            addTimedSetpoint(1,1,.9,-20,-62,190);

            addAutoModule(PlacePurple);
            addTimedSetpoint(1,1,.9,-10,-62,190);

            addAutoModule(Retract);

            addTimedSetpoint(1,1,1.5,-20,-65,-90);
            addPause(1);
            addAutoModule(PlaceYellow);

            addTimedSetpoint(1,1,1.5,-80,-70,-90);
            addPause(1);

            addTimedSetpoint(1,1,1.5,-84.5,-70,-90);

            addPause(1);
            addAutoModule(Place);
            addTimedSetpoint(1,1,1.5,-70,-70,-90);


            addTimedSetpoint(1,1,.5,-40,-17,180);
            addTimedSetpoint(1,1,.5,-40,-17,90);

            addTimedSetpoint(1,1,1,-70,-13,95);
            addPause(20);





        }, () -> {

            addTimedSetpoint(1,1,1,-23,-30,0);
            addTimedSetpoint(1,1,1,-23,-33,180);

            addAutoModule(PlacePurple);
            addAutoModule(Retract);


            addTimedSetpoint(1,1,1.5,-15,-25,180);
            addTimedSetpoint(1,1,1.5,-25,-25,-90);

            addPause(1);
            addAutoModule(PlaceYellow);

            addTimedSetpoint(1,1,1.5,-80,-50,-90);
            addPause(1);

            addTimedSetpoint(1,1,1.5,-85,-50,-90);

            addPause(1);
            addAutoModule(Place);
            addTimedSetpoint(1,1,1.5,-70,-50,-90);

            addTimedSetpoint(1,1,.5,-40,-17,180);
            addTimedSetpoint(1,1,.5,-40,-17,90);

            addTimedSetpoint(1,1,1,-70,-12,95);
            addPause(20);
        });

    }


}
