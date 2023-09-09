package gt.com.ad.service;

import java.util.Optional;

import gt.com.ad.data.entity.AdmFilter;

public interface IAdmFilterService {
    public void saveFilter(AdmFilter filter);
    public Iterable<AdmFilter> getAllFilters();
    public Optional<AdmFilter> getFilterId(int id);
    public void updateFilterById(AdmFilter filter);
}
