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
@Table(name = "hairdressers")
public class Hairdresser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nick")
    private String nick;

    @Column(name = "hairdresser_type")
    private HairdresserType hairdresserType;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Hairdresser(String nick, HairdresserType hairdresserType, String email, String password) {
        this.nick = nick;
        this.hairdresserType = hairdresserType;
        this.email = email;
        this.password = password;
    }
}