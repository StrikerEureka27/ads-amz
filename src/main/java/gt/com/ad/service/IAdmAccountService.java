package gt.com.ad.service;

import java.util.Optional;

import gt.com.ad.data.AdmAccount;

public interface IAdmAccountService {
    public void saveAccount(AdmAccount account);
    public Iterable<AdmAccount> getAllAccounts();
    public Optional<AdmAccount> getAccountById(int id);
    public void updateAccountById(AdmAccount account);
}
