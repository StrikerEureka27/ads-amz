package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;
import gt.com.ad.data.entity.AdmAccountFilter;

public interface IAdmAccountFilterDao extends CrudRepository<AdmAccountFilter, Integer> {
    public Iterable<AdmAccountFilter> findAllByAccount(int accountId);
}
