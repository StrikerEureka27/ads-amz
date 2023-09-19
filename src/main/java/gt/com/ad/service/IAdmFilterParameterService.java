package gt.com.ad.service;

import gt.com.ad.data.entity.AdmFilterParameter;

public interface IAdmFilterParameterService {
    public Iterable<AdmFilterParameter> getFilterParameters();
    public Iterable<AdmFilterParameter> getFilterParametersByFilter(int filterId);
    public void createFilterParameters(Iterable<AdmFilterParameter> filterParameters);
    public void deleteFilterParameters(int filterParameterId);
}
