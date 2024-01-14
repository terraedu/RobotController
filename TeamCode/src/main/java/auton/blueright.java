package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;

@Autonomous(name = "blue right", group = "auto", preselectTeleOp = "TerraOp")
public class blueright extends AutoFramework {

    @Override
    public void initialize() {

        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        AutoFramework auto = this;
        auto.scan(true, "blue", "right");
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

    @Override
    public void define() {
        customCase(()-> {
            addTimedSetpoint(1,1,1.5,-10,-65,0);
            addTimedSetpoint(1,1,1.5,-10,-65,-90);
            addTimedSetpoint(1,1,.5,40,-65,-90);
            addTimedSetpoint(1,1,.5,0,-65,-100);


            addAutoModule(PlacePurple);

            addAutoModule(Retract);
            addTimedSetpoint(1,1,.5,0,-65,-100);



        }, () -> {
            addTimedSetpoint(1,1,1.5,0,-50,0);
            addTimedSetpoint(1,1,.9,-15,-50,-180);
            addTimedSetpoint(1,1,.9,-15,-62,-165);

            addAutoModule(PlacePurple);
            addTimedSetpoint(1,1,.9,-10,-62,-165);

            addAutoModule(Retract);

            addTimedSetpoint(1,.8,.9,-10,-30,-180);






        }, () -> {
            addTimedSetpoint(1,1,1,-29,-30,0);
            addTimedSetpoint(1,1,1,-29,-35,-160);



            addAutoModule(PlacePurple);
            addAutoModule(Retract);
            addTimedSetpoint(1,1,.5,-29,-33,-180);






        });

    }


}
