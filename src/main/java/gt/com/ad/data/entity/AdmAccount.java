package gt.com.ad.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "adm_account")
public class AdmAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Column(name = "created_by")
    private String createdBy;
    private boolean active;

    @ManyToMany
    @JoinTable(name = "adm_account_filter", joinColumns = @JoinColumn(name = "account"), inverseJoinColumns = @JoinColumn(name = "filter"))
    Set<AdmFilter> filters = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "adm_account_reference", joinColumns = @JoinColumn(name = "account"), inverseJoinColumns = @JoinColumn(name = "reference"))
    List<AdmReference> references = new ArrayList<AdmReference>();

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
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

    public List<AdmReference> getReferences() {
        return references;
    }

    public void setReferences(List<AdmReference> references) {
        this.references = references;
    }

}
