package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import androidx.annotation.CallSuper;
import automodules.AutoModuleUser;
import autoutil.vision.CaseScannerRect;
import autoutil.vision.RecorderPipeline;
import autoutil.vision.SampleScanner;
import autoutil.vision.Scanner;
import elements.FieldSide;
import global.Common;
import global.General;
import global.Modes;
import robot.RobotUser;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

import org.openftc.easyopencv.OpenCvPipeline;

public abstract class Tele extends OpMode implements Common, RobotUser, AutoModuleUser, Modes {
    /**
     * Base class for teleops
     * NOTE: If you are creating a real teleop then call activate in initTele
     */


    /**
     * Init method runs when the user clicks the init button to run a teleop
     */
    public void initTele(){}

    /**
     * Start method runs when the user clicks the start button after init
     */
    public void startTele() {}

    /**
     * Loop method runs over and over after start
     */
    public abstract void loopTele();

    /**
     * Stop method runs when the program ends
     */
    public void stopTele(){}


    /**
     * Internal final teleop methods
     * NOTE: Do not use or override these
     */

    // Scanner Code
    public OpenCvPipeline Recorder;
    public OpenCvPipeline Scanner;

    public void scan(boolean view){
        Recorder = new RecorderPipeline();
        Scanner = new SampleScanner();
        camera.start(true);
        camera.camera1.setPipeline(Scanner);
    }

    @Override
    public final void init() {
        reference(this);
        activate();
        initTele();
    }

    @Override
    public final void start(){
        ready();
        startTele();
    }

    @Override
    public final void loop() {
        loopTele();
        update(true);
    }

    @Override
    public final void stop() {
        stopTele();
        end();
    }
}
