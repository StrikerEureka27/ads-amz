package gt.com.ad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.AdmAccount;
import gt.com.ad.data.AdmFilter;
import gt.com.ad.data.AdmParameter;
import gt.com.ad.data.KrnRepository;
import gt.com.ad.data.Log;
import gt.com.ad.service.AdmAccountService;
import gt.com.ad.service.AdmFilterService;
import gt.com.ad.service.AdmParameterService;
import gt.com.ad.service.KrnRepositoryService;
import gt.com.ad.service.LogService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private")
public class Controller {

    @Autowired
    KrnRepositoryService adsamzservice;

    @Autowired
    AdmAccountService accountservice;

    @Autowired
    AdmFilterService filterservice;

    @Autowired
    AdmParameterService paramservice;

    @Autowired
    LogService adsamzservicelog;

    @GetMapping("/accounts")
    public ResponseEntity<Iterable<AdmAccount>> getAllAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body(accountservice.getAllAccounts());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AdmAccount> getAccountById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(accountservice.getAccountById(id).get());
    }

    @PostMapping("/account/create")
    public ResponseEntity<AdmAccount> createAccount(@RequestBody AdmAccount account) {
        accountservice.saveAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PutMapping("/account/{id}/update")
    public ResponseEntity<AdmAccount> updateAccountById(@RequestBody AdmAccount account) {
        accountservice.updateAccountById(account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @GetMapping("/filters")
    public ResponseEntity<Iterable<AdmFilter>> getAllFilters() {
        return ResponseEntity.status(HttpStatus.OK).body(filterservice.getAllFilters());
    }

    @GetMapping("/filter/{id}")
    public ResponseEntity<AdmFilter> getFilterById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(filterservice.getFilterId(id).get());
    }

    @PostMapping("/filter/create")
    public ResponseEntity<AdmFilter> createAccount(@RequestBody AdmFilter filter) {
        filterservice.saveFilter(filter);
        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }

    @PutMapping("/filter/{id}/update")
    public ResponseEntity<AdmFilter> updateFilterById(@RequestBody AdmFilter filter) {
        filterservice.updateFilterById(filter);
        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }

    @GetMapping("/parameters")
    public ResponseEntity<Iterable<AdmFilter>> getAllParams() {
        return ResponseEntity.status(HttpStatus.OK).body(filterservice.getAllFilters());
    }

    @GetMapping("/parameter/{id}")
    public ResponseEntity<AdmParameter> getParamById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(paramservice.getParameterId(id).get());
    }

    @PostMapping("/parameter/create")
    public ResponseEntity<AdmParameter> createParameter(@RequestBody AdmParameter parameter) {
        paramservice.saveParameter(parameter);
        return ResponseEntity.status(HttpStatus.OK).body(parameter);
    }

    @PutMapping("/parameters/{id}/update")
    public ResponseEntity<AdmParameter> updateParameterById(@RequestBody AdmParameter parameter) {
        paramservice.saveParameter(parameter);
        return ResponseEntity.status(HttpStatus.OK).body(parameter);
    }

    @GetMapping("/files")
    public ResponseEntity<Iterable<KrnRepository>> getAllAdsFile() {
        Iterable<KrnRepository> files = adsamzservice.getAllAdsFile();
        files.forEach((e) -> e.setFile(null));
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("processed") boolean processed) {
        String response = adsamzservice.saveAdsFile(file, processed);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("file/step/{id}/update")
    public ResponseEntity<String> updateStep(@PathVariable int id) {
        String response = adsamzservice.findFileById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("file/{id}/processed")
    public ResponseEntity<Boolean> isProcessed(@PathVariable int id) {
        boolean response = adsamzservice.isFileProcessed(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/file/{id}/delete")
    public ResponseEntity<String> deleteFile(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(adsamzservice.deleteFileById(id));
    }

    @GetMapping("/file/{id}/download")
    public ResponseEntity<byte[]> downloadAssignment(@PathVariable int id) {
        byte[] response = adsamzservice.downloadAdsFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + id + "\"")
                .body(response);
    }

    @GetMapping("/logs")
    public Iterable<Log> getAllAdsLog() {
        return adsamzservicelog.getAllLogs();
    }

}
