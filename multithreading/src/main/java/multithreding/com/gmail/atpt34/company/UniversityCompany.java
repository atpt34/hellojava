package multithreding.com.gmail.atpt34.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import multithreding.com.gmail.atpt34.queue.StupidQueue;
import multithreding.com.gmail.atpt34.student.Student;
import multithreding.com.gmail.atpt34.students.StudentRunner;
import multithreding.com.gmail.atpt34.universities.LingUniversityRunner;
import multithreding.com.gmail.atpt34.universities.MathUniversityRunner;
import multithreding.com.gmail.atpt34.universities.RandUniversityRunner;
import multithreding.com.gmail.atpt34.util.Pair;

/**
 * 
 * @author atpt34
 *
 */
public class UniversityCompany {
    
    private UniversityCompany() {}
    
    public static Pair<Integer, Integer> runCompany(int lingCount, int mathCount) {
        Queue<Student> lingQueue = new LinkedList<>(Collections.nCopies(lingCount, Student.LINGUIST));
        Queue<Student> mathQueue = new LinkedList<>(Collections.nCopies(mathCount , Student.MATHEMATICIAN));
        Queue<Student> randUniversity = new LinkedList<>();
        Queue<Student> mathUniversity = new LinkedList<>();
        Queue<Student> linguistUniversity = new LinkedList<>();
        StupidQueue stupidQueue = new StupidQueue();
        
        Thread t1 = new Thread(new StudentRunner(lingQueue, stupidQueue));
        Thread t2 = new Thread(new StudentRunner(mathQueue, stupidQueue));        
        Thread t3 = new Thread(new RandUniversityRunner(randUniversity, stupidQueue));
        Thread t4 = new Thread(new MathUniversityRunner(mathUniversity, stupidQueue));
        Thread t5 = new Thread(new LingUniversityRunner(linguistUniversity, stupidQueue));

        try {
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();            
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            System.out.println("Results:");
            printSizes(lingQueue, mathQueue, randUniversity, mathUniversity, linguistUniversity, stupidQueue);
            return countStudentsInUniversities(randUniversity, mathUniversity, linguistUniversity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static Pair<Integer, Integer> countStudentsInUniversities(Queue<Student> randUniversity,
            Queue<Student> mathUniversity, Queue<Student> linguistUniversity) {
        
        Pair<Integer, Integer> lingUniversityStudents = countStudentsInUniversity(linguistUniversity);
        Pair<Integer, Integer> mathUniversityStudents = countStudentsInUniversity(mathUniversity);
        Pair<Integer, Integer> randUniversityStudents = countStudentsInUniversity(randUniversity);        
        int lingCount = lingUniversityStudents.getFirst();
        int mathCount = lingUniversityStudents.getSecond();
        lingCount += mathUniversityStudents.getFirst();
        mathCount += mathUniversityStudents.getSecond();
        lingCount += randUniversityStudents.getFirst();
        mathCount += randUniversityStudents.getSecond();
        return new Pair<>(lingCount, mathCount);
        
    }
    private static Pair<Integer, Integer> countStudentsInUniversity(Queue<Student> university) {
    int mathCount = 0;
    int lingCount = 0;
    for (Student student : university) {
        if (student == Student.LINGUIST) {
            lingCount++;
        } else {
            mathCount++;
        }
    }
    return new Pair<>(lingCount, mathCount);
    
}

    private static boolean checkSizes(int lingCount, int mathCount, Queue<Student> randUniversity,
            Queue<Student> mathUniversity, Queue<Student> lingUniversity, StupidQueue stupidQueue) {
        return ((lingCount + mathCount) == 
                (randUniversity.size() + mathUniversity.size() + lingUniversity.size())) 
                && (stupidQueue.size() == 0);        
    }

    private static void printSizes(Queue<Student> lingQueue, Queue<Student> mathQueue, 
            Queue<Student> randUniversity, Queue<Student> mathUniversity, 
            Queue<Student> linguistUniversity, StupidQueue stupidQueue) {
        System.out.println("stupidQueue.size() = " + stupidQueue.size());
        System.out.println("lingQueue.size() = " + lingQueue.size());
        System.out.println("mathQueue.size() = " + mathQueue.size());
        System.out.println("randUniversity.size() = " + randUniversity.size());
        System.out.println("mathUniversity.size() = " + mathUniversity.size());
        System.out.println("linguistUniversity.size() = " + linguistUniversity.size());
    }
}
