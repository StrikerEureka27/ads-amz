package gt.com.ad.service.crud;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmFilterDao;
import gt.com.ad.data.entity.AdmFilter;
import gt.com.ad.service.IAdmFilterService;

@Service
public class AdmFilterService implements IAdmFilterService {

    @Autowired
    IAdmFilterDao filterdao;

    @Override
    public void saveFilter(AdmFilter filter) {
        filterdao.save(filter);
    }

    @Override
    public Iterable<AdmFilter> getAllFilters() {
        return filterdao.findAll();
    }

    @Override
    public Optional<AdmFilter> getFilterId(int id) {
        return filterdao.findById(id);
    }

    @Override
    public void updateFilterById(AdmFilter filter) {
        filterdao.save(filter);
    }

    @Override
    public void deleteFilterById(int filterId) {
        filterdao.deleteById(filterId);
    }
    
}
