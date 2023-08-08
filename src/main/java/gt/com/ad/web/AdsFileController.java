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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.AdsLog;
import gt.com.ad.data.Adsfile;
import gt.com.ad.service.AdsFileService;
import gt.com.ad.service.AdsLogService;

@RestController
@CrossOrigin("*")
@RequestMapping("/adsamz")
public class AdsFileController {

    @Autowired
    AdsFileService adsamzservice;

    @Autowired
    AdsLogService adsamzservicelog;

    @GetMapping("/all")
    public Iterable<Adsfile> getAllAdsFile(){
        return adsamzservice.getAllAdsFile();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("processed") boolean processed){
        String response = adsamzservice.saveAdsFile(file, processed);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/step/{id}")
    public ResponseEntity<String> updateStep(@PathVariable int id){
        String response = adsamzservice.findFileById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(adsamzservice.deleteFileById(id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAssignment(@PathVariable int id) {

        byte[] response = adsamzservice.downloadAdsFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/xlsx"))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + id + "\""
                )
                .body(response);
    }

    @GetMapping("/log/all")
    public Iterable<AdsLog> getAllAdsLog(){
        return adsamzservicelog.getAllLogs();
    }

}
    

