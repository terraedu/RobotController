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

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;

public class NewOdometry extends RobotPart {

    public double x, y, h;

    public GoBildaPinpointDriver odo;


    @Override
    public void init() {
        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-131.5, 55);
        odo.setEncoderResolution(8192);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        reset();
    }

    public void update(){


        h = cookery(odo.getHeading());
        x = odo.getPosX();
        y = odo.getPosY();



    }

    private double lastAngle = 0;

    private double cookery(double angle) {
        if (Double.isNaN(angle)) {
            return lastAngle;
        } else {
            lastAngle = angle;
            return angle;
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