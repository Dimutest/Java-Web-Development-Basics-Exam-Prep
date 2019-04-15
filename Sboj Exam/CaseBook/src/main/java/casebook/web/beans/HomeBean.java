package casebook.web.beans;

import casebook.domain.models.view.JobHomeViewModel;
import casebook.service.JobService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "homeBean")
@RequestScoped
public class HomeBean extends BaseBean {

    private List<JobHomeViewModel> models;
    private JobService jobService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.models = this.jobService
                .getAllJobs()
                .stream()
                .map(j -> this.modelMapper.map(j, JobHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobHomeViewModel> getModels() {
        return models;
    }

    public void setModels(List<JobHomeViewModel> models) {
        this.models = models;
    }
}
