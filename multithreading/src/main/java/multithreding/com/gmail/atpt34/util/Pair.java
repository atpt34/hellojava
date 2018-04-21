package multithreding.com.gmail.atpt34.util;

import java.util.Objects;

public class Pair<T, U> {
    private final T first;
    private final U second;
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
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Pair))
            return false;
        Pair other = (Pair) obj;
        if (first == null) {
            if (other.first != null)
                return false;
        } else if (!first.equals(other.first))
            return false;
        if (second == null) {
            if (other.second != null)
                return false;
        } else if (!second.equals(other.second))
            return false;
        return true;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pair [first=").append(first).append(", second=").append(second).append("]");
        return builder.toString();
    }    
}
