package ua.kpi.tef.util;

import ua.kpi.tef.model.UserMeal;
import ua.kpi.tef.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * me
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> result = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
        
        System.out.println(result);
        result = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        System.out.println(result);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, 
            LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapDayCalories = getCaloriesPerDay(mealList);
        System.out.println(mapDayCalories);
        List<UserMealWithExceed> result = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            LocalDate mealDay = userMeal.getDateTime().toLocalDate();
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                boolean exceeded = mapDayCalories.get(mealDay) > caloriesPerDay;
                result.add(new UserMealWithExceed(userMeal.getDateTime(), 
                        userMeal.getDescription(), userMeal.getCalories(), exceeded));
            }
        }
        return result;
    }

    private static Map<LocalDate, Integer> getCaloriesPerDay(List<UserMeal> mealList) {
        Map<LocalDate, Integer> mapDayCalories = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate mealDay = userMeal.getDateTime().toLocalDate();
            int dayCalories = mapDayCalories.getOrDefault(mealDay, 0);
            mapDayCalories.put(mealDay, userMeal.getCalories() + dayCalories);
        }
        return mapDayCalories;
    }
    
    public static List<UserMealWithExceed>  getFilteredWithExceededStream(List<UserMeal> mealList, 
            LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapDayCalories = getCaloriesPerDayStream(mealList);
        System.out.println(mapDayCalories);
        return mealList.stream()
        .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
        .map(m -> new UserMealWithExceed(m.getDateTime(), m.getDescription(), 
                m.getCalories(), mapDayCalories.get(m.getLocalDate()) > caloriesPerDay)).collect(Collectors.toList());
    }
    
    private static Map<LocalDate, Integer> getCaloriesPerDayStream(List<UserMeal> mealList) {
      return  mealList.stream()
          .collect(
//              Collectors.groupingBy(UserMeal::getLocalDate,
//                Collectors.reducing(0, UserMeal::getCalories, Integer::sum))
            Collectors.groupingBy(m -> m.getDateTime().toLocalDate(), 
              Collectors.summingInt(UserMeal::getCalories))
          );
    }
}
