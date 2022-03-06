package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.model.Subject;
import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GradeTest extends Base {

    GradeRepository gradeRepository;

    @BeforeEach
    void before() {
        gradeRepository = daoFactory.newGradeRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveGrade() {
        // TODO
        Subject math = Fixtures.createSubject();
        Grade grade = Fixtures.createGrade(math);

        entityManager.getTransaction().begin();
        entityManager.persist(math);
        gradeRepository.save(grade);
        entityManager.getTransaction().commit();
        entityManager.detach(math);
        entityManager.detach(grade);

        var pGrade = gradeRepository.findById(grade.getId());
        assertThat(pGrade).isNotNull().isNotSameAs(grade);
        assertThat(pGrade.getValue()).isEqualTo(grade.getValue());
        assertThat(pGrade.getWeight()).isEqualTo(grade.getWeight());
    }

    @Test
    void shouldFailUpgradeGrade() {
        // TODO, ici tester que la mise à jour n'a pas eu lieu.
        Subject physics = Fixtures.createSubject();
        Grade grade = Fixtures.createGrade(physics);
        grade.setValue((float) 9.0);

        entityManager.getTransaction().begin();
        entityManager.persist(physics);
        gradeRepository.save(grade);
        entityManager.getTransaction().commit();

        grade.setValue((float) 16.2);
        gradeRepository.save(grade);

        entityManager.detach(grade);

        Grade pGrade = gradeRepository.findById(grade.getId());
        assertThat(pGrade.getValue()).isNotEqualTo(grade.getValue());
    }

    @Test
    void shouldFindHighestGrades() {
        // TODO
        Subject subject = Fixtures.createSubject();
        Grade gradeA = Fixtures.createGrade(subject);
        Grade gradeB = Fixtures.createGrade(subject);
        Grade gradeC = Fixtures.createGrade(subject);
        Grade gradeD = Fixtures.createGrade(subject);

        gradeA.setValue((float) 15);
        gradeB.setValue((float) 13);
        gradeC.setValue((float) 19.2);
        gradeD.setValue((float) 14.55);

        entityManager.getTransaction().begin();
        entityManager.persist(subject);
        gradeRepository.save(gradeA);
        gradeRepository.save(gradeB);
        gradeRepository.save(gradeC);
        gradeRepository.save(gradeD);
        entityManager.getTransaction().commit();
        entityManager.detach(gradeA);
        entityManager.detach(gradeB);
        entityManager.detach(gradeC);
        entityManager.detach(gradeD);

        List<Grade> result = gradeRepository.findHighestGrades(2);
        assertThat(result).isNotEmpty();
        List<Grade> highestGrades = new ArrayList<Grade>();
        highestGrades.add(gradeC);
        highestGrades.add(gradeA);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getValue()).isEqualTo(highestGrades.get(0).getValue());
        assertThat(result.get(1).getValue()).isEqualTo(highestGrades.get(1).getValue());
    }

    @Test
    void shouldFindHighestGradesBySubject() {
        // TODO
        final var math = Fixtures.createSubject();
        final var physics = Fixtures.createSubject();
        final var chemistry = Fixtures.createSubject();

        final var gradeA = Fixtures.createGrade(math);
        final var gradeB = Fixtures.createGrade(physics);
        final var gradeC = Fixtures.createGrade(physics);
        final var gradeD = Fixtures.createGrade(chemistry);
        final var gradeE = Fixtures.createGrade(physics);

        gradeA.setValue((float) 17);
        gradeB.setValue((float) 16);
        gradeC.setValue((float) 17);
        gradeD.setValue((float) 2.5);
        gradeE.setValue((float) 14.6);

        entityManager.getTransaction().begin();
        entityManager.persist(math);
        entityManager.persist(physics);
        entityManager.persist(chemistry);
        gradeRepository.save(gradeA);
        gradeRepository.save(gradeB);
        gradeRepository.save(gradeC);
        gradeRepository.save(gradeD);
        gradeRepository.save(gradeE);
        entityManager.getTransaction().commit();

        var higherGradesSubject = (ArrayList<Grade>) gradeRepository.findHighestGradesBySubject(2, physics);
        assertThat(higherGradesSubject).isNotEmpty();
        var higherGradesSubjectR = new ArrayList<>();
        higherGradesSubjectR.add(gradeC);
        higherGradesSubjectR.add(gradeB);
        assertThat(higherGradesSubject).isEqualTo(higherGradesSubjectR);
    }

}
