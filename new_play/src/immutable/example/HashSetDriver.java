package immutable.example;

import java.util.HashSet;
import java.util.Objects;

final class ImmutablePerson {
    private final String name;
    private final int age;
    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ImmutablePerson [name=").append(name).append(", age=").append(age).append("]");
        return builder.toString();
    }
    public String toStringTwo() {
        return Objects.toString(name) + Objects.toString(age);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    public int hashCodeTwo() {
        return Objects.hash(age, name);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ImmutablePerson other = (ImmutablePerson) obj;
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
public class HashSetDriver {

    public static void main(String[] args) {

//        int[] arr = {13, 31, 4, 13, -14, 9, 19 };
//        for (int i : arr) {
//            System.out.println(i);
//        }
        ImmutablePerson p = null;
        if (p == null) {
            p = new ImmutablePerson("Oleksii", 23);
        }
        
        HashSet<ImmutablePerson> hs = new HashSet<>();
        hs.add(p);
        hs.add(new ImmutablePerson("david", 3));
        
        System.out.println(hs.contains(p));
        System.out.println(p);
        System.out.println(hs.contains(new ImmutablePerson("Oleksii", 24)));
        System.out.println(hs.contains(new ImmutablePerson("Oleksii", 23)));
        System.out.println(hs.size());
        
        System.out.println(p.hashCode() + " and " + p.hashCodeTwo());
        System.out.println(p.toStringTwo());
        System.out.println(null == null);
    }

}
