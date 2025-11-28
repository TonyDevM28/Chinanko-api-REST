package chinanko.chinanko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import chinanko.chinanko.dto.RoleRequest;
import chinanko.chinanko.dto.RoleResponse;
import chinanko.chinanko.mapper.RoleMapper;
import chinanko.chinanko.model.Role;
import chinanko.chinanko.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role created = repository.save(RoleMapper.toEntity(request));
        return RoleMapper.toResponse(created);
    }

    @Override
    public List<RoleResponse> getAll() {
        return repository.findAll().stream()
        .map(RoleMapper::toResponse)
        .toList();
    }
    
    @Override
    public RoleResponse update(Integer idRole, RoleRequest req) {
        Role existing = repository.findById(idRole)
            .orElseThrow(() -> new EntityNotFoundException("rol no encontrado: " + idRole));
        RoleMapper.copyToEntity(req, existing);
        Role saved = repository.save(existing);
        return RoleMapper.toResponse(saved);
        
    }

    @Override
    public RoleResponse findById(Integer idRole) {
        Role r = repository.findById(idRole).orElse(null);
        return RoleMapper.toResponse(r);
    }
}
