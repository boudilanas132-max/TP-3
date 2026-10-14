package com.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nom_complet", nullable = false)
    private String nom;

    // La suppression de l'utilisateur supprime ses réservations.
    // Retirer une réservation de cette collection la supprime également.
    @OneToMany(
        mappedBy = "utilisateur",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Reservation> reservations = new HashSet<>();

    public Utilisateur() {
    }

    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setUtilisateur(this);
    }

    public void retirerReservation(Reservation reservation) {
        reservations.remove(reservation);

        if (reservation.getUtilisateur() == this) {
            reservation.setUtilisateur(null);
        }
    }
}