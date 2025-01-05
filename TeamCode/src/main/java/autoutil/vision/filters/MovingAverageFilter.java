package autoutil.vision.filters;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageFilter implements Filter{

    private final int windowSize;
    private final Queue<Double> window = new LinkedList<>();
    private double sum = 0.0;

    public MovingAverageFilter(int windowSize) {
        this.windowSize = windowSize;
    }

    @Override
    public double estimate(double value) {
        sum += value;
        window.add(value);

        if (window.size() > windowSize) {
            sum -= window.poll();
        }

        return sum / window.size();
    }
}
