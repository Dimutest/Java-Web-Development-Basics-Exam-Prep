package casebook.web.beans;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserFriendsViewModel;
import casebook.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserFriendsBean {

    private List<UserFriendsViewModel> models;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserFriendsBean() {
    }

    @Inject
    public UserFriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initModels();
    }

    private void initModels() {
        String id = (String) (((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession()).getAttribute("userId");
        // here
//        this.models = this.userService
//                .getUserById((String) ((HttpSession) FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getSession(false))
//                .getAttribute("id"))
//                .getFriends()
//                .stream()
//                .map(f -> this.modelMapper.map(f, UserFriendsViewModel.class))
//                .collect(Collectors.toList());
        this.models = this.userService
                .getUserById(id)
                .getFriends()
                .stream()
                .map(f -> this.modelMapper.map(f, UserFriendsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<UserFriendsViewModel> getModels() {
        return models;
    }

    public void setModels(List<UserFriendsViewModel> models) {
        this.models = models;
    }

    public void removeFriend(String id) throws IOException {
        String loggedInUserId = (String) ((HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false))
                .getAttribute("userId");

        UserServiceModel loggedInUser = this.userService.getUserById(loggedInUserId);
        UserServiceModel friendToRemove = this.userService.getUserById(id);
        loggedInUser.getFriends().removeIf(friend -> friend.getId().equals(friendToRemove.getId()));
        friendToRemove.getFriends().removeIf(friend -> friend.getId().equals(loggedInUser.getId()));

        if (!this.userService.removeFriend(loggedInUser)) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        if (!this.userService.removeFriend(friendToRemove)) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("/friends");
    }
}
