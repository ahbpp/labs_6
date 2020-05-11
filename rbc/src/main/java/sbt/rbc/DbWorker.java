package sbt.rbc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface DbWorker extends CrudRepository<RbcDB, Long> {


    //Optional<RbkDb> findById(String date);

    //Double findCurrencyById(Long id);
    Optional<RbcDB> findByDate(String date);
    Optional<RbcDB> findById(String date);
    //Optional<ArrayList<String>> getAllDates();
    //List<RbkDb> fillLastDays(int days);
}