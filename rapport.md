Nous avons utilisé tout deux utilisé l'IDE Eclipse

La base de données a été conçue à partir d’un MCD afin de garantir la cohérence des relations entre les entités principales : Apprenti, Maître d’apprentissage, Entreprise, Rapport, Soutenance, Mission et Visite.
Nous avons opté pour MariaDB 10.11, hébergée sur AlwaysData, et avons configuré notre application pour se connecter à ce serveur distant.

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

a) Nous souhaitons attirer l’attention du correcteur sur la mise en place du backend et la connexion avec la base de données distante ainsi que sur le design réalisé afin d’avoir une interface utilisateur claire et intuitive.
b) La plus grande difficulté rencontrée a été la mise en ligne de l’application.
Lors de notre premier essai d’hébergement sur AlwaysData, le site et la base de données y étaient déployés, mais Spring Boot crashait à l’initialisation, rendant l’application inutilisable. Après plusieurs tentatives, nous avons décidé de changer d’hébergeur et d’utiliser Render, ce qui nous a permis de déployer correctement notre projet et de résoudre le problème.
c) Rafael : mise en place du backend, connexion à la base de données distante, création des contrôleurs et des méthodes CRUD (en collaboration avec Élody). Développé les fichiers JavaScript et les fonctions d’appel à la base de données.
Élody : réalisation complète de la partie front-end du site web, gestion de la recherche des apprentis, et création des classes entités JPA pour la base de données.
d) Amélioration de la compréhension du modèle MVC et de l’articulation entre le front-end, le contrôleur et la base de données.
Organisation du cours qui a facilité la compréhension des notions abordées et leur mise en pratique.
Découverte et maîtrise de Spring Boot.
e) Nous n’avons pas eu le temps d’implémenter la fonctionnalité bonus permettant d’extraire des données depuis un fichier Excel car nous manquions de temps.
f) Nous avons utilisé des controleurs, des services et des repositories.
Nous avons pour chaque service et controller effectué de l'injection de dépendance.