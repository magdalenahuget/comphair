package com.huget.comphair.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "hairdresser_actions",
            joinColumns = { @JoinColumn(name = "hairdresser_id") },
            inverseJoinColumns = { @JoinColumn(name = "action_id") })
    private Set<Action> actions = new HashSet<>();

    public Hairdresser(String nick, HairdresserType hairdresserType, String email, String password) {
        this.nick = nick;
        this.hairdresserType = hairdresserType;
        this.email = email;
        this.password = password;
    }

    public void addAction(Action action) {
        this.actions.add(action);
        action.getHairdressers().add(this);
    }

    public void removeAction(long actionId) {
        Action actionToRemove = this.actions.stream().filter(action -> action.getId() == actionId).findFirst().orElse(null);
        if (actionToRemove != null) {
            this.actions.remove(actionToRemove);
            actionToRemove.getHairdressers().remove(this);
        }
    }
}