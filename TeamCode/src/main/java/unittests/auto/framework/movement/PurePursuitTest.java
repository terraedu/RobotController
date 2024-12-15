package unittests.auto.framework.movement;

import autoutil.AutoFramework;
import unittests.auto.AutoUnitTest;

public class PurePursuitTest extends AutoUnitTest {
    /**
     * Test pure pursuit
     */

    @Override
    protected void start() {
        setAuto(new AutoFramework() {
            @Override
            public void define() {
                setConfig(DefaultConfig);
            }

            @Override
            public void initialize() {
                addWaypoint(0, 40, 0);
            }
        });
    }
}