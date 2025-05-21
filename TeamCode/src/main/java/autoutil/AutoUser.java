package autoutil;

import autoutil.generators.Generator;
import autoutil.generators.LineGen;
import autoutil.generators.PoseGen;
import autoutil.reactors.JunctionReact;
import autoutil.reactors.JunctionReact2;
import autoutil.reactors.NonstopReact;
import autoutil.reactors.NStopNewReact;
import autoutil.reactors.Reactor;
import autoutil.reactors.SloDownStopReact;
import util.codeseg.ReturnCodeSeg;
import util.template.ParameterConstructor;

public interface AutoUser {


    // TOD5done Fix naming conventions (drop mecanum, setpoint -> SP)
    // SETPOINT -> SP, PURE PURSUIT -> PP, REACTOR -> REACT, SOME CASES OF MECANUM ->, NO -> N, SLOW -> SLO, WAYPOINT -> WP


    ReturnCodeSeg<LineGen> lineGen = generator(LineGen.class);
    ReturnCodeSeg<PoseGen> poseGen = generator(PoseGen.class);

    ReturnCodeSeg<autoutil.reactors.PIDReact> PIDReact = reactor(autoutil.reactors.PIDReact.class);
    ReturnCodeSeg<autoutil.reactors.PPReact> PPReact = reactor(autoutil.reactors.PPReact.class);
    ReturnCodeSeg<JunctionReact> mecJunctionReact = reactor(JunctionReact.class);
    ReturnCodeSeg<JunctionReact2> mecJunctionReact2 = reactor(JunctionReact2.class);
    ReturnCodeSeg<NonstopReact> mecNonstopReact = reactor(NonstopReact.class);
    ReturnCodeSeg<NonstopReact.NonstopReactSP> mecNonstopReactSP = reactor(NonstopReact.NonstopReactSP.class);
    ReturnCodeSeg<NonstopReact.NonstopReactTurnSP> turnReactSP = reactor(NonstopReact.NonstopReactTurnSP.class);
    ReturnCodeSeg<NStopNewReact> nStopNewReact = reactor(NStopNewReact.class);
    ReturnCodeSeg<NStopNewReact.NStopNewReactHalt> nStopNewReactHalt = reactor(NStopNewReact.NStopNewReactHalt.class);
    ReturnCodeSeg<NStopNewReact.NStopNewReactNHeading> nStopNewReactNHeading = reactor(NStopNewReact.NStopNewReactNHeading.class);
    ReturnCodeSeg<SloDownStopReact> sloDownStopReact = reactor(SloDownStopReact.class);
    ReturnCodeSeg<NonstopReact.NonstopReactSPslow> slow = reactor(NonstopReact.NonstopReactSPslow.class);

    AutoSegment<?, ?> DefaultSP = new AutoSegment<>(PIDReact, poseGen);
    AutoSegment<?, ?> JunctionSP = new AutoSegment<>(mecJunctionReact, poseGen);
    AutoSegment<?, ?> JunctionSP2 = new AutoSegment<>(mecJunctionReact2, poseGen);
    AutoSegment<?, ?> DefaultWP = new AutoSegment<>(PPReact, lineGen);
    AutoSegment<?, ?> NonstopWP = new AutoSegment<>(mecNonstopReact, lineGen);
    AutoSegment<?, ?> NonstopSP = new AutoSegment<>(mecNonstopReactSP, lineGen);
    AutoSegment<?, ?> turnSP = new AutoSegment<>(turnReactSP, lineGen);
    AutoSegment<?, ?> nStopNewSP = new AutoSegment<>(nStopNewReact, lineGen);
    AutoSegment<?, ?> nStopNewHaltSP = new AutoSegment<>(nStopNewReactHalt, lineGen);
    AutoSegment<?, ?> nStopNewNHeadingSP = new AutoSegment<>(nStopNewReactNHeading, lineGen);
    AutoSegment<?, ?> sloDownStopSP = new AutoSegment<>(sloDownStopReact, lineGen);
    AutoSegment<?, ?> sloSP = new AutoSegment<>(slow, lineGen);

    AutoConfig DefaultConfig = new AutoConfig(DefaultSP, DefaultWP);
    AutoConfig NonstopConfig = new AutoConfig(NonstopSP, NonstopWP);
    AutoConfig nStopNewConfig = new AutoConfig(nStopNewSP, DefaultWP);




    static <T extends Generator> ReturnCodeSeg<T> generator(Class<T> type){ return ParameterConstructor.getNewInstance(type); }
    static <T extends Reactor> ReturnCodeSeg<T> reactor(Class<T> type){ return ParameterConstructor.getNewInstance(type); }
}
