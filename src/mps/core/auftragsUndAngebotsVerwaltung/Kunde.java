package mps.core.auftragsUndAngebotsVerwaltung;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Kunde implements EKunde,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Attribute */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long nr; 
	private String name;
	@OneToOne
	private Adresse adresse;
	

	public Long getNr() {
		// TODO Auto-generated method stub
		return nr;
	}
	
	@SuppressWarnings("unused")
	private void setNr(Long nr) {
		this.nr = nr; 
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name of Client
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * @param address of client
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	
	
	@Override 
	/** use business-key equality for implementing equals */
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Kunde)) return false;
		Kunde that = (Kunde)o;
		return this.getName().equals(that.getName()) && 
			   this.getAdresse().equals(that.getAdresse());
	}

	public static Kunde create(String name, Adresse adresse) {
		Kunde kunde = new Kunde();
		kunde.setName(name);
		kunde.setAdresse(adresse);
		
		return kunde;
	}

}
