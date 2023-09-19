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

import gt.com.ad.data.entity.AdmFilter;
import gt.com.ad.data.entity.AdmFilterParameter;
import gt.com.ad.data.entity.AdmFilterType;
import gt.com.ad.service.crud.AdmFilterParameterService;
import gt.com.ad.service.crud.AdmFilterService;
import gt.com.ad.service.crud.AdmFilterTypeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amz-api-private/filter")
public class FilterController {

    @Autowired
    AdmFilterService filterservice;

    @Autowired
    AdmFilterParameterService filterparameterservice;

    @Autowired
    AdmFilterTypeService filtertypeservice;

    @GetMapping("/all")
    public ResponseEntity<Iterable<AdmFilter>> getAllFilters() {
        return ResponseEntity.status(HttpStatus.OK).body(filterservice.getAllFilters());
    }

    @GetMapping("/{filterId}")
    public ResponseEntity<AdmFilter> getFilterById(@PathVariable int filterId) {
        return ResponseEntity.status(HttpStatus.OK).body(filterservice.getFilterId(filterId).get());
    }

    @PostMapping("/create")
    public ResponseEntity<AdmFilter> createAccount(@RequestBody AdmFilter filter) {
        filterservice.saveFilter(filter);
        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }

    @PutMapping("/update")
    public ResponseEntity<AdmFilter> updateFilterById(@RequestBody AdmFilter filter) {
        filterservice.updateFilterById(filter);
        return ResponseEntity.status(HttpStatus.OK).body(filter);
    }

    @DeleteMapping("/{filterId}/delete")
    public ResponseEntity<String> deleteFilterById(@PathVariable int filterId) {
        filterservice.deleteFilterById(filterId);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }

    @GetMapping("/types")
     public ResponseEntity<Iterable<AdmFilterType>> getAllFilterTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(filtertypeservice.getAllFilterTypes());
    }

    // parameters relationship

    @GetMapping("/parameters")
    public ResponseEntity<Iterable<AdmFilterParameter>> getFilterParameters() {
        return ResponseEntity.status(HttpStatus.OK).body(filterparameterservice.getFilterParameters());
    }

    @GetMapping("{filterId}/parameter")
    public ResponseEntity<Iterable<AdmFilterParameter>> getFilterParamtersByFilterId(@PathVariable int filterId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(filterparameterservice.getFilterParametersByFilter(filterId));
    }

    @PostMapping("/parameter/create")
    public ResponseEntity<Iterable<AdmFilterParameter>> createFilterParameter(@RequestBody Iterable<AdmFilterParameter> filterParameter) {
        filterparameterservice.createFilterParameters(filterParameter);
        return ResponseEntity.status(HttpStatus.OK).body(filterParameter);
    }

    @PutMapping("/parameter/update")
    public ResponseEntity<Iterable<AdmFilterParameter>> updateFilterParamter(@RequestBody Iterable<AdmFilterParameter> filterParameters) {
        filterparameterservice.createFilterParameters(filterParameters);
        return ResponseEntity.status(HttpStatus.OK).body(filterParameters);
    }

    @DeleteMapping("parameter/{filterParameterId}/delete")
    public ResponseEntity<String> updateFilterParamter(@PathVariable int filterParameterId) {
        filterparameterservice.deleteFilterParameters(filterParameterId);
        return ResponseEntity.status(HttpStatus.OK).body("done");
    }

}
