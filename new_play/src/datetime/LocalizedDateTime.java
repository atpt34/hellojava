package datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

public class LocalizedDateTime {

    public static void main(String[] args) {

//        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.CHINA);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.TAIWAN);
        
        
        LocalDate now = LocalDate.now();
//        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
        System.out.println(now.format(formatter));
        
        



    }

}
