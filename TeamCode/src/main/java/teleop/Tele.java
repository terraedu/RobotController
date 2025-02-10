package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.opencv.core.Size;

import automodules.AutoModuleUser;
import autoutil.vision.SampleScanner;
import global.Common;
import global.Modes;
import robot.RobotUser;

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
//    public YoloScanner yoloScanner;
    public SampleScanner sampleScanner = new SampleScanner();
    public static Size frameSize = new Size(640, 480);

    public void scan(boolean view){
        camera.start(view, frameSize);
        camera.camera.setPipeline(sampleScanner);
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
        odometry.getPose();
    }

    @Override
    public final void stop() {
        stopTele();
        end();
    }
}
