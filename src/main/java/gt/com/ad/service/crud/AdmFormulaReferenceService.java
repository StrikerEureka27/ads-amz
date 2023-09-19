package gt.com.ad.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmFormulaReferenceDao;
import gt.com.ad.data.entity.AdmFormulaReference;
import gt.com.ad.service.IAdmFormulaReferenceService;


@Service
public class AdmFormulaReferenceService implements IAdmFormulaReferenceService {

    @Autowired
    IAdmFormulaReferenceDao formulareferencedao;

    @Override
    public Iterable<AdmFormulaReference> getFormulaReferences() {
        return formulareferencedao.findAll();
    }

    @Override
    public Iterable<AdmFormulaReference> getFormulaReferencesByFormula(int formulaId) {
       return formulareferencedao.findAllByFormula(formulaId);
    }

    @Override
    public void createFormulaReferences(Iterable<AdmFormulaReference> formulaReferences) {
        formulareferencedao.saveAll(formulaReferences);
    }

    @Override
    public void deleteFormulaReferences(int formulaReferencesId) {
        formulareferencedao.deleteById(formulaReferencesId);
    }
    
}
