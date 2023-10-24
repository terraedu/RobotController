package auton;

import autoutil.AutoFramework;

public class TerraAutoCenterStageBlueBottom extends AutoFramework {
    @Override
    public void define() {
        // THIS IS ONLY A GENERAL POINT NOT ACTUAL CODE
        // ALSO THE VALUES ARE HIGHLY INACCURATE AND MUST BE TESTED
        addTimedSetpoint(1.0, 0.5, 2, 0, 50, 90);
        addTimedSetpoint(1.0, 0.5, 2, 0, 45, 0);
        addTimedSetpoint(1.0, 0.5, 4, 0, -132, 180);
        addTimedSetpoint(1.0, 0.5, 4, 0, -132, 180);
        addTimedSetpoint(1.0, 0.5, 4, 0, -132, 0);
        addTimedSetpoint(1.0, 0.5, 1, 25, 0, 0);
        addTimedSetpoint(1.0, 0.5, 1, 0, -15, 0);

    }

    @Override
    public void initialize() {

    }
}
