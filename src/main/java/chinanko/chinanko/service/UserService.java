package chinanko.chinanko.service;

import java.util.List;
import java.util.Optional;

import chinanko.chinanko.dto.UserLoginRequest;
import chinanko.chinanko.dto.UserRequest;
import chinanko.chinanko.dto.UserResponse;
import chinanko.chinanko.model.User;

public interface UserService {
    List<UserResponse> findAll();

    UserResponse findById(Integer idUser);

    UserResponse create(UserRequest request);

    UserResponse update(Integer idUser, UserRequest request);

    public List<UserResponse> getUserByName(String name);

    public Optional<UserResponse> getUserByEmail(String email);

    public List<UserResponse> findAll(int page, int pageSize);
    public User authenticate(UserLoginRequest input);
}
