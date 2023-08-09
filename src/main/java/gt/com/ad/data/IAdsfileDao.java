package gt.com.ad.data;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface IAdsfileDao extends CrudRepository<Adsfile,Integer> {
    public Iterable<Adsfile> findByIsProcessed(boolean isProcessed);
    public Adsfile readFileById(int id);
}
