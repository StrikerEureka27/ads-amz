package gt.com.ad.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import gt.com.ad.data.IKrnRepositoryDao;
import gt.com.ad.data.ILogDao;
import gt.com.ad.data.KrnRepository;
import gt.com.ad.data.Log;

@Service
public class KrnRepositoryService implements IKrnRepositoryService {

    @Autowired
    IKrnRepositoryDao krnrepository;

    @Autowired
    ILogDao adslog;

    @Override
    public Iterable<KrnRepository> getAllAdsFile() {
        return krnrepository.findAll();
    }

    @Override
    public String saveAdsFile(MultipartFile request, boolean processed) {
        String fileName = StringUtils.cleanPath(request.getOriginalFilename());
        try {
            KrnRepository f = new KrnRepository();
            f.setName(fileName);
            f.setFile(request.getBytes());
            f.setProcessed(processed);
            krnrepository.save(f);
            String response = String.format("File %d loaded successfully.", f.getId());
            Log l = new Log();
            l.setMessage(response);
            adslog.save(l);
            return response;
        } catch (IOException ex) {
            ex.printStackTrace();
            String response = String.format("Error saving the file.");
            Log l = new Log();
            l.setMessage(response);
            adslog.save(l);
            return response;
        }

    }

    @Override
    public byte[] downloadAdsFile(int id) {
        KrnRepository f = krnrepository.readFileById(id);
        return f.getFile();
    }

    @Override
    public void saveSimpleAdsFile(KrnRepository f) {
        krnrepository.save(f);
    }

    @Override
    public String findFileById(int id) {
        Optional<KrnRepository> f = krnrepository.findById(id);
        if(f.isPresent()){
            f.get().setStep(1);
            krnrepository.save(f.get());
            return String.format("File %s updated successfully.", f.get().getName());
        }else{
            return String.format("Error updating file %s .", f.get().getName());
        }
    }

    @Override
    public String deleteFileById(int id) {
        KrnRepository f = krnrepository.readFileById(id);
        krnrepository.delete(f);
        Log l = new Log();
        String response = String.format("File %d deleted successfully.", f.getId());
        l.setMessage(response);
        adslog.save(l);
        return response;
    }

    @Override
    public boolean isFileProcessed(int id) {
        Optional<KrnRepository> f = krnrepository.findById(id);
        if(f.isPresent()){
           if(f.get().isProcessed() && f.get().getStep() == 2){
            return f.get().isProcessed();
           }else{
            return false;
           }
        }else{
            return false;
        }
    }


}
