# Hibernate XML

Ce projet est un exemple simple de mise en place d'un projet Java avec Hibernate en utilisant des mappings XML (`.hbm.xml`) au lieu d'annotations JPA.

## Objectif

L'application montre comment :

- configurer Hibernate avec un fichier `hibernate.cfg.xml` ;
- mapper une entité Java vers une table SQL via un fichier XML ;
- se connecter à une base PostgreSQL ;
- enregistrer un objet Java dans la base avec une transaction Hibernate.

## Structure du projet

- `src/main/java/com/aboulma/entities/Produit.java` : classe métier représentant un produit ;
- `src/main/resources/produit.hbm.xml` : mapping XML de `Produit` vers la table `produits` ;
- `src/main/resources/hibernate.cfg.xml` : configuration Hibernate et paramètres de connexion ;
- `src/main/java/com/aboulma/util/HibernateUtil.java` : utilitaire de création et fermeture de la `SessionFactory` ;
- `src/main/java/com/aboulma/Main.java` : point d'entrée de l'application.

## Ce que fait l'application

Au lancement, le programme :

1. initialise Hibernate ;
2. ouvre une session ;
3. démarre une transaction ;
4. crée un objet `Produit` ;
5. le sauvegarde dans la base de données ;
6. valide la transaction ;
7. ferme proprement la SessionFactory.

## Technologie utilisée

- Java 17
- Maven
- Hibernate ORM 6
- PostgreSQL

## Prérequis

Avant de lancer le projet, il faut :

- installer Java 17 ;
- installer Maven ;
- créer une base PostgreSQL nommée `hibernateXml_db` ;
- vérifier que PostgreSQL écoute sur `localhost:5432` ;
- utiliser le compte `postgres` avec le mot de passe configuré dans `hibernate.cfg.xml`.

## Configuration de la base

Le fichier `src/main/resources/hibernate.cfg.xml` contient la connexion PostgreSQL :

```xml
<property name="connection.url">jdbc:postgresql://localhost:5432/hibernateXml_db</property>
<property name="connection.username">postgres</property>
<property name="connection.password">65939191</property>
```

Il faut ajuster ces valeurs selon votre environnement local.

## Lancer le projet

Depuis la racine du projet :

```bash
mvn clean compile
```

Puis exécuter la classe principale :

```bash
mvn exec:java -Dexec.mainClass="com.aboulma.Main"
```

Si la configuration Maven ne contient pas le plugin `exec`, vous pouvez simplement lancer la classe via votre IDE ou un lancement Java classique.

## Exemple fonctionnel

Le programme enregistre un produit comme :

- nom : `Ordinateur Portable`
- prix : `1200.50`
- quantité en stock : `10`

Ensuite Hibernate envoie les instructions SQL nécessaires pour insérer cette ligne dans la table `produits`.

## Résumé

Ce projet sert d'exemple pédagogique pour comprendre :

- la configuration Hibernate ;
- l'utilisation des mappings XML ;
- la persistance d'un objet Java vers PostgreSQL ;
- le fonctionnement de base des transactions ORM.

C'est un projet idéal pour apprendre les fondements de Hibernate sans passer par les annotations JPA.
