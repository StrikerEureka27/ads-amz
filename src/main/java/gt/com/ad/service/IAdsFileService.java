package gt.com.ad.service;

import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.Adsfile;

public interface IAdsFileService {
    public Iterable<Adsfile> getAllAdsFile();
    public String saveAdsFile(MultipartFile request, boolean processed);
    public byte[] downloadAdsFile(int fileName);
    public void saveSimpleAdsFile(Adsfile f);
    public String findFileById(int id);
}
