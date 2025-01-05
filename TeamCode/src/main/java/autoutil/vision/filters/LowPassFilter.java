package autoutil.vision.filters;

public class LowPassFilter implements Filter {
    private final double gain;
    private double prevValue;

    public LowPassFilter(double gain) {
        this.gain = gain;
    }

    @Override
    public double estimate(double value) {
        prevValue = gain * value + (1 - gain) * prevValue;
        return prevValue;
    }
}
