package test_project;

import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Parameterized.class)
public class ParseKeyValuesFromString {
    private static final String KEY_REGEX = "[a-z]{2,5}";
    private static final String VALUE_REGEX = "[0-9]{3}";
    private static final Pattern PATTERN = Pattern.compile(
            String.format("(Key=)(%s)(, Value=)(%S)(;)(\r?\n)?", KEY_REGEX, VALUE_REGEX));
    private static final int PATTERN_KEY_GROUP_INDEX = 2;
    private static final int PATTERN_VALUE_GROUP_INDEX = 4;
  
    /*
     * You can always parse simple text with regular expressions.
     */
    public static Map<String, Integer> parse(String input) {
        Matcher matcher = PATTERN.matcher(input);
        Map<String, Integer> map = new HashMap<>();
        while (matcher.find()) {
            String key = matcher.group(PATTERN_KEY_GROUP_INDEX);
            System.out.println(key);
            String value = matcher.group(PATTERN_VALUE_GROUP_INDEX);
            System.out.println(value);
            map.put(key, Integer.parseInt(value));
        }
        return map;
    }
    
    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"Key=a4, Value=077;", Map.of()},
            {"Key=a, Value=077;", Map.of()},
            {"Key=aaaaaa, Value=077;", Map.of()},
            {"Key=aa, Value=7a;", Map.of()},
            {"Key=aa, Value=77;", Map.of()},
            {"Key=aa, Value=1234;", Map.of()},
            {"Key=aa,Value=077;", Map.of()},
            {"Key=aa, Value=077", Map.of()},
            {"Key=aa, Value=077;", Map.<String, Integer>of("aa", 77)},
            {"Key=aa, Value=077;\n", Map.<String, Integer>of("aa", 77)},
            {"Key=ggf, Value=177;", Map.<String, Integer>of("ggf", 177)},
            {"Key=za, Value=917;\n  Key=bb, Value=532;", Map.<String, Integer>of("za", 917, "bb", 532)},
            {"Key=zz, Value=147;  Key=cc, Value=232;\n", Map.<String, Integer>of("zz", 147, "cc", 232)},
            {"Key=ab, Value=111;  Key=abc, Value=222;\n Key=abcd, Value=333;", Map.<String, Integer>of("ab", 111, "abc", 222, "abcd", 333)},
            {"Key=za, Value=917;\n  Key=bb, Value=5v2;", Map.<String, Integer>of("za", 917)},
            {"Key=za Value=917;\n  Key=bb, Value=520;", Map.<String, Integer>of("bb", 520)},
        });
    }
    
    @Parameter(0)
    public String input;
    
    @Parameter(1)
    public Map<String, Integer> expectedMap;
    
    @Test
    public void test() throws Exception {
        assertEquals(expectedMap, parse(input));
    }
}
