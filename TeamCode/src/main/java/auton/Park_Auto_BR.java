package auton;

import autoutil.AutoFramework;

public class Park_Auto_BR extends AutoFramework {
    @Override
    public void define() {
        drive.move(0.2, 1, 0);
        drive.stop();
    }

    @Override
    public void initialize() {

    }
}
