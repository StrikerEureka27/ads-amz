package gt.com.ad.web;


import org.json.JSONObject;
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

import gt.com.ad.Sender;
import gt.com.ad.data.entity.AdmAccount;
import gt.com.ad.data.entity.AdmFilter;
import gt.com.ad.data.entity.AdmParameter;
import gt.com.ad.data.entity.KrnRepository;
import gt.com.ad.data.entity.Log;
import gt.com.ad.service.crud.AdmAccountService;
import gt.com.ad.service.crud.AdmFilterService;
import gt.com.ad.service.crud.AdmParameterService;
import gt.com.ad.service.crud.KrnRepositoryService;
import gt.com.ad.service.crud.LogService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private")
public class Controller {

    @Autowired
    KrnRepositoryService adsamzservice;


    @Autowired
    LogService adsamzservicelog;

    @Autowired
    Sender sender;


    @GetMapping("/files")
    public ResponseEntity<Iterable<KrnRepository>> getAllAdsFile() {
        Iterable<KrnRepository> files = adsamzservice.getAllAdsFile();
        files.forEach((e) -> e.setFile(null));
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("processed") boolean processed, @RequestParam("account") int accountId) {
        String response = adsamzservice.saveAdsFile(file, processed, accountId);
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

    @GetMapping("/rpc")
    public String testrpc(){
        JSONObject json = new JSONObject();
        json.put("message", "Hello from rpc");
        String response = sender.convertSendAndReceive(json.toString());
        return response;
    }

}
