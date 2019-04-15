package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserProfileViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@RequestScoped
public class UserProfileBean {

    private UserProfileViewModel model;
    private UserService userService;
    private ModelMapper mapper;

    public UserProfileBean() {
    }

    @Inject
    public UserProfileBean(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.initModel();
    }

    private void initModel() {
//        String id = FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap()
//                .get("id");
//        UserServiceModel userServiceModel = this.userService.getUserById(id);
//
//        if (userServiceModel == null) {
//            throw new IllegalArgumentException("Something went wrong!");
//        }
//
//        this.model = this.mapper.map(userServiceModel, UserProfileViewModel.class);
    }

    public UserProfileViewModel getModel() {
        return model;
    }

    public void setModel(UserProfileViewModel model) {
        this.model = model;
    }
}
