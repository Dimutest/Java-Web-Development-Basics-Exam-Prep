package casebook.web.beans;

import casebook.domain.models.service.JobServiceModel;
import casebook.domain.models.view.JobDetailsViewModel;
import casebook.service.JobService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "jobDetailsBean")
@RequestScoped
public class JobDetailsBean {

    private JobDetailsViewModel model;
    private JobService jobService;
    private ModelMapper modelMapper;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        JobServiceModel jobServiceModel = this.jobService.getJobById(id);

        this.model = this.modelMapper.map(jobServiceModel, JobDetailsViewModel.class);
    }

    public JobDetailsViewModel getModel() {
        return model;
    }

    public void setModel(JobDetailsViewModel model) {
        this.model = model;
    }
}
