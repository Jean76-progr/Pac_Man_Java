# Pac-Man Game

Un jeu Pac-Man classique développé en Java avec JavaFX, offrant une expérience de jeu rétro avec des fonctionnalités modernes.

## 🎮 Caractéristiques

- Interface graphique moderne avec JavaFX
- 4 niveaux de jeu uniques
- Système de score
- Support multilingue (Français, Anglais, Tchèque)
- Effets sonores et musique de fond
- Options configurables (volume, langue)
- Système de meilleurs scores
- Animation fluide
- Contrôles intuitifs

## 🛠️ Prérequis

- Java 17 ou supérieur
- JavaFX 17
- Gradle (pour la compilation et l'exécution)

## 📥 Installation

1. Clonez le repository :
```bash
git clone [url-du-repo]
```

2. Naviguez vers le dossier du projet :
```bash
cd pacman-game
```

3. Compilez le projet avec Gradle :
```bash
./gradlew build
```

## 🎯 Lancement du jeu

Exécutez le projet avec Gradle :
```bash
./gradlew run
```

## 🎲 Comment jouer

- Utilisez les touches directionnelles (↑, ↓, ←, →) pour déplacer Pac-Man
- Collectez tous les points pour marquer des points
- Évitez les fantômes
- Trouvez la clé pour débloquer la porte et passer au niveau suivant
- Appuyez sur ECHAP pour retourner au sélecteur de niveau

## 🔧 Configuration

Le jeu utilise un fichier `config.properties` pour stocker les paramètres :
- Volume de la musique
- Volume des effets sonores
- Langue du jeu

Ces paramètres peuvent être modifiés dans le menu Options du jeu.

## 📁 Structure du projet

```
pacman-game/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ijae/
│   │   │       └── xjanelj/
│   │   │           ├── controller/
│   │   │           ├── model/
│   │   │           ├── util/
│   │   │           └── view/
│   │   └── resources/
│   │       ├── images/
│   │       ├── sounds/
│   │       └── messages_*.properties
├── build.gradle
└── README.md
```

## 🌍 Internationalisation

Le jeu supporte plusieurs langues :
- 🇫🇷 Français
- 🇬🇧 Anglais
- 🇨🇿 Tchèque

La langue peut être changée dans le menu Options.

## 🤝 Contribution

Les contributions sont les bienvenues ! N'hésitez pas à :
1. Fork le projet
2. Créer une branche pour votre fonctionnalité
3. Commiter vos changements
4. Push vers la branche
5. Ouvrir une Pull Request

## 📝 Licence

Ce projet est sous licence Apache 2.0 - voir le fichier LICENSE pour plus de détails.

## 🎵 Crédits

- Sons et musiques : Effets sonores classiques de Pac-Man
- Design : Inspiré du Pac-Man original de Namco

## ⚠️ Notes techniques

- Le jeu utilise JavaFX pour le rendu graphique
- La gestion du son est faite via les API MediaPlayer de JavaFX
- Le système de score est persistant entre les sessions
- Les niveaux sont définis via des matrices de caractères dans le code source

## 🐛 Résolution des problèmes courants

1. **Le jeu ne se lance pas**
    - Vérifiez que Java 17+ est installé
    - Vérifiez que JavaFX est correctement configuré

2. **Pas de son**
    - Vérifiez les paramètres de volume dans le menu Options
    - Vérifiez que les fichiers sons sont présents dans le dossier resources

3. **Performances faibles**
    - Vérifiez que votre JVM a suffisamment de mémoire allouée
    - Réduisez le volume sonore ou désactivez les effets sonores