package gt.com.ad.service;

import java.util.Optional;

import gt.com.ad.data.entity.AdmFormula;


public interface IAdmFormulaService {
    public void saveFormula(AdmFormula formula);

    public Iterable<AdmFormula> getAllFormula();

    public Optional<AdmFormula> getFormulaId(int formulaId);

    public void updateFormulaById(AdmFormula formula);

    public void deleteFormulaById(int formulaId);
}
