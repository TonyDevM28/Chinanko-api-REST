package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ProfileUserRequest;
import chinanko.chinanko.dto.ProfileUserResponse;

public interface ProfileUserService {
    List<ProfileUserResponse> listAll();
    ProfileUserResponse getById(Integer idProfileUser);
    ProfileUserResponse create(ProfileUserRequest p);
    ProfileUserResponse update(Integer idProfileUser, ProfileUserRequest p);
}
