# Réponses aux questions du sujet : #
 
## I. Algorithmique : ##

- Les cellules ont **deux** états : *vivant* ou *mort*
- Il serait donc judicieux d'utiliser une **variable booléenne** pour représenter l'état des cellules 
- Un **tableau de booléens à deux dimensions** semble être le choix approprié de variable.
 
## II. Etude des règles : ##

- L'état d'une cellule dépend de l'état de ses 8 voisins

### Le cas du milieu ###

On se place au milieu de la grille donc on ne prendra pas de précautions sur l'existence des 8 voisins dans ce pseuso-code ci

```
Entrée : grille, le tableau de la partie indexé par x et y

entier NbVoisinsVivants = 0;
booléen EtatCellule = G[x][y];

POUR (i entier allant de -1 à 1)
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
		// Condition de mort :
		SI NbVoisinsVivants différent de 2 ou de 3
			EtatCellule = faux
		FIN SI
	CAS faux
		// Condition de naissance :
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

Dans le cas général d'un tableau de taille n,m, il faut vérifier avant de relever sa valeur que chaque voisin est bien dans le tableau pour réaliser cela nous avons rajouté un `SI`.

```
Entrée : grille, le tableau de la partie de taille n,m

POUR ( x entier allant de 0 à n)
	POUR (y entier allant de 0 à m) //On teste chaque case du tableau
		
		// Le comptage des voisins vivants

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
			CAS vrai : //Si la cellule est vivante : conditions de mort
				SI (NbVoisinsVivants ne pas vaut 2 ou 3)
					G2[x][y] = faux
				SINON
					G2[x][y] = vrai
				FIN SI
			CAS faux : //Si la cellule est morte : condition de naissance
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

*Remarque :* On stocke la grille dans un nouvelle variable complètement reconstruite pour éviter les effets de bords dûs aux références.

### Evaluation de la Complexité ###

On a deux boucles POUR imbriquées ; on réalise donc `n*m` itérations pour tester les cases du tableau. Les deux boucles POUR suivantes auront *au maximum* 3 itérations et n'influent donc pas sur la complexité de cet algorithme.
On peut se placer dans l'approximation n=m (tableau carré), d'où une compleité en O(n^2).

Une approche plus fine de la complexité de cet algorithme est l'évaluation du nombre de recherches , on effectue plusieurs itérations des deux boucles POUR suivantes, on peut donc calculer le nombre de recherches de voisins en séparant les cas en fonction du nombre de voisins :

1. Les 4 **coins** ont 3 voisins : `R = 4*3 = 12`, soit une **complexité constante**.

2. Les cases sur **les bords** hormis les 4 coins ont 5 voisins et ils sont au nombre de (n-2) par côté en n (2 côtés) et (m-2) par côté en m (2 cotés également) :
```R = 2*5*(m-2+n-2) = 10*(m+n-4)```
Soit, dans l'approximation m = n, une **complexité linéaire** en O(n).

3. Les **cases restantes** sont des cases ayant toutes 8 voisins. Elles sont au nombre de (m-2)*(n-2) car on a déjà traité les cases situées au bord du tableau, il reste donc le "rectangle intérieur". Cela donne un nombre de recherches de voisins égal à :
```R = 8 * (n-2) * (m-2) = 8*m*n - 16*m - 16*n + 32```
Soit, dans l'approximation m=n, une **complexité quadratique** en 0(n^2).
	
Le nombre total de recherches de voisins est donc :
```
R = 8*m*n - 16*m - 16*n + 32 + 10*(m+n-4) + 12
R = 8*m*n - 6*m - 6*n + 4
```
Soit, dans l'approximation m = n :
```
R = 8*n^2 - 12*n + 4
```


Nous retrouvons une **complexité en `n^2`**, où `n` est la taille caractéristique du tableau.

À titre indicatif, voici le nombre de recherches de voisins pour quelques valeurs de n, dans le cas d'un tableau carré :

|**n** |2   |3   |4   |5   |
|---   |--- |--- |--- |--- |
|**R** |12  |40  |84  |144 |
