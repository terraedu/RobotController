package auton.blueauton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.Case;
import elements.TeamProp;
import robotparts.RobotPart;

@Autonomous(name = "B. Left PY&P ", group = "auto", preselectTeleOp = "TerraOp")
public class B_LEFT_PY_P extends AutoFramework {
    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
        outtake.moveStart();
        outtake.openClawHalf();
        propCaseDetected = TeamProp.FIRST;
        AutoFramework auto = this;
        auto.scan(true, "blue", "left");

    }
    AutoModule Extake = new AutoModule(
            intake.moveTime(.5,.25)


    );
    AutoModule Drop = new AutoModule(
            outtake.stageMiddle(.5).attach(outtake.stageClose(.5)),
            lift.stageLift(.7,35),
            outtake.stageEnd(1),
            RobotPart.pause(.5),
            outtake.stageOpen(.2),
            RobotPart.pause(.5)
    );
    AutoModule Reset = new AutoModule(
            outtake.stageStart(1),
            lift.stageLift(.6,0)

    );
    @Override
    public void define() {
        customCase(() -> {
            addTimedSetpoint(1.0,1,.2,0,15,0);
            addTimedSetpoint(1.0, 1,1, 0, 35, 90);
            addTimedSetpoint(1.0,1,1,-42,65,90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,-93,45,90);
            addAutoModule(Drop);
            RobotPart.pause(1);
            addAutoModule(Reset);
            addTimedSetpoint(1.0,1,.2,-85,45,90);
            addTimedSetpoint(1.0,1,1,-85,5,0);
            addTimedSetpoint(1.0,1,1,-115,5,0);

        }, () -> {
            addTimedSetpoint(1.0,1,.2,0,15,0);
            addTimedSetpoint(1.0, 1,1, 0, 35, 90);
            addTimedSetpoint(1.0,1,1,-35,95,90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,-113,50,90);
            addAutoModule(Drop);
            RobotPart.pause(1);
            addAutoModule(Reset);
            addTimedSetpoint(1.0,1,.2,-85,45,90);
            addTimedSetpoint(1.0,1,1,-85,5,0);
            addTimedSetpoint(1.0,1,1,-115,5,0);

        }, () -> {
            addTimedSetpoint(1.0, 1, .2, 0, 15, 0);
            addTimedSetpoint(1.0, 1, 1, 16, 60, 90);
            addAutoModule(Extake);
            addTimedSetpoint(1.0,1,1,-113,60,90);
            addAutoModule(Drop);
            RobotPart.pause(1);
            addAutoModule(Reset);
            addTimedSetpoint(1.0,1,.2,-85,45,90);
            addTimedSetpoint(1.0,1,1,-85,5,0);
            addTimedSetpoint(1.0,1,1,-115,5,0);
        });//
    }
    @Override
    public void postProcess() {
        autoPlane.reflectY();
        autoPlane.reflectX();
    }


}
