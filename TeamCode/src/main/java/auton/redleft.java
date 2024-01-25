package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.TeamProp;

@Autonomous(name = "red left", group = "auto", preselectTeleOp = "TerraOp")
public class redleft extends AutoFramework {

    @Override
    public void initialize() {

        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
//        AutoFramework auto = this;
//        auto.scan(true, "red", "left");
        propCaseDetected = TeamProp.FIRST;
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
            addTimedSetpoint(1,1,1,-29,30,0);
            addTimedSetpoint(1,1,1,-29,35,160);



//            addAutoModule(PlacePurple);
//            addAutoModule(Retract);
//            addTimedSetpoint(1,1,.5,-29,33,180);
        }, () -> {
            addTimedSetpoint(1,1,1.5,0,-50,0);
            addTimedSetpoint(1,1,1,30,-50,180);
            addTimedSetpoint(1,1,1,30,-62,165);

            addAutoModule(PlacePurple);
            addTimedSetpoint(1,1,.9,10,-62,165);

            addAutoModule(Retract);

            addTimedSetpoint(1,.8,.9,10,-30,180);






        }, () -> {

            addTimedSetpoint(1,1,1.5,10,-65,0);
            addTimedSetpoint(1,1,1.5,10,-65,90);
            addTimedSetpoint(1,1,.5,-40,-65,90);
            addTimedSetpoint(1,1,.5,7,-65,100);


            addAutoModule(PlacePurple);

            addAutoModule(Retract);
            addTimedSetpoint(1,1,.5,0,-65,100);









        });

    }


}
