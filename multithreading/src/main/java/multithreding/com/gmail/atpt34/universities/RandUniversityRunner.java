package multithreding.com.gmail.atpt34.universities;

import java.util.Queue;

import multithreding.com.gmail.atpt34.queue.StupidQueue;
import multithreding.com.gmail.atpt34.student.Student;

public 
class RandUniversityRunner extends UniversityRunner {
    public RandUniversityRunner(Queue<Student> students, StupidQueue share) {
        super(students, share);
    }
    @Override
    public void run() {                
        while(!share.isEmpty()) {
            try {
                students.add(share.take());                
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }                        
        }               
    }
}
