package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);
    List<MealTo> listOfMeals = (Arrays.asList(
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Яичница на утречко с колбаской и овощами", 500, false),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Супец на обед, да котлетки под картошечку", 1000, false),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Салатец на ужин, с мясцом походу, раз 500 калорий", 500, false),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Ночной дожор", 100, true),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Плотный завтрак, мясо, тесто, даже мазик", 1000, true),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Лобстеры, но мало, так что снова 500 калорий", 500, true),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Пивасик", 410, true)
    ));


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");


        request.setAttribute("listOfMeals", listOfMeals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
