package robotparts.sensors;

import static global.General.hardwareMap;
import static global.Modes.TeleStatus.BLUE;
import static global.Modes.TeleStatus.RED;
import static global.Modes.teleStatus;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRColor;

import automodules.stage.Exit;
import automodules.stage.Stage;
import auton.redauton.RED_CLOSE_PY_P;
import elements.GameElement;
import global.Modes;
import robotparts.RobotPart;

public class ColorSensors extends RobotPart {
    public ColorSensor cso1, cso2;
    public boolean sample = true;

    @Override
    public void init() {
        cso1 = hardwareMap.get(ColorSensor.class, "cso1");
        cso2 = hardwareMap.get(ColorSensor.class, "cso2");


    }



    public int getLight1(){

        return cso1.alpha();
    }
    public int getLight2(){
        return cso2.alpha();
    }

//    public int getRed(){ return cso1.red();}
//    public int getGreen(){ return cso1.green();}
//    public int getBlue(){ return cso1.blue();}
    public int getRed(){ return cso1.red();}
    public int getGreen(){ return cso1.green();}
    public int getBlue(){ return cso1.blue();}

//    double cso1Val = cso1.alpha();
//    double cso2Val = cso2.alpha();

//    public double endIntake1(){
//        return cso1Val = 501;
//
//    }
//
//    public double endIntake2(){
//        return cso2Val = 501;
//
//    }

    public GameElement isObject(){
        if (cso1.alpha() > 500 && cso2.alpha() > 500){
            return GameElement.PIXEL1;
        }else{
            return GameElement.NONE;
        }

    }

    public GameElement isoneObject(){
        if (cso1.alpha() > 500 || cso2.alpha() > 500){
            return GameElement.PIXEL1;
        }else{
            return GameElement.NONE;
        }

    }

    public GameElement isObjectGone(){
        if (cso1.alpha() < 500 && cso2.alpha() < 500){
            return GameElement.PIXELGONE;
        }else{
            return GameElement.NONE;
        }

    }

    public GameElement isredSample(){
        if (teleStatus.equals((BLUE)))
            return GameElement.WRONGCOLOR;
        if (teleStatus.equals(RED) && cso1.green() <= 80 && cso1.red() <= 60) {
            return GameElement.REDSAMPLE;
        }else{
            return GameElement.NONE;
        }
    }

    public GameElement isblueSample(){
        if (teleStatus.equals((RED)))
            return GameElement.WRONGCOLOR;
        if (teleStatus.equals(BLUE) && cso1.blue() <= 255 && cso1.blue() >= 200){
            return GameElement.BLUESAMPLE;
        }else{
            return GameElement.NONE;
        }
    }

    public GameElement isyellowSample(){
        if (cso1.red() >= 65 && cso1.green() >= 80){
            return GameElement.YELLOWSAMPLE;
        }else{
            return GameElement.NONE;
        }
    }

    public GameElement sampleIn(){
        if (cso1.alpha() > 500 || cso1.alpha() > 500);
        return GameElement.SAMPLE;
    }

    public boolean isRedSample(){
        return isredSample().equals(GameElement.REDSAMPLE);
    }

    public boolean isBlueSample(){
        return isblueSample().equals(GameElement.BLUESAMPLE);
    };;

    public boolean isYellowSample(){ return isyellowSample().equals(GameElement.YELLOWSAMPLE);}

    public boolean isSampleIn(){
        return isObject().equals(GameElement.SAMPLE);
    }

    public boolean isPixel1(){
        return isObject().equals(GameElement.PIXEL1);
    }
    public boolean isPixel(){
        return isoneObject().equals(GameElement.PIXEL1);
    }

    public boolean isPixelGone(){
        return isObjectGone().equals(GameElement.PIXELGONE);
    }


    public Exit exitIntake(){return new Exit(this::isPixel1);}
    public Exit exitIntake2(){return new Exit(this::isPixel);}

    public Exit exitExtake(){return new Exit(this::isPixelGone);}


    public Exit exitRed(){return new Exit(this::isRedSample);}
    public Exit exitBlue(){return new Exit(this::isBlueSample);}
    public Exit exitYellow(){return new Exit(this::isYellowSample);}
    public Exit exitSample(){return new Exit(this::isSampleIn);}




}
