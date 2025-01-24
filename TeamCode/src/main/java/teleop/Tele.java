package teleop;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import automodules.AutoModuleUser;
import autoutil.vision.SampleScanner;
import autoutil.vision.yolovision.YoloScanner;
import global.Common;
import global.Modes;
import robot.RobotUser;

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
    public OpenCvPipeline yoloScanner;
    public OpenCvPipeline sampleScanner;

    public void scan(boolean view){
        yoloScanner = new YoloScanner();
        sampleScanner = new SampleScanner();
        camera.start(true);
        camera.camera1.setPipeline(sampleScanner);
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
