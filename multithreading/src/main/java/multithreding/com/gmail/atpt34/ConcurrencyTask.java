package multithreding.com.gmail.atpt34;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import multithreding.com.gmail.atpt34.student.Student;

/**
 * Multithreading task done with BlockingQueue and AtomicInteger.
 * 
 * @author atpt34
 *
 */
public class ConcurrencyTask {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.submit(() -> System.out.println("hello world! " + Thread.currentThread().getName()));
//        executorService.submit(() -> System.out.println("hello another world! " + Thread.currentThread().getName()));
//        executorService.shutdown();

//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("hello future! " + Thread.currentThread().getName()));
//        CompletableFuture.runAsync(() -> System.out.println("hello another future! " + Thread.currentThread().getName()));
//        completableFuture.get(1, TimeUnit.MILLISECONDS);
        
        
        int lingCount = 7500;
        int mathCount = 5120;
        Queue<Student> lingQueue = new LinkedList<>(
                Collections.nCopies(lingCount, Student.LINGUIST));
        Queue<Student> mathQueue = new LinkedList<>(
                Collections.nCopies(mathCount , Student.MATHEMATICIAN));
        Queue<Student> randUniversity = new ArrayDeque<>();
        Queue<Student> mathUniversity = new LinkedList<>();
        Queue<Student> linguistUniversity = new LinkedList<>();
        
        int capacity = 50;
        Queue<Student> studentQueue = new ArrayBlockingQueue<>(capacity);
        
        class StudentProducer implements Runnable {
            private Queue<Student> studentQueue;
            private Queue<Student> companyQueue;
            public StudentProducer(Queue<Student> studentQueue, 
                    Queue<Student> companyQueue) {
                this.studentQueue = studentQueue;
                this.companyQueue = companyQueue;
            }
            @Override
            public void run() {
                while (!studentQueue.isEmpty()) {
                    Student student = studentQueue.poll();
                    while (!companyQueue.offer(student));
                }
            }
        }
        
        executorService.submit(new StudentProducer(lingQueue, studentQueue));
        executorService.submit(new StudentProducer(mathQueue, studentQueue));
        
        class SpecificUniversity implements Runnable {
            private AtomicInteger studentCount;
            private Queue<Student> universityQueue;
            private Queue<Student> studentQueue;
            private Student specificStudent;
            public SpecificUniversity(Queue<Student> studentQueue, 
                    Queue<Student> universityQueue, 
                    Student specificStudent, AtomicInteger studentCount) {
                this.studentCount = studentCount;
                this.universityQueue = universityQueue;
                this.specificStudent = specificStudent;
                this.studentQueue = studentQueue;
            }
            @Override
            public void run() {
                while (studentCount.get() != 0) {
                    Student student = studentQueue.poll();
                    if (student == null) {
                        continue;
                    }
                    if (student == specificStudent) {
                        universityQueue.add(student);
                        studentCount.decrementAndGet();
                    } else {
                        while (!studentQueue.offer(student));
                    }
                }
            }
        }
        
        int studentsTotal = lingCount + mathCount;
        AtomicInteger studentCount = new AtomicInteger(studentsTotal);
        executorService.submit(new SpecificUniversity(studentQueue, mathUniversity, 
                Student.MATHEMATICIAN, studentCount));
        executorService.submit(new SpecificUniversity(studentQueue, linguistUniversity, 
                Student.LINGUIST, studentCount));
        
        class RandomUniversity implements Runnable {
            private Queue<Student> randUniversity;
            private Queue<Student> studentQueue;
            private AtomicInteger studentCount;
            public RandomUniversity(Queue<Student> studentQueue, 
                    Queue<Student> randUniversity,
                    AtomicInteger studentCount) {
                this.randUniversity = randUniversity;
                this.studentQueue = studentQueue;
                this.studentCount = studentCount;
            }
            @Override
            public void run() {
                while (studentCount.get() != 0) {
                    Student student = studentQueue.poll();
                    if (student == null) {
                        continue;
                    }
                    randUniversity.add(student);
                    studentCount.decrementAndGet();
                }
            }
            
        }
        
        executorService.submit(new RandomUniversity(studentQueue, randUniversity, studentCount));
        
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(studentQueue);
        System.out.println(studentCount);
        System.out.println("students total = " + studentsTotal);
        System.out.println(mathUniversity.size());
        System.out.println(linguistUniversity.size());
//        System.out.println(randUniversity.stream().filter(s -> s == Student.MATHEMATICIAN).count());
//        System.out.println(randUniversity.stream().filter(s -> s == Student.LINGUIST).count());
        
        Map<Student, Long> collect = randUniversity.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        () -> new EnumMap<>(Student.class),
                        Collectors.counting()));
        
        System.out.println(collect.get(Student.MATHEMATICIAN));
        System.out.println(collect.get(Student.LINGUIST));
    }

}
