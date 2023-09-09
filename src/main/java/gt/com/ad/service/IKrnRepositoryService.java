package gt.com.ad.service;

import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.entity.KrnRepository;

public interface IKrnRepositoryService {
    public Iterable<KrnRepository> getAllAdsFile();
    public String saveAdsFile(MultipartFile request, boolean processed, int accountId);
    public byte[] downloadAdsFile(int fileName);
    public void saveSimpleAdsFile(KrnRepository f);
    public String findFileById(int id);
    public String deleteFileById(int id);
    public boolean isFileProcessed(int id);
}
