package algorithm.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * 认识比较器
 * 任何比较器，compare方法里，遵循一个统一的规范：
 * 1、返回负数时，认为第一个参数应该排在前面
 * 2、返回正数时，认为第二个参数应该排在前面
 * 3、返回零时，认为哪个参数放前面无所谓
 * 比较器的实质是重载比较运算符！！！
 * @author HuanyuLee
 * @date 2023/5/7
 */
public class WhatIsComparator {
    static class Student {
        String name;
        int id;
        int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("A", 101, 18);
        Student s2 = new Student("B", 101, 20);
        Student s3 = new Student("C", 102, 21);
        Student s4 = new Student("D", 103, 20);
        Student s5 = new Student("E", 103, 17);
        Student[] students = new Student[]{s5, s2, s3, s4, s1};
        // 根据ID升序
        // Arrays.sort(students, Comparator.comparingInt(o -> o.id));

        // 根据Age降序
        // Arrays.sort(students, (o1, o2) -> o2.age - o1.age);

        // 根据ID升序，如果ID一样，按照Age降序
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.id != o2.id ? o1.id - o2.id : o2.age - o1.age;
            }
        });

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("=================================");

        //有序表
        TreeMap<Student, String> treeMap = new TreeMap<>(
                (o1, o2) -> o1.id != o2.id ? o1.id - o2.id : o1.age - o2.age);
        treeMap.put(s3, "C");
        treeMap.put(s1, "A");
        treeMap.put(s4, "D");
        treeMap.put(s2, "B");
        treeMap.put(s5, "E");

        for (Student student : treeMap.keySet()) {
            System.out.println(student);
        }
    }
}
