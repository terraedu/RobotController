package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import javax.crypto.Mac;

import automodules.AutoModule;
import automodules.AutoModuleUser;
import autoutil.vision.JunctionScannerAll;
import elements.FieldPlacement;
import geometry.framework.Point;
import geometry.position.Pose;
import geometry.position.Vector;
import math.polynomial.Linear;
import robotparts.electronics.output.OLed;
import teleutil.TeleTrack;
import teleutil.button.Button;
import teleutil.button.OnNotHeldEventHandler;
import util.Timer;
import util.codeseg.CodeSeg;
import util.template.Precision;

import static global.General.bot;
import static global.General.cameraMonitorViewId;
import static global.General.fault;
import static global.General.fieldPlacement;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;
import static global.General.voltageScale;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.GamepadMode.*;
import static global.Modes.OuttakeStatus.*;
import static global.Modes.Height.*;
import static teleutil.button.Button.*;
import static teleutil.TeleTrack.*;

@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOp extends Tele {

    @Override
    public void initTele() {

    }

    @Override
    public void loopTele() {

        double scale = 0.5;


        if (gamepad1.dpad_up){
            drive.move(scale,0,0);
        }else if(gamepad1.dpad_right){
            drive.move(0,scale,0);
        }else if(gamepad1.dpad_left){
            drive.move(0,-scale,0);
        }else if(gamepad1.dpad_down){
            drive.move(-scale,0,0);
        }else {
            drive.move(gph1.ry*scale,gph1.rx*scale,gph1.lx*scale);
        }
    }

}


