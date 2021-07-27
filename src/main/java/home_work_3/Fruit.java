package home_work_3;

public class Fruit <T extends Object> {
double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Fruit(double weight) {
        this.weight = weight;

    }


}
