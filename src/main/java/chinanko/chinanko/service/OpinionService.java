package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.OpinionRequest;
import chinanko.chinanko.dto.OpinionResponse;

public interface OpinionService {
    
    OpinionResponse create(OpinionRequest request);
    
    OpinionResponse update(Integer id, OpinionRequest request);
    
    void delete(Integer id);
    
    List<OpinionResponse> getByInterestPoint(Integer interestPointId, int page, int pageSize);
    
    List<OpinionResponse> getByUser(Integer userId, int page, int pageSize);
}