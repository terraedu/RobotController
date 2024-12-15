package robotparts.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import automodules.stage.Exit;
import robotparts.RobotPart;

import static global.General.hardwareMap;
import static global.Modes.RobotStatus.PLACING;
import static global.Modes.robotStatus;

public class DistanceSensors extends RobotPart {
    public DistanceSensor dsol, dsor;

    @Override
    public void init() {
            dsol = hardwareMap.get(DistanceSensor.class, "dsol");
            dsor = hardwareMap.get(DistanceSensor.class, "dsor");


    }



    public double getCMDistanceLeft() {
        return dsol.getDistance(DistanceUnit.CM);
    }
    public double getCMDistanceRight() {
        return dsor.getDistance(DistanceUnit.CM);
    }



    public double getMETERDistance() {
        return dsol.getDistance(DistanceUnit.METER);
    }

    public boolean isClose(){

        return (dsor.getDistance(DistanceUnit.CM) < 17) && (dsor.getDistance(DistanceUnit.CM) < 17);
    }

    public boolean jigglepeak(){

        return (dsor.getDistance(DistanceUnit.CM) < 15) && (dsor.getDistance(DistanceUnit.CM) < 15);
    }



    public boolean isClosey(){

        return (dsor.getDistance(DistanceUnit.CM) < 80) && (dsor.getDistance(DistanceUnit.CM) < 80);
    }

    public boolean left(){
        return (dsor.getDistance(DistanceUnit.CM) > dsor.getDistance(DistanceUnit.CM));
    }
    public boolean right(){
        return (dsor.getDistance(DistanceUnit.CM) < dsor.getDistance(DistanceUnit.CM));
    }


    public Exit exitDrive(){return new Exit(this::isClose);}
    public Exit exitDriveclose(){return new Exit(this::jigglepeak);}

    public Exit exitStrafe(){return new Exit(this::isClosey);}
    public Exit exitRight(){return new Exit(this::right);}
    public Exit exitLeft(){return new Exit(this::left);}




    //    public Stage moveDist(double t){ return super.customTime(this::moveDist, t);}
    public boolean IsRightClose(){
        if(robotStatus.modeIs(PLACING)) {
            return dsor.getDistance(DistanceUnit.CM) < 50;
        }
        return false;
    }
    public boolean IsLeftClose(){
        if(robotStatus.modeIs(PLACING)) {
            return dsol.getDistance(DistanceUnit.CM) < 30;
        }
        return false;
    }

    public boolean IsRightSuperClose(){
        return dsor.getDistance(DistanceUnit.CM) == 20;
    }
    public boolean IsLeftSuperClose(){
        return dsol.getDistance(DistanceUnit.CM) == 20;
    }


}
