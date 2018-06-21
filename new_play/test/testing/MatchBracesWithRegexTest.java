package testing;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import org.junit.Test;

class BraceMatcher {
    
    public static boolean matchBraces(String str) {
        Map<Character, Integer> openings = Map.of('(', 1, '[', 2);
        Map<Character, Integer> closings = Map.of(')', 1, ']', 2);
        Deque<Integer> stack = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (openings.containsKey(c)) {
                stack.push(openings.get(c));
            } else {
                if (closings.containsKey(c) && (closings.get(c) == stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    public static boolean matchBracesWithRegex(String str) {
        int prev = 0;
        do {
            prev = str.length();
            str = str.replaceAll("((\\(\\))|(\\[\\]))", "");
        } while(prev != str.length());
        return str.isEmpty();
    }
}

public class MatchBracesWithRegexTest {

    @Test
    public void testMatchBraces() throws Exception {
        assertTrue(BraceMatcher.matchBraces(""));
        assertTrue(BraceMatcher.matchBraces("()"));
        assertTrue(BraceMatcher.matchBraces("[]"));
        assertTrue(BraceMatcher.matchBraces("()[]"));
        assertTrue(BraceMatcher.matchBraces("[]()"));
        assertTrue(BraceMatcher.matchBraces("([])"));
        assertTrue(BraceMatcher.matchBraces("[()]"));
        assertTrue(BraceMatcher.matchBraces("(()[])"));
        assertTrue(BraceMatcher.matchBraces("[([])]"));
        assertTrue(BraceMatcher.matchBraces("[([])]([()])"));
        assertTrue(BraceMatcher.matchBraces("[()[(([])[[]]()[([])])](()[])]"));
        
        assertFalse(BraceMatcher.matchBraces("(]"));
        assertFalse(BraceMatcher.matchBraces("[(])"));
    }
    @Test
    public void testEmpty() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex(""));
    }
    
    @Test
    public void testSimple() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex("()"));
    }
    
    @Test
    public void testSimpleNested() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex("(())"));
        assertTrue(BraceMatcher.matchBracesWithRegex("((()))"));
        assertTrue(BraceMatcher.matchBracesWithRegex("(((())))"));
    }
    
    @Test
    public void testSimpleSequence() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex("()()"));
        assertTrue(BraceMatcher.matchBracesWithRegex("()()()"));
        assertTrue(BraceMatcher.matchBracesWithRegex("()()()()"));
    }
    
    @Test
    public void testSimpleMixed() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex("(()())"));
        assertTrue(BraceMatcher.matchBracesWithRegex("()(())"));
        assertTrue(BraceMatcher.matchBracesWithRegex("(())()"));
        assertTrue(BraceMatcher.matchBracesWithRegex("(()(()))"));
        assertTrue(BraceMatcher.matchBracesWithRegex("(()())()"));
        assertTrue(BraceMatcher.matchBracesWithRegex("()(()())"));
        assertTrue(BraceMatcher.matchBracesWithRegex("(())(())"));
        assertTrue(BraceMatcher.matchBracesWithRegex("(())()()"));
    }
    
    @Test
    public void testBig() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex("(((())()())()(()))()((((())))()(()())())(()())((()))"));
    }
    
    @Test
    public void testBracesWithSquareBracket() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex("[]()"));
        assertTrue(BraceMatcher.matchBracesWithRegex("()[]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("([])"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[()]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("([()])"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[([])]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("(()[])"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[[]()]"));
    }
    
    @Test
    public void testBracesWithSquareBracketIllegal() throws Exception {
        assertFalse(BraceMatcher.matchBracesWithRegex("(]"));
        assertFalse(BraceMatcher.matchBracesWithRegex("[)"));
        assertFalse(BraceMatcher.matchBracesWithRegex("[(])"));
        assertFalse(BraceMatcher.matchBracesWithRegex("([)]"));
        assertFalse(BraceMatcher.matchBracesWithRegex("(][())"));
        assertFalse(BraceMatcher.matchBracesWithRegex("[[(])]"));
        assertFalse(BraceMatcher.matchBracesWithRegex("([[(])])"));
        assertFalse(BraceMatcher.matchBracesWithRegex("[(([])])"));
        assertFalse(BraceMatcher.matchBracesWithRegex("((()[)])"));
        assertFalse(BraceMatcher.matchBracesWithRegex("[[[(]()])]"));
    }
    
    @Test
    public void testSquareBracket() throws Exception {
        assertTrue(BraceMatcher.matchBracesWithRegex("[]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[][]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[[]]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[][][]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[][[]]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[[]][]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[[[]]]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[[[]]][]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[][[[]]]"));
        assertTrue(BraceMatcher.matchBracesWithRegex("[[]][[]]"));
    }
    
    @Test
    public void testNotBrace() throws Exception {
        assertFalse(BraceMatcher.matchBracesWithRegex("aa"));
        assertFalse(BraceMatcher.matchBracesWithRegex("<>"));
        
        assertFalse(BraceMatcher.matchBracesWithRegex("{}"));
    }
    
    @Test
    public void testOneIllegal() throws Exception {
        assertFalse(BraceMatcher.matchBracesWithRegex(")"));
        assertFalse(BraceMatcher.matchBracesWithRegex("))"));
        assertFalse(BraceMatcher.matchBracesWithRegex(")))"));
        assertFalse(BraceMatcher.matchBracesWithRegex("("));
        assertFalse(BraceMatcher.matchBracesWithRegex("(("));
        assertFalse(BraceMatcher.matchBracesWithRegex("((("));
    }
    
    @Test
    public void testMoreIllegal() throws Exception {
        assertFalse(BraceMatcher.matchBracesWithRegex(")("));
        assertFalse(BraceMatcher.matchBracesWithRegex(")()"));
        assertFalse(BraceMatcher.matchBracesWithRegex("()("));
        assertFalse(BraceMatcher.matchBracesWithRegex("())("));
        assertFalse(BraceMatcher.matchBracesWithRegex(")(()"));
        assertFalse(BraceMatcher.matchBracesWithRegex(")()("));
        assertFalse(BraceMatcher.matchBracesWithRegex("())(()"));
        assertFalse(BraceMatcher.matchBracesWithRegex("(())())"));
    }
        
}
