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

    static double[][] pred = new double[][]{
            new double[]{0},
            new double[]{1},
            new double[]{0},
            new double[]{1}
    };


    public static void main(String[] args) {
        // input layer
        List<Neyron> inputLayer = new ArrayList<>();
        for (int i = 0; i < inputSize; i++) {
            Neyron neyron = new Neyron(i, hiddenLayerSize, "input layer", inputData[0][i]);
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
        for (int i = 0; i < outputSize; i++) {
            Neyron neyron = new Neyron(i, 0, "output layer ");
            neyron.inputs = hiddenLayer;
            outputLayer.add(neyron);
        }

        System.out.println(inputLayer);
        System.out.println(hiddenLayer);
        System.out.println(outputLayer);
        
        for(int i = 0; i < 20; i++ ) {
           // relu(hiddenLayer);
            double error = Math.pow(outputLayer.get(0).getValue() - pred[0][0], 2);
            System.out.println("error " + error + "ou val " + outputLayer.get(0).getValue());
            double delta = outputLayer.get(0).getValue() - pred[0][0];
            List<Neyron> sumList = new ArrayList<>();
            sumList.addAll(inputLayer);
            sumList.addAll(hiddenLayer);

            for (Neyron neyron: sumList){
                System.out.println(neyron);
                for (double weight: neyron.weights) {
                    if (neyron.inputs != null) {
                        System.out.println("-------" + weight);
                         weight - neyron.getValue() * delta*0.01;
                        System.out.println(neyron);
                        System.out.println("------");
                    }
                }
            }

        }

        System.out.println(inputLayer);
        System.out.println(hiddenLayer);
        System.out.println(outputLayer);

    }

    public static void relu(List<Neyron> neyronsList) {
        for (Neyron neyron: neyronsList) {
            if (neyron.getValue() < 0) neyron.setValue(0);
        }
    }
}
