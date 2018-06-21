package testing;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

import org.junit.Test;

public class QueueRemoveTest {

    @Test
    public void doTest() throws Exception {
        HashSet<Integer> expected = new HashSet<>();
        Queue<Integer> randomizedQueue = new ArrayDeque<>();
        assertTrue("wasn't init as empty queue", randomizedQueue.isEmpty());
        int n = 10;
        for (int i = 0; i < n; i++) {
            expected.add(i);
            randomizedQueue.add(i);
        }
        assertFalse("queue can't be empty after add", randomizedQueue.isEmpty());
        Integer dequeued = randomizedQueue.poll();
        assertNotNull("removed item can't be null", dequeued);
        expected.remove(dequeued);
        HashSet<Integer> actual = new HashSet<>();
        for (int integer : randomizedQueue) {
            actual.add(integer);
        }
        assertEquals("random item wasn't removed or other elements were affected", expected, actual);
    }
}
