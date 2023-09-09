package gt.com.ad.data.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "adm_account_filter")
public class AdmAccountFilter implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account")
    private AdmAccount account;
    @Column(name = "filter")
    private AdmFilter filter;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public AdmAccount getAccount() {
        return account;
    }
    public void setAccount(AdmAccount account) {
        this.account = account;
    }
    public AdmFilter getFilter() {
        return filter;
    }
    public void setFilter(AdmFilter filter) {
        this.filter = filter;
    }

    


}
