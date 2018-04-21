package multithreding.com.gmail.atpt34.students;

import java.util.Queue;

import multithreding.com.gmail.atpt34.queue.StupidQueue;
import multithreding.com.gmail.atpt34.student.Student;

public class StudentRunner implements Runnable {
    private Queue<Student> students;
    private StupidQueue share;
    public StudentRunner(Queue<Student> students, StupidQueue share) {
        this.students = students;
        this.share = share;
    }
    @Override
    public void run() {        
        while(!students.isEmpty()) {
            share.put(students.poll());
        }
    }
}

