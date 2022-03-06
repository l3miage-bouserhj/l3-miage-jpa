package fr.uga.im2ag.l3.miage.db.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
@Entity
@NamedQueries({
    @NamedQuery(name = "findHighestGrades", query = "select g from Grade g order by g.value desc"),
    @NamedQuery(name = "findHighestGradeBySubject", query = "select g from Grade g where g.subject = :subject order by g.value DESC")
})
@Table(name = "Grade")
public class Grade {

    @Id @GeneratedValue
    Long id;
    @ManyToOne
    private Subject subject;
    @Column(name = "grade")
    private Float value;
    @Column(name = "weight")
    private Float weight;

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Grade setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public Grade setValue(Float value) {
        this.value = value;
        return this;
    }

    public Float getWeight() {
        return weight;
    }

    public Grade setWeight(Float weight) {
        this.weight = weight;
        return this;
    }

    // Ajout des méthodes equals() et haschCade() (étant obligatoires, adder() et toString() étant optionnelles) 

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (!(other instanceof Grade)) {return false;}
        Grade grade = (Grade) other;
        if ((this.getId() == null && grade.getId() != null) || (this.getId() != null && grade.getId() == null)) {
            return false;
        }
        return /*this.getId() == grade.getId() &&*/ this.getSubject() == grade.getSubject() && this.getValue() == grade.getValue() && this.getWeight() == grade.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getSubject(),getValue(),getWeight());
    }
}
