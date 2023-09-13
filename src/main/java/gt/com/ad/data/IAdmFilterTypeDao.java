package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmFilterType;

public interface IAdmFilterTypeDao extends CrudRepository<AdmFilterType, Integer> {
    
}
