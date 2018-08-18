package test_project;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.junit.Test;



public class StackMaxElem {

    private static class StackWithMax<T extends Comparable<T>> {
        private Deque<T> stack;
        private List<T> maxStack;
        public StackWithMax(Collection<T> c) {
            maxStack = new LinkedList<>();
            stack = new ArrayDeque<>();
            for (T integer : c) {
                push(integer);
            }
            
        }
        public T pop() {
            T pop = stack.pop();
            if (pop.compareTo(max()) == 0) {
                maxStack.remove(maxStack.size() - 1);
            }
            return pop;
        }
        public void push(T a) {
            stack.push(a);
            if (maxStack.isEmpty() || a.compareTo(max()) >= 0) {
                maxStack.add(a);
            }
        }
        /* constant time operation */
        public T max() {
            if (maxStack.isEmpty()) {
                throw new RuntimeException("empty stack");
            }
            return maxStack.get(maxStack.size() - 1);
        }
        public boolean isEmpty() {
            return stack.isEmpty();
        }
        public int size() {
            return stack.size();
        }
    }
    
    public static <T> Deque<T> listToStack(List<T> list) {
        ArrayDeque<T> stack = new ArrayDeque<T>();
        for (T t : list) {
            stack.addLast(t);
        }
        return stack;
    }
    
    public <T extends Comparable<T>> void checkStackWorking(StackWithMax<T> stack, Deque<T> seq) {
        assertEquals(seq.size(), stack.size());
        while (!stack.isEmpty()) {
            assertEquals(seq.pollLast(), stack.max());
//            System.out.println(stack.max());
            stack.pop();
        }
    }
    
    @Test
    public void testOneElem() {
        int elem = 7;
        StackWithMax<Integer> stack = new StackWithMax<Integer>(List.of(elem));
        checkStackWorking(stack, listToStack(List.of(elem)));
    }
    
    @Test
    public void testTwoElems() throws Exception {
        StackWithMax<Integer> stack = new StackWithMax<>(List.of(1, 3));
        checkStackWorking(stack, listToStack(List.of(1, 3)));
    }
    
    @Test
    public void testTwoElems2() throws Exception {
        List<Integer> sample = List.of(11, 3);
        StackWithMax stack = new StackWithMax(sample);
        checkStackWorking(stack, listToStack(constructSeqFromList(sample)));
    }
    
    @Test
    public void testTwoElems3() throws Exception {
        List<String> sample = List.of("a", "c", "b");
        StackWithMax<String> stack = new StackWithMax<>(sample);
        checkStackWorking(stack, listToStack(constructSeqFromList(sample)));
    }
    
    @Test
    public void testAsc() throws Exception {
        StackWithMax stack = new StackWithMax(List.of(1, 3, 5, 8, 11));
        checkStackWorking(stack, listToStack(List.of(1, 3, 5, 8, 11)));
    }
    
    @Test
    public void testDesc() throws Exception {
        StackWithMax stack = new StackWithMax(List.of(11, 8, 5, 3, 1));
        checkStackWorking(stack, listToStack(Collections.nCopies(5, 11)));
    }
    
    @Test
    public void testRepeats() throws Exception {
        List<Integer> sample = List.of(3, 5, 5, 3, 1);
        StackWithMax stack = new StackWithMax(sample);
        checkStackWorking(stack, listToStack(constructSeqFromList(sample)));
    }
    
    @Test
    public void testRandom() throws Exception {
        StackWithMax stack = new StackWithMax(List.of(7, 3, 5, 8, 3));
        checkStackWorking(stack, listToStack(constructSeqFromList(List.of(7, 3, 5, 8, 3))));
    }
    
    @Test
    public void testRandomNew() throws Exception {
        List<Integer> list = ThreadLocalRandom.current().ints().limit(10000).boxed().collect(Collectors.toList());
        StackWithMax stack = new StackWithMax(list);
        checkStackWorking(stack, listToStack(constructSeqFromList(list)));
    }

    @Test
    public void testConstructSeqFromList() throws Exception {
        assertEquals(List.of(3, 3, 3), constructSeqFromList(List.of(3, 2, 1)));
        assertEquals(List.of(1, 2, 3), constructSeqFromList(List.of(1, 2, 3)));
    }
    
    private static <T extends Comparable<T>> List<T> constructSeqFromList(List<T> list) {
        List<T> res = new ArrayList<>();
        res.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            res.add(Collections.max(list.subList(0, i + 1), T::compareTo));
        }
        return res;
    }
}
