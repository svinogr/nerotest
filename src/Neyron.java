import java.util.*;

public class Neyron {
    public String nameOfLayer;
    public int number;
    public List<Neyron> inputs;
    public List<Double> weights;
    private double value;

    public Neyron(int number, int connects, String nameOfLayer, double value) {
        this(number,connects, nameOfLayer);
        this.value = value;

    }

    public Neyron(int number, int connects, String nameOfLayer) {
        this.number = number;
        generateWeight(connects);
        this.nameOfLayer = nameOfLayer;
    }


    private void generateWeight(int connects) {
        Random random = new Random();
        weights = new ArrayList<>();

        for (int i = 0; i < connects; i++) {
            double rDigit = random.nextDouble();
            double scale = Math.pow(10, 2); //2 нужное количество знаков после запятой
            rDigit = Math.ceil(rDigit * scale) / scale;
            weights.add(rDigit);
        }
    }

    public double getValue() {
        if (this.inputs != null) {
            for (Neyron input : inputs) {

                value += input.weights.get(number) * input.getValue();


            }
        } else return value;

        return value;
    }

    public String getNameOfLayer() {
        return nameOfLayer;
    }

    public void setNameOfLayer(String nameOfLayer) {
        this.nameOfLayer = nameOfLayer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Neyron> getInputs() {
        return inputs;
    }

    public void setInputs(List<Neyron> inputs) {
        this.inputs = inputs;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neyron neyron = (Neyron) o;
        return number == neyron.number && Double.compare(neyron.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, value);
    }

    @Override
    public String toString() {
        return "Neyron{" +
                "nameOfLayer='" + nameOfLayer + '\'' +
                ", number=" + number +
                ", value=" + getValue() +
                '}';
    }
}
