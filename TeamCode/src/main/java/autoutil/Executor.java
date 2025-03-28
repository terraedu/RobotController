package autoutil;

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
        whileActive(() -> !stage.shouldStop(), stage::loop);
        stage.runOnStop();
    }

    @Override
    public boolean condition() { return auto.condition(); }
}