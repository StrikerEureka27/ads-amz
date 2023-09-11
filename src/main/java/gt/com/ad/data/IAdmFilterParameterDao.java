package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmFilterParameter;

public interface IAdmFilterParameterDao extends CrudRepository<AdmFilterParameter, Integer>{
    public Iterable<AdmFilterParameter> findAllByFilter(int filterId);
}
