package gt.com.ad.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.AdmParameter;
import gt.com.ad.data.IAdmParameterDao;


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
