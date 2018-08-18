package test_project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;
import static org.junit.Assert.*;

public class SerializationCtor {

    /**
     * 
     * @author Oleksii_Tretinichenk
     *
     */
    static class Animal {
//        static class Animal implements Serializable {
        public Animal() {
            System.out.println("Animal ctor");
        }
    }
    
    static class Part implements Serializable {
        private static final long serialVersionUID = 1L;
        private int a = 10;
        public Part() {
            a = 200;
            System.out.println("Part ctor!");
        }
        @Override
        public String toString() {
            return "Part [a=" + a + "]";
        }
        
    }
    
    /**
     * @author Oleksii_Tretinichenk
     *
     */
    public static class Person extends Animal implements Serializable {
        private static final long serialVersionUID = -1L;
        static String country = "ITALY";
        private int age;
        private String name;
        private Part part = new Part();
        
        private transient int height;
        private transient String hello;
        private transient final String helloFinal;
        
        public Person() {
            System.out.println("Person ctor, set height, hello, helloFinal");
            height = 12345;
            hello = "hello";
            helloFinal = "helloFinal";
        }
        
        public static String getCountry() {
            return country;
        }
        public static void setCountry(String country) {
            Person.country = country;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public static long getSerialversionuid() {
            return serialVersionUID;
        }

        public String getHello() {
            return hello;
        }

        public void setHello(String hello) {
            this.hello = hello;
        }

        public String getHelloFinal() {
            return helloFinal;
        }

        public Part getPart() {
            return part;
        }

        public void setPart(Part part) {
            this.part = part;
        }

        @Override
        public String toString() {
            return "Person [age=" + age + ", name=" + name + ", part=" + part + ", height=" + height + ", hello="
                    + hello + ", helloFinal=" + helloFinal + "]";
        }

        private void writeObject(ObjectOutputStream oos) 
                throws IOException {
            oos.defaultWriteObject();
        }
        
        private void readObject(ObjectInputStream ois) 
                throws ClassNotFoundException, IOException {
            ois.defaultReadObject();
            hello = "hell";
        }
    }
    
//    static class SuperPerson extends Person implements Serializable {
    static class SuperPerson extends Person {
        private static final long serialVersionUID = -3129545867224174305L;

        public SuperPerson() {
            System.out.println("SuperPerson ctor");
        }
    }
    
    @Test
    public void whenSerializingAndDeserializing_ThenObjectIsTheSame() 
            throws IOException, ClassNotFoundException { 
//        Person person = new Person();
        SuperPerson person = new SuperPerson();
        person.setAge(20);
        person.setName("Joe");
        
        System.out.println("serialization...");
        FileOutputStream fileOutputStream = new FileOutputStream("yourfile2.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println(person);
        
        System.out.println("deserialization...");
        System.out.println("Note: no ctor call when deserialization !!!");
        System.out.println("Note: base ctor called iff is NOT serializable !!!");
        System.out.println("Note: ctor of extending class is NOT called");
        System.out.println("If fields classes are not serializable exception will be thrown");
        System.out.println("if ser & des classes serVerUID differs then InvalidClassException will be thrwons");
        FileInputStream fileInputStream = new FileInputStream("yourfile2.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        Person p2 = (Person) objectInputStream.readObject();
        SuperPerson p2 = (SuperPerson) objectInputStream.readObject();
        objectInputStream.close(); 
        System.out.println(p2);
        
        assertTrue(p2.getAge() == person.getAge());
        assertTrue(p2.getName().equals(person.getName()));
    }
}
