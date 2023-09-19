package gt.com.ad.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gt.com.ad.data.IAdmAccountFormulaDao;
import gt.com.ad.data.entity.AdmAccountFormula;
import gt.com.ad.service.IAdmAccountFormulaService;


@Service
public class AdmAccountFormulaService implements IAdmAccountFormulaService {

    @Autowired
    IAdmAccountFormulaDao accountformuladao;

    @Override
    public Iterable<AdmAccountFormula> getAccountFormulas() {
        return accountformuladao.findAll();
    }

    @Override
    public Iterable<AdmAccountFormula> getAccountFormulasByAccount(int accountId) {
        return accountformuladao.findAllByAccount(accountId);
    }

    @Override
    public void createAccountFormulas(Iterable<AdmAccountFormula> accountFormulas) {
        accountformuladao.saveAll(accountFormulas);
    }

    @Override
    public void deleteAccountFormulas(int accountFormulasId) {
        accountformuladao.deleteById(accountFormulasId);
    }
    
}
