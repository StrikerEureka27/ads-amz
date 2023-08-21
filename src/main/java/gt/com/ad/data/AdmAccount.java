package gt.amz.data;

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
import jakarta.persistence.Table;


@Entity
@Table(name = "adm_account")
public class AdmAccount implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by")
    private LocalDateTime createdBy;
    private boolean active;

    @ManyToMany
    @JoinTable(name = "adm_account_filter",joinColumns = @JoinColumn(name = "account"), inverseJoinColumns = @JoinColumn(name = "filter"))
    Set<AdmFilter> filters = new HashSet<>();
    
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(LocalDateTime createdBy) {
        this.createdBy = createdBy;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Set<AdmFilter> getFilters() {
        return filters;
    }
    public void setFilters(Set<AdmFilter> filters) {
        this.filters = filters;
    }

    

    


}
