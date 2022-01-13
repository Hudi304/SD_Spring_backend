package com.example.ProiectDistributedSystems;

import com.example.ProiectDistributedSystems.model.*;
import com.example.ProiectDistributedSystems.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProiectDistributedSystemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProiectDistributedSystemsApplication.class, args);
    }


    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           DeviceRepository deviceRepository,
                           SensorRepository sensorRepository,
                           AdminRepository adminRepository,
                           LogRepository logRepository) {
        return args -> {

            Admin admin = Admin.builder()
                    .username("admin")
                    .password("admin")
                    .build();
            adminRepository.save(admin);

            Device device = Device.builder()
                    .address("SomeDeviceAdress")
                    .description("SomeDeviceDescription")
                    .avgConsumption(15d)
                    .maxConsumption(25d)
                    .build();

            Device device2 = Device.builder()
                    .address("SomeDeviceAdress2")
                    .description("SomeDeviceDescription2")
                    .avgConsumption(1d)
                    .maxConsumption(2d)
                    .build();

            User user = User.builder()
                    .password("Hudi")
                    .username("Hudi")
                    .address("cevaAdresa")
                    .birthDate("cavaNastere")
                    .isAdmin(false)
                    .build();


            Sensor sensor = Sensor.builder()
                    .description("SomeSensor1")
                    .maximumValueMonitored(1d)
                    .build();

            Sensor sensor2 = Sensor.builder()
                    .description("SomeSensor12")
                    .maximumValueMonitored(2d)
                    .build();


            Log log1 = Log.builder()
                    .date("Sat Dec 11 2021 10:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(1.5d)
                    .build();
            Log log2 = Log.builder()
                    .date("Sat Dec 11 2021 11:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(2.2d)
                    .build();
            Log log3 = Log.builder()
                    .date("Sat Dec 11 2021 12:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(8d)
                    .build();
            Log log4 = Log.builder()
                    .date("Sat Dec 11 2021 13:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(11d)
                    .build();
            Log log5 = Log.builder()
                    .date("Sat Dec 11 2021 14:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(11d)
                    .build();
            Log log6 = Log.builder()
                    .date("Sat Dec 11 2021 15:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(11d)
                    .build();

            Log log21 = Log.builder()
                    .date("Sat Dec 12 2021 10:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor2)
                    .value(1.5d)
                    .build();
            Log log22 = Log.builder()
                    .date("Sat Dec 12 2021 11:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor2)
                    .value(2.2d)
                    .build();
            Log log23 = Log.builder()
                    .date("Sat Dec 12 2021 12:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor2)
                    .value(8d)
                    .build();
            Log log24 = Log.builder()
                    .date("Sat Dec 12 2021 13:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor2)
                    .value(11d)
                    .build();
            Log log25 = Log.builder()
                    .date("Sat Dec 12 2021 14:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor2)
                    .value(11d)
                    .build();
            Log log26 = Log.builder()
                    .date("Sat Dec 12 2021 15:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor2)
                    .value(11d)
                    .build();


            Set<Log> logs = new HashSet<>();
            logs.add(log1);
            logs.add(log2);
            logs.add(log3);
            logs.add(log4);
            logs.add(log5);
            logs.add(log6);


            Set<Log> logs2 = new HashSet<>();
            logs.add(log21);
            logs.add(log22);
            logs.add(log23);
            logs.add(log24);
            logs.add(log25);
            logs.add(log26);

//            sensor.setLogs(logs);
//            sensor2.setLogs(logs2);


//            logRepository.save(log1);
//            logRepository.save(log2);
//            logRepository.save(log3);
//            logRepository.save(log4);
//            logRepository.save(log5);
//            logRepository.save(log6);
//
//            logRepository.save(log21);
//            logRepository.save(log22);
//            logRepository.save(log23);
//            logRepository.save(log24);
//            logRepository.save(log25);
//            logRepository.save(log26);



            deviceRepository.save(device);
            deviceRepository.save(device2);


            userRepository.save(user);

            userRepository.findFirstByUsernameAndPassword(user.getUsername(), user.getPassword());
            deviceRepository.findFirstByDescriptionAndAddress(device.getDescription(), device.getAddress());

            device.setUser(user);
            device2.setUser(user);

            sensor.setDevice(device);
            sensor2.setDevice(device2);

            sensorRepository.save(sensor);
            deviceRepository.save(device);

            sensorRepository.save(sensor2);
            deviceRepository.save(device2);

            userRepository.save(user);
        };
    }

}
