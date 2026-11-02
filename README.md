# Mini-SAAQclic

GTI100, séance 2 : méthodes, classes, objets et héritage.

Vous construisez le noyau d'un petit système de permis et de transactions.
Les tarifs, les numéros et les règles sont fictifs et simplifiés pour l'exercice.

**Règle du cours : aucun outil d'IA pour générer le code.**

## Démarrer

1. Sur la page de ce dépôt, cliquez **Use this template**, puis **Create a new repository**.
2. Choisissez votre compte comme propriétaire et la visibilité **Private**.
3. Dans votre nouveau dépôt, ouvrez **Settings**, **Collaborators**, **Add people**, puis invitez `imenBen`.
4. Cliquez **Code**, onglet **Codespaces**, **Create codespace on main**. VS Code s'ouvre dans le navigateur.

Travaillez toujours dans votre dépôt, jamais dans le dépôt du cours.
Votre compte GitHub comprend un quota mensuel de Codespaces. Fermez l'onglet à la fin de la séance et
supprimez le Codespace quand vous avez terminé vos missions et poussé votre travail.

## La boucle de travail

1. Lisez la mission ci-dessous, puis ouvrez le fichier indiqué dans `src/main/java/saaqclic`.
2. Écrivez votre code.
3. Dans le terminal : `bash verifier.sh 1` (remplacez 1 par le numéro de la mission).
4. Rouge : lisez le message, corrigez, recommencez. Vert : passez à l'étape 5.
5. Dans l'onglet Source Control : écrivez un message, puis **Commit** et **Sync Changes** (push).
6. Sur GitHub, ouvrez l'onglet **Actions**, puis la dernière exécution : votre score y est affiché.

Pour voir votre score sans passer par GitHub : `bash verifier.sh points`.

Les tests sont dans `src/test/java/saaqclic`. Lisez-les quand vous êtes bloqué, sans les modifier.
Faites les missions dans l'ordre : chacune s'appuie sur les précédentes.
Du code qui ne compile pas donne zéro à toutes les missions. Ne le poussez pas.

## Missions

| Mission | Fichiers | Points |
| --- | --- | --- |
| 0. Premier push | `Bienvenue` | 5 |
| 1. Méthodes | `Tarifs` | 15 |
| 2. Classe et objets | `Permis` | 20 |
| 3. static et références | `Permis`, `Guichet` | 15 |
| 4. Héritage | `PermisApprenti` | 20 |
| 5. Polymorphisme | `Renouvellement`, `Immatriculation`, `Caisse` | 15 |
| Boss | `Amende` (à créer) | 10 |

### Mission 0 : premier push

Faites retourner à `Bienvenue.message()` le texte exact `Bienvenue sur mini-SAAQclic`.
Vérifiez avec `bash verifier.sh 0`, puis faites commit et push.

### Mission 1 : méthodes

Dans `Tarifs` :

- `fraisRenouvellement(int annees)` : 30 $ par année, 0 $ si la durée est nulle ou négative.
- `fraisRenouvellement(int annees, boolean enLigne)` : même calcul, moins 5 $ en ligne, jamais sous 0 $.
  Réutilisez la première méthode.
- `numeroValide(String numero)` : une majuscule suivie de 12 chiffres, et `false` si `numero` vaut `null`.
- Écrivez la Javadoc de `numeroValide`, avec `@param` et `@return`.

### Mission 2 : classe et objets

Dans `Permis` :

- Corrigez le constructeur à trois paramètres. Il contient un bogue, et un nombre de points négatif doit devenir 0.
- Complétez le constructeur à deux paramètres avec `this(...)`.
- Écrivez les accesseurs, `ajouterPoints` et `estSuspendu`.
- `estSuspendu` doit utiliser `limitePoints()`, sans écrire 15. La mission 4 vous dira pourquoi.

### Mission 3 : static et références

- Dans `Permis`, comptez les permis créés avec un attribut `static`. Chaque permis compte une seule fois.
- Dans `Guichet`, `corrigerTitulaire` ne fonctionne pas. Trouvez pourquoi et corrigez-la.
- Complétez `remplacer` et `compterSuspendus`.

### Mission 4 : héritage

`PermisApprenti` est un `Permis` dont la limite est de 4 points.

- Faites-le hériter de `Permis`. Le code cessera de compiler : lisez l'erreur et corrigez le constructeur.
- Redéfinissez `limitePoints()`. Ne redéfinissez pas `estSuspendu()`.
- Redéfinissez `toString()` en réutilisant celui du parent.

### Mission 5 : polymorphisme

`Transaction` est fournie, ne la modifiez pas.

- `Renouvellement` : `montant()` réutilise `Tarifs`, `description()` retourne par exemple `Renouvellement 2 an(s)`.
- `Immatriculation` : `montant()` retourne `TARIF`, `description()` retourne par exemple `Immatriculation ABC 123`.
- `Caisse.total` additionne les montants, sans `instanceof` et sans nommer les classes enfants.

### Boss : ajouter sans modifier

Créez le fichier `Amende.java`, sans toucher à `Caisse` :

- `public Amende(String reference, double montantBase, int joursDeRetard)` ;
- `montant()` : le montant de base, plus 2 $ par jour de retard ;
- `description()` : par exemple `Amende (10 j de retard)`.
