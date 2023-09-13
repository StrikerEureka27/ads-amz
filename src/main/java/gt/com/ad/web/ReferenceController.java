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

import gt.com.ad.data.entity.AdmReference;
import gt.com.ad.service.crud.AdmReferenceService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private/reference")
public class ReferenceController {

    @Autowired
    AdmReferenceService referenceservice;

    @GetMapping("/all")
    public ResponseEntity<Iterable<AdmReference>> getAllReference() {
        return ResponseEntity.status(HttpStatus.OK).body(referenceservice.getAllReference());
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<AdmReference> getFilterById(@PathVariable int referenceId) {
        return ResponseEntity.status(HttpStatus.OK).body(referenceservice.getReferenceId(referenceId).get());
    }

    @PostMapping("/create")
    public ResponseEntity<AdmReference> createAccount(@RequestBody AdmReference reference) {
        referenceservice.saveReference(reference);
        return ResponseEntity.status(HttpStatus.OK).body(reference);
    }

    @PutMapping("/update")
    public ResponseEntity<AdmReference> updateFilterById(@RequestBody AdmReference reference) {
       referenceservice.saveReference(reference);
        return ResponseEntity.status(HttpStatus.OK).body(reference);
    }

    @DeleteMapping("/{referenceId}/delete")
    public ResponseEntity<String> deleteFilterById(@PathVariable int referenceId) {
        referenceservice.deleteReferenceById(referenceId);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }
    
}
