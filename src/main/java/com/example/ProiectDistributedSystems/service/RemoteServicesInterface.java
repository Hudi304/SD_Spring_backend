package com.example.ProiectDistributedSystems.service;

import com.example.ProiectDistributedSystems.model.Device;
import com.example.ProiectDistributedSystems.model.Log;
import org.springframework.http.ResponseEntity;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RemoteServicesInterface {

    String testMethod() throws RemoteException;

    Map<String, List<Log>> getRecordingsByDateAndDayNumber(Long userId, String date, int daysNumber);

    Set<Log> computeLogs(Long sensorId);

//    ResponseEntity computeLogs(Long sensorId);

//    List<Log> computeLogs(Long sensorId);


    List<Log> getAllRecordings();

    Device getDevice(Long sensorId);
}
