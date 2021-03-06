package multithreding.com.gmail.atpt34.universities;

import java.util.Queue;

import multithreding.com.gmail.atpt34.queue.StupidQueue;
import multithreding.com.gmail.atpt34.student.Student;

public class MathUniversityRunner extends UniversityRunner {
    public MathUniversityRunner(Queue<Student> students, StupidQueue share) {
        super(students, share);
    }
    @Override
    public void run() {                
        while(!share.isEmpty()) {            
            try {
                Student student = share.peek();
                if (student == Student.MATHEMATICIAN) {
                    students.add(share.take());
                }
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }                        
        }                
    }
}
