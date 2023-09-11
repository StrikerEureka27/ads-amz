package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmAccountReference;

public interface IAdmAccountReferenceDao extends CrudRepository<AdmAccountReference, Integer> {
    public Iterable<AdmAccountReference> findAllByAccount(int accountId);
}
