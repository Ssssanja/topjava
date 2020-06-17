package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create (Meal meal) {
        log.info("create {}", meal);
        return repository.save(meal);
    }
    public void delete(int id) {
        log.info("delete meal {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(int id) {
        log.info("get meal {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Collection<Meal> getAll() {
        log.info("getAll Meals");
        return repository.getAll();
    }

    public void update(Meal meal) {
        log.info("update {}", meal);
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }
}