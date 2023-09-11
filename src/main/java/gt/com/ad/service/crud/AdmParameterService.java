package gt.com.ad.service.crud;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmParameterDao;
import gt.com.ad.data.entity.AdmParameter;
import gt.com.ad.service.IAdmParameterService;


@Service
public class AdmParameterService implements IAdmParameterService {

    @Autowired
    IAdmParameterDao parameterdao;

    @Override
    public void saveParameter(AdmParameter parameter) {
        parameterdao.save(parameter);
    }

    @Override
    public Iterable<AdmParameter> getAllParameters() {
        return parameterdao.findAll();
    }

    @Override
    public Optional<AdmParameter> getParameterId(int id) {
        return parameterdao.findById(id);
    }

    @Override
    public void updateParameterById(AdmParameter parameter) {
        parameterdao.save(parameter);
    }
    
}
