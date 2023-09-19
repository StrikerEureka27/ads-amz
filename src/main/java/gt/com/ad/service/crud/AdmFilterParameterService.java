package gt.com.ad.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmFilterParameterDao;
import gt.com.ad.data.entity.AdmFilterParameter;
import gt.com.ad.service.IAdmFilterParameterService;


@Service
public class AdmFilterParameterService implements IAdmFilterParameterService {

    @Autowired
    IAdmFilterParameterDao filterparameterdao;

    @Override
    public Iterable<AdmFilterParameter> getFilterParameters() {
        return filterparameterdao.findAll();
    }

    @Override
    public Iterable<AdmFilterParameter> getFilterParametersByFilter(int filterId) {
        return filterparameterdao.findAllByFilter(filterId);
    }

    @Override
    public void createFilterParameters(Iterable<AdmFilterParameter> filterParameters) {
        filterparameterdao.saveAll(filterParameters);
    }

    @Override
    public void deleteFilterParameters(int filterParameterId) {
        filterparameterdao.deleteById(filterParameterId);
    }

}
