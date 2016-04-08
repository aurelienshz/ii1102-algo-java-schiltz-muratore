# Réponses aux questions du sujet : #
 
## I. Algorithmique : ##

- Les cellules ont **deux** états *vivant* ou *mort*
- Il serait donc judicieux d'utiliser une **variable booléenne** pour représenter l'état des cellules 
- Un **Tableau deux dimension de booléens** semble être le choix approprié de variable.
 
## II. Etude des règles : ##

- L'état d'une cellule dépend de l'état de ses 8 voisins

### Le cas du milieu ###

On se place au milieu de la grille donc on ne prendra pas de précautions sur l'existance des 8 voisins dans ce pseuso-code ci
```
Entrée : grille, le tableau de la partie indexé par x et y

entier NbVoisinsVivants = 0
booléen EtatCellule = G[x][y]

POUR ( i entier allant de -1 à 1 )
	POUR (j entier allant de -1 à 1)
		SI (i et j pas tous deux égaux à 0)
			SI (G[x+i][y+j] est à vrai)
				NbVoisinsVivants++;
			FIN SI
		FIN SI
	FIN POUR
FIN POUR

SWITCH (EtatCellule)
	CAS vrai : 
		SI NbVoisinsVivants ne pas vaut 2 ou 3
			EtatCellule = faux
		FIN SI
	CAS faux
		SI NbVoisinsVivants vaut 3
			EtatCellule = vrai
		FIN SI
FIN SWITCH
```
*on ne reprendra que les boucles FOR, le SWITCH reste identique*

### Le cas en G[0,0] ###

Pour la cellule en [0,0], elle n'aura que 3 voisins (bas, droite, et en bas à droite)
On a donc enlevé les cas ou i et j vont en négatif, ils vont donc de 0 à 1 et plus de -1 à 1
```
POUR ( i entier allant de 0 à 1 )
	POUR (j entier allant de 0 à 1)
		SI (i et j pas tous deux égaux à 0)
			SI (grille[x+i][y+j] est à vrai)
				NbVoisinsVivants++;
			FIN SI
		FIN SI
	FIN POUR
FIN POUR
```
### Le cas en Général ###

Dans le cas général d'un tableau de taille n,m, il faut vérifier avant de relever sa valeur que chaque voisin est bien dans le tableau pour réaliser cela nous avons rajouté un SI.

```
Entrée : grille, le tableau de la partie de taille n,m

POUR ( x entier allant de 0 à n)
	POUR (y entier allant de 0 à m) //On teste chaque case du tableau
		
		//Le comptage des voisins vivants
		entier NbVoisinsVivants = 0
		POUR ( i entier allant de -1 à 1 )
			POUR (j entier allant de -1 à 1)
				SI (i et j pas tous deux égaux à 0)
					SI (x+i >= 0 && x+i < n && y+i >= 0 && y+i < m) // Le SI qui vérifie l'existence du voisin
						SI (G[x+i][y+j] est à vrai)
							NbVoisinsVivants++;
						FIN SI
					FIN SI
				FIN SI
			FIN POUR
		FIN POUR
		
		//En fonction du nombre de voisins, détermination de l'état de la cellule
		SWITCH (G[x][y])
			CAS vrai : //Si la cellule est vivante
				SI (NbVoisinsVivants ne pas vaut 2 ou 3)
					G2[x][y] = faux
				SINON
					G2[x][y] = vrai
				FIN SI
			CAS faux : //Si la cellule est morte
				SI (NbVoisinsVivants vaut 3)
					G2[x][y] = vrai
				SINON
					G2[x][y] = faux
				FIN SI
		FIN SWITCH
		
	FIN POUR
FIN POUR

Sortie : G2 le tableau à l'état suivant.
```
*On met la grille dans un nouvelle variable complètement reconstruite pour éviter les effets de bord et les pointeurs.* 

### Evaluation de la Complexité ###

- Comme l'on a deux boucles POUR imbriquées on fait n*m itérations pour tester les cases du tableau.
Donc si l'on prends n=m on est en n^2.

- Pour chaque case l'on effectue plusieurs itérations des deux boucles POUR suivantes, on pourrait calculer les itérations en séparant les cas en fonction nu nombre de voisins.

	1. Les 4 **coins** ont 3 voisins : 4*3 = 12.
	2. Les cases sur **les bords** hormis les coins ont 5 voisins et ils sont au nombre de (n-2) par côté en n (soit 2) et (m-2) par côté en m (soit 2) : 5*2*(n-2+m-2) = 10*(n+m-4)
	3. Les **cases restantes** sont des cases ayant tous leurs 8 voisins, ils sont au nombre de (n-1)*(m-1) car on a traité tous les cas particuliers sur les bors, il reste donc le "rectangle intérieur"

Ce qui nous fait au total : 2*(4*m*n+m+n-10)*n*m (ou 8 * m^2 *n^2 +2*m^2 *n+2*m*n^2 -20*m*n)

Par exemple si n = m on a 2*(n^2)*(4*(n^2)+2*n-10)

Voici le nombre de recherches de voisins pour quelques valeurs de n :

|**n** |2   |3   |4   |5   |
|---   |--- |--- |--- |--- |
|Valeur|80  |576 |1984|5000|
