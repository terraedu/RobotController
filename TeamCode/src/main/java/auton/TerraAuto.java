package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.AutoFramework;
import elements.Case;
import elements.FieldSide;

public class TerraAuto extends AutoFramework {

    @Override
    public void initAuto() {
        setConfig(mecanumDefaultConfig);
    }

    @Override
    public void preProcess() {
        caseDetected = Case.FIRST;
        if(!upper&&isFlipped() || upper&&!isFlipped()){ flipCases();}
    }

    @Override
    public void define() {
        addWaypoint(0, 80, 0);
        addWaypoint(0, 111, 0);
        addWaypoint(0, 130, 35);
        addSetpoint(-7, 140, 55);
        customNumber(5, i -> {
            addWaypoint(10, 128, 75);
            addWaypoint(40, 126, 90);
            addSetpoint(54, 126, 90);
            addWaypoint(40, 126, 90);
            addWaypoint(10, 128, 75);
            addSetpoint(-7, 140, 55);
        });
        customCase(() -> {
            addWaypoint(-7, 128, 90);
            addWaypoint(-20, 128, 90);
            addWaypoint(-55, 130, 70);
            addWaypoint(-60, 132, 45);
            addSetpoint(-62, 70, 0);
        }, () -> {
            addWaypoint(0, 130, 35);
            addWaypoint(0, 105, 0);
            addSetpoint(0, 70, 0);
        }, () -> {
            addWaypoint(7, 128, 90);
            addWaypoint(20, 128, 90);
            addWaypoint(48, 124, 70);
            addWaypoint(50, 116, 50);
            addWaypoint(56, 95, 0);
            addSetpoint(58, 70, 0);
        });
    }

    @Override
    public void postProcess() { if(upper){ flip(); } }

    protected boolean upper = false;
//
//    @Autonomous(name = "TerraAutoLowerBlue", group = "auto")
//    public static class TerraAutoLowerBlue extends TerraAuto {{ fieldSide = FieldSide.BLUE; }}
//    @Autonomous(name = "TerraAutoLowerRed", group = "auto")
//    public static class TerraAutoLowerRed extends TerraAuto {{ fieldSide = FieldSide.RED; }}
//    @Autonomous(name = "TerraAutoUpperBlue", group = "auto")
//    public static class TerraAutoUpperBlue extends TerraAuto {{ fieldSide = FieldSide.BLUE; upper = true; }}
//    @Autonomous(name = "TerraAutoUpperRed", group = "auto")
//    public static class TerraAutoUpperRed extends TerraAuto {{ fieldSide = FieldSide.RED; upper = true; }}

}