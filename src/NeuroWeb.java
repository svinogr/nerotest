import java.util.ArrayList;
import java.util.List;

public class NeuroWeb {
    public static int inputSize = 3;
    public static int hiddenLayerSize = 4;
    public static int outputSize = 1;

    static double[][] inputData = new double[][]{
            new double[]{1, 0, 1},
            new double[]{0, 1, 1},
            new double[]{0, 0, 1},
            new double[]{1, 1, 1}
    };

    public static void main(String[] args) {
        // input layer
        List<Neyron> inputLayer = new ArrayList<>();
        for (int i = 0; i < inputSize; i++) {
            Neyron neyron = new Neyron(i, hiddenLayerSize, "input layer", inputData[1][i]);
            inputLayer.add(neyron);
        }

        // hidden layer
        List<Neyron> hiddenLayer = new ArrayList<>();
        for (int i = 0; i < hiddenLayerSize; i++) {
            Neyron neyron = new Neyron(i, outputSize, "hidden layer");
            neyron.inputs = inputLayer;
            hiddenLayer.add(neyron);
        }

        // output layer
        List<Neyron> outputLayer = new ArrayList<>();
        for(int i = 0; i < outputSize; i++) {
            Neyron neyron = new Neyron(i, 0, "output layer ");
            neyron.inputs = hiddenLayer;
            outputLayer.add(neyron);
        }

        System.out.println(inputLayer);
        System.out.println(hiddenLayer);
        System.out.println(outputLayer);
    }


}
