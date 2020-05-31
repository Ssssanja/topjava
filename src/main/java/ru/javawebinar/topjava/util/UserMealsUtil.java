package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess>result = new ArrayList<>();
        Map<LocalDate, Integer> data = new TreeMap<>();
        for (UserMeal uM : meals){
            if (!data.containsKey(uM.getDateTime().toLocalDate())){
                data.put(uM.getDateTime().toLocalDate(), uM.getCalories());
            }
            else {
                int temporal = data.get(uM.getDateTime().toLocalDate());
                temporal += uM.getCalories();
                data.put(uM.getDateTime().toLocalDate(), temporal);
            }
        }
        for (Map.Entry<LocalDate, Integer> entry : data.entrySet()){
            if (entry.getValue()<=2000){
                for (UserMeal uM : meals){
                    if (uM.getDateTime().toLocalDate().isEqual(entry.getKey())&&uM.getDateTime().toLocalTime().isAfter(startTime)&&uM.getDateTime().toLocalTime().isBefore(endTime)){
                        result.add(new UserMealWithExcess(uM.getDateTime(), uM.getDescription(), uM.getCalories(), false));
                    }
                }
            }
            else if (entry.getValue()>2000){
                for (UserMeal uM : meals){
                    if (uM.getDateTime().toLocalDate().isEqual(entry.getKey())&&uM.getDateTime().toLocalTime().isAfter(startTime)&&uM.getDateTime().toLocalTime().isBefore(endTime)){
                        result.add(new UserMealWithExcess(uM.getDateTime(), uM.getDescription(), uM.getCalories(), true));
                    }
                }
            }
        }
        return result;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
