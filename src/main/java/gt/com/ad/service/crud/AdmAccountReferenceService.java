package gt.com.ad.service.crud;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gt.com.ad.data.IAdmAccountReferenceDao;
import gt.com.ad.data.entity.AdmAccountReference;
import gt.com.ad.service.IAdmAccountReferenceService;

@Service
public class AdmAccountReferenceService implements IAdmAccountReferenceService {

    @Autowired
    IAdmAccountReferenceDao accountreferencedao;

    @Override
    public Iterable<AdmAccountReference> getAccountReferences() {
        return accountreferencedao.findAll();
    }

    @Override
    public Iterable<AdmAccountReference> getAccountReferenceByAccount(int accountId) {
        return accountreferencedao.findAllByAccount(accountId);
    }

    @Override
    public void createAccountReferences(Iterable<AdmAccountReference> accountReferences) {
        accountreferencedao.saveAll(accountReferences);
    }

    @Override
    public void deleteAccountReferences(int accountReferencesId) {
        accountreferencedao.deleteById(accountReferencesId);
    }


    
}
