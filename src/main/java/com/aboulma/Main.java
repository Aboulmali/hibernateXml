package com.aboulma;

import com.aboulma.entities.Produit;
import com.aboulma.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Initialisation Hibernate...");
        Transaction tx = null;
        // Utilisation du try-with-resources pour fermer automatiquement la Session
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            // démarrage de la transaction
            tx = session.beginTransaction();
            Produit produit = new Produit();
            produit.setNom("Ordinateur Portable");
            produit.setPrix(1200.50);
            produit.setQuantiteStock(10);
            // Sauvegarde de l'objet produit dans la base de données
            session.save(produit);
            // validation de la transaction
            tx.commit();
            System.out.println("Produit sauvegardé avec succès : " + produit.getNom());
        } catch (Exception e) {
            if (tx != null && tx.getStatus().canRollback()) {
                tx.rollback();
                System.err.println("Transaction annulée en raison d'une erreur.");
            }
            e.printStackTrace();
        } finally {
            // Fermeture globale à la fin du programme pour quitter proprement
            HibernateUtil.shutdown();
        }
    }
}

// SessionFactory sessionFactory = null;
// sessionFactory = new Configuration().configure().buildSessionFactory();

//            if (sessionFactory != null) {
//                sessionFactory.close();
//            }