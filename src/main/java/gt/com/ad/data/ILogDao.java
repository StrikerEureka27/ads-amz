package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.Log;

public interface ILogDao extends CrudRepository<Log, Integer> {
    
}
