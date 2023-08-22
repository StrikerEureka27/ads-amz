package gt.com.ad.service;

import java.util.Optional;

import gt.com.ad.data.AdmParameter;

public interface IAdmParameterService {
    public void saveParameter(AdmParameter parameter);
    public Iterable<AdmParameter> getAllParameters();
    public Optional<AdmParameter> getParameterId(int id);
    public void updateParameterById(AdmParameter parameter);
}
