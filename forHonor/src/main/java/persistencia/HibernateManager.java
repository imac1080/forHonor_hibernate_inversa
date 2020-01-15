package persistencia;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

import practica.Faccion;
import practica.Personaje;

public class HibernateManager {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		int opcion = menu();
		while (opcion != 0) {
			if (opcion == 1) {
				List personaje = session.createQuery("FROM Personaje").list();
				Iterator iterator = personaje.iterator();
				while (iterator.hasNext()) {
					Personaje element = (Personaje) iterator.next();
					System.out.println("ID: " + element.getPersonajeId());
					System.out.println("Nom: " + element.getNombrePersonaje());
					System.out.println("Ataque: " + element.getAtaque());
					System.out.println("Defensa: " + element.getDefensa());
					System.out.println("Faccion: " + element.getFaccion().getNombreFaccion());
					System.out.println();
				}
			}
			if (opcion == 2) {
				Scanner keyboard = new Scanner(System.in);
				System.out.println("Digues el id del personatje");
				Personaje per;
				int id = keyboard.nextInt();
				boolean encontrado = false;
				keyboard.nextLine();
				List personaje = session.createQuery("FROM Personaje").list();
				Iterator iterator = personaje.iterator();
				while (iterator.hasNext()) {
					Personaje element = (Personaje) iterator.next();
					if (element.getPersonajeId() == id) {
						per = element;
						encontrado = true;
						break;
					}
				}
				if (encontrado) {
					session.beginTransaction();
					System.out.println("Digues el ataque");
					int ataque = keyboard.nextInt();
					keyboard.nextLine();
					iterator = personaje.iterator();
					while (iterator.hasNext()) {
						Personaje element = (Personaje) iterator.next();
						if (element.getPersonajeId() == id) {
							element.setAtaque(ataque);
							System.out.println("ID: " + element.getPersonajeId());
							System.out.println("Nom: " + element.getNombrePersonaje());
							System.out.println("Ataque: " + element.getAtaque());
							System.out.println("Defensa: " + element.getDefensa());
							System.out.println("Faccion: " + element.getFaccion().getNombreFaccion());
							System.out.println();
							session.save(element);
							session.getTransaction().commit();
							break;
						}
					}
				} else {
					System.out.println("\nId de personatje no trobat\n");
				}

			}
			if (opcion == 3) {
				Scanner keyboard = new Scanner(System.in);
				System.out.println("Digues el id del personatje");
				Personaje per;
				int id = keyboard.nextInt();
				boolean encontrado = false;
				keyboard.nextLine();
				List personaje = session.createQuery("FROM Personaje").list();
				Iterator iterator = personaje.iterator();
				while (iterator.hasNext()) {
					Personaje element = (Personaje) iterator.next();
					if (element.getPersonajeId() == id) {
						per = element;
						encontrado = true;
						break;
					}
				}
				if (encontrado) {
					session.beginTransaction();
					iterator = personaje.iterator();
					while (iterator.hasNext()) {
						Personaje element = (Personaje) iterator.next();
						if (element.getPersonajeId() == id) {
							session.delete(element);
							session.getTransaction().commit();
							System.out.println("\nborrat\n");
							break;
						}
					}
				}

			}
			if (opcion == 4) {
				Scanner keyboard = new Scanner(System.in);
				System.out.println("Digues el id de la faccion");
				Faccion fac;
				int id = keyboard.nextInt();
				boolean encontrado = false;
				keyboard.nextLine();

				List faccion = session.createQuery("FROM Faccion").list();
				Iterator iterator = faccion.iterator();
				while (iterator.hasNext()) {
					Faccion element = (Faccion) iterator.next();
					if (element.getFaccionId() == id) {
						fac = element;
						encontrado = true;
						break;
					}
				}
				if (encontrado) {
					boolean encontradoPersonatje=false;
					List personaje = session.createQuery("FROM Personaje").list();
					iterator = personaje.iterator();
					while (iterator.hasNext()) {
						Personaje element = (Personaje) iterator.next();
						if (element.getFaccion().getFaccionId() == id) {
							encontradoPersonatje = true;
							break;
						}
					}
					if (encontradoPersonatje) {
						System.out.println("La faccion no esta vuida");
					}else {
						session.beginTransaction();
						iterator = faccion.iterator();
						while (iterator.hasNext()) {
							Faccion element = (Faccion) iterator.next();
							if (element.getFaccionId() == id) {
								session.delete(element);
								session.getTransaction().commit();
								System.out.println("\nborrat\n");
								break;
							}
						}
					}
				}else {
					System.out.println("\nNo exsisteix la faccio\n");
				}
			}
			if (opcion == 5) {
				Scanner keyboard = new Scanner(System.in);
				System.out.println("Digues el id de la faccion");
				Faccion fac;
				int id = keyboard.nextInt();
				boolean encontrado = false;
				keyboard.nextLine();

				List faccion = session.createQuery("FROM Faccion").list();
				Iterator iterator = faccion.iterator();
				while (iterator.hasNext()) {
					Faccion element = (Faccion) iterator.next();
					if (element.getFaccionId() == id) {
						fac = element;
						encontrado = true;
						break;
					}
				}
				if (encontrado) {
					boolean encontradoPersonatje=false;
					List personaje = session.createQuery("FROM Personaje").list();
					iterator = personaje.iterator();
					System.out.println("Digues el id del personatje");
					int idPerso = keyboard.nextInt();
					Personaje per = null;
					while (iterator.hasNext()) {
						Personaje element = (Personaje) iterator.next();
						if (element.getPersonajeId() == idPerso) {
							per = element;
							encontradoPersonatje = true;
							break;
						}
					}
					if (encontradoPersonatje) {
						session.beginTransaction();
//						Query query = session.createSQLQuery(
//								"CALL `Change_Faction`("+per.getPersonajeId()+", "+per.getFaccion().getFaccionId()+");");

						Query query = session.createSQLQuery(
								"CALL `Change_Faction`(1, 2);");
						System.out.println("Faccion de persona cambiada");
						System.out.println("ID: " + per.getPersonajeId());
						System.out.println("Nom: " + per.getNombrePersonaje());
						System.out.println("Ataque: " + per.getAtaque());
						System.out.println("Defensa: " + per.getDefensa());
						System.out.println("Faccion: " + per.getFaccion().getFaccionId());
						System.out.println();
						session.getTransaction().commit();
					}else {
						System.out.println("No exsisteix el personatje");
					}
				}else {
					System.out.println("\nNo exsisteix la faccio\n");
				}
			}
			opcion = menu();
		}

//		session.beginTransaction();
//		Bliblioteca biblio = new Bliblioteca();
//		biblio.setNom("Josu");
//		session.save(biblio);
//		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		System.out.println("Base de datos actualizada");
	}

	public static int menu() {
		Scanner keyboard = new Scanner(System.in);
		int opcion = 0;
		System.out.println("-----------");
		System.out.println("MENU");
		System.out.println("1. Llistar tots els personatjes");
		System.out.println("2. Modificar el ataque de un personatje");
		System.out.println("3. Borrar un personatje");
		System.out.println("4. Borrar una faccion solo si la faccion no tiene ningun personaje asociado");
		System.out.println("5. Cridar procedure cambiar de faccio personatje");
		System.out.println("0. Sortir");
		if (keyboard.hasNextInt()) {
			opcion = keyboard.nextInt();
		}
		return opcion;
	}
}
