package multithreding.com.gmail.atpt34.queue;

import java.util.LinkedList;
import java.util.Queue;

import multithreding.com.gmail.atpt34.student.Student;

/**
 * 
 * @author atpt34
 *
 */
public class StupidQueue {
    private Queue<Student> students;
    public StupidQueue() {
        this.students = new LinkedList<>();
    }
    private static final int MAX_CAP = 5;    
    private static final int MIN_CAP = 0;
    private final Object lock = new Object();    
    public void put(Student student) {
        synchronized (lock) {
            while (students.size() > MAX_CAP) {                  
                try { 
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            students.add(student);
            lock.notifyAll();
        }
    }
    public Student take() {
        synchronized (lock) {
            while (students.size() <= MIN_CAP) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }   
            lock.notifyAll();
            return students.poll();            
        }
    }
    public Student peek() {
        synchronized (lock) {
            return students.peek();
        }
    }
    public int size() {
        synchronized (lock) {
            return students.size();
        }                           
    }
    public boolean isEmpty() {   
        synchronized (lock) {
            return students.isEmpty();
        }                    
    }
}
