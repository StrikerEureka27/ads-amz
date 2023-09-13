package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmFormula;

public interface IAdmFormulaDao extends CrudRepository<AdmFormula, Integer> {
    
}
