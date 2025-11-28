package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.TypeOfNotificationResponse;
import chinanko.chinanko.mapper.TypeOfNotificationMapper;
import chinanko.chinanko.model.TypeOfNotification;
import chinanko.chinanko.repository.TypeOfNotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeOfNotificationServiceImpl implements TypeOfNotificationService {

    private final TypeOfNotificationRepository repository;

    @Override
    @Transactional(readOnly = true)
    public TypeOfNotificationResponse getById(Integer id) {
        TypeOfNotification entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type of Notification not found with id: " + id));
        return TypeOfNotificationMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfNotificationResponse> getAll() {
        return repository.findAll().stream()
                .map(TypeOfNotificationMapper::toResponse)
                .collect(Collectors.toList());
    }
}