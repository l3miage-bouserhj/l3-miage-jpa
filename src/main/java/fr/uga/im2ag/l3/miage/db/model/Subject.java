package fr.uga.im2ag.l3.miage.db.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository

@Entity
@NamedQuery(name="subject-by-id",
            query="select s from Subject s where s.id = :id")
@Table(name = "Subject")
public class Subject {

    @Id @GeneratedValue Long id;

    // private Long id;
    private String name;
    private Integer points;
    private Float hours;
    private Date start;
    @Column (name = "end_date")
    private Date end;

    public Long getId() {
        return id;
    }

    /*public Subject setId(Long id) {
        this.id = id;
        return this;
    }*/

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public Subject setPoints(Integer points) {
        this.points = points;
        return this;
    }

    public Float getHours() {
        return hours;
    }

    public Subject setHours(Float hours) {
        this.hours = hours;
        return this;
    }

    public Date getStart() {
        return start;
    }

    public Subject setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public Subject setEnd(Date end) {
        this.end = end;
        return this;
    }

    // Ajout des méthodes equals() et haschCade() (étant obligatoires, adder() et toString() étant optionnelles) 

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
