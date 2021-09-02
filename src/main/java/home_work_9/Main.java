package home_work_9;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    /*
Написать функцию, принимающую список Student и возвращающую список уникальных курсов, на которые подписаны студенты.

Написать функцию, принимающую на вход список Student и возвращающую список из трех самых любознательных
(любознательность определяется количеством курсов).

Написать функцию, принимающую на вход список Student и экземпляр Course, возвращающую список студентов,
которые посещают этот курс.
     */
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Oleg", Arrays.asList(new Course("Math"), new Course("Biology"),
                new Course("Testing"))));
        students.add(new Student("Anton", Arrays.asList(new Course("Math"), new Course("Geography"),
                new Course("Testing6"), new Course("Testing2"))));
        students.add(new Student("Oleg2", Arrays.asList(new Course("Math"), new Course("Biology"),
                new Course("Testing"))));
        students.add(new Student("Oksana", Arrays.asList(new Course("Physics"), new Course("Biology"),
                new Course("Testing"), new Course("Testing1"), new Course("Testing7"))));

        //1
        System.out.println(students.stream()
                .map(s -> s.getCourses())
                .flatMap(c -> c.stream())
                .collect(Collectors.toSet()));

        //2
        System.out.println(students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                .limit(3)
                .collect(Collectors.toList()));

        //3
        Course course = new Course("Testing7");
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(course))
                .collect(Collectors.toList()));

    }
}