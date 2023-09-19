package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.AdmFormulaReference;

public interface IAdmFormulaReferenceDao extends CrudRepository<AdmFormulaReference, Integer> {
        public Iterable<AdmFormulaReference> findAllByFormula(int formulaId);

}
