package gt.com.ad.service;

import java.util.Optional;

import gt.com.ad.data.entity.AdmReference;

public interface IAdmReferenceService {
    public void saveReference(AdmReference reference);
    public Iterable<AdmReference> getAllReference();
    public Optional<AdmReference> getReferenceId(int referenceId);
    public void updateReferenceById(AdmReference reference);
    public void deleteReferenceById(int referenceId);
}
