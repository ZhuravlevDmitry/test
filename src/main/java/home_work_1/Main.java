
/*
 *	Журавлев Дмитрий
 *	Д/1 № 1
 */


package home_work_1;

public class Main {


    public static void main(String[] args) {

        Athletes human1 = new Human("Борис", 10, 11, "Человек");
        Athletes robot1 = new Robot("Глорис", 40, 15, "Робот");
        Athletes cat1 = new Cat("Морис", 30, 21, "Кот");

        Treadmill[] treadmills = {new Treadmill(15), new Treadmill(20), new Treadmill(35)};
        Barrier[] barriers = {new Barrier(4), new Barrier(20), new Barrier(5)};

        //Набор препятствий
        Track[] tracks;
        tracks = new Track[]{new Track("Barrier", 0), new Track("Treadmill", 0),
                new Track("Barrier", 1), new Track("Treadmill", 2)};
        //Набор участников
        Athletes[] athletes = {human1, robot1, cat1};

        for (Athletes athlete : athletes) {
            System.out.println("Атлет - " + athlete.type +" " + athlete.name + "! Может пробежать - "
                    + athlete.canRun + ". Может прыгнуть на высоту - " + athlete.canJump);
            athlete.running(tracks, treadmills, barriers);
            System.out.println();
        }
    }
}

/*
    Вывод в консоль:

Атлет - Человек Борис! Может пробежать - 10. Может прыгнуть на высоту - 11
Перепрыгнул барьер высотой - 4
Не смог пробежать дорожку длиной - 15, осталось сил только на -  10. Сошёл с дистанции!


Атлет - Робот Глорис! Может пробежать - 40. Может прыгнуть на высоту - 15
Перепрыгнул барьер высотой - 4
Пробежал дорожку длинной - 15
Не смог перепрыгнуть барьер высотой - 20. Сошёл с дистанции!


Атлет - Кот Морис! Может пробежать - 30. Может прыгнуть на высоту - 21
Перепрыгнул барьер высотой - 4
Пробежал дорожку длинной - 15
Перепрыгнул барьер высотой - 20
Не смог пробежать дорожку длиной - 35, осталось сил только на -  15. Сошёл с дистанции */
