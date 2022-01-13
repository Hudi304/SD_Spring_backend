package com.example.ProiectDistributedSystems.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sensor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column
    private Double maximumValueMonitored;
// todo Vechi
//    @OneToOne(mappedBy = "sensor")
//    @JsonBackReference
    //todo NOI


    @OneToOne
    @JoinColumn(name = "device_id")
    @JsonBackReference
    private Device device;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Log> logs;

    @Builder
    public Sensor(String description, Double maximumValueMonitored, Device device, Set<Log> logs) {
        this.description = description;
        this.maximumValueMonitored = maximumValueMonitored;
        this.device = device;
        this.logs = logs;
    }

//    @Override
//    public String toString() {
//        return "Sensor{" +
//                "id=" + id +
//                ", description='" + description + '\'' +
//                ", maximumValueMonitored=" + maximumValueMonitored +
////                ", device=" + device +
////                ", logs=" + logs +
//                '}';
//    }
}
