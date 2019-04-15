package casebook.repository;

import casebook.domain.entities.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobRepositoryImpl extends BaseRepository implements JobRepository {

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Job save(Job job) {
        return this.executeTransaction((em) -> {
            em.persist(job);
            return job;
        });
    }

    @Override
    public Job update(Job job) {
        return this.executeTransaction((em) -> {
            em.merge(job);
            return job;
        });
    }

    @Override
    public List<Job> findAll() {
        return this.executeTransaction((em) -> {
            return em.createNativeQuery("SELECT * FROM jobs", Job.class)
                    .getResultList();
        });
    }

    @Override
    public Job findById(String id) {
        return this.executeTransaction((em) -> {
            return (Job) em.createNativeQuery("SELECT * FROM jobs WHERE id = '" + id + "'", Job.class)
                    .getSingleResult();
        });
    }

    @Override
    public void delete(String id) {
        this.executeTransaction((em) -> {
            em.createNativeQuery("DELETE FROM jobs WHERE id='" + id + "'").executeUpdate();
            return null;
        });
    }
}
