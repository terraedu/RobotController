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

    @Override
    public void define() {
        customCase(()-> {
            addTimedSetpoint(1,1,1.5,-10,-65,0);
            addTimedSetpoint(1,1,1.5,-10,-65,-90);
            addTimedSetpoint(1,1,.5,25,-65,-90);
            addTimedSetpoint(1,1,.5,5,-65,-100);


            addAutoModule(PlacePurple);
            addAutoModule(Retract);
            addTimedSetpoint(1,1,.5,-40,-17,180);
            addTimedSetpoint(1,1,.5,-40,-17,90);

            addTimedSetpoint(1,1,1,-70,-12,85);
            addPause(20);



        }, () -> {
            addTimedSetpoint(1,1,1.5,-15,-50,0);
            addTimedSetpoint(1,1,.9,-20,-50,180);
            addTimedSetpoint(1,1,.9,-20,-62,190);

            addAutoModule(PlacePurple);
            addTimedSetpoint(1,1,.9,-10,-62,190);

            addAutoModule(Retract);
            addTimedSetpoint(1,1,.5,-40,-17,180);
            addTimedSetpoint(1,1,.5,-40,-17,90);

            addTimedSetpoint(1,1,1,-70,-12,85);
            addPause(20);





        }, () -> {

            addTimedSetpoint(1,1,1,-29,-30,0);
            addTimedSetpoint(1,1,1,-29,-33,180);

            addAutoModule(PlacePurple);
            addAutoModule(Retract);
            addTimedSetpoint(1,1,.5,-40,-17,180);
            addTimedSetpoint(1,1,.5,-40,-17,90);

            addTimedSetpoint(1,1,1,-70,-7,85);
            addPause(20);
        });

    }


}
