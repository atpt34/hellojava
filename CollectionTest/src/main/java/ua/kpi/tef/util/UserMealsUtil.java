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

/**
 * GKislin
 * 31.05.2015.
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
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, 
            LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapDayCalories = getCaloriesPerDay(mealList);
        System.out.println(mapDayCalories);
        List<UserMealWithExceed> result = new ArrayList<UserMealWithExceed>();
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
        Map<LocalDate, Integer> mapDayCalories = new HashMap<LocalDate, Integer>();
        for (UserMeal userMeal : mealList) {
            LocalDate mealDay = userMeal.getDateTime().toLocalDate();
            int dayCalories = mapDayCalories.getOrDefault(mealDay, 0);
            mapDayCalories.put(mealDay, userMeal.getCalories() + dayCalories);
        }
        return mapDayCalories;
    }
}
