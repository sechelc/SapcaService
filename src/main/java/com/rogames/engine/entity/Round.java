package com.rogames.engine.entity;

import javax.persistence.*;

/**
 * Created by sechelc on 10.04.2016.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID",unique = true, length = 20)
    private Long id;
    @Column
    private String text;
    @Column
    private String points;
    @Column
    private String options;

    @Transient
    private Team team;


    public Round() {
    }

    public Round(String text, String points, String options) {
        this.text = text;
        this.points = points;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
