package sbt.weather.jpa_data;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateCrudRepository extends CrudRepository<EntityRate, Long> {
    Optional<EntityRate> findByDateAndDays(String date, int days);
    void delete(EntityRate entity);
    EntityRate save(EntityRate rate);
}