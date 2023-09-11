package gt.com.ad.service;

import gt.com.ad.data.entity.AdmAccountFilter;

public interface IAdmAccountFilterService {
    public Iterable<AdmAccountFilter> getAccountFilters();
    public Iterable<AdmAccountFilter> getAccountFiltersByAccount(int accountId);
    public void createAccountFilters(Iterable<AdmAccountFilter> accountFilters);
}
