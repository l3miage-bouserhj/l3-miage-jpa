package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.model.GraduationClass;
import fr.uga.im2ag.l3.miage.db.model.Student;
import fr.uga.im2ag.l3.miage.db.model.Subject;
import fr.uga.im2ag.l3.miage.db.model.Person.Gender;
import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class StudentTest extends Base {

    StudentRepository studentRepository;

    @BeforeEach
    void before() {
        studentRepository = daoFactory.newStudentRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveStudent() {
        // TODO
        GraduationClass gradClass = Fixtures.createClass();
    	gradClass.setName("L3MIAGE");
    	gradClass.setYear(2022);
    	gradClass.setStudents(null);
        Student student = Fixtures.createStudent(gradClass);
        
        entityManager.getTransaction().begin();
        entityManager.persist(gradClass);
        studentRepository.save(student);
        entityManager.getTransaction().commit();
        entityManager.detach(student);
        
        var studentL3 = studentRepository.findById(student.getId());
        assertThat(studentL3).isNotNull().isNotSameAs(student);
        assertThat(studentL3.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(studentL3.getLastName()).isEqualTo(student.getLastName());
        assertThat(studentL3.getGender()).isEqualTo(student.getGender());
    }

    @Test
    void shouldFindStudentHavingGradeAverageAbove() {
        // TODO
        Subject subject = Fixtures.createSubject();
        subject.setName("Math");
        subject.setHours((float) 5.0);
        
        // Create a date object
        @SuppressWarnings("deprecation")
        Date d = new Date(2022,2,28);
        @SuppressWarnings("deprecation")
        Date e = new Date(2022,3,1);
        
        subject.setStart(d);
        subject.setPoints(5);
        subject.setEnd(e);
        
        
        // Création de la note
           
        Grade grade = Fixtures.createGrade(subject);
        grade.setValue((float) 10.0);
        grade.setWeight((float) 1.5);
        
        var anotherGrade = Fixtures.createGrade(subject);
        anotherGrade.setValue((float) 12.0);
        anotherGrade.setWeight((float) 1.5);
           
        GraduationClass gradClass = Fixtures.createClass();
        gradClass.setName("L3MIAGE");
        gradClass.setYear(2022);
        gradClass.setStudents(null);
    
        Student student = Fixtures.createStudent(gradClass);
        student.setFirstName("Dmitrii");
        student.setGender(Gender.MALE);
        Date Dmitrii_bth = new Date(1999,3,18);
        student.setBirth(Dmitrii_bth);
    
        List<Grade> grades = new ArrayList<Grade>(); 
        grades.add(grade);
        grades.add(anotherGrade);
        
        student.setGrades(grades);
        student.setBelongTo(gradClass);
        student.setLastName("Crivoi");
        
        entityManager.getTransaction().begin();
        
        entityManager.persist(subject);
        entityManager.persist(gradClass);
        entityManager.persist(grade);
        entityManager.persist(anotherGrade);
        entityManager.persist(student);
        
        entityManager.getTransaction().commit();
    
        entityManager.detach(subject);
        entityManager.detach(gradClass);
        entityManager.detach(grade);
        entityManager.detach(anotherGrade);
        entityManager.detach(student);           
           
        assertThat(studentRepository.findStudentHavingGradeAverageAbove((float) 16.5).get(0).getFirstName()=="Dmitrii");
       
    }

}
