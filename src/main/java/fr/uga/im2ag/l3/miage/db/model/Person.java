package fr.uga.im2ag.l3.miage.db.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Person {

    @Id @GeneratedValue
    Long id;
    private Gender gender;
    private String firstName;
    private String lastName;
    private Date birth;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getBirth() {
        return birth;
    }

    public Person setBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Person setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public enum Gender {
        FEMALE, MALE, FLUID
    }

    // Ajout des méthodes equals() et haschCade() (étant obligatoires, adder() et toString() étant optionnelles) 

    @Override
    public boolean equals(Object other) {
        if (this == other) {return true;}
        if (!(other instanceof Person)) {return false;}
        Person person = (Person) other;
        if ((this.getId() == null && person.getId() != null) || (this.getId() != null && person.getId() == null)) {
            return false;
        }
        return /*this.getId() == person.getId() &&*/ this.getFirstName() == person.getFirstName() && this.getLastName() == person.getLastName() && this.getBirth() == person.getBirth() && this.getGender() == person.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getFirstName(),getLastName(),getBirth(),getGender());
    }
}
