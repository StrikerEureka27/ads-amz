package gt.com.ad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import gt.com.ad.Sender;
import gt.com.ad.data.entity.KrnRepository;
import gt.com.ad.data.entity.Log;
import gt.com.ad.service.crud.KrnRepositoryService;
import gt.com.ad.service.crud.LogService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private/file")
public class FileController {

    @Autowired
    KrnRepositoryService repositoryservice;

    @Autowired
    LogService logservice;

    @Autowired
    Sender sender;


    @GetMapping("/all")
    public ResponseEntity<Iterable<KrnRepository>> getAllAdsFile() {
        Iterable<KrnRepository> files = repositoryservice.getAllAdsFile();
        files.forEach((e) -> e.setFile(null));
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("processed") boolean processed, @RequestParam("account") int accountId) {
        String response = repositoryservice.saveAdsFile(file, processed, accountId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/step/{fileId}/update")
    public ResponseEntity<String> updateStep(@PathVariable int fileId) {
        String response = repositoryservice.findFileById(fileId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{fileId}/processed")
    public ResponseEntity<Boolean> isProcessed(@PathVariable int fileId) {
        boolean response = repositoryservice.isFileProcessed(fileId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{fileId}/delete")
    public ResponseEntity<String> deleteFile(@PathVariable int fileId) {
        return ResponseEntity.status(HttpStatus.OK).body(repositoryservice.deleteFileById(fileId));
    }

    @GetMapping("/{fileId}/download")
    public ResponseEntity<byte[]> downloadAssignment(@PathVariable int fileId) {
        byte[] response = repositoryservice.downloadAdsFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileId + "\"")
                .body(response);
    }

    @GetMapping("/logs")
    public Iterable<Log> getAllAdsLog() {
        return logservice.getAllLogs();
    }
    
}
