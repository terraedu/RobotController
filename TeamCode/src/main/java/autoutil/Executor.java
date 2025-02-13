package autoutil;

import static global.General.log;
import static robot.RobotUser.drive;
import static robot.RobotUser.odometry;

import automodules.stage.Stage;
import autoutil.generators.Generator;
import autoutil.reactors.Reactor;
import util.template.Iterator;

public class Executor implements Iterator {

    protected final Reactor reactor;
    protected final Generator generator;
    protected final AutoFramework auto;

    public Executor(AutoFramework auto, Generator generator, Reactor reactor){
        this.auto = auto; this.generator = generator; this.reactor = reactor;
    }

    public final void followPath() {
        reactor.init();
        reactor.setTarget(generator.getTarget());
        Stage stage = generator.getStage(reactor);
        stage.start();
         whileActive(() -> !stage.shouldStop(), ()-> {
            stage.loop(); odometry.update(); log.show(odometry.getPose());
        }
        );
        stage.runOnStop();
    }

    @Override
    public boolean condition() {return auto.condition();}}