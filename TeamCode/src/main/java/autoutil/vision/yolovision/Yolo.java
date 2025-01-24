package autoutil.vision.yolovision;

import org.opencv.core.Mat;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Yolo {

    Net model;
    List<String> outputNames;

    public Yolo() {
        model = initModel();
        outputNames = getOutputNames(model);
    }

    private Net initModel() {
        String onnx = "/sdcard/EasyOpenCV/model_16.onnx";
        return Dnn.readNetFromONNX(onnx);
    }

    private static List<String> getOutputNames(Net net) {
        List<String> names = new ArrayList<>();

        List<Integer> outLayers = net.getUnconnectedOutLayers().toList();
        List<String> layersNames = net.getLayerNames();

        for (Integer item : outLayers) {
            names.add(layersNames.get(item - 1));
        }

        return names;
    }

    public Map<BoundingBox, Double> forward(Mat frame, Mat blob) {
        model.setInput(blob);

        Map<BoundingBox, Double> boxes = new HashMap<>();
        List<Mat> results = new ArrayList<>();
        model.forward(results);
        System.out.println(results.get(0).size().width);
        Mat result16 = results.get(0).reshape(0, (int) results.get(0).size().width);
        System.out.println(result16);

        for (int j = 0; j < 5; j++) {
            System.out.println(result16.row(0).get(0, j)[0]);
        }

        for (int i = 0; i < result16.rows(); i++) {
            Mat row = result16.row(i);
//            System.out.println(row);
            BoundingBox box = new BoundingBox(row, frame.cols(), frame.rows());

            double confidence = row.get(0, 4)[0];
            if (confidence >= 0.9f) {
                System.out.println("Confidence: " + confidence);
                System.out.println(box);

                boxes.put(box, confidence);
            }
        }
        return boxes;
    }
}