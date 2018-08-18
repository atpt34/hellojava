package test_project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class StableSort {

    private static class Person implements Comparable<Person> {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public int getAge() {
            return age;
        }
        public String getName() {
            return name;
        }
        @Override
        public int compareTo(Person o) {
            int to = name.compareTo(o.name);
            if (to == 0) {
                return age - o.age;
            }
            return to;
        }
        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + age;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Person other = (Person) obj;
            if (age != other.age)
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }
        
    }
    
    public List<Person> sort(List<Person> list) {
        List<Person> result = new ArrayList<>(list);
        Collections.sort(result, Person::compareTo);
        return result;
    }
    
    public List<Person> sortByAge(List<Person> list) {
        List<Person> result = new ArrayList<>(list);
        Collections.sort(result, Comparator.comparingInt(Person::getAge).reversed());
        return result;
    }
    
    private static class Pair<T, U> {
        private T first;
        private U second;
        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }
        public T getFirst() {
            return first;
        }
        public U getSecond() {
            return second;
        }
    }
    
    public List<Person> stableSortByName(List<Person> list) {
        List<Pair<Person, Integer>> entries = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entries.add(new Pair<>(list.get(i), i));
        }
        Comparator<Person> comparing = Comparator.comparing(Person::getName);
        Comparator<Pair<Person, Integer>> cmp = Comparator.comparing(Pair::getFirst, comparing);
        Comparator<Pair<Person, Integer>> cmpWithIndex = cmp.thenComparingInt(Pair::getSecond);
        
        // anonymous class
//        Comparator<Pair<Person, Integer>> cmp = new Comparator<Pair<Person, Integer>>() {
//            @Override
//            public int compare(Pair<Person, Integer> p1, Pair<Person, Integer> p2) {
//                int to = p1.getFirst().getName().compareTo(p2.getFirst().getName());
//                if (to == 0) {
//                    return p1.getSecond().compareTo(p2.getSecond());
//                }
//                return to;
//            }
//        };
        return entries.stream()
                .sorted(cmpWithIndex)
                .map(Pair::getFirst).collect(Collectors.toList());
    }
    
    /*public List<Person> stableSortByName(List<Person> list) {
        LinkedHashMap<Person, Integer> entries = new LinkedHashMap<>();
        for (int i = 0; i < list.size(); i++) {
            entries.put(list.get(i), i);
        }
        Comparator<Person> cmp = Comparator.comparing(Person::getName); // Entry.comparingByValue()
        return entries.entrySet().stream()
                .sorted(Entry.comparingByKey(cmp))
                .map(Entry::getKey).collect(Collectors.toList());
    }*/
    
    private static Person person1 = new Person("alex", 20);
    private static Person person2 = new Person("alex", 21);
    private static Person person3 = new Person("alex", 22);
    private static Person person4 = new Person("zeta", 33);
    private static Person person5 = new Person("zeta", 35);
    private static Person person6 = new Person("zeta", 38);
    
    private static final List<Person> SORTED = List.of(person1, person2, person3, person4, person5, person6);
    private static final List<Person> LIST = List.of(person5, person1, person6, person2, person3, person4);
    
    
    
    @Test
    public void testSort() throws Exception {
        List<Person> expected = SORTED;
        List<Person> actual = sort(LIST);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSortByAge() throws Exception {
        List<Person> expected = List.of(person6, person5, person4, person3, person2, person1);
        List<Person> actual = sortByAge(LIST);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testStableSortByName() throws Exception {
        List<Person> list = List.of(person3, person5, person1, person4, person6, person2);
        List<Person> actual = stableSortByName(list);
        List<Person> expected = List.of(person3, person1, person2, person5, person4, person6);
        assertEquals(expected, actual);
    }
}
