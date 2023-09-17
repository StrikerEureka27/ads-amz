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
import gt.com.ad.data.entity.AdmAccount;
import gt.com.ad.data.entity.AdmAccountFilter;
import gt.com.ad.data.entity.AdmAccountFormula;
import gt.com.ad.data.entity.AdmAccountReference;
import gt.com.ad.service.crud.AdmAccountFilterService;
import gt.com.ad.service.crud.AdmAccountFormulaService;
import gt.com.ad.service.crud.AdmAccountReferenceService;
import gt.com.ad.service.crud.AdmAccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private/account")
public class AccountController {

    @Autowired
    AdmAccountService accountservice;

    @Autowired
    AdmAccountFilterService accountfilterservice;

    @Autowired
    AdmAccountReferenceService accountreferenceservice;

    @Autowired
    AdmAccountFormulaService accountformulaservice;

    @GetMapping("/all")
    public ResponseEntity<Iterable<AdmAccount>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountservice.getAllAccounts());
    }

    @PostMapping("/create")
    public ResponseEntity<AdmAccount> createAccount(@RequestBody AdmAccount account) {
        accountservice.saveAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PutMapping("/update")
    public ResponseEntity<AdmAccount> updateAccountById(@RequestBody AdmAccount account) {
        accountservice.updateAccountById(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AdmAccount> getAccountById(@PathVariable int accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountservice.getAccountById(accountId).get());
    }

    // references relationship

    @GetMapping("/reference")
    public ResponseEntity<Iterable<AdmAccountReference>> getAccountReference() {
        return ResponseEntity.status(HttpStatus.OK).body(accountreferenceservice.getAccountReferences());
    }

    @GetMapping("{accountId}/reference")
    public ResponseEntity<Iterable<AdmAccountReference>> getAccountReferenceByAccountId(@PathVariable int accountId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountreferenceservice.getAccountReferenceByAccount(accountId));
    }

    @PostMapping("/reference/create")
    public ResponseEntity<Iterable<AdmAccountReference>> createAccountReferences(@RequestBody Iterable<AdmAccountReference> accountReferences) {
        accountreferenceservice.createAccountReferences(accountReferences);
        return ResponseEntity.status(HttpStatus.OK).body(accountReferences);
    }

    @PutMapping("/reference/update")
    public ResponseEntity<Iterable<AdmAccountReference>> updateAccountReferences(@RequestBody Iterable<AdmAccountReference> accountReferences) {
        accountreferenceservice.createAccountReferences(accountReferences);
        return ResponseEntity.status(HttpStatus.OK).body(accountReferences);
    }

    @DeleteMapping("/reference/{accountReferencesId}/delete")
    public ResponseEntity<String> deleteAccountReferences(@PathVariable int accountReferencesId) {
        accountreferenceservice.deleteAccountReferences(accountReferencesId);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }

    // filter relationship

    @GetMapping("/filter")
    public ResponseEntity<Iterable<AdmAccountFilter>> getAccountFilters() {
        return ResponseEntity.status(HttpStatus.OK).body(accountfilterservice.getAccountFilters());
    }

    @GetMapping("{accountId}/filter")
    public ResponseEntity<Iterable<AdmAccountFilter>> getAccountFilterByAccountId(@PathVariable int accountId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountfilterservice.getAccountFiltersByAccount(accountId));
    }

    @PostMapping("/filter/create")
    public ResponseEntity<Iterable<AdmAccountFilter>> createAccountFilter(@RequestBody Iterable<AdmAccountFilter> accountFilters) {
        accountfilterservice.createAccountFilters(accountFilters);
        return ResponseEntity.status(HttpStatus.OK).body(accountFilters);
    }

    @PutMapping("/filter/update")
    public ResponseEntity<Iterable<AdmAccountFilter>> updateAccountFilter(@RequestBody Iterable<AdmAccountFilter> accountFilters) {
        accountfilterservice.createAccountFilters(accountFilters);
        return ResponseEntity.status(HttpStatus.OK).body(accountFilters);
    }

    // formula relationship

    @GetMapping("/formula")
    public ResponseEntity<Iterable<AdmAccountFormula>> getAccountFormulas() {
        return ResponseEntity.status(HttpStatus.OK).body(accountformulaservice.getAccountFormulas());
    }

    @GetMapping("{accountId}/formula")
    public ResponseEntity<Iterable<AdmAccountFormula>> getAccountFormulaByAccountId(@PathVariable int accountId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountformulaservice.getAccountFormulasByAccount(accountId));
    }

    @PostMapping("/formula/create")
    public ResponseEntity<Iterable<AdmAccountFormula>> createAccountFormula(@RequestBody Iterable<AdmAccountFormula> accountFilters) {
        accountformulaservice.createAccountFormulas(accountFilters);
        return ResponseEntity.status(HttpStatus.OK).body(accountFilters);
    }

    @PutMapping("/formula/update")
    public ResponseEntity<Iterable<AdmAccountFormula>> updateAccountFormula(@RequestBody Iterable<AdmAccountFormula> accountFormulas) {
        accountformulaservice.createAccountFormulas(accountFormulas);
        return ResponseEntity.status(HttpStatus.OK).body(accountFormulas);
    }

    



}
