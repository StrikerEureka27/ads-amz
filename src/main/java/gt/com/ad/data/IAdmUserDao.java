package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;


public interface IAdmUserDao extends CrudRepository<AdmUser, Integer> {
    public AdmUser findByUsername(String username);
}
