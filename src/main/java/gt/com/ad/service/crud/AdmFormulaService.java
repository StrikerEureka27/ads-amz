package gt.com.ad.service.crud;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmFormulaDao;
import gt.com.ad.data.entity.AdmFormula;
import gt.com.ad.service.IAdmFormulaService;


@Service
public class AdmFormulaService implements IAdmFormulaService {

    @Autowired
    IAdmFormulaDao formuladao;

    @Override
    public void saveFormula(AdmFormula formula) {
        formuladao.save(formula);
    }

    @Override
    public Iterable<AdmFormula> getAllFormula() {
        return formuladao.findAll();
    }

    @Override
    public Optional<AdmFormula> getFormulaId(int formulaId) {
        return formuladao.findById(formulaId);
    }

    @Override
    public void updateFormulaById(AdmFormula formula) {
        formuladao.save(formula);
    }

    @Override
    public void deleteFormulaById(int formulaId) {
        formuladao.deleteById(formulaId);
    }
    
}
