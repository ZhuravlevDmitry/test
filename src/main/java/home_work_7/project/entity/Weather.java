package home_work_7.project.entity;
//city, date1, valueMinimum1, valueMaximum1, link
public class Weather {
    private String city;
    private String date1;
    private double valueMinimum1;
    private double valueMaximum1;
    private String link;

    public Weather(String city, String date1, double valueMinimum1, double valueMaximum1, String link) {
        this.city = city;
        this.date1 = date1;
        this.valueMinimum1 = valueMinimum1;
        this.valueMaximum1 = valueMaximum1;
        this.link = link;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public double getValueMinimum1() {
        return valueMinimum1;
    }

    public void setValueMinimum1(double valueMinimum1) {
        this.valueMinimum1 = valueMinimum1;
    }

    public double getValueMaximum1() {
        return valueMaximum1;
    }

    public void setValueMaximum1(double valueMaximum1) {
        this.valueMaximum1 = valueMaximum1;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", date1='" + date1 + '\'' +
                ", valueMinimum1=" +  String.format("%.2f",valueMinimum1) +
                ", valueMaximum1=" +  String.format("%.2f", valueMaximum1)+
                ", link='" + link + '\'' +
                '}';
    }

}
