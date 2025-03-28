package automodules;

import static global.Modes.RobotStatus.DRIVING;
import static global.Modes.RobotStatus.INTAKING;
import static global.Modes.RobotStatus.SAMPLE;
import static global.Modes.RobotStatus.SPECIMEN;
import static global.Modes.robotStatus;

import org.firstinspires.ftc.teamcode.R;

import auton.Auto;
import robot.RobotUser;
import robotparts.RobotPart;

public interface AutoModuleUser extends RobotUser {

    AutoModule DRIVINGMODE = new AutoModule().setStartCode(()-> robotStatus.set(DRIVING));
    AutoModule SPECIMENMODE = new AutoModule().setStartCode(()-> robotStatus.set(SPECIMEN));
    AutoModule SAMPLEMODE = new AutoModule().setStartCode(()-> robotStatus.set(SAMPLE));
    AutoModule resetturret = new AutoModule(intake.turretReset(.1));

    // fat automodules
    AutoModule intakeOut = new AutoModule(
            extendo.stageLift(1,25).attach(            outtake.clawRelease(.1)),
            intake.intakeSeek(.1)

    ).setStartCode(()-> robotStatus.set(INTAKING));

    AutoModule clearIntake = new AutoModule(

    );

    AutoModule intakeIn = new AutoModule(
            intake.clawDown(.1),
            intake.clawGRAB(.1),
            RobotPart.pause(.1)
            ,            intake.init(.1).attach(outtake.upSpecimen(.1)),
            extendo.stageLift(1,-1).attach(intake.clawAdjust(.1)),
            RobotPart.pause(.05),
            intake.clawGRAB(.1)
            ,(outtake.init(.1)),
            RobotPart.pause(.05),

            outtake.clawGrab(.1),
            RobotPart.pause(.05),
            intake.clawRELEASE(0.1)


    ).setStartCode(()-> robotStatus.set(DRIVING));

    AutoModule high = new AutoModule(


            lift.stageLift(1, 56).attach(outtake.placeHigh(0.1))
//            intake.init(0.1)
    ).setStartCode(()-> robotStatus.set(SAMPLE));

    AutoModule down = new AutoModule(
            outtake.clawRelease(0.1),
            RobotPart.pause(.05),

            outtake.init(0.1),
            lift.stageLift(1, 0)
//            intake.init(0.1)
    ).setStartCode(()-> robotStatus.set(DRIVING));



}