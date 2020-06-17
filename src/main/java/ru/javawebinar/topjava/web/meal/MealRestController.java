package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;
import java.util.Collections;

@Controller
public class MealRestController {
    private MealService service;

    public MealRestController(MealService service) {
        this.service = service;
    }
     public Meal create(Meal meal) {return service.create(meal);}

     public void delete(int id) {service.delete(id);}

     public Meal get(int id) {return service.get(id);}

     public void update(Meal meal){service.update(meal);}

     public Collection<Meal> getAll(){return service.getAll();}
}