package gt.com.ad.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmAccountFilterDao;
import gt.com.ad.data.entity.AdmAccountFilter;
import gt.com.ad.service.IAdmAccountFilterService;

@Service
public class AdmAccountFilterService implements IAdmAccountFilterService {

    @Autowired
    IAdmAccountFilterDao accountfilterdao;

    @Override
    public Iterable<AdmAccountFilter> getAccountFilters() {
        return accountfilterdao.findAll();
    }

    @Override
    public Iterable<AdmAccountFilter> getAccountFiltersByAccount(int accountId) {
        return accountfilterdao.findAllByAccount(accountId);
    }

    @Override
    public void createAccountFilters(Iterable<AdmAccountFilter> accountFilters) {
        accountfilterdao.saveAll(accountFilters);
    }

}
