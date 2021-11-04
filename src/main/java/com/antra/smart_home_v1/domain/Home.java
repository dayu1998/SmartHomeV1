package com.antra.smart_home_v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="home")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String address;


    @OneToMany(mappedBy = "home", cascade = {CascadeType.ALL})
    private List<Device> devices;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



}
