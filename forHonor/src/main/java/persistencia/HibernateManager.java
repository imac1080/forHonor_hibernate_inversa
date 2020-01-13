package persistencia;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import practica.Faccion;

public class HibernateManager {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query sqlQuery = session.createSQLQuery("select * from faccion").addEntity("faccion", Faccion.class);
//		List<Object[]> klienci = sqlQuery.list();
//		for (Object[] klient : klienci) {
//	        Klienci[] retunArray = new Klienci[2];
//
//	        Klienci klient1 = new Klienci();
//
//	        klient1.setId( ((BigDecimal)klient[0]).longValue() );
//	        klient1.setNrKlienta( ((BigDecimal)klient[1]).longValue() );
//	        klient1.setNrKlientaNiekod( ((BigDecimal)klient[2]).longValue() );
//	        klient1.setNazwaKlienta( (String) klient[3] );
//
//	        Klienci klient2 = new Klienci();
//
//	        klient2.setId( ((BigDecimal)klient[4]).longValue() );
//	        klient2.setNrKlienta( ((BigDecimal)klient[5]).longValue() );
//	        klient2.setNrKlientaNiekod( ((BigDecimal)klient[6]).longValue() );
//	        klient2.setNazwaKlienta( (String) klient[7] );
//
//	        retunArray[0] = klient1;
//	        retunArray[1] = klient2;
//
//	        returnList.add(retunArray);
//
//	    }
		
		
//		session.beginTransaction();
//		Bliblioteca biblio = new Bliblioteca();
//		biblio.setNom("Josu");
//		session.save(biblio);
//		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		System.out.println("Base de datos actualizada");
	}
}
