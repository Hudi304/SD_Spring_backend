package com.example.ProiectDistributedSystems.service;

import com.example.ProiectDistributedSystems.DTO.SensorReadingMQDTO;
import com.example.ProiectDistributedSystems.configs.MQConfig;

import com.example.ProiectDistributedSystems.model.Log;
import com.example.ProiectDistributedSystems.model.Sensor;
import com.example.ProiectDistributedSystems.repository.LogRepository;
import com.example.ProiectDistributedSystems.repository.SensorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Set;


@Component
public class RabbitMQRecieve {

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private LogRepository logRepository;

    private final static String QUEUE_NAME = "hello";

    public boolean isFirstReading = true;
    double previousConsumption = 0f;

    HashMap<String, Double> sensorLastValues = new HashMap<String, Double>();


    public RabbitMQRecieve(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(SensorReadingMQDTO energy) {

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();


        System.out.println(localDate + " " + localTime + " | " + energy.toString());

        Long sensorID = energy.getSensorID();

        Sensor sensor = sensorRepository.findFirstById(sensorID);

        if (sensor != null) {


            Log log = Log.builder()
                    .date(energy.getTimeStamp().toString())
                    .sensor(sensor)
                    .value(energy.getValue())
                    .build();

            Set<Log> sensorLogs = sensor.getLogs();

            sensorLogs.add(log);
            sensor.setLogs(sensorLogs);

            sensorRepository.save(sensor);
//
            if (isFirstReading) {
                double peak = energy.getValue() - previousConsumption;
                energy.setMessage("FIRST");
                simpMessagingTemplate.convertAndSend("/topic/updateService", energy);
                previousConsumption = energy.getValue();
                isFirstReading = false;
                sensorLastValues.put("" + sensor.getId(), energy.getValue());

            } else {
                double maxSensorValue = sensor.getMaximumValueMonitored();
                if (maxSensorValue < energy.getValue() - sensorLastValues.get("" + sensor.getId())) {
                    energy.setMessage("EXCEEDS");
                    simpMessagingTemplate.convertAndSend("/topic/updateService", energy);
                    previousConsumption = energy.getValue();
                    sensorLastValues.put("" + sensor.getId(), energy.getValue());

                }else{
                    energy.setMessage("NORMAL");
                    simpMessagingTemplate.convertAndSend("/topic/updateService", energy);
                    previousConsumption = energy.getValue();

                    sensorLastValues.put("" + sensor.getId(), energy.getValue());

                }
            }
        }
    }


}
