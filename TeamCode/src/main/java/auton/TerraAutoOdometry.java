package auton;

import static global.General.bot;
import static global.General.fieldPlacement;
import static global.General.fieldSide;
import static global.Modes.Height.HIGH;

import android.support.v4.os.IResultReceiver;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import automodules.AutoModule;
import autoutil.AutoFramework;
import elements.Field;
import elements.FieldPlacement;
import elements.FieldSide;
import elements.GameItems;
import geometry.position.Pose;

//@Autonomous(name = "TerraAutoOdometry" +
//        "", group = "auto", preselectTeleOp = "TerraOp")
public class TerraAutoOdometry extends AutoFramework {


    @Override
    public void initialize() {
        this.setConfig(NonstopConfig);
        bot.saveLocationOnField();
    }

    @Override
    public void define() {
        addTimedSetpoint(1.0, 0.8, 10, 0, 115, 0);
        addSegment(1.0, DefaultSP, 0, 115, 0);
    }

    @Autonomous(name = "TerraOdoTest", group = "auto", preselectTeleOp = "TerraOp")
    public static class TerraOdoTest extends TerraAutoOdometry {{ fieldSide = FieldSide.BLUE; fieldPlacement = FieldPlacement.LOWER; startPose = new Pose(20.5, Field.width/2.0 - Field.tileWidth - GameItems.Cone.height - 16,90); }}


}

