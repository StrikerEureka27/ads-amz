package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

public interface IKrnRepositoryDao extends CrudRepository<KrnRepository, Integer> {
    public KrnRepository readFileById(int id);
}
