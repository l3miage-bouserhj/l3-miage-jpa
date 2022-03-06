package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.model.GraduationClass;
import fr.uga.im2ag.l3.miage.db.model.Subject;
import fr.uga.im2ag.l3.miage.db.model.Teacher;
import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeacherTest extends Base {

    TeacherRepository teacherRepository;

    @BeforeEach
    void before() {
        teacherRepository = daoFactory.newTeacherRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveTeacher()  {
        // TODO
        Subject subject = Fixtures.createSubject();
    	subject.setName("Chemistry");
    	
    	GraduationClass terminale = Fixtures.createClass();
    	terminale.setName("Terminale");
    	terminale.setYear(2019);
    	terminale.setStudents(null);
    	
    	Grade grade_a = Fixtures.createGrade(subject);
    	grade_a.setValue((float) 10.0);
    	grade_a.setWeight((float) 1.5);
    	
    	Grade grade_b = Fixtures.createGrade(subject);
    	grade_b.setValue((float) 12.0);
    	grade_b.setWeight((float) 1.5);
    	
    	Teacher teacher = Fixtures.createTeacher(subject, terminale, null);
    	
        entityManager.getTransaction().begin();

        entityManager.persist(subject);
        entityManager.persist(terminale);
        entityManager.persist(grade_a);
        entityManager.persist(grade_b);
        entityManager.persist(teacher);

        entityManager.getTransaction().commit();
        
        entityManager.detach(subject);
        entityManager.detach(terminale);
        entityManager.detach(grade_a);
        entityManager.detach(grade_b);
        entityManager.detach(teacher);
        
        Teacher aTeacher = teacherRepository.findById(teacher.getId());
        assertThat(aTeacher.getId()==1);
        assertThat(aTeacher.getHeading().getName()=="Terminale");
        assertThat(aTeacher.getTeaching().getName()=="Chemistry");
    }

    @Test
    void shouldFindHeadingGraduationClassByYearAndName() {
        // TODO
        Subject subject = Fixtures.createSubject();
    	subject.setName("History");
    	
    	GraduationClass gradClass = Fixtures.createClass();
    	gradClass.setName("GEO");
    	gradClass.setYear(2015);
    	gradClass.setStudents(null);
    	
    	Grade gradeA = Fixtures.createGrade(subject);
    	gradeA.setValue((float) 10.0);
    	gradeA.setWeight((float) 1.5);
    	
    	Grade gradeB = Fixtures.createGrade(subject);
    	gradeB.setValue((float) 12.0);
    	gradeB.setWeight((float) 1.5);

    	Teacher teacher = Fixtures.createTeacher(subject, gradClass, null);
    	teacher.setFirstName("Walter");
    	
        entityManager.getTransaction().begin();
        
        entityManager.persist(subject);
        entityManager.persist(gradClass);
        entityManager.persist(gradeA);
        entityManager.persist(gradeB);
        entityManager.persist(teacher);

        entityManager.getTransaction().commit();

        entityManager.detach(subject);
        entityManager.detach(gradClass);
        entityManager.detach(gradeA);
        entityManager.detach(gradeB);
        entityManager.detach(teacher);
        
        assertThat(teacherRepository.findHeadingGraduationClassByYearAndName(2015, "GEO").getFirstName()).isEqualTo(teacher.getFirstName());
        assertThat(teacherRepository.findHeadingGraduationClassByYearAndName(2015, "GEO").getHeading().getName() == "History");
        assertThat(teacherRepository.findHeadingGraduationClassByYearAndName(2015, "GEO").getHeading().getYear() == 2015);
    }

}
