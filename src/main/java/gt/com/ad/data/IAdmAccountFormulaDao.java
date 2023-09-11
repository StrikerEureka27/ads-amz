package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmAccountFormula;

public interface IAdmAccountFormulaDao extends CrudRepository<AdmAccountFormula, Integer>{
    public Iterable<AdmAccountFormula> findAllByAccount(int accountId);
}
