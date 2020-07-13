package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Meal m WHERE m.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("SELECT FROM meals WHERE user_id=:userId  AND date_time >=startDateTime AND date_time < endDateTime ORDER BY date_time DESC")
    List<Meal> getBetweenHalfOpen(@Param("startDateTime")LocalDateTime startDateTime, @Param("endDateTime")LocalDateTime endDateTime, @Param("userId") int userId);
}
