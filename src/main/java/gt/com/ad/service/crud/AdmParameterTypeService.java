package gt.com.ad.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmParameterTypeDao;
import gt.com.ad.data.entity.AdmParameterType;
import gt.com.ad.service.IAdmParameterTypeService;

@Service
public class AdmParameterTypeService implements IAdmParameterTypeService {

    @Autowired
    IAdmParameterTypeDao parametertypeservice;

    @Override
    public Iterable<AdmParameterType> getAllParamterTypes() {
        return parametertypeservice.findAll();
    }
    
}
