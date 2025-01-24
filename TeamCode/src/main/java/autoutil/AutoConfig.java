package autoutil;

import autoutil.vision.old.CaseScanner;

public class AutoConfig {
    private final AutoSegment<?,?> getWaypointSegment, getSetpointSegment;
    public <T extends CaseScanner> AutoConfig(AutoSegment<?,?> set, AutoSegment<?,?> way){
        this.getWaypointSegment = way; this.getSetpointSegment = set;
    }

    public AutoSegment<?,?> getWaypointSegment(){ return getWaypointSegment; }
    public AutoSegment<?, ?> getSetpointSegment(){ return getSetpointSegment; }
}
