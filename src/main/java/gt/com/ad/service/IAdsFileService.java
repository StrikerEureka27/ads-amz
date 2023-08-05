package gt.com.ad.service;

import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.Adsfile;

public interface IAdsFileService {
    public Iterable<Adsfile> getAllAdsFile();
    public String saveAdsFile(MultipartFile request);
}
