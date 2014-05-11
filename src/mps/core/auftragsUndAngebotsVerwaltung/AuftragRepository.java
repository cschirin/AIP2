package mps.core.auftragsUndAngebotsVerwaltung;

import org.hibernate.Session;

public class AuftragRepository {

	 static EAuftrag readAuftrag(Long auftragNr) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Auftrag auftrag = (Auftrag) session.get(Auftrag.class, auftragNr);
		
		session.getTransaction().commit();
		
		return auftrag;
	}

	public static Auftrag createPersistent(boolean istAbgeschlossen, String beauftragtAm ,
			Angebot angebot) {
		//Create transient object
		Auftrag auftrag = Auftrag.create(istAbgeschlossen,beauftragtAm);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//Set references
		auftrag.setAngebot(angebot);
		auftrag.setFertigungsauftragNr(null); //currently unknown
		//Set reference back
		angebot.setAuftrag(auftrag);
		
		//Update the newly created object
		session.save(auftrag);
		//Update the already existing object
		session.merge(angebot);
		
		session.getTransaction().commit();
		
		return auftrag;
	}

}
