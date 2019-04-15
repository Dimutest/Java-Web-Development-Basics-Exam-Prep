package casebook.repository;

import casebook.domain.entities.Job;

public interface JobRepository extends GenericRepository<Job, String> {
    void delete(String id);
}
