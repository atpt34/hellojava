package com.concur.oleksa;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

enum Student {
    LINGUIST, MATHEMATICAN
}

class StudentProducer implements Runnable {
    private Queue<Student> lingStudents;
    private Queue<Student> mathStudents;
    private BlockingQueue<Student> studentQueue;
    private AtomicBoolean stop;
    
    public StudentProducer(Queue<Student> lingStudents, Queue<Student> mathStudents,
            BlockingQueue<Student> studentQueue, AtomicBoolean stop) {
        this.lingStudents = lingStudents;
        this.mathStudents = mathStudents;
        this.studentQueue = studentQueue;
        this.stop = stop;
    }

    @Override
    public void run() {
            try {
                while (!bothEmpty()) {
                    Student student = chooseStudent();
                    studentQueue.put(student);
                }   
                stop.set(true);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }               
    }

    private boolean bothEmpty() {
        return lingStudents.isEmpty() && mathStudents.isEmpty();
    }

    private Student chooseStudent() {
        if (!mathStudents.isEmpty() && !lingStudents.isEmpty()) {
            if (ThreadLocalRandom.current().nextInt(2) == 0) {
                return lingStudents.remove();
            }
            return mathStudents.remove();                
        }         
        if (!mathStudents.isEmpty()) {
            return mathStudents.remove();
        }
        if (!lingStudents.isEmpty()) {
            return lingStudents.remove();
        }
        return null;
    }
    
}

class StudentConsumer implements Runnable {
    private Queue<Student> mathUniversityStudents;
    private Queue<Student> lingUniversityStudents;
    private Queue<Student> midUniversityStudents;
    private BlockingQueue<Student> studentQueue;
    private AtomicBoolean stop;
    
    public StudentConsumer(BlockingQueue<Student> studentQueue, AtomicBoolean stop) {
        this.mathUniversityStudents = new LinkedList<>();
        this.lingUniversityStudents = new LinkedList<>();
        this.midUniversityStudents = new LinkedList<>();
        this.studentQueue = studentQueue;
        this.stop = stop;
    }

    @Override
    public void run() {
        try {            
            int turn = 1;
            boolean norandom = true;
            int random = 0;
            loop:
            while (!stop.get() || !studentQueue.isEmpty()) {
                Student student = studentQueue.take();
                switch (turn) {
                case 1:
                    if (student == Student.MATHEMATICAN) {
                        mathUniversityStudents.add(student);
                        break;
                    } else {
                        turn = 2;
                    }
                case 2:
                    if (student == Student.LINGUIST) {
                        lingUniversityStudents.add(student);
                        break;
                    } else {
                        turn = 3;
                    }
                default:// case 3
                    midUniversityStudents.add(student);
                    if (norandom) {
                        random = ThreadLocalRandom.current().nextInt(5);
                        norandom = false;
                    } else {
                        random--;
                    }
                    if (random == 0) {
                        turn = 1;
                        norandom = true;
                    }                                 
                    break;
                }
            }            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public String getStringResults() {
        return mathUniversityStudents.size() + " " + lingUniversityStudents.size() + " " + midUniversityStudents.size();
    }
    public int getResultSize() {
        return mathUniversityStudents.size() + lingUniversityStudents.size() + midUniversityStudents.size();
    }
}


public class ConcurTaskDriver extends Object {

    public static void main(String[] args) {

        System.out.println("hello students");
        int lingCount = 250;
        Queue<Student> lingQueue = new LinkedList<>(Collections.nCopies(lingCount, Student.LINGUIST));
        int mathCount = 200;
        Queue<Student> mathQueue = new ArrayDeque<>(Collections.nCopies(mathCount , Student.MATHEMATICAN));
        System.out.println("whole size: " + (lingQueue.size() + mathQueue.size()));
        
        AtomicBoolean stop = new AtomicBoolean(false);
        int capacity = 50;
        BlockingQueue<Student> studentQueue = new LinkedBlockingQueue<>(capacity);
//        new SynchronousQueue<>(); Semaphore CircularArrayList Collections.synchronizedSet Vector
        
        StudentConsumer studentConsumer = new StudentConsumer(studentQueue, stop);
        StudentProducer studentProducer = new StudentProducer(lingQueue, mathQueue, studentQueue, stop);
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(studentProducer);
        executor.execute(studentConsumer);
        
        
        
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdown();
            System.out.println(studentConsumer.getStringResults());
            System.out.println("result size: " + studentConsumer.getResultSize());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
    }

}