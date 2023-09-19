package gt.com.ad.service;

import gt.com.ad.data.entity.AdmFormulaReference;

public interface IAdmFormulaReferenceService {
    public Iterable<AdmFormulaReference> getFormulaReferences();
    public Iterable<AdmFormulaReference> getFormulaReferencesByFormula(int formulaId);
    public void createFormulaReferences(Iterable<AdmFormulaReference> formulaReferences);
    public void deleteFormulaReferences(int formulaReferencesId);
};
