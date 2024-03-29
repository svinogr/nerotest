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
        double[] weights0_1 = randomWeights(hidenSizeNeurons * inputs);
        double[] weights1_2 = randomWeights(hidenSizeNeurons * 1);
        System.out.println("--------------------------------------------------------------------------------------------");

        for (int i = 0; i < 1; i++) {
            double layer2Error = 0;
            for (int j = 0; j < inputData.length; j++) {
                double[] layer0 = inputData[j];
                System.out.println("***************OneLayer(input)**************************");
                double[] layer1 = multiInputsWithWeights(layer0, weights0_1, hidenSizeNeurons);//умножаем входы на веса первого слоя
                layer1 = relu(layer1); //возвращает x если значение больше нуля иначе 0
                System.out.println("*****************************************");
                System.out.println("***************HidenLayer(input)**************************");
                double[] layer2 = multiInputsWithWeights(layer1, weights1_2, 1);
            }
        }
    }

    private static double[] relu(double[] layer) {
        System.out.println("******************Relu***********************");
        System.out.println(layer.length);
        for (double i : layer) {
            if (i < 0) i = 0;
            System.out.println("relu " + i);
        }
        System.out.println(Arrays.toString(layer));
        System.out.println("***********************");
        return layer;
    }

    private static double[] randomWeights(int size) {
        Random random = new Random();
        double matrixRes[] = new double[size];

        for (int i = 0; i < matrixRes.length; i++) {
            double rDigit = random.nextDouble();
            double scale = Math.pow(10, 2); //2 нужное количество знаков после запятой
            rDigit = Math.ceil(rDigit * scale) / scale;
            matrixRes[i] = rDigit;
        }

        System.out.println(Arrays.toString(matrixRes));
        return matrixRes;
    }
    private static double[] multiInputsWithWeights(double[] inputs, double[] weights, int outputSize) {
        System.out.println("*************multiInputsWithWeights*************");
        double[] output = new double[outputSize];
        System.out.println("lenth layer " + inputs.length + " weight " + weights.length + " res " + output.length);
        System.out.println(Arrays.toString(inputs) + " - " + Arrays.toString(weights));
        int start = 0;
        int stepFor = weights.length / inputs.length;
        int off = 0;
        System.out.println("step = " + stepFor);

        for (int i = 0; i < output.length;i++) {
            if (start >= weights.length) break;
            off = start + stepFor;

            for (int k = start; k < off ; k++) {
                System.out.println("start=" + start + " off=" + off+ " k=" + k);
                System.out.println(weights[k] + " * " + inputs[i] );
                output[i] += inputs[i] * weights[k];
            }

            start = start + stepFor;
            System.out.println("=========");
        }

        System.out.println("output=" + Arrays.toString(output));
        System.out.println("*********************************************************************************");
        return output;
    }

/*
    private static double[] multiInputsWithWeights(double[] inputs, double[] weights, int outputSize) {
        System.out.println("*************multiInputsWithWeights*************");
        double[] output = new double[outputSize];
        System.out.println("lenth layer " + inputs.length + " weight " + weights.length + " res " + output.length);
        System.out.println(Arrays.toString(inputs) + " - " + Arrays.toString(weights));
        int start = 0;
        int stepFor = weights.length / inputs.length;
        int off = 0;
        System.out.println("step = " + stepFor);

        for (int i = 0; i < output.length;i++) {
            if (start >= weights.length) break;
             off = start + stepFor;

            for (int k = start; k < off ; k++) {
                System.out.println("start=" + start + " off=" + off+ " k=" + k);
                System.out.println(weights[k] + " * " + inputs[i] );
                output[i] += inputs[i] * weights[k];
            }

            start = start + stepFor;
            System.out.println("=========");
        }

        System.out.println("output=" + Arrays.toString(output));
        System.out.println("*********************************************************************************");
        return output;
    }
*/

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
