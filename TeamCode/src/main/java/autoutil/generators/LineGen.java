package autoutil.generators;

import geometry.position.Line;
import geometry.position.Pose;

public class LineGen extends PoseGen {
    private Line line;

    @Override
    public void add(Pose start, Pose target) {
        line = new Line(start.getPoint().getCopy(), target.getPoint().getCopy());
    }

    public Line getLine(){ return line; }
}
