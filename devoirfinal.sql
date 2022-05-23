-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 22 mai 2022 à 23:32
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `devoirfinal`
--

-- --------------------------------------------------------

--
-- Structure de la table `bug`
--
use heroku_50176582f9ef3a5;
CREATE TABLE `bug` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `etat` bit(1) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `logiciel_id` int(11) DEFAULT NULL,
  `urgence_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `bug`
--

INSERT INTO `bug` (`id`, `description`, `etat`, `nom`, `logiciel_id`, `urgence_id`) VALUES
(1, 'bug 1', b'0', 'bug 1', 1, 1),
(3, 'bug 2', b'0', 'bug 2', 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `logiciel`
--

CREATE TABLE `logiciel` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `envirenement` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `logiciel`
--

INSERT INTO `logiciel` (`id`, `description`, `envirenement`, `nom`) VALUES
(1, 'Spring tool swit', 'java', 'java'),
(2, 'pucharm', 'python', 'python'),
(3, 'dev c++', 'c++', 'c'),
(4, 'sublim text', 'FrantEnd', 'frant');

-- --------------------------------------------------------

--
-- Structure de la table `privilige`
--

CREATE TABLE `privilige` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `description`, `nom`) VALUES
(1, 'Administrateur', 'Admin'),
(2, 'Client', 'Client'),
(3, 'Devloppeur', 'Dev');

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `priorite` int(11) NOT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `bug_id` int(11) DEFAULT NULL,
  `dev_user_id` int(11) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ticket`
--

INSERT INTO `ticket` (`id`, `description`, `nom`, `priorite`, `statut`, `bug_id`, `dev_user_id`, `user_user_id`) VALUES
(5, 'description 2', 'tirgani ticket 2', 1, 'resolue', 1, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `urgence`
--

CREATE TABLE `urgence` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `urgence`
--

INSERT INTO `urgence` (`id`, `description`, `nom`) VALUES
(1, 'Urgent Important', 'Urgent Important'),
(2, 'Urgent Non Important', 'Urgent Non Important'),
(3, 'Non Urgent  Important', 'Non Urgent Important'),
(4, 'Non Urgent Non Important', 'Non Urgent Non Important');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `username`, `role_id`) VALUES
(1, 'admin.tirgani@gmail.com', 'admin', 'admin', 1),
(2, 'clinet.tirgani-Emsi@gmail.com', 'client', 'client', 2),
(3, 'developpeur@gmail.com', 'dev', 'dev', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bug`
--
ALTER TABLE `bug`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkxd4w0dnwfrklefv42iwj9bjs` (`logiciel_id`),
  ADD KEY `FKh6jldrk0ren32vqp44a4921xi` (`urgence_id`);

--
-- Index pour la table `logiciel`
--
ALTER TABLE `logiciel`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `privilige`
--
ALTER TABLE `privilige`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK43bnjksu2inxvcjtv48b43ajv` (`role_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn3dheupb58n84dmdhxmqqsvfg` (`bug_id`),
  ADD KEY `FKpv4nwapar0bclsxnfead79wtu` (`dev_user_id`),
  ADD KEY `FK8nvar4k6awxj2y81vwyc1blff` (`user_user_id`);

--
-- Index pour la table `urgence`
--
ALTER TABLE `urgence`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bug`
--
ALTER TABLE `bug`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `logiciel`
--
ALTER TABLE `logiciel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `privilige`
--
ALTER TABLE `privilige`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `urgence`
--
ALTER TABLE `urgence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
