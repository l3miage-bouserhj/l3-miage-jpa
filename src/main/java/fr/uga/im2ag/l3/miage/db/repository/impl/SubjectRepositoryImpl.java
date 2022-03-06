package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import fr.uga.im2ag.l3.miage.db.model.Subject;
import fr.uga.im2ag.l3.miage.db.model.Teacher;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

public class SubjectRepositoryImpl extends BaseRepositoryImpl implements SubjectRepository {


    public SubjectRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void save(Subject entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Subject entity) {
        entityManager.remove(entity);
    }

    @Override
    public Subject findById(Long id) {
        // TODO
        /*
        Subject subject = entityManager.createNamedQuery("subject-by-id", Subject.class)
        .setParameter("id", id)
        .getSingleResult();
        return subject;
        */
        // OR
        return entityManager.find(Subject.class, id);
    }

    @Override
    public List<Subject> getAll() {
        // TODO
        String jpql = "select s from Subject s";
        List<Subject> subject = entityManager.createQuery(jpql, Subject.class)
        .getResultList();
        return subject;
    }

    @Override
    public Collection<Teacher> findTeachers(Long id) {
        // TODO
        String jpql = "select t from Teacher t join t.teaching s where s.id = :id";
        Collection<Teacher> teachers = entityManager.createQuery(jpql, Teacher.class)
                .setParameter("id", id)
                .getResultList();
        return teachers;
    }
}
