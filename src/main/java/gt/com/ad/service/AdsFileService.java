package gt.com.ad.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.poi.ss.formula.functions.Now;
import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.Adsfile;
import gt.com.ad.data.IAdsfileDao;

@Service
public class AdsFileService implements IAdsFileService {

    @Autowired
    IAdsfileDao adsfile;

    @Override
    public Iterable<Adsfile> getAllAdsFile() {
        return adsfile.findAll();
    }

    @Override
    public String saveAdsFile(MultipartFile request, boolean processed) {
        String fileName = StringUtils.cleanPath(request.getOriginalFilename());
        try {
            Adsfile f = new Adsfile();
            f.setName(fileName);
            f.setFile(request.getBytes());
            f.setProcessed(processed);
            adsfile.save(f);
            return String.format("File %s saved successfully.", fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return String.format("Error saving the file %s.", fileName);
        }

    }

    @Override
    public byte[] downloadAdsFile(int id) {
        Adsfile f = adsfile.readFileById(id);
        return f.getFile();
    }

    @Override
    public void saveSimpleAdsFile(Adsfile f) {
        adsfile.save(f);
    }

    @Override
    public String findFileById(int id) {
        Optional<Adsfile> f = adsfile.findById(id);
        if(f.isPresent()){
            f.get().setStep(1);
            adsfile.save(f.get());
            return String.format("File %s updated successfully.", f.get().getName());
        }else{
            return String.format("Error updating file %s .", f.get().getName());
        }
    }


}
