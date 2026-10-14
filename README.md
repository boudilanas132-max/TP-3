# TP 3
<img width="718" height="346" alt="TP 3-1" src="https://github.com/user-attachments/assets/c9cb3d9b-c4cf-4019-82b9-74a08cf341c6" />

## Explication des résultats
- Insertion dans la table utilisateurs : Hibernate enregistre l'utilisateur Alice Dupont avec son adresse e-mail.
  
- Insertion dans la table reservations : une réservation est créée avec ses dates, son statut et les identifiants de la salle
 et de l'utilisateur associés.

- Insertion dans la table salle_equipement : Hibernate enregistre l'association entre la salle et l'équipement.
  
- Utilisateur enregistré : Alice Dupont : le programme confirme la création de l'utilisateur.
  
- Salle enregistrée : Salle A : la salle est créée avec succès.
  
- Équipement associé : Projecteur : le projecteur est associé à la salle.

- Réservation enregistrée avec succès : le programme confirme que l'opération de réservation s'est terminée correctement.

  <img width="723" height="385" alt="TP 3-2" src="https://github.com/user-attachments/assets/0c337b3e-2837-497e-8dde-db443b3bc04a" />

## Explication des résultats

- Insertion dans la table salles : Hibernate prépare l'insertion d'une salle avec son identifiant, sa capacité, sa localisation et son nom.

- Insertion dans la table equipements : Hibernate prépare l'enregistrement d'un équipement dans la base de données.

- Insertion dans la table utilisateurs : Hibernate prépare l'enregistrement d'un utilisateur avec son identifiant, son adresse e-mail et son nom complet.

- Insertion dans la table reservations : Hibernate prépare l'enregistrement d'une réservation contenant les dates de début et de fin, le statut, ainsi que les identifiants de la salle et de l'utilisateur associés.

  <img width="717" height="322" alt="TP3-3" src="https://github.com/user-attachments/assets/59779f42-3b61-40f8-aba8-7163094a7579" />

## Explication des résultats

- Création de l’utilisateur : un utilisateur avec l’adresse e-mail alice-test@example.com est créé avec succès.

- Création de la réservation : une réservation est enregistrée dans la base de données avec l’identifiant 1.

- Suppression de la réservation : Hibernate exécute une requête SQL DELETE pour supprimer la réservation correspondante.

- Vérification : Hibernate effectue une requête SELECT pour rechercher la réservation concernée.

- Résultat final : le message confirme que le test est réussi et que la réservation a été supprimée avec succès.

  <img width="717" height="299" alt="TP3-4" src="https://github.com/user-attachments/assets/b1d91c7f-8c76-40ef-9bc4-10dbb90c512f" />

## Explication des résultats

- Vérification des réservations : Hibernate exécute des requêtes SQL SELECT pour rechercher les réservations associées à l’utilisateur.
  
- Suppression de l’utilisateur et de ses réservations : le programme supprime l’utilisateur ainsi que les réservations qui lui sont associées.

- Exécution des requêtes SQL : Hibernate génère automatiquement les requêtes nécessaires pour accéder aux données et vérifier les réservations concernées.

- Résultat final : le message confirme la réussite du test et indique que l’utilisateur et ses réservations ont été supprimés avec succès.

