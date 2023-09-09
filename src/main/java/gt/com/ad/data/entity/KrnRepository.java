package gt.com.ad.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "krn_repository")
public class KrnRepository implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @Column(name = "processed")
    private boolean isProcessed;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "file", columnDefinition = "BLOB")
    private byte[] file;
    private int step;
    @Column(name = "account")
    private int accountId;

    // @JsonBackReference
    @ManyToMany
    @JoinTable(name = "adm_account_repository", joinColumns = @JoinColumn(name = "account"), inverseJoinColumns = @JoinColumn(name = "repository"))
    private Set<AdmAccount> accounts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private AdmAccount account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Set<AdmAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AdmAccount> accounts) {
        this.accounts = accounts;
    }

    public AdmAccount getAccount() {
        return account;
    }

    public void setAccount(AdmAccount account) {
        this.account = account;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    

    

}
