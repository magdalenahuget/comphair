package com.huget.comphair.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name="roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @Enumerated(EnumType.STRING)
    private RoleType name;

    public Role() {

    }

    public Role(RoleType name) {
        this.name = name;
    }
}
