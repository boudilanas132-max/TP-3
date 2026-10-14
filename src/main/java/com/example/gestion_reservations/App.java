package com.example.gestion_reservations;

import com.example.model.*;
import javax.persistence.*;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory(
                    "gestion-reservations-pu"
            );
            em = emf.createEntityManager();

            // TEST : orphanRemoval
            System.out.println("\n========== TEST A : orphanRemoval ==========");

            em.getTransaction().begin();

            Utilisateur utilisateurA = new Utilisateur(
                    "Alice Test A",
                    "alice-test-a@example.com"
            );

            Salle salleA = new Salle(
                    "Salle Test A", 20, "Premier étage"
            );

            Equipement equipementA =
                    new Equipement("Projecteur Test A");

            salleA.ajouterEquipement(equipementA);

            em.persist(salleA);
            em.persist(utilisateurA);

            LocalDateTime debutA = LocalDateTime.now();

            Reservation reservationA = new Reservation(
                    debutA,
                    debutA.plusHours(2),
                    "CONFIRMEE"
            );

            utilisateurA.ajouterReservation(reservationA);
            salleA.ajouterReservation(reservationA);

            em.persist(reservationA);
            em.getTransaction().commit();

            Long utilisateurAId = utilisateurA.getId();
            Long reservationAId = reservationA.getId();

            System.out.println("Utilisateur créé : "
                    + utilisateurA.getEmail());
            System.out.println("Réservation créée : " + reservationAId);

            // Retirer la réservation de la collection de l'utilisateur
            em.getTransaction().begin();

            Utilisateur utilisateurATest =
                    em.find(Utilisateur.class, utilisateurAId);

            Reservation reservationATest =
                    em.find(Reservation.class, reservationAId);

            if (utilisateurATest == null || reservationATest == null) {
                throw new IllegalStateException(
                        "Préparation du test A impossible."
                );
            }

            Salle salleATest = reservationATest.getSalle();

            utilisateurATest.retirerReservation(reservationATest);

            // Synchroniser la collection de la salle
            if (salleATest != null) {
                salleATest.getReservations().remove(reservationATest);
            }

            em.flush();
            em.getTransaction().commit();
            em.clear();

            Reservation resultatA =
                    em.find(Reservation.class, reservationAId);

            if (resultatA == null) {
                System.out.println(
                        "TEST A REUSSI : la réservation a été supprimée."
                );
            } else {
                System.out.println(
                        "TEST A ECHOUE : la réservation existe encore."
                );
            }

            // TEST : suppression en cascade
            System.out.println(
                    "\n========== TEST B : suppression en cascade =========="
            );

            em.getTransaction().begin();

            Utilisateur utilisateurB = new Utilisateur(
                    "Bob Test B",
                    "bob-test-b@example.com"
            );

            Salle salleB = new Salle(
                    "Salle Test B", 15, "Deuxième étage"
            );

            Equipement equipementB =
                    new Equipement("Ecran Test B");

            salleB.ajouterEquipement(equipementB);

            em.persist(salleB);
            em.persist(utilisateurB);

            LocalDateTime debutB = LocalDateTime.now();

            Reservation reservationB1 = new Reservation(
                    debutB,
                    debutB.plusHours(1),
                    "CONFIRMEE"
            );

            Reservation reservationB2 = new Reservation(
                    debutB.plusDays(1),
                    debutB.plusDays(1).plusHours(2),
                    "EN_ATTENTE"
            );

            utilisateurB.ajouterReservation(reservationB1);
            utilisateurB.ajouterReservation(reservationB2);

            salleB.ajouterReservation(reservationB1);
            salleB.ajouterReservation(reservationB2);

            em.persist(reservationB1);
            em.persist(reservationB2);

            em.getTransaction().commit();

            Long utilisateurBId = utilisateurB.getId();
            Long reservationB1Id = reservationB1.getId();
            Long reservationB2Id = reservationB2.getId();

            System.out.println("Utilisateur créé : "
                    + utilisateurB.getEmail());
            System.out.println("Réservation B1 créée : " + reservationB1Id);
            System.out.println("Réservation B2 créée : " + reservationB2Id);

            // Supprimer l'utilisateur : ses réservations sont supprimées grâce à CascadeType.ALL dans Utilisateur.java
            em.getTransaction().begin();

            Utilisateur utilisateurBSupprimer =
                    em.find(Utilisateur.class, utilisateurBId);

            if (utilisateurBSupprimer != null) {
                em.remove(utilisateurBSupprimer);
            }

            em.getTransaction().commit();
            em.clear();

            // Vérifier que l'utilisateur et ses réservations n'existent plus
            Utilisateur resultatUtilisateurB =
                    em.find(Utilisateur.class, utilisateurBId);

            Reservation resultatB1 =
                    em.find(Reservation.class, reservationB1Id);

            Reservation resultatB2 =
                    em.find(Reservation.class, reservationB2Id);

            if (resultatUtilisateurB == null
                    && resultatB1 == null
                    && resultatB2 == null) {
                System.out.println(
                        "TEST B REUSSI : l'utilisateur et ses réservations "
                                + "ont été supprimés."
                );
            } else {
                System.out.println(
                        "TEST B ECHOUE : certaines entités existent encore."
                );
            }

        } catch (Exception e) {

            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            System.err.println("\nERREUR PENDANT LES TESTS :");
            e.printStackTrace();

        } finally {

            if (em != null && em.isOpen()) {
                em.close();
            }

            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}