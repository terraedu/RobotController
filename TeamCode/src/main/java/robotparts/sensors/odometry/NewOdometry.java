package robotparts.sensors.odometry;

import com.qualcomm.robotcore.hardware.DcMotor;

import geometry.position.Pose;
import geometry.position.Vector;
import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.input.IEncoder;
import util.codeseg.ExceptionCodeSeg;
import util.template.Precision;

import static global.General.hardwareMap;
import static robot.RobotFramework.odometryThread;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;

public class NewOdometry extends RobotPart {

    public double x, y, h;

//    public final ExceptionCodeSeg<RuntimeException> odometryUpdateCode = this::update;
    public GoBildaPinpointDriver odo;


    @Override
    public void init() {
        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-32, 55);
        odo.setEncoderResolution(234.057143);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.REVERSED);
        odo.resetPosAndIMU();

//        odometryThread.setExecutionCode(odometryUpdateCode);
        reset();
    }

    public void update(){

        odo.update();
        Pose2D pos = odo.getPosition();

        h = nanAngle(pos.getHeading(AngleUnit.DEGREES));
        x = nanX((pos.getY(DistanceUnit.MM)/10));
        y = nanY((pos.getX(DistanceUnit.MM)/10));
    }

    private double lastAngle = 0;
    private double lastX = 0;
    private double lastY = 0;


    public double getEncX(){ return odo.getEncoderX();}
    public double getEncY(){ return odo.getEncoderY();}

    int counterH;
    int counterX;
    int counterY;

    //cookery
    private double nanAngle(double h) {
        if (Double.isNaN(h)) {
            counterH++;
            return lastAngle;
        } else if (counterH == 50) {
            h = 0;
            return h;
        } else {
            lastAngle = h;
            return h;
        }
    }
    private double nanX(double x) {
        if (Double.isNaN(x)) {
            counterX++;
            return lastX;
        } else if (counterX == 50) {
            h = 0;
            return h;
        } else {
            lastX = x;
            return x;
        }
    }
    private double nanY(double y) {
        if (Double.isNaN(y)) {
            counterY++;
            return lastY;
        } else if (counterY == 50) {
            h = 0;
            return h;
        } else {
            lastY = y;
            return y;
        }
    }

    public final double getX(){ return x; }
    public final double getY(){ return y; }
    public double getHeading() { return h; }
    public Pose getPose() { return new Pose(x,y,h); }


    public void reset(){
        odo.resetPosAndIMU();
    }


    public void reset(Pose pose){
        odo.resetPosAndIMU();

        x = odo.getPosX(); y = odo.getPosY(); h = odo.getHeading();



    }


}