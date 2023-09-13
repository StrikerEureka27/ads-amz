package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmReferenceType;

public interface IAdmReferenceTypeDao extends CrudRepository<AdmReferenceType, Integer> {
    
}
