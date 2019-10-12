package com.anisaha.adt.priorityqueue;

import java.util.PriorityQueue;

public class PriorityQueueInJava {
    public static void main(String[] args) {
        PriorityQueue<Student> pq = new PriorityQueue<>(5,
                (Student st1, Student st2) -> {
                    if (st1.cgpa < st2.cgpa)
                        return 1;
                    else if (st1.cgpa > st2.cgpa)
                        return -1;

                    return 0;
                });


        // higher the cpga more the priority
        Student sharmaJiKaLadka = new Student("Rohit Sharma", 9.6);
        Student self = new Student("Ghoochu Das", 3.01);
        Student backBencher = new Student("Anmol Ratan", 6.56);
        pq.add(sharmaJiKaLadka);
        pq.add(self);
        pq.add(backBencher);

        System.out.println("PQ poll call on all pq objects:");

        pq.spliterator().forEachRemaining(System.out::println);

        /*while (!pq.isEmpty())
            System.out.println(pq.poll());
        */
    }
}


class Student {
    public String name;
    public double cgpa;

    public Student(String name, double cgpa) {
        this.name = name;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student name " + name + ", CGPA is " + cgpa;
    }
}
