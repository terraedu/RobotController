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
            addTimedSetpoint(.7,.5,1.5,0,-65,0);
            addTimedSetpoint(.7,.5,1.5,0,-65,-90);
            addTimedSetpoint(.7,.5,.5,20,-65,-90);
            addTimedSetpoint(.7,.5,.5,-5,-65,-90);


            addAutoModule(PlacePurple);
            addAutoModule(Retract);
            addTimedSetpoint(1,.8,.9,-20,-25,180);
            addTimedSetpoint(1,.8,2,-70,-10,180);



        }, () -> {
            addTimedSetpoint(1,.8,2,-15,-50,0);
            addTimedSetpoint(1,.8,.9,-20,-50,180);
            addTimedSetpoint(1,.8,.9,-20,-62,190);

            addAutoModule(PlacePurple);
            addAutoModule(Retract);
            addTimedSetpoint(1,.8,.9,-20,-30,180);
            addTimedSetpoint(1,.8,1.2,-90,-30,180);






        }, () -> {

            addTimedSetpoint(.7,.7,2,-27,-40,0);
            addTimedSetpoint(.7,.5,2,-27,-43,180);
            addAutoModule(PlacePurple);
            addAutoModule(Retract);
            addTimedSetpoint(1,.8,.9,-20,-25,180);
            addTimedSetpoint(1,.8,1.2,-90,-20,180);
        });

    }


}
