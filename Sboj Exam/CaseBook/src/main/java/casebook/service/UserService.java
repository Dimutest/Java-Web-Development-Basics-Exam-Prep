package casebook.service;

import casebook.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel createUser(UserServiceModel userServiceModel);

    UserServiceModel getUserByUsername(String username);
}
