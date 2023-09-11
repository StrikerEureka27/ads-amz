package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmParameter;

public interface IAdmParameterDao extends CrudRepository<AdmParameter, Integer> {
    
}
