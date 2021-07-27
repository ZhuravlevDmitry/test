package home_work_3;

public class Orange extends Fruit {
    public Orange(Double weight) {
        super(1.5);
    }

    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                '}';
    }
}

