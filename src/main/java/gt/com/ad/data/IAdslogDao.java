package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdsLog;

public interface IAdslogDao extends CrudRepository<AdsLog, Integer> {
    
}
