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

import gt.com.ad.data.entity.AdmParameter;
import gt.com.ad.data.entity.AdmParameterType;
import gt.com.ad.service.crud.AdmParameterService;
import gt.com.ad.service.crud.AdmParameterTypeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private/parameter")
public class ParameterController {

    @Autowired
    AdmParameterService paramaterservice;

    @Autowired
    AdmParameterTypeService parametertypeservice;

    @GetMapping("/all")
    public ResponseEntity<Iterable<AdmParameter>> getAllParams() {
       return ResponseEntity.status(HttpStatus.OK).body(paramaterservice.getAllParameters());
    }

    @GetMapping("/types")
    public ResponseEntity<Iterable<AdmParameterType>> getAllParameterTypes() {
       return ResponseEntity.status(HttpStatus.OK).body(parametertypeservice.getAllParamterTypes());
    }

    @GetMapping("/{filterId}")
    public ResponseEntity<AdmParameter> getParamById(@PathVariable int filterId) {
        return ResponseEntity.status(HttpStatus.OK).body(paramaterservice.getParameterId(filterId).get());
    }

    @PostMapping("/create")
    public ResponseEntity<AdmParameter> createParameter(@RequestBody AdmParameter parameter) {
        paramaterservice.saveParameter(parameter);
        return ResponseEntity.status(HttpStatus.OK).body(parameter);
    }

    @PutMapping("/update")
    public ResponseEntity<AdmParameter> updateParameterById(@RequestBody AdmParameter parameter) {
        paramaterservice.saveParameter(parameter);
        return ResponseEntity.status(HttpStatus.OK).body(parameter);
    }

    @DeleteMapping("/{parameterId}/delete")
    public ResponseEntity<String> deleteFilterById(@PathVariable int parameterId) {
        paramaterservice.deleteParameterById(parameterId);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }

}
