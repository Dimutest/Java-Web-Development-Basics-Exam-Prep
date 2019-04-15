package casebook.web.beans;

import casebook.domain.models.binding.JobCreateBindingModel;
import casebook.domain.models.service.JobServiceModel;
import casebook.service.JobService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "jobCreateBean")
@RequestScoped
public class JobCreateBean extends BaseBean {

    private JobCreateBindingModel model;
    private JobService jobService;
    private ModelMapper modelMapper;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        this.model = new JobCreateBindingModel();
    }

    public JobCreateBindingModel getModel() {
        return model;
    }

    public void setModel(JobCreateBindingModel model) {
        this.model = model;
    }

    public void createJob() {
        this.jobService.createJob(
                this.modelMapper.map(this.model, JobServiceModel.class));

        this.redirect("/home");
    }
}
