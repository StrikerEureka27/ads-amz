package gt.com.ad.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gt.com.ad.data.IAdmReferenceTypeDao;
import gt.com.ad.data.entity.AdmReferenceType;
import gt.com.ad.service.IAdmReferenceTypeService;

@Service
public class AdmReferenceTypeService implements IAdmReferenceTypeService {

    @Autowired
    IAdmReferenceTypeDao referencetypedao;

    @Override
    public Iterable<AdmReferenceType> getAllReferenceTypes() {
        return referencetypedao.findAll();
    }
    
}
