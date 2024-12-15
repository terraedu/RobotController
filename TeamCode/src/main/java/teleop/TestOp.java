package teleop;

import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.TeleStatus.BLUE;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TestOp TESTING ONLY", group = "TeleOp")
public class TestOp extends Tele {

    @Override
    public void initTele() {
        voltageScale = 1;
        teleStatus.set(BLUE);

               /**
         * Start code
         */


    }

    @Override
    public void startTele() {



    }

    public boolean isBlueSample(){
        if(colorSensorsNew.getRed() <= 30 && colorSensorsNew.getBlue() <= 100 ){// getGreen >= 45
            return true;}
        return false;
    }

    public boolean isRedSample(){
        if(colorSensorsNew.getRed() <= 40 && colorSensorsNew.getBlue() <= 50 ){ // getGreen >= 50
            return true;}
        return false;
    }

    public boolean isYellowSample(){
        if(colorSensorsNew.getRed() >= 25 && colorSensorsNew.getGreen() >= 45  && colorSensorsNew.getBlue() >= 40 ){
            return true;}
        return false;
    }

    public String isColor(){
      if (isRedSample() == true){ //&& colorSensorsNew.getGreen() <= 60 && colorSensorsNew.getBlue() <= 60
            return "SAMPLE IS RED";
      }
      if (isBlueSample() == true){ //colorSensorsNew.getRed() >= 35 && colorSensorsNew.getRed() <= 85 && colorSensorsNew.getGreen() >= 80 && colorSensorsNew.getGreen() <= 130 &&
            return "SAMPLE IS BLUE";
      }
      if (isYellowSample() == true){//if (colorSensorsNew.getRed() >= 30 && colorSensorsNew.getRed() <= 45 && colorSensorsNew.getGreen() >= 60 & colorSensorsNew.getGreen() <= 75 && colorSensorsNew.getBlue() >= 50 && colorSensorsNew.getBlue() <= 65){
            return "SAMPLE IS YELLOW";
      }
        return "NO SAMPLE";
    }

    @Override
    public void loopTele() {

        //color sensors
//        log.show(ColorSensors.colorSensorsNew.isRedSample());
//        log.show(ColorSensors.colorSensorsNew.isBlueSample());
//        log.show(ColorSensors.colorSensorsNew.isYellowSample());
        log.show("red",colorSensorsNew.getRed());
        log.show("green",colorSensorsNew.getGreen());
        log.show("blue",colorSensorsNew.getBlue());
        log.show("color",isColor());

//        log.show("SAMPLE", isRed());
//        log.show("SAMPLE", isGreen());
//        log.show("SAMPLE", isBlue());




//        /**
//         * Gets Distance
//         */
//
//        log.show("right distance (cm)", distanceSensorsNew.getCMDistanceRight());
//        log.show("left distance (cm)", distanceSensorsNew.getCMDistanceLeft());
//
//        /**
//         * Gets light of color sensor
//         */
//        log.show("light 1", colorSensorsNew.getLight1());
//        log.show("light 2", colorSensorsNew.getLight2());


        /**
         * odo pose
         */
        log.show("pose", odometry.getPose());

//        /**
//         * Outtake Status
//         */
//        log.show("OuttakeStatus", outtakeStatus.get());
//
//        /**
//         * Heading
//         */
//        log.show("Gyro Heading", gyro.getHeading());
//
//
//        /**
//         * lift encoder positions
//         */
//        log.show("Right Motor", lift.motorRight.getPosition());
//        log.show("Left Motor", lift.motorLeft.getPosition());
//
//
//        /**
//         * drive mode
//         */
//        log.show("DriveMode", driveMode.get());
//
    }
//
    }
//
//
