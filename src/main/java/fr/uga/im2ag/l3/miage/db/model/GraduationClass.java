package fr.uga.im2ag.l3.miage.db.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
        @NamedQuery(name = "gcfindByYearAndName", query = "select gc from GraduationClass gc where gc.year = :year and gc.name = :name"),
        @NamedQuery(name = "gcgetAll", query = "select gc from GraduationClass gc")
})
@Table(name = "GraduationClass")

public class GraduationClass {
    @Id
    @GeneratedValue
    @Column(name = "id") 
    private Long id;
    @Column(name = "Grad_name")
    private String name;
    @Column(name = "Grad_year")
    private Integer year;
    @OneToMany(mappedBy = "belongTo")
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GraduationClass setId(Long id) {
        this.id = id;
        return this;
    }

    public GraduationClass setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public GraduationClass setYear(Integer year) {
        this.year = year;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public GraduationClass setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    // Ajout des méthodes equals() et haschCade() (étant obligatoires, adder() et toString() étant optionnelles) 

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (!(other instanceof GraduationClass)) {return false;}
        GraduationClass gradClass = (GraduationClass) other;
        if ((this.getId() == null && gradClass.getId() != null) || (this.getId() != null && gradClass.getId() == null)) {
            return false;
        }
        return /*this.getId() == grade.getId() &&*/ this.getName() == gradClass.getName() && this.getYear() == gradClass.getYear() && this.getStudents() == gradClass.getStudents();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getName(),getYear(),getStudents());
    }
}