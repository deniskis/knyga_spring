package ernadas_knygai;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Entity
public class Salis implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;

    private String salis;	
    
    private String salies_kodas;
    
    @OneToMany(/*mappedBy = "Knygas",*/cascade = CascadeType.ALL)
    @JoinColumn(name="salies_kodas", referencedColumnName="salies_kodas", insertable=false, updatable=false)    
    private List<Knygas> knygas;     

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getSalis() {
		return salis;
	}

	public void setSalis(String salis) {
		this.salis = salis;
	}

	public String getSalies_kodas() {
		return salies_kodas;
	}

	public void setSalies_kodas(String salies_kodas) {
		this.salies_kodas = salies_kodas;
	}

	public List<Knygas> getKnygas() {
		return knygas;
	}

	public void setKnygas(List<Knygas> knygas) {
		this.knygas = knygas;
	}    
    
    
}
