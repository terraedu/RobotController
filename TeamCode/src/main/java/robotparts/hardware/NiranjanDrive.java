package robotparts.hardware;

import automodules.AutoModule;
import automodules.stage.Stage;
import geometry.position.Vector;
import math.misc.Logistic;
import math.polynomial.Linear;
import robotparts.RobotPart;
import robotparts.electronics.ElectronicType;
import robotparts.electronics.continuous.CMotor;
import util.Timer;
import util.codeseg.ReturnCodeSeg;
import util.template.Precision;

import static global.General.bot;
import static global.Modes.Drive.FAST;
import static global.Modes.Drive.MEDIUM;
import static global.Modes.Drive.SLOW;
import static global.Modes.driveMode;

public class NiranjanDrive {
    public class Drive extends RobotPart {
        public CMotor fr, br, fl, br;
        @Override
        public void init() {

        }
    }
}
