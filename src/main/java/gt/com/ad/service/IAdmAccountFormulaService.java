package gt.com.ad.service;

import gt.com.ad.data.entity.AdmAccountFormula;

public interface IAdmAccountFormulaService {
    public Iterable<AdmAccountFormula> getAccountFormulas();
    public Iterable<AdmAccountFormula> getAccountFormulasByAccount(int id);
    public void createAccountFormulas(Iterable<AdmAccountFormula> accountFormulas);
    public void deleteAccountFormulas(int accountFormulasId);
}
