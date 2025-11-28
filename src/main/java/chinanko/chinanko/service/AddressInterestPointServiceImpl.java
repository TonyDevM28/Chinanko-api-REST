package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.AddressInterestPointRequest;
import chinanko.chinanko.dto.AddressInterestPointResponse;
import chinanko.chinanko.mapper.AddressInterestPointMapper;
import chinanko.chinanko.model.AddressInterestPoint;
import chinanko.chinanko.repository.AddressInterestPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressInterestPointServiceImpl implements AddressInterestPointService {

    private final AddressInterestPointRepository repository;

    @Override
    @Transactional
    public AddressInterestPointResponse create(AddressInterestPointRequest request) {
        AddressInterestPoint entity = AddressInterestPointMapper.toEntity(request);
        AddressInterestPoint saved = repository.save(entity);
        return AddressInterestPointMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AddressInterestPointResponse update(Integer id, AddressInterestPointRequest request) {
        AddressInterestPoint existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
        
        AddressInterestPointMapper.copyToEntity(existing, request);
        AddressInterestPoint saved = repository.save(existing);
        return AddressInterestPointMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AddressInterestPointResponse getById(Integer id) {
        AddressInterestPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + id));
        return AddressInterestPointMapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Address not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressInterestPointResponse> getByPostalCode(String postalCode, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AddressInterestPoint> result = repository.findByPostalCode(postalCode, pageable);
        
        return result.stream()
                .map(AddressInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressInterestPointResponse> getByTownId(Integer townId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<AddressInterestPoint> result = repository.findByInterestPoint_Town_IdTown(townId, pageable);
        
        return result.stream()
                .map(AddressInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressInterestPointResponse> searchByStreetName(String streetName) {
        return repository.findByStreetContainingIgnoreCase(streetName).stream()
                .map(AddressInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}