package autoutil.vision.yolovision;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;

public class BoundingBox {
    public int x;
    public int y;
    public int width;
    public int height;

    public BoundingBox(Mat row, int cols, int rows) {
        x = (int) (row.get(0, 0)[0] * cols);
        y = (int) (row.get(0, 1)[0] * rows);
        width = (int) (row.get(0, 2)[0] * cols) - x;
        height = (int) (row.get(0, 3)[0] * rows) - y;
    }

    public Rect toRect(int padX, int padY, Size frameSize) {
        return new Rect(x, y, width, height );
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", width: " + width + ", height: " + height;
    }
}
