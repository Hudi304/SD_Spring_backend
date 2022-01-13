package com.example.ProiectDistributedSystems.repository;

import com.example.ProiectDistributedSystems.model.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface LogRepository extends CrudRepository<Log, Long> {


    //    @Query("SELECT u FROM log u WHERE u.sensor_id = :sensorId")
//    Set<Log> findAllBySensorId(Long sensorId);


//    @Query(value = "SELECT log " +
//            "FROM Log log, Sensor sen, Device dev " +
//            "WHERE log.sensor.id = :guid ")
//    Set<Log> findAllBySensorId(@Param("guid") Long sensor_id);


    @Query(value = "SELECT er " +
            "FROM Log er, Sensor s, Device d " +
            "WHERE er.sensor.id = s.id " +
            "AND s.id = d.sensor.id " +
            "AND d.user.id = :guid ")
    Set<Log> findAllBySensorId(@Param("guid") Long user_id);


    List<Log> findAll();
}
