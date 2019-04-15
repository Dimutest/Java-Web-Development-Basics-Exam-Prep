package casebook.web.beans;

import casebook.domain.models.binding.UserRegisterBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("registerBean")
@RequestScoped
public class RegisterBean extends BaseBean {

    private UserRegisterBindingModel model;
    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getModel() {
        return model;
    }

    public void setModel(UserRegisterBindingModel model) {
        this.model = model;
    }

    public void register() {
        if(!this.model.getPassword()
                .equals(this.model.getConfirmPassword())) {
            return;
        }

        this.userService.createUser(
                this.modelMapper.map(this.model, UserServiceModel.class));
        this.redirect("/login");
    }
}
