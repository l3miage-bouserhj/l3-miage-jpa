package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import fr.uga.im2ag.l3.miage.db.model.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepositoryImpl extends BaseRepositoryImpl implements StudentRepository {


    /**
     * Build a base repository
     *
     * @param entityManager the entity manager
     */
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void save(Student entity) {
        entityManager.persist(entity);

    }

    @Override
    public void delete(Student entity) {
        entityManager.remove(entity);

    }

    @Override
    public Student findById(Long id) {
        // TODO
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAll() {
        // TODO
        return entityManager.createNamedQuery("stugetAll", Student.class)
                .getResultList();
        /* ou bien 
        String jpql = "select s from Student s";
        List<Student> students = entityManager.createQuery(jpql, Student.class)
        .getResultList();
        return students;
        */
    }

    @Override
    public List<Student> findStudentHavingGradeAverageAbove(float minAverage) {
        // TODO
        /*return entityManager.createNamedQuery("stuHavingGradeAboveAverage", Student.class)
                .setParameter("minAverage", minAverage)
                .getResultList();
         ou bien*/ 
        String jpql = "select s from Student s join s.grades g group by s.firstName, s.lastName HAVING avg(g.value * g.weight) > :minAverage";
        List<Student> students = entityManager.createQuery(jpql, Student.class)
        .setParameter("minAverage", minAverage)
        .getResultList();
        return students;
        
    }
}
