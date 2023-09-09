package gt.com.ad.data.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "adm_filter_parameter")
public class AdmFilterParameter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "filter")
    private AdmFilter filter;

    @Column(name = "parameter")
    private AdmParameter parameter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdmFilter getFilter() {
        return filter;
    }

    public void setFilter(AdmFilter filter) {
        this.filter = filter;
    }

    public AdmParameter getParameter() {
        return parameter;
    }

    public void setParameter(AdmParameter parameter) {
        this.parameter = parameter;
    }

    


}
