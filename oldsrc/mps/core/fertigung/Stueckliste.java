package mps.core.fertigung;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Stueckliste {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long nr;
	private String gueltigAb;
	private String gueltigBis;
	@OneToOne
	private Bauteil bauteil;
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(
		    name="STUECKLISTE_STUECKLISTENPOSITION",
		    joinColumns=
		        @JoinColumn(name="STUECKLISTE_ID"),
		    inverseJoinColumns=
		        @JoinColumn(name="STUECKLISTENPOSITION_ID")
		    )
	private Set<StuecklistenPosition> stuecklistenPosition = new HashSet<StuecklistenPosition>();

	

	public String getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(String gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public String getGueltigBis() {
		return gueltigBis;
	}

	public void setGueltigBis(String gueltigBis) {
		this.gueltigBis = gueltigBis;
	}

	public Bauteil getBauteil() {
		return bauteil;
	}

	public void setBauteil(Bauteil bauteil) {
		this.bauteil = bauteil;
	}

	public Set<StuecklistenPosition> getStuecklistenPosition() {
		return stuecklistenPosition;
	}

	public void setStuecklistenPosition(
			Set<StuecklistenPosition> stuecklistenPosition) {
		this.stuecklistenPosition = stuecklistenPosition;
	}
	
	public String toString(){
		StringBuffer sl = new StringBuffer();
		for(StuecklistenPosition sp:stuecklistenPosition) sl.append(sp.toString());
		return "Stueckliste Nr: "+nr+" gueltig ab "+gueltigAb+" bis "+gueltigBis+"\n"+sl;
	}
	
	@Override
	public boolean equals(Object o){
	    boolean result = false;
	    if (o instanceof Stueckliste) {
	        Stueckliste that = (Stueckliste) o;
	        result = ( this.getGueltigAb().equals(that.getGueltigAb()) 
	        		&& this.getGueltigBis().equals(that.getGueltigBis()));
	    }
	    return result;
	}
}