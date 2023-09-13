package gt.com.ad.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmFilterTypeDao;
import gt.com.ad.data.entity.AdmFilterType;
import gt.com.ad.service.IAdmFilterTypeService;

@Service
public class AdmFilterTypeService implements IAdmFilterTypeService {

    @Autowired
    IAdmFilterTypeDao filtertypedao;

    @Override
    public Iterable<AdmFilterType> getAllFilterTypes() {
        return filtertypedao.findAll();
    }
    
}
