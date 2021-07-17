package home_work_1;

public abstract class Athletes {
    protected static int run;

    protected static int getRun() {
        return run;
    }

    public static void setRun(int run) {
        Athletes.run = run;
    }

    protected String name;
    protected int canRun;
    protected int canJump;
    protected String type;

    public Athletes(String name, int canRun, int canJump, String type) {
        this.name = name;
        this.canRun = canRun;
        this.canJump = canJump;
        this.type = type;
        this.run = canRun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCanRun() {
        return canRun;
    }

    public void setCanRun(int canRun) {
        this.canRun = canRun;
    }

    public int getCanJump() {
        return canJump;
    }

    public void setCanJump(int canJump) {
        this.canJump = canJump;
    }

    // Метод который отправляет атлетов на полусу препятствий
    public void running(Track[] tracks, Treadmill[] treadmills, Barrier[] barriers) {
        int x = 0;
        int y = 0;

        setRun(getCanRun());
        for (Track track : tracks) {
            if (track.getVid().equals("Treadmill")) {
                x = getRun() - treadmills[track.getNumber()].getLength();
                if (x >= 0) {
                    System.out.println("Пробежал дорожку длинной - " + treadmills[track.getNumber()].getLength());
                    setRun(x);
                } else {
                    System.out.println("Не смог пробежать дорожку длиной - " + treadmills[track.getNumber()].getLength() +
                            ", осталось сил только на -  " + getRun() + ". Сошёл с дистанции!");
                    break;
                }
            } else {
                y = getCanJump() - barriers[track.getNumber()].getHeight();
                if (y >= 0) {
                    System.out.println("Перепрыгнул барьер высотой - " + barriers[track.getNumber()].getHeight());

                } else {
                    System.out.println("Не смог перепрыгнуть барьер высотой - " + barriers[track.getNumber()].getHeight()
                            + ". Сошёл с дистанции!");
                    break;
                }
            }
        }
    }
}
