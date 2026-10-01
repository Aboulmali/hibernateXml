package com.aboulma.entities;

import java.io.Serializable;

public class Produit implements Serializable {

    private Long id;
    private String nom;
    private double prix;
    private int quantiteStock;

    // 1. Constructeur vide (par défaut)
    public Produit() {
    }

    // 2. Constructeur avec paramètres
    public Produit(Long id, String nom, double prix, int quantiteStock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
    }

    // 3. Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }
}