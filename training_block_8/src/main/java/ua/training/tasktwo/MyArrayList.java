package ua.training.tasktwo;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<E> extends AbstractList<E> {

    private ArrayList<E> values;
    
    public MyArrayList() {
        this.values = new ArrayList<E>();
    }
    
    public MyArrayList(int capacity) {
        this.values = new ArrayList<E>(capacity);
    }
    
    public MyArrayList(Collection<E> values) {
        this.values = new ArrayList<E>(values);
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        values.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        values.sort(c);
    }

    @Override
    public Spliterator<E> spliterator() {
        return values.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return values.removeIf(filter);
    }

    @Override
    public Stream<E> stream() {
        return values.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return values.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        values.forEach(action);
    }

    @Override
    public boolean add(E e) {
        return values.add(e);
    }

    @Override
    public E set(int index, E element) {
        return values.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        values.add(index, element);
    }

    @Override
    public E remove(int index) {
        return values.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return values.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return values.lastIndexOf(o);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return values.addAll(index, c);
    }

    @Override
    public Iterator<E> iterator() {
        return values.iterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return values.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return values.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return values.subList(fromIndex, toIndex);
    }

    @Override
    public boolean equals(Object o) {
        return values.equals(o);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return values.hashCode();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return values.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return values.contains(o);
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return values.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // TODO Auto-generated method stub
        return values.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return values.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return values.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // TODO Auto-generated method stub
        return values.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return values.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return values.retainAll(c);
    }

    @Override
    public String toString() {
        return values.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return values.clone();
    }

    @Override
    public E get(int index) {
        return values.get(index);
    }

    @Override
    public int size() {
        return values.size();
    }

}
