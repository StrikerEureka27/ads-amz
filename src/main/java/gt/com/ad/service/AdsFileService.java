package gt.com.ad.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.Adsfile;
import gt.com.ad.data.IAdsfileDao;

public class AdsFileService implements IAdsFileService {

    @Autowired
    IAdsfileDao adsfile;

    @Override
    public Iterable<Adsfile> getAllAdsFile() {
        return adsfile.findAll();
    }

    @Override
    public String saveAdsFile(MultipartFile request) {
        String fileName = StringUtils.cleanPath(request.getOriginalFilename());
        try {
            Adsfile f = new Adsfile();
            f.setName(fileName);
            f.setFile(request.getBytes());
            adsfile.save(f);
            return String.format("File %s saved successfully.", fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return String.format("Error saving the file %s.", fileName);
        }

    }

}
