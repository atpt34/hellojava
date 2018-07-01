package testing;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.Test;

class Employee {
    Map<String, Integer> skills;
    public Employee(Map<String, Integer> skills) {
        this.skills = Objects.requireNonNull(skills);
    }
    // time complexity: O(req size);
    public boolean meetsRequirements(Map<String, Integer> requirements) {
        return requirements.entrySet().stream().allMatch(req -> skills.getOrDefault(req.getKey(), 0) >= req.getValue());
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(skills);
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee))
            return false;
        Employee other = (Employee) obj;
        return skills.equals(other.skills);
    }
    @Override
    public String toString() {
        return "Employee [skills=" + skills + "]";
    }
}

class EmployeeUtils {
    // time complexity: O(emp size * req size);
    public static List<Employee> getMatchedToRequirements(List<Employee> employees, Map<String, Integer> requirements) {
        return employees.stream().filter(e -> e.meetsRequirements(requirements)).collect(Collectors.toList());
    }
}

public class FilterEmployeeSkillsTest {

    @Test
    public void testGetMatchedToRequirementsLevels() throws Exception {
        Employee e1 = new Employee(Map.of("java", 5, "js", 2, "git", 1));
        Employee e2 = new Employee(Map.of("java", 2, "js", 4, "algorithms", 4, "testing", 5, "git", 5));
        Employee e3 = new Employee(Map.of("java", 5, "js", 4, "algorithms", 4, "testing", 5));
        Employee e4 = new Employee(Map.of("java", 5, "js", 1, "algorithms", 4, "testing", 5));
        Employee e5 = new Employee(Map.of("java", 4, "js", 4, "algorithms", 5, "testing", 5));
        Employee e6 = new Employee(Map.of("java", 3, "js", 2, "algorithms", 3, "testing", 5, "git", 1));
        Employee e7 = new Employee(Map.of("java", 4, "js", 4, "algorithms", 4, "testing", 5, "git", 2));
        Employee e8 = new Employee(Map.of("java", 3, "js", 2, "algorithms", 4, "testing", 4, "git", 3));
        Employee e9 = new Employee(Map.of("java", 4, "js", 4, "algorithms", 5, "testing", 5));
        List<Employee> list = List.of(e1, e2, e3, e4, e5, e6, e7, e8, e9);
        Map<String, Integer> requirements = Map.of("java",  3, "js", 2, "algorithms", 4, "testing", 5);
        List<Employee> expected = List.of(e3, e5, e7, e9);
        assertEquals(expected, EmployeeUtils.getMatchedToRequirements(list, requirements));
    }
    
    @Test
    public void testEmployeeEqualsHashCodeToString() throws Exception {
        Employee e1 = new Employee(Map.of("java", 5, "js", 2, "git", 2));
        Map<String, Integer> skills = new HashMap<>();
        skills.put("js", 2);
        skills.put("git", 2);
        skills.put("java", 5);
        Employee e2 = new Employee(skills);
        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
        assertEquals(e1.toString(), "Employee [skills=" + skills + "]");
    }
}
