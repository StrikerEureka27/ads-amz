package gt.com.ad.data;

import org.springframework.data.repository.CrudRepository;

import gt.com.ad.data.entity.KrnRepository;

public interface IKrnRepositoryDao extends CrudRepository<KrnRepository, Integer> {
    public KrnRepository readFileById(int id);
}
