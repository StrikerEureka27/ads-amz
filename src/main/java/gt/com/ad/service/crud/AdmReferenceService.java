package gt.com.ad.service.crud;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmReferenceDao;
import gt.com.ad.data.entity.AdmReference;
import gt.com.ad.service.IAdmReferenceService;


@Service
public class AdmReferenceService implements IAdmReferenceService{

    @Autowired
    IAdmReferenceDao referencedao;

    @Override
    public void saveReference(AdmReference reference) {
        referencedao.save(reference);
    }

    @Override
    public Iterable<AdmReference> getAllReference() {
        return referencedao.findAll();
    }

    @Override
    public Optional<AdmReference> getReferenceId(int referenceId) {
        return referencedao.findById(referenceId);
    }

    @Override
    public void updateReferenceById(AdmReference reference) {
        referencedao.save(reference);
    }

    @Override
    public void deleteReferenceById(int referenceId) {
        referencedao.deleteById(referenceId);
    }
    
}
