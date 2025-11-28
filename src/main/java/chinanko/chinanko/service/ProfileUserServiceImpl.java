package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.ProfileUserRequest;
import chinanko.chinanko.dto.ProfileUserResponse;
import chinanko.chinanko.mapper.ProfileUserMapper;
import chinanko.chinanko.model.ProfileUser;
import chinanko.chinanko.repository.ProfileUserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileUserServiceImpl implements ProfileUserService{

    private final ProfileUserRepository repository;
    private final ProfileUserMapper mapper;

  

    @Override
    public List<ProfileUserResponse> listAll() {
        return repository.findAll().stream().map(ProfileUserMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public ProfileUserResponse getById(Integer idProfileUser) {
        return ProfileUserMapper.toResponse(repository.findById(idProfileUser).orElse(null));
    }

    @Override
    public ProfileUserResponse create(ProfileUserRequest p) {
        ProfileUser entity = mapper.toEntity(p);
        ProfileUser saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public ProfileUserResponse update(Integer idProfileUser, ProfileUserRequest p) {
        ProfileUser existing = repository.findById(idProfileUser).orElse(null);
        if(existing == null) {
            return null;
        }
        mapper.copyToEntity(existing, p);
        ProfileUser updated = repository.save(existing);
        return mapper.toResponse(updated);
    }
}
