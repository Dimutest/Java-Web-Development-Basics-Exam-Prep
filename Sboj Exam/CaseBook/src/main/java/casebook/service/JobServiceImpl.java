package casebook.service;

import casebook.domain.entities.Job;
import casebook.domain.models.service.JobServiceModel;
import casebook.repository.JobRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobServiceModel createJob(JobServiceModel jobServiceModel) {
        return this.modelMapper.map(
                this.jobRepository
                        .save(this.modelMapper.map(jobServiceModel, Job.class)),
                JobServiceModel.class);
    }

    @Override
    public void removeJob(String id) {
        this.jobRepository.delete(id);
    }

    @Override
    public JobServiceModel getJobById(String id) {
        return this.modelMapper.map(this.jobRepository.findById(id), JobServiceModel.class);
    }

    @Override
    public List<JobServiceModel> getAllJobs() {
        return this.jobRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, JobServiceModel.class))
                .collect(Collectors.toList());
    }
}
