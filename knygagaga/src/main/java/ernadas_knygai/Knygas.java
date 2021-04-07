package ernadas_knygai;

import java.util.List;
import java.util.Set;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Knygas implements Serializable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Id;

    private String pav;
    
    private String author;
    
    private int pages;
    
    private String genre;
    
    private String salies_kodas;
    
    @ManyToOne
    @JoinColumn(name="salies_kodas", referencedColumnName="salies_kodas", insertable=false, updatable=false)
    private Salis salis;    

	public Salis getSalis() {
		return salis;
	}

	public void setSalis(Salis salis) {
		this.salis = salis;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getpav() {
		return pav;
	}

	public void setpav(String pav) {
		this.pav = pav;
	}

	public String getauthor() {
		return author;
	}

	public void setauthor(String author) {
		this.author = author;
	}

	public int getpages() {
		return pages;
	}

	public void setpages(int pages) {
		this.pages = pages;
	}

	public String getgenre() {
		return genre;
	}

	public void setgenre(String genre) {
		this.genre = genre;
	}
	
	public String getSalies_kodas() {
		return salies_kodas;
	}

	public void setSalies_kodas(String salies_kodas) {
		this.salies_kodas = salies_kodas;
	}

}
