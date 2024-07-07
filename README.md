# Projet E_bank

Ce projet est une application de gestion bancaire permettant de gérer des comptes, des cartes bancaires, des bénéficiaires et des transactions.

## Fonctionnalités principales

### Gestion des comptes

- **Création de comptes bancaires**
  - Type de compte (Courant, Épargne, etc.)
  - Solde initial
  - Date de création

- **Consultation des soldes et historiques de transactions**
  - ID de la transaction
  - Date et heure
  - Montant
  - Type de transaction (Crédit, Débit)
  - Description

- **Fermeture de comptes**
  - Raison de la fermeture

### Gestion des cartes bancaires

- **Consultation des informations de la carte**
  - Numéro de carte
  - Date d'expiration
  - Type de carte (Débit, Crédit)

- **Activation et désactivation de la carte**

- **Blocage en cas de perte ou de vol**
  - Raison du blocage

### Transferts d'argent

- **Transferts internes entre comptes du même utilisateur**
  - Montant
  - Description

- **Transferts externes vers des comptes dans d'autres banques**
  - Détails du compte externe (Numéro de compte, Banque, etc.)
  - Montant
  - Description

- **Gestion des bénéficiaires**
  - Nom du bénéficiaire
  - Détails du compte (Numéro de compte, Banque, etc.)

## Comment contribuer

Si vous souhaitez contribuer à ce projet, vous pouvez suivre ces étapes :

1. Clonez ce repository sur votre machine locale.
2. Créez une nouvelle branche (`git checkout -b amélioration-fonctionnalité`).
3. Faites vos modifications et committez-les (`git commit -am 'Ajouté une nouvelle fonctionnalité'`).
4. Poussez vers la branche (`git push origin amélioration-fonctionnalité`).
5. Soumettez une Pull Request.

## Contact

Pour toute question ou préoccupation concernant ce projet, n'hésitez pas à me contacter à [mohammedamin.elkettabi@gmail.com].


## Prérequis

Avant de commencer, assurez-vous d'avoir installé les outils suivants :

- Java JDK 17 ou supérieur
- Maven
- PostgreSQL (ou une autre base de données supportée par Spring Boot)

## Configuration

### Base de données

- Assurez-vous que PostgreSQL est installé et démarré sur votre machine.
- Créez une base de données nommée `E_bank` (ou utilisez un autre nom si vous le souhaitez).
- Configurez les informations de connexion à la base de données dans le fichier `application.properties` sous `src/main/resources`.

Exemple de configuration pour PostgreSQL :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/E_bank
spring.datasource.username=votre_nom_utilisateur
spring.datasource.password=votre_mot_de_passe
spring.jpa.hibernate.ddl-auto=update

## Enaa | 2024 : MOHAMMED AMIN EL KETTABI

