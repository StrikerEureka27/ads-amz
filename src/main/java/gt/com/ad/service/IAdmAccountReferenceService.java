package gt.com.ad.service;

import gt.com.ad.data.entity.AdmAccountReference;

public interface IAdmAccountReferenceService {
    public Iterable<AdmAccountReference> getAccountReferences();
    public Iterable<AdmAccountReference> getAccountReferenceByAccount(int id);
    public void createAccountReferences(Iterable<AdmAccountReference> accountReferences);
}
