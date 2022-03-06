package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import fr.uga.im2ag.l3.miage.db.model.GraduationClass;

import javax.persistence.EntityManager;
import java.util.List;

public class GraduationClassRepositoryImpl extends BaseRepositoryImpl implements GraduationClassRepository {

    public GraduationClassRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public GraduationClass findByYearAndName(Integer year, String name) {
        // TODO DONE
        String jpql = "select gc from GraduationClass gc where gc.year = :year and gc.name = :name";
        GraduationClass gradClass = entityManager.createQuery(jpql, GraduationClass.class)
        .setParameter("year", year)
        .setParameter("name", name)
        .getSingleResult();
        return gradClass;
        /* ou bien
        return entityManager.createNamedQuery("gcfindByYearAndName", GraduationClass.class)
                .setParameter("year", year)
                .setParameter("name", name)
                .getSingleResult();
        */
    }

    @Override
    public void save(GraduationClass entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(GraduationClass entity) {
        entityManager.remove(entity);
    }

    @Override
    public GraduationClass findById(Long id) {
        // TODO DONE
        return entityManager.find(GraduationClass.class, id);
    }

    @Override
    public List<GraduationClass> getAll() {
        // TODO DONE
        String jpql = "select gc from GraduationClass gc";
        List<GraduationClass> gradClasses = entityManager.createQuery(jpql, GraduationClass.class)
        .getResultList();
        return gradClasses;
        /* ou bien
        return entityManager.createNamedQuery("gcgetAll", GraduationClass.class)
                .getResultList();
        */
    }
}
