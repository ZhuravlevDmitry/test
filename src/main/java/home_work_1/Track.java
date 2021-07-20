package home_work_1;

public class Track {
    private String vid;
    private int number;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Track(String vid, int number) {
        this.vid = vid;
        this.number = number;
    }


}
