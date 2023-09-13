package gt.com.ad.data.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "adm_reference")
public class AdmReference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String value;
    private String header;
    private int level;
    private int type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", insertable = false, updatable = false)
    private AdmReferenceType referenceType;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AdmReferenceType getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(AdmReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "AdmReference [id=" + id + ", name=" + name + ", value=" + value + ", header=" + header + ", level="
                + level + ", type=" + type + "]";
    }

}
