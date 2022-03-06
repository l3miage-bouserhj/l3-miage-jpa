package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
@Entity
@NamedQueries({
    @NamedQuery(name = "stugetAll", query = "select s from Student s"),
    @NamedQuery(name = "stuHavingGradeAboveAverage", query = "select s from Student s join s.grades g group by s.firstName, s.lastName HAVING avg(g.value * g.weight) > :minAverage")
})
@Table(name = "Student")
public class Student extends Person {

    @ManyToOne
    private GraduationClass belongTo;
    @OneToMany
    private List<Grade> grades;

    public GraduationClass getBelongTo() {
        return belongTo;
    }

    public Student setBelongTo(GraduationClass belongTo) {
        this.belongTo = belongTo;
        return this;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public Student setGrades(List<Grade> grades) {
        this.grades = grades;
        return this;
    }

    // Ajout des méthodes equals() et haschCade() (étant obligatoires, adder() et toString() étant optionnelles) 

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (!(other instanceof Student)) {return false;}
        Student student = (Student) other;
        if ((this.getId() == null && student.getId() != null) || (this.getId() != null && student.getId() == null)) {
            return false;
        }
        return super.equals((Person) other) && this.getBelongTo() == student.getBelongTo() && this.getGrades() == student.getGrades();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBelongTo(), getGrades());
    }
}
