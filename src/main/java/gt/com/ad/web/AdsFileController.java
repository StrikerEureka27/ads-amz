package gt.com.ad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.Adsfile;
import gt.com.ad.service.AdsFileService;

@RestController
@CrossOrigin("*")
@RequestMapping("/adsamz")
public class AdsFileController {

    @Autowired
    AdsFileService adsamzservice;

    @GetMapping("/all")
    public Iterable<Adsfile> getAllAdsFile(){
        return adsamzservice.getAllAdsFile();
    }

    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        String response = adsamzservice.saveAdsFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
