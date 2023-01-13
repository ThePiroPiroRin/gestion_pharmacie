To use this app:
Please add attached jars in project structure modules add jars.
Hover over red imports and do the suggested add requires... corrections.
Create a database and add its information in the ConnexionDB class.
Create 4 tables one for products, orders, sales and employees named produits, commandes, vente and employes respectively.
Make sure the tables are created in accordance to the screenshots attached in dbScreens using the following instructions:

create table employes(id int not null auto_increment, nom varchar(50) not null, prenom varchar(50), tel varchar(50), fonction varchar(50), passwd varchar(50), username varchar(50), constraint pk_emp primary key(id));

create table produits(id int not null auto_increment, nom varchar(50) not null, qte int DEFAULT 0, prix int, description varchar(50), constraint pk_prod primary key(id));

create table commandes(nom_pro varchar(50) not null, prix int, qtite int, nom_Fr varchar(50), est_recu tinyint default 0, est_complet tinyint default 0, qtite_recu int, id int not null auto_increment, LaDate date, constraint pk_com primary key(id));

create table vente(id int not null auto_increment, NomMedicament varchar(50) not null, QtMedicament int, PrixUnitaire int, PrixVente int, LaDate date, constraint pk_vnt primary key(id));

Thank you for your time!



