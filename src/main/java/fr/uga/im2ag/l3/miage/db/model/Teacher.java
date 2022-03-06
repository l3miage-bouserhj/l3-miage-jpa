package fr.uga.im2ag.l3.miage.db.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
@Entity
@NamedQuery(name = "teachHeadingGradClassByYearAndName", query = "select t from Teacher t join t.heading gc where gc.year = :year and gc.name = :name")
@Table(name = "Teacher")
public class Teacher extends Person {

    @ManyToOne
    private Subject teaching;
    @OneToMany
    private List<Student> favorites;
    @OneToOne
    private GraduationClass heading;

    public Subject getTeaching() {
        return teaching;
    }

    public Teacher setTeaching(Subject teaching) {
        this.teaching = teaching;
        return this;
    }

    public List<Student> getFavorites() {
        return favorites;
    }

    public Teacher setFavorites(List<Student> favorites) {
        this.favorites = favorites;
        return this;
    }

    public GraduationClass getHeading() {
        return heading;
    }

    public Teacher setHeading(GraduationClass heading) {
        this.heading = heading;
        return this;
    }

    // Ajout des méthodes equals() et haschCade() (étant obligatoires, adder() et toString() étant optionnelles) 

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (!(other instanceof Teacher)) {return false;}
        Teacher teacher = (Teacher) other;
        if ((this.getId() == null && teacher.getId() != null) || (this.getId() != null && teacher.getId() == null)) {
            return false;
        }
        return super.equals((Person) other) && this.getTeaching() == teacher.getTeaching() && this.getFavorites() == teacher.getFavorites() && this.getHeading() == teacher.getHeading();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTeaching(), getHeading(), getFavorites());
    }
}
