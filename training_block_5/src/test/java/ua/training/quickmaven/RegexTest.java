package ua.training.quickmaven;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ua.training.quickmaven.controller.RegexConstants;

public class RegexTest extends TestCase {

    public static List<String> VALID_NAMES = new ArrayList<String>();
    static {
        VALID_NAMES.add("Oleksii");
        VALID_NAMES.add("OLEKSII");
        VALID_NAMES.add("oleksii");
        VALID_NAMES.add("oleksii-oleksandr");
        VALID_NAMES.add("Maksim");
    }
    public static List<String> VALID_NICK_NAMES = new ArrayList<String>();
    static {
        VALID_NICK_NAMES.add("OLEKSII24");
        VALID_NICK_NAMES.add("Oleksii");
        VALID_NICK_NAMES.add("oleksii");
        VALID_NICK_NAMES.add("Maksim");
        VALID_NICK_NAMES.add("Maksim666");
    }
    public static List<String> VALID_EMAILS = new ArrayList<String>();
    static {
        VALID_EMAILS.add("ATPT34@GMAIL.COM");
        VALID_EMAILS.add("atpt34@gmail.com");
        VALID_EMAILS.add("atpt.34@gmail.com");
        VALID_EMAILS.add("atpt34@new-planet.com");
    }
    public static List<String> VALID_PHONES = new ArrayList<String>();
    static {
        VALID_PHONES.add("0991122333");
        VALID_PHONES.add("099 1122333");
        VALID_PHONES.add("09911-22-333");
        VALID_PHONES.add("+380991122333");
        VALID_PHONES.add("+38099 1122333");
        VALID_PHONES.add("+3809911-22-333");
        VALID_PHONES.add("+38099 11-22-333");
    }
    public static List<String> VALID_ICQS = new ArrayList<String>();
    static {
        VALID_ICQS.add("OLEksii_trt");
        VALID_ICQS.add("OLEksii.trt");
        VALID_ICQS.add("OLEksii%trt");
        VALID_ICQS.add("oLEksii/trt");
        VALID_ICQS.add("OLEksii+trt");
        VALID_ICQS.add("OLEksii-trt");
        VALID_ICQS.add("OLEKSII24");
        VALID_ICQS.add("Oleksii");
        VALID_ICQS.add("oleksii");
        VALID_ICQS.add("Maksim");
        VALID_ICQS.add("Maksim666");
    }
    public static List<String> INVALID_NAMES = new ArrayList<String>();
    static {
        INVALID_NAMES.add("");
        INVALID_NAMES.add("ii");
        INVALID_NAMES.add("Oleksii.");
        INVALID_NAMES.add("oleksiioleksiioleksiioleksiioleksii");
        INVALID_NAMES.add("oleksii--oleksandr");
        INVALID_NAMES.add("oleksii.oleksandr");
        INVALID_NAMES.add("Maksim666");
        INVALID_NAMES.add("666Maksim");
    }
    public static List<String> INVALID_NICK_NAMES = new ArrayList<String>();
    static {
        INVALID_NICK_NAMES.add("");
        INVALID_NICK_NAMES.add("abcde");
        INVALID_NICK_NAMES.add("oleksii-oleksandr");
        INVALID_NICK_NAMES.add("oleksii.oleksandr");
        INVALID_NICK_NAMES.add("0LEKSII");
    }
    public static List<String> INVALID_EMAILS = new ArrayList<String>();
    static {
        INVALID_EMAILS.add("");
        INVALID_EMAILS.add("ATPT34");
        INVALID_EMAILS.add("ATPT34GMAIL.COM");
        INVALID_EMAILS.add("atpt34gmail.com");
        INVALID_EMAILS.add("atpt34@gmail");
        INVALID_EMAILS.add("atpt.gmail.com");
        INVALID_EMAILS.add("34@new-planet.com");
        INVALID_EMAILS.add("34@new.planet.com");
    }
    public static List<String> INVALID_PHONES = new ArrayList<String>();
    static {
        INVALID_PHONES.add("");
        INVALID_PHONES.add("1122333");
        INVALID_PHONES.add("9991122333");
        INVALID_PHONES.add("112-23-33");
        INVALID_PHONES.add("+38099 112-23-33");
    }
    public static List<String> INVALID_ICQS = new ArrayList<String>();
    static {
        INVALID_ICQS.add("");
        INVALID_ICQS.add("abcde");
        INVALID_ICQS.add("0LEKSII");
        INVALID_ICQS.add(".LEKSII");
        INVALID_ICQS.add("OLEksii\trt");
    }
    
    public void testValidNames() {
        for (String string : VALID_NAMES) {
            assertTrue(isMatchRegex(string, RegexConstants.NAME));
        }
    }
    
    public void testInvalidNames() {
        for (String string : INVALID_NAMES) {
            assertFalse(isMatchRegex(string, RegexConstants.NAME));
        }
    }
    
    public void testValidNickNames() {
        for (String string : VALID_NICK_NAMES) {
            assertTrue(isMatchRegex(string, RegexConstants.NICK_NAME));
        }
    }
    
    public void testInvalidNickNames() {
        for (String string : INVALID_NICK_NAMES) {
            assertFalse(isMatchRegex(string, RegexConstants.NICK_NAME));
        }
    }

    public void testValidEmails() {
        for (String string : VALID_EMAILS) {
            assertTrue(isMatchRegex(string, RegexConstants.EMAIL));
        }
    }
    
    public void testInvalidEmails() {
        for (String string : INVALID_EMAILS) {
            assertFalse(isMatchRegex(string, RegexConstants.EMAIL));
        }
    }
    
    public void testValidPhones() {
        for (String string : VALID_PHONES) {
            assertTrue(isMatchRegex(string, RegexConstants.PHONE));
        }
    }
    
    public void testInvalidPhones() {
        for (String string : INVALID_PHONES) {
            assertFalse(isMatchRegex(string, RegexConstants.PHONE));
        }
    }
    
    public void testValidIcqs() {
        for (String string : VALID_ICQS) {
            assertTrue(isMatchRegex(string, RegexConstants.ICQ));
        }
    }
    
    public void testInvalidIcqs() {
        for (String string : INVALID_ICQS) {
            assertFalse(isMatchRegex(string, RegexConstants.ICQ));
        }
    }
    
    private boolean isMatchRegex(String string, String regex) {
        return string.matches(regex);
    }
}
