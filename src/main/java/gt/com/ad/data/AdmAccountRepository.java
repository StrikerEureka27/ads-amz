package gt.com.ad.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adm_account_repository")
public class AdmAccountRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account")
    private AdmAccount account;

    @Column(name = "repository")
    private KrnRepository repository;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public KrnRepository getRepository() {
        return repository;
    }

    public void setRepository(KrnRepository repository) {
        this.repository = repository;
    }

    public AdmAccount getAccount() {
        return account;
    }

    public void setAccount(AdmAccount account) {
        this.account = account;
    }

   

}
