package com.aboulma.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Gestionnaire de configuration et de cycle de vie de la SessionFactory Hibernate.
 * Utilise l'approche moderne recommandée depuis Hibernate 5+.
 */
public class HibernateUtil {
    // Registre des services internes d'Hibernate
    private static StandardServiceRegistry registry;
    // Instance unique de la SessionFactory (Pattern Singleton)
    private static SessionFactory sessionFactory;
    // Constructeur privé pour empêcher l'instanciation de cette classe utilitaire
    private HibernateUtil() {
    }
    /**
     * Récupère ou initialise l'instance unique de la SessionFactory.
     * @return SessionFactory configurée
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // 1. Initialisation du registre à partir du fichier 'hibernate.cfg.xml'
                // Hibernate cherche automatiquement ce fichier à la racine du dossier 'resources'
                registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
                // 2. Création des métadonnées à partir du registre configuré
                MetadataSources sources = new MetadataSources(registry);
                Metadata metadata = sources.getMetadataBuilder().build();
                // 3. Construction de la SessionFactory finale
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                System.out.println(" [HibernateUtil] SessionFactory initialisée avec succès !");
            } catch (Exception e) {
                System.err.println(" [HibernateUtil] Échec de la création de la SessionFactory !");
                e.printStackTrace();
                // En cas d'erreur, on détruit le registre pour libérer la mémoire et les connexions
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                throw new ExceptionInInitializerError("Impossible de charger la configuration Hibernate : " + e.getMessage());
            }
        }
        return sessionFactory;
    }
    /**
     * Ferme proprement la SessionFactory et libère le pool de connexions à PostgreSQL.
     * À appeler à la fin de votre application (par exemple à la fermeture de Main).
     */
    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("[HibernateUtil] SessionFactory fermée proprement.");
        }
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
