package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.ReportProfileUserRequest;
import chinanko.chinanko.dto.ReportProfileUserResponse;
import chinanko.chinanko.mapper.ReportProfileUserMapper;
import chinanko.chinanko.model.ReportProfileUser;
import chinanko.chinanko.repository.ReportProfileUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportProfileUserServiceImpl implements ReportProfileUserService {

    private final ReportProfileUserRepository repository;

    @Override
    @Transactional
    public ReportProfileUserResponse create(ReportProfileUserRequest request) {
        // 1. Convertir el DTO (Request) a la Entidad de base de datos
        ReportProfileUser entity = ReportProfileUserMapper.toEntity(request);
        
        // 2. Guardar la entidad en la base de datos
        ReportProfileUser savedEntity = repository.save(entity);
        
        // 3. Convertir la entidad guardada de vuelta a DTO (Response)
        return ReportProfileUserMapper.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ReportProfileUserResponse getById(Integer id) {
        // Buscar por ID, si no existe lanzamos excepción
        ReportProfileUser entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + id));
        
        return ReportProfileUserMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportProfileUserResponse> getAll() {
        // Obtenemos todo, convertimos a stream, mapeamos cada entidad a DTO y recolectamos en lista
        return repository.findAll().stream()
                .map(ReportProfileUserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReportProfileUserResponse update(Integer id, ReportProfileUserRequest request) {
        // 1. Buscar si existe el reporte
        ReportProfileUser existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Report not found with id: " + id));

        // 2. Actualizar los campos de la entidad existente con los datos nuevos del request
        ReportProfileUserMapper.copyToEntity(existingEntity, request);

        // 3. Guardar los cambios
        ReportProfileUser updatedEntity = repository.save(existingEntity);

        return ReportProfileUserMapper.toResponse(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // Verificamos existencia antes de borrar
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Report not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // --- Métodos específicos de búsqueda ---

    @Override
    @Transactional(readOnly = true)
    public List<ReportProfileUserResponse> getByUserId(Integer userId) {
        return repository.findByProfileUser_IdProfileUser(userId).stream()
                .map(ReportProfileUserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportProfileUserResponse> getByTypeId(Integer typeId) {
        return repository.findByTypeOfReport_IdTypeReport(typeId).stream()
                .map(ReportProfileUserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportProfileUserResponse> searchByDescription(String text) {
        return repository.findByDescriptionContainingIgnoreCase(text).stream()
                .map(ReportProfileUserMapper::toResponse)
                .collect(Collectors.toList());
    }
}