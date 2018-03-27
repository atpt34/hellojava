package ua.training.tasktwo;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/**
 * 
 * @author atpt34
 *
 * @param <E>
 */
public class MyArrayList<E> extends AbstractList<E> 
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{

    private static final long serialVersionUID = -7912989857716725259L;
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;
    
    public MyArrayList(Class<E> clazz) {
        this(clazz, DEFAULT_CAPACITY);
    }
    
    
    @SuppressWarnings("unchecked")
    public MyArrayList(Class<E> clazz, int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                capacity);
        this.elements = (E[]) Array.newInstance(clazz, capacity);
    }
    
    public MyArrayList(Class<E> clazz, Collection<? extends E> c) {
        this(clazz, c.size());
        size = c.size();
        c.toArray(elements);
    }
    
    public int getCurrentCapacity() {
        return elements.length;
    }
    
    private void ensureCapacityInternal(int newSize) {
        if (elements.length < newSize) {
            int newCapacity = elements.length << 1;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  
        elements[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        rangeCheck(index);
        ensureCapacityInternal(size + 1);  
        System.arraycopy(elements, index, elements, index + 1,
                         size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = elementData(index);
        elements[index] = element;
        return oldValue;
    }
    
    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("remove method is not supported");
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elements[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elements[i]))
                    return i;
        }
        return -1;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("remove method is not supported");
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("remove method is not supported");

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("remove method is not supported");
    }

    @Override
    protected Object clone() {
        throw new UnsupportedOperationException("clone method is not supported");
    }

    E elementData(int index) {
        return elements[index];
    }
    
    @Override
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }
    
    private void rangeCheck(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("index out of bound");
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(elements);
        result = prime * result + size;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyArrayList<?> other = (MyArrayList<?>) obj;
        if (!Arrays.equals(elements, other.elements))
            return false;
        if (size != other.size)
            return false;
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MyArrayList [elements=").append(Arrays.toString(elements)).append(", size=").append(size)
                .append("]");
        return builder.toString();
    }

}
