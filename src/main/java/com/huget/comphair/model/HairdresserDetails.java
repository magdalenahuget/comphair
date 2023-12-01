package com.huget.comphair.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "hairdresser_details")
public class HairdresserDetails {

    @Id
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String description;

    @OneToOne(fetch= FetchType.LAZY)
    @MapsId
    @JoinColumn(name ="hairdresser_id")
    private Hairdresser hairdresser;

}
