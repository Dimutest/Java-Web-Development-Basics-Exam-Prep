package casebook.web.beans;

import casebook.domain.models.binding.UserLoginBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("loginBean")
@RequestScoped
public class LoginBean extends BaseBean {

    private UserLoginBindingModel model;
    private UserService userService;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.model = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getModel() {
        return model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }

    public void login() {
        UserServiceModel user = this.userService.getUserByUsername(this.model.getUsername());

        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(this.model.getPassword()))) {
            return;
        }

        var sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("user-id", user.getId());
        sessionMap.put("username", user.getUsername());
        this.redirect("/home");
    }
}
