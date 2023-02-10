import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Neron {
    static double[] weightForOne = new double[]{0.5, 0.48, -0.7};
    static double[] weightRes;

    static double[][] inputData = new double[][]{
            new double[]{1, 0, 1},
            new double[]{0, 1, 1},
            new double[]{0, 0, 1},
            new double[]{1, 1, 1}
    };

    static double[][] wAnds = new double[][]{
            new double[]{0},
            new double[]{1},
            new double[]{0},
            new double[]{1}
    };

    public static void main(String[] args) {
        // one();
        two();

    }

    private static void two() {
        int inputs = 3;
        int hidenSizeNeurons = 4;
        double[][] weights0_1 = randomWeights(inputData[0].length, inputs);
        double[][] weights1_2 = randomWeights(hidenSizeNeurons, 1);
        System.out.println("--------------------------------------------------------------------------------------------");

        for (int i = 0; i < 60; i++){
            double layer2Error = 0;
            for(int j = 0; j < inputData.length; j++){
                double[] layer0 = inputData[j];
                double[] layer1 = multiLayer_weight(layer0, weights0_1);//умножаем входы на веса первого слоя
                layer1 = relu(layer1); //возвращает x если значение больше нуля иначе 0
           //     double[] layer2 = multiLayer_weight(layer1, weights1_2);
            }
        }
    }

    private static double[] relu(double[] layer) {
        System.out.println(layer.length);
        for (double i: layer) {
            if (i < 0) i = 0;
            System.out.println("relu " + i);
        }
        System.out.println(Arrays.toString(layer));
        return layer;
    }

    private static double[][] randomWeights(int column, int row) {
        Random random = new Random();
        double matrixRes[][] = new double[row][column];

        for (int i = 0; i < matrixRes.length; i++) {
            for (int j = 0; j < matrixRes[0].length; j++) {
                double rDigit = random.nextDouble();
                double scale = Math.pow(10, 2); //2 нужное количество знаков после запятой
                rDigit = Math.ceil(rDigit * scale)/scale;
                matrixRes[i][j] = rDigit;
            }
        }

        System.out.println(Arrays.deepToString(matrixRes));
        return matrixRes;
    }

    private static double[] multiLayer_weight(double[] layer, double[][] weights) {
        double[] res = new double[layer.length];
        System.out.println("lenth layer " + layer.length + " weight " + weights.length);
        System.out.println(Arrays.toString(layer) +" - " +Arrays.deepToString(weights));
        for (int i = 0; i < res.length; i++) {
            System.out.println("i =  " + i );
            for (int j = 0; j < weights.length; j++) {
                System.out.println(weights[i][j] + " * " + layer[i]+" j = "+j);
                res[i] += layer[i] * weights[i][j];

            }
            System.out.println("=========");
        }

        System.out.println(Arrays.toString(res));
        System.out.println("*********************************************************************************");
        return res;
    }

    private static void one() {
        double inputOne[] = inputData[0];
        double goalPred[] = wAnds[0];

        for (int i = 0; i < 40; i++) {
            double errForAll = 0;
            for (int j = 0; j < wAnds.length; j++) {
                inputOne = inputData[j];
                double pred = getPred(inputOne, weightForOne);
                goalPred = wAnds[j];
                //System.out.println(pred);
                double erroreOne = (pred - goalPred[0]) * (pred - goalPred[0]);
                // System.out.println(erroreOne);
                errForAll += erroreOne;
                double delta = pred - goalPred[0];
                //  System.out.println(delta);
                // weight[0] = weight[0] - (inputOne[0] * delta)* 0.1;
                weightForOne = getScal(weightForOne, inputOne, delta);
                // System.out.println("weight = " + Arrays.toString(weight));
                // System.out.println("eror" + erroreOne + " - pred" + pred);
                System.out.println("pred " + pred);
            }

            System.out.println("err " + errForAll);
            System.out.println("err " + Arrays.toString(weightForOne));
        }

        startNer(inputData, weightForOne);
    }

    private static void startNer(double[][] inputdata, double[] weights) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < inputdata.length; i++) {
            double inp[] = inputdata[i];
            double r = 0;
            for (int j = 0; j < inp.length; j++) {
                r += inp[j] * weights[j];
            }

            result.add(r);
        }

        for (double r : result) {
            if (r > 0.9) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static double[] getScal(double weight[], double[] inputOne, double delta) {
        for (int i = 0; i < inputOne.length; i++) {
            weight[i] -= (inputOne[i] * delta) * 0.1;
            //     System.out.println(result);
        }

        return weight;
    }


    private static double getPred(double[] inputOne, double[] weight) {
        double result = 0.0;

        for (int i = 0; i < inputOne.length; i++) {
            result += inputOne[i] * weight[i];
            //    System.out.println("i" + result);
        }

        return result;
    }
}
