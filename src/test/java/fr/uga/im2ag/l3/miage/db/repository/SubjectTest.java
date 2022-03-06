package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Subject;
import fr.uga.im2ag.l3.miage.db.model.Teacher;
import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

class SubjectTest extends Base {

    SubjectRepository subjectRepository;
    GraduationClassRepository graduationClassRepository;

    @BeforeEach
    void before() {
        subjectRepository = daoFactory.newSubjectRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveSubject() {

        final var subject = Fixtures.createSubject();
            final var matiere = Fixtures.createSubject();

        entityManager.getTransaction().begin();
        subjectRepository.save(subject);
            subjectRepository.save(matiere);
        entityManager.getTransaction().commit();
        entityManager.detach(subject);
            entityManager.detach(matiere);

        var pSubject = subjectRepository.findById(subject.getId());
        assertThat(pSubject).isNotNull().isNotSameAs(subject);
        assertThat(pSubject.getName()).isEqualTo(subject.getName());

            var pMatiere = subjectRepository.findById(matiere.getId());
            assertThat(pMatiere).isNotNull().isNotSameAs(matiere);
            assertThat(pMatiere.getName()).isEqualTo(matiere.getName());

    }

    @Test
    void shouldFindTeachersForSubject() {
        // TODO
        Subject subject = Fixtures.createSubject();
        Subject subject_S = Fixtures.createSubject();

        subject.setName("Histoire");
        subject_S.setName("Science");
        
        Teacher teacher = Fixtures.createTeacher(subject, null, null);
        Teacher teacher_2 = Fixtures.createTeacher(subject, null, null);
        Teacher teacher_3 = Fixtures.createTeacher(subject_S, null, null);

        teacher.setFirstName("Tarik");
        teacher_2.setFirstName("Bobby");
        teacher_3.setFirstName("Garry");
        
        entityManager.getTransaction().begin();
        
        entityManager.persist(subject);
        entityManager.persist(subject_S);
        entityManager.persist(teacher);
        entityManager.persist(teacher_2);
        entityManager.persist(teacher_3);
        
        entityManager.getTransaction().commit();

        entityManager.detach(subject_S);
        entityManager.detach(subject);
        entityManager.detach(teacher);
        entityManager.detach(teacher_2);
        entityManager.detach(teacher_3);

        ArrayList<Teacher> result =  new ArrayList<Teacher>(subjectRepository.findTeachers(subject.getId()) );
        assertThat(result.get(0).getFirstName()=="Tarik");
        assertThat(result.get(1).getFirstName()=="Bobby");
        assertThat(result.size()==2);
        
        ArrayList<Teacher> result_2 = new ArrayList<Teacher>( subjectRepository.findTeachers(subject_S.getId()) );
        assertThat(result_2.get(0).getFirstName()=="Garry");
        assertThat(result_2.size()==1);
    }

}
