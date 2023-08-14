package gt.com.ad.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.camel.component.jpa.Consumed;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "repository")
public class Adsfile implements Serializable {

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
        if(createdAt==null){
            this.createdAt = null;
        }
        this.createdAt = createdAt;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
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

    @Consumed
    public void markAsProcessed(){
        this.isProcessed =  true;
        this.step = 3;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (isProcessed ? 1231 : 1237);
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + Arrays.hashCode(file);
        result = prime * result + step;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Adsfile other = (Adsfile) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (isProcessed != other.isProcessed)
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (!Arrays.equals(file, other.file))
            return false;
        if (step != other.step)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Adsfile [id=" + id + ", name=" + name + ", isProcessed=" + isProcessed + ", createdAt=" + createdAt
                + ", file=" + Arrays.toString(file) + ", step=" + step + "]";
    }

}
