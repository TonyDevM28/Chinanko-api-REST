package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.AddressEventRequest;
import chinanko.chinanko.dto.AddressEventResponse;
import chinanko.chinanko.mapper.AddressEventMapper;
import chinanko.chinanko.model.AddressEvent;
import chinanko.chinanko.repository.AddressEventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressEventServiceImpl implements AddressEventService {

    private final AddressEventRepository repository;

    @Override
    @Transactional
    public AddressEventResponse create(AddressEventRequest request) {
        AddressEvent entity = AddressEventMapper.toEntity(request);
        AddressEvent saved = repository.save(entity);
        return AddressEventMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AddressEventResponse update(Integer id, AddressEventRequest request) {
        AddressEvent existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
        
        AddressEventMapper.copyToEntity(existing, request);
        AddressEvent saved = repository.save(existing);
        return AddressEventMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressEventResponse getById(Integer id) {
        AddressEvent entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
        return AddressEventMapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Address not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressEventResponse> getByPostalCode(String postalCode, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AddressEvent> result = repository.findByPostalCode(postalCode, pageable);
        return result.stream().map(AddressEventMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressEventResponse> getByEvent(Integer eventId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AddressEvent> result = repository.findByEvent_IdEvent(eventId, pageable);
        return result.stream().map(AddressEventMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressEventResponse> getByTown(Integer townId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AddressEvent> result = repository.findByEvent_Town_IdTown(townId, pageable);
        return result.stream().map(AddressEventMapper::toResponse).collect(Collectors.toList());
    }
}