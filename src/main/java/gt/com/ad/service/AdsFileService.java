package gt.com.ad.service;

import java.io.IOException;

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


}
