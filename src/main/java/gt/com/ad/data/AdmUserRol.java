package gt.com.ad.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adm_user_rol")
public class AdmUserRol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user")
    private AdmUser user;
    @Column(name = "rol")
    private AdmRol rol;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public AdmUser getUser() {
        return user;
    }
    public void setUser(AdmUser user) {
        this.user = user;
    }
    public AdmRol getRol() {
        return rol;
    }
    public void setRol(AdmRol rol) {
        this.rol = rol;
    }

    

}
