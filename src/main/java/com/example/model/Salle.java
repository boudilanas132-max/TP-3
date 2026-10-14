package com.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "salles")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private int capacite;

    private String localisation;

    // Une salle peut être associée à plusieurs réservations
    @OneToMany(mappedBy = "salle")
    private Set<Reservation> reservations = new HashSet<>();

    // Une salle peut posséder plusieurs équipements
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name = "salle_equipement",
        joinColumns = @JoinColumn(name = "salle_id"),
        inverseJoinColumns = @JoinColumn(name = "equipement_id")
    )
    private Set<Equipement> equipements = new HashSet<>();

    public Salle() {
    }

    public Salle(String nom, int capacite, String localisation) {
        this.nom = nom;
        this.capacite = capacite;
        this.localisation = localisation;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setSalle(this);
    }

    public void retirerReservation(Reservation reservation) {
        reservations.remove(reservation);

        if (reservation.getSalle() == this) {
            reservation.setSalle(null);
        }
    }

    public Set<Equipement> getEquipements() {
        return equipements;
    }

    public void ajouterEquipement(Equipement equipement) {
        equipements.add(equipement);
        equipement.getSalles().add(this);
    }

    public void retirerEquipement(Equipement equipement) {
        equipements.remove(equipement);
        equipement.getSalles().remove(this);
    }
}