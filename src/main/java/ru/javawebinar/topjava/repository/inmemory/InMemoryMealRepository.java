package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map <Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(meal.getUserId()).put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.get(meal.getUserId()).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        for (Map.Entry<Integer,Map<Integer, Meal>> entry : repository.entrySet()){
            for (Map.Entry<Integer, Meal>mealEntry: entry.getValue().entrySet()){
                if (mealEntry.getKey().equals(id)){
                    result = entry.getValue().remove(id)!=null;
                }
            }
        }
        return result;
    }

    @Override
    public Meal get(int id) {
        Meal meal = null;
        for (Map.Entry<Integer,Map<Integer, Meal>> entry : repository.entrySet()){
            for (Map.Entry<Integer, Meal>mealEntry: entry.getValue().entrySet()){
                if (mealEntry.getKey().equals(id)){
                    meal = mealEntry.getValue();
                }
            }
        }
        return meal;
    }

    @Override
    public Collection<Meal> getAll() {
        Comparator<Meal>comp = new Comparator<Meal>() {
            @Override
            public int compare(Meal o1, Meal o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        };
        List<Meal>result = new ArrayList<Meal>((Collection<? extends Meal>) comp);
        for (Map.Entry<Integer,Map<Integer, Meal>> entry : repository.entrySet()){
            for (Map.Entry<Integer, Meal>mealEntry: entry.getValue().entrySet()){
                result.add(mealEntry.getValue());
            }
        }
        Collections.reverse(result);
        return result;
    }
}

