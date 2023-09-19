package gt.com.ad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.ad.data.entity.AdmAccountReference;
import gt.com.ad.data.entity.AdmFilterParameter;
import gt.com.ad.data.entity.AdmFormula;
import gt.com.ad.data.entity.AdmFormulaReference;
import gt.com.ad.service.crud.AdmFormulaReferenceService;
import gt.com.ad.service.crud.AdmFormulaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private/formula")
public class FormulaController {

    @Autowired
    AdmFormulaService formulaservice;

    @Autowired
    AdmFormulaReferenceService formulareferenceservice;

    @GetMapping("/all")
    public ResponseEntity<Iterable<AdmFormula>> getAllFormula() {
        return ResponseEntity.status(HttpStatus.OK).body(formulaservice.getAllFormula());
    }

    @GetMapping("/{formulaId}")
    public ResponseEntity<AdmFormula> getFilterById(@PathVariable int formulaId) {
        return ResponseEntity.status(HttpStatus.OK).body(formulaservice.getFormulaId(formulaId).get());
    }

    @PostMapping("/create")
    public ResponseEntity<AdmFormula> createAccount(@RequestBody AdmFormula formula) {
        formulaservice.saveFormula(formula);
        return ResponseEntity.status(HttpStatus.OK).body(formula);
    }

    @PutMapping("/update")
    public ResponseEntity<AdmFormula> updateFilterById(@RequestBody AdmFormula formula) {
        formulaservice.saveFormula(formula);
        return ResponseEntity.status(HttpStatus.OK).body(formula);
    }

    @DeleteMapping("/{formulaId}/delete")
    public ResponseEntity<String> deleteFilterById(@PathVariable int formulaId) {
        formulaservice.deleteFormulaById(formulaId);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }

    // references relationship

    @GetMapping("/reference")
    public ResponseEntity<Iterable<AdmFormulaReference>> getAccountReference() {
        return ResponseEntity.status(HttpStatus.OK).body(formulareferenceservice.getFormulaReferences());
    }

    @GetMapping("{formulaId}/reference")
    public ResponseEntity<Iterable<AdmFormulaReference>> getAccountReferenceByAccountId(@PathVariable int formulaId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(formulareferenceservice.getFormulaReferencesByFormula(formulaId));
    }

    @PostMapping("/reference/create")
    public ResponseEntity<Iterable<AdmFormulaReference>> createAccountReferences(
            @RequestBody Iterable<AdmFormulaReference> formulaReference) {
        formulareferenceservice.createFormulaReferences(formulaReference);
        return ResponseEntity.status(HttpStatus.OK).body(formulaReference);
    }

    @PutMapping("/reference/update")
    public ResponseEntity<Iterable<AdmFormulaReference>> updateAccountReferences(
            @RequestBody Iterable<AdmFormulaReference> formulaReferences) {
        formulareferenceservice.createFormulaReferences(formulaReferences);
        return ResponseEntity.status(HttpStatus.OK).body(formulaReferences);
    }

    @DeleteMapping("/reference/{formulaReferenceId}/delete")
    public ResponseEntity<String> deleteAccountReferences(@PathVariable int formulaReferenceId) {
        formulareferenceservice.deleteFormulaReferences(formulaReferenceId);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }

}
