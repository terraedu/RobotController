package autoutil.vision;

import static global.General.log;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import elements.TeamProp;

public class CaseScannerContours extends Scanner{

    private volatile TeamProp caseDetected = TeamProp.FIRST;
    protected final TeamProp[] cases = new TeamProp[]{TeamProp.FIRST, TeamProp.SECOND, TeamProp.THIRD};
    String color;
    int index;
    public int detectProp(Mat input) {
        Rect leftRect = new Rect(1, 1, 426, 719);
        Rect centerRect = new Rect(426, 1, 426, 719);
        Rect rightRect = new Rect(852, 1, 426, 719);

//        input.copyTo(output);

        Imgproc.rectangle(input, leftRect, WHITE, 5);
        Imgproc.rectangle(input, centerRect, WHITE, 5);
        Imgproc.rectangle(input, rightRect, WHITE, 5);
        Mat mat = new Mat();
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        Scalar lowerBound1 = null;
        Scalar upperBound1 = null;
        Scalar lowerBound2 = null;
        Scalar upperBound2 = null;
        Mat lowerThresh = new Mat();
        Mat upperThresh = new Mat();
        Mat finalThresh = new Mat();
        Mat rgb = new Mat();

        if (color.equalsIgnoreCase("red")) {
            lowerBound1 = new Scalar(0, 150, 50);
            upperBound1 = new Scalar(5, 255, 255);
            lowerBound2 = new Scalar(160, 50, 50);
            upperBound2 = new Scalar(180, 255, 255);
            Core.inRange(mat, lowerBound1, upperBound1, lowerThresh);
            Core.inRange(mat, lowerBound2, upperBound2, upperThresh);
            Core.add(lowerThresh, upperThresh, finalThresh);

            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.morphologyEx(finalThresh, finalThresh, Imgproc.MORPH_OPEN, kernel);
            Imgproc.morphologyEx(finalThresh, finalThresh, Imgproc.MORPH_CLOSE, kernel);
        } else if (color.equalsIgnoreCase("blue")) {
            lowerBound2 = new Scalar(99, 135, 51);
            upperBound2 = new Scalar(116, 226, 255);
            Core.inRange(mat, lowerBound2, upperBound2, finalThresh);

            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.morphologyEx(finalThresh, finalThresh, Imgproc.MORPH_OPEN, kernel);
            Imgproc.morphologyEx(finalThresh, finalThresh, Imgproc.MORPH_CLOSE, kernel);
        } else {
            log.show("Invalid color");
        }


        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(finalThresh, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        double maxArea = 0;
        MatOfPoint largestContour = null;
        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);
            if (area > maxArea) {
                maxArea = area;
                largestContour = contour;
            }
        }

        int c = 0;
        if (largestContour != null) {
            Rect rect = Imgproc.boundingRect(largestContour);
            Imgproc.drawContours(input, contours, contours.indexOf(largestContour), new Scalar(0, 0, 255), 3);
            Imgproc.rectangle(input, rect, new Scalar(0, 255, 0), 2);
            c = getCase(rect);
        }
        mat.release();
        lowerThresh.release();
        upperThresh.release();
        finalThresh.release();
        rgb.release();
        return c;
    }
    @Override
    public void run(Mat input) {
        caseDetected = cases[detectProp(input)];
    }

    @Override
    public void preProcess(Mat input) {

    }

    @Override
    public void postProcess(Mat input) {

    }

    @Override
    public void message() {
        log.show("Case Detected", caseDetected);
    }

    public void setColor (String color) {
        this.color = color;
    }

    public int getCase(Rect rect) {
        int case1Width = 426;
        int case2Width = 852;
        int case3Width = 1278;

        int center = (rect.x + (rect.width/2));

        if (center < case1Width) {
            return 0;
        } else if (center > case1Width && center < case2Width) {
            return 1;
        } else if (center > case2Width && center < case3Width) {
            return 2;
        }
        return -1;
    }

    public TeamProp getCase() {
        return caseDetected;
    }
}
