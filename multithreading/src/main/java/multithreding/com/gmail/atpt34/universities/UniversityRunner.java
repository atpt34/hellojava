package multithreding.com.gmail.atpt34.universities;

import java.util.Queue;

import multithreding.com.gmail.atpt34.queue.StupidQueue;
import multithreding.com.gmail.atpt34.student.Student;

public abstract class UniversityRunner implements Runnable {
    protected Queue<Student> students;
    protected StupidQueue share;
    UniversityRunner(Queue<Student> students, StupidQueue share) {
        this.students = students;
        this.share = share;
    }
}
