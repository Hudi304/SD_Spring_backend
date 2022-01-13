package com.example.ProiectDistributedSystems.service;


import com.example.ProiectDistributedSystems.model.Device;
import com.example.ProiectDistributedSystems.model.Log;
import com.example.ProiectDistributedSystems.model.Sensor;
import com.example.ProiectDistributedSystems.repository.DeviceRepository;
import com.example.ProiectDistributedSystems.repository.LogRepository;
import com.example.ProiectDistributedSystems.repository.SensorRepository;

import java.rmi.RemoteException;
import java.util.*;

public class RemoteServiceImplementation implements RemoteServicesInterface {

    LogRepository logRepository;
    SensorRepository sensorRepository;
    DeviceRepository deviceRepository;

    public RemoteServiceImplementation(LogRepository logRepository, DeviceRepository deviceRepository, SensorRepository sensorRepository) {
        this.logRepository = logRepository;
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public String testMethod() throws RemoteException {
        System.out.println("Test method working.");
        return "Working";
    }

    // todo primul grafic are pe ox 24h si pe oy consum, numarul de zile este variabil
    //  daca pui mai multe zile din front le aduna pe fiecare ora luni ora 12 50w marti ora 12 50 w
    //  pe o zi la 12 e 50w pe 2 e 110w
    //  la al doilea e media lor nu suma
    @Override
    public Set<Log> computeLogs(Long user_id) {
        System.out.println("RPC computeLogs user_id : " + user_id);

        Set<Log> logs = new HashSet<>();
        try {

             logs = logRepository.findAllBySensorId(user_id);




        } catch (Exception e) {
            System.out.println("EXCEPTION");
        }


        return logs;
    }

    @Override
    public Map<String, List<Log>> getRecordingsByDateAndDayNumber(Long userId, String date, int daysNumber) {
        System.out.println("RPC Get Recordings by date and days requested.");
//        List<Log> sensorRecordings = logRepository.findAllByUserId(userId);
//        List<Log> resultList;
//        Map<String, List<Log>> resultMap = new HashMap<>(Collections.emptyMap());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        SimpleDateFormat formatOnlyDate = new SimpleDateFormat("yyyy-MM-dd");
//        try{
//            Date startingDate = format.parse(date);
//            for (int i = 0; i <= daysNumber; i++) {
//                Date currentDay = Date.from(startingDate.toInstant().plus(i, ChronoUnit.DAYS));
//                currentDay = formatOnlyDate.parse(formatOnlyDate.format(currentDay));
//                resultList = new ArrayList<>();
//                for (Log recording : sensorRecordings) {
//                    Date recordingDate = format.parse(recording.getTimestamp());
//                    Date nextDay = Date.from(currentDay.toInstant().plus(1, ChronoUnit.DAYS));
//                    System.out.println(recording);
//                    if (recordingDate.after(currentDay) && recordingDate.before(nextDay)) {
//                        resultList.add(recording);
//                    }
//                }
//                resultMap.put(currentDay.toString(), resultList);
//            }
//            return resultMap;
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return Collections.emptyMap();
//        }

        return Collections.emptyMap();
    }


    @Override
    public List<Log> getAllRecordings() {
        List<Log> resultList = new ArrayList<>();
        logRepository.findAll().forEach(resultList::add);
        return resultList;
    }

    @Override
    public Device getDevice(Long sensorId) {
        return deviceRepository.findFirstBySensorId(sensorId);
    }
}