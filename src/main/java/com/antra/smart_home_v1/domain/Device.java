package com.antra.smart_home_v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer serialNumber;

    private String deviceName;

    @Column(name="make")
    private String make;

    @Column(name="status")
    private Boolean status;

    private String category;

    private String location;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="home_id")
    private Home home;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


}
