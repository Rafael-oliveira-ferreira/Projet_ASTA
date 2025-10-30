Dans le cadre du module Développement Full Stack avec Java du semestre 7, nous avons été amenés à réaliser, en binôme, un projet intitulé Application de Suivi de Tutorats d’Apprentis (ASTA).
L’objectif principal de ce projet était de remplacer un système existant de gestion des suivis d’apprentis, jusque-là basé sur un fichier Excel, par une application web moderne, fiable et sécurisée. Cette application devait permettre au tuteur enseignant de consulter, modifier et gérer facilement les informations relatives à ses apprentis, leurs entreprises, leurs missions, leurs visites, et leurs soutenances.

Conformément au cahier des charges, le projet devait intégrer toutes les données présentes dans le système actuel, tout en respectant une architecture propre et évolutive.
Nous avons donc utiliser le framework Spring Boot afin de bénéficier d’une structure robuste et d’une intégration fluide avec nos différents modules.
Pour la gestion de la sécurité, nous avons implémenté Spring Security connecté via http.userDetailsService et BCryptPasswordEncoder assurant le hachage sécurisé des mots de passe.

La base de données a été conçue à partir d’un MCD afin de garantir la cohérence des relations entre les entités principales : Apprenti, Maître d’apprentissage, Entreprise, Rapport, Soutenance, Mission et Visite.
Nous avons opté pour MariaDB, hébergée sur AlwaysData, et avons configuré notre application pour se connecter à ce serveur distant.

Chaque entité dispose de son propre Controller, Service et Repository, permettant la gestion complète du cycle de vie des données (création, lecture, modification, suppression). Nous avons également distingué les Controllers destinés à l’affichage de pages et les RestControllers pour les échanges de données via API.
L’interface utilisateur repose sur trois pages principales :

une page de connexion,

une page d’accueil présentant la liste des apprentis suivis par le maître d’apprentissage connecté,

une page de détails d’un apprenti permettant la consultation et la modification de ses informations.

Les interactions avec l’API sont gérées grâce à des fonctions JavaScript assurant le chargement dynamique des données, ainsi que les opérations CRUD sur les entités.
Enfin, le projet a été conteneurisé avec Docker et déployé sur le service Render, accessible à l’adresse suivante :
👉 https://projet-asta.onrender.com/login

(Remarque : Render met en veille le conteneur après un certain temps d’inactivité, ce qui peut rallonger le temps de chargement initial.)

Pour tester l’application, les identifiants suivants peuvent être utilisés :

Login : test_account

Mot de passe : test_account

Un Swagger a également été mis en place afin de documenter l’ensemble des endpoints REST :
👉 https://projet-asta.onrender.com/swagger-ui/index.html#/

Des exceptions ont été créées afin de gérer les cas de création de données incohérente (ajout de deux visites à la même date pour un maitre d'apprentissage) et également pour gérer les données qui ne seraient pas trouvées.