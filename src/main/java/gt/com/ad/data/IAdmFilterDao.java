package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmFilter;

public interface IAdmFilterDao extends CrudRepository<AdmFilter, Integer> {
    
}
