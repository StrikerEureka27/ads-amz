package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmReference;

public interface IAdmReferenceDao extends CrudRepository<AdmReference, Integer> {
    
}
