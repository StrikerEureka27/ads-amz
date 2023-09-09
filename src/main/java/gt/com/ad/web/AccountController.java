package gt.com.ad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.ad.data.entity.AdmAccount;
import gt.com.ad.service.AdmAccountService;

@RestController
@RequestMapping("/amz-api-private/account")
public class AccountController {

    @Autowired
    AdmAccountService accountservice;

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

    @GetMapping("find/{id}")
    public ResponseEntity<AdmAccount> getAccountById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountservice.getAccountById(id).get());
    }

}
