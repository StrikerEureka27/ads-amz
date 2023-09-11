package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmAccount;

public interface IAdmAccountDao extends CrudRepository<AdmAccount, Integer> {
    
}
