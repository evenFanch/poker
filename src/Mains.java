import java.util.*;

public class Mains 
{
	public static boolean estUnePaire(Carte[] tab)
	{
		int valeur =0;

		for (int i=0; i<tab.length-1; i++)
		{
			valeur = tab[i].getValeur();

			for (int j=i+1; j<tab.length; j++)
				if (valeur== tab[j].getValeur())
					return true;
		}

		return false;
	}

	public static boolean estUneDoublePaire(Carte[] tab)
	{
		int valeur1 =0;
		int valeur2 =0;

		boolean paire1, paire2;

		paire1=paire2=false;

		for (int i=0; i<tab.length-1; i++)
		{
			if (!paire1)
				valeur1  = tab[i].getValeur();
			else 
				valeur2 = tab[i].getValeur();

			for (int j=i+1; j<tab.length; j++)
			{
				if (!paire1 && valeur1== tab[j].getValeur())
					paire1=true;
				else if (paire1 && valeur2== tab[j].getValeur())
					paire2=true;
			}
		}

		return paire1 && paire2;
	}

	public static boolean estBrelan(Carte[] tab)
	{
		int valeur = 0;

		boolean unePaire;
		unePaire=false;

		for (int i=0; i<tab.length-1; i++)
		{
			valeur = tab[i].getValeur();
			unePaire=false;
			for (int j=i+1; j<tab.length; j++)
			{
				if (!unePaire && valeur     == tab[j].getValeur())
					unePaire=true;
				else if (unePaire && valeur == tab[j].getValeur())
					return true;
			}
		}
		return false;
	}

	public static boolean estSuite(Carte[] tab)
	{

		Comparator<Carte> comparator = new Comparator<Carte>()
		{
			public int compare(Carte c1, Carte c2)
			{
				return c2.getValeur()-c1.getValeur();
			}
		};

		ArrayList<Carte> temp= new ArrayList<Carte>();
		for (Carte c : tab)
			temp.add(c);
		temp.sort(comparator);
		System.out.println(temp);

		int cpt=1;

		for (int i=0; i<temp.size()-1; i++)
		{
			if (temp.get(i).getValeur()==temp.get(i+1).getValeur()+1) 
				cpt++;
			else 
				cpt=1;
			if (cpt==5)
				return true;
		}
		return false;
	}

	public static int[] mainFinale(Carte[] tab)
	{
		/*
		 * Valeurs de la mains finale 
		 * [0,1,2,3,4,5]
		 * indice 0 = type de mains
		 * indice 1 -> 5 les cartes de qualités décroissante en fonction de la mains 
		 * 
		 * Pour comparer deux mains, on peut juste comparer les indices un par un juste ce qu'il y en 
		 * ai un superieur a l'autre
		 * 
		 * valeurs pour l'indice 0 : 
		 * Carte Haute 			= 0
		 * Paire 				= 1 
		 * Double Paire 		= 2 
		 * Brelan 				= 3 
		 * Suite 				= 4 
		 * Couleur			 	= 5 
		 * Full 				= 6 
		 * Carré 				= 7 
		 * Quinte Flush 		= 8
		 * Qiunte Flush Royal 	= 9 
		 */

		//Initialisation
		int[] tabMainF = new int[6];
		ArrayList<Carte> carteUtiliser = new ArrayList<Carte>();

		//comparateur servant a trier une liste dans l'ordre decroissant
		Comparator<Carte> comparator = new Comparator<Carte>()
		{
			public int compare(Carte c1, Carte c2)
			{
				return c2.getValeur()-c1.getValeur();
			}
		};


		//Verification Paire
		if (Mains.estUnePaire(tab))
		{
			tabMainF= new int[6];
			tabMainF[0]=1;
			carteUtiliser = new ArrayList<Carte>();

			for (int i=0;i<tab.length-1;i++)
				for (int j=i+1;j<tab.length;j++)
					if (tab[i].getValeur()==tab[j].getValeur())
					{
						tabMainF[1] = tab[i].getValeur();
						tabMainF[2] = tab[j].getValeur();
						carteUtiliser.add(tab[i]);
						carteUtiliser.add(tab[j]);
					}
		}


		//Verification Double Paire
		if (Mains.estUneDoublePaire(tab))
		{
			tabMainF= new int[6];
			tabMainF[0]=2;
			carteUtiliser = new ArrayList<Carte>();

			for (int i=0;i<tab.length-1;i++)
				for (int j=i+1;j<tab.length;j++)
					if (tab[i].getValeur()==tab[j].getValeur())
					{
						carteUtiliser.add(tab[i]);
						carteUtiliser.add(tab[j]);
					}
			carteUtiliser.sort(comparator);

			for (int i=0; i<carteUtiliser.size(); i++)
				if (i<4)
					tabMainF[i+1] = carteUtiliser.get(i).getValeur();

			if (carteUtiliser.size()>4)
			{
				carteUtiliser.remove(4);
				carteUtiliser.remove(4);
			}

		}

		//Verification Brelan
		if (Mains.estBrelan(tab))
		{
			tabMainF= new int[6];
			tabMainF[0]=3;
			carteUtiliser = new ArrayList<Carte>();

			for (int i=0;i<tab.length-2;i++)
				for (int j=i+1;j<tab.length-1;j++)
					for (int k=j+1;k<tab.length;k++)
						if (tab[i].getValeur()==tab[j].getValeur() && tab[i].getValeur()==tab[k].getValeur())
						{
							carteUtiliser.add(tab[i]);
							carteUtiliser.add(tab[j]);
							carteUtiliser.add(tab[k]);
						}
			carteUtiliser.sort(comparator);

			System.out.println(carteUtiliser);

			for (int i=0; i<carteUtiliser.size(); i++)
				if (i<3)
					tabMainF[i+1] = carteUtiliser.get(i).getValeur();

			if (carteUtiliser.size()>3)
			{
				carteUtiliser.remove(3);
				carteUtiliser.remove(3);
				carteUtiliser.remove(3);
			}
			System.out.println(carteUtiliser);
		}

		//Verification Suite
		if (Mains.estSuite(tab))
		{
			tabMainF= new int[6];
			tabMainF[0]=4;
			carteUtiliser = new ArrayList<Carte>();
			ArrayList<Carte> tempSuite= new ArrayList<Carte>();
			for (Carte c : tab)
				tempSuite.add(c);

			for (int i=0;i<tempSuite.size();i++)
				for (int j=i+1;j<tab.length-1;j++)
					for (int k=j+1;k<tab.length;k++)
						if (tab[i].getValeur()==tab[j].getValeur() && tab[i].getValeur()==tab[k].getValeur())
						{
							carteUtiliser.add(tab[i]);
							carteUtiliser.add(tab[j]);
							carteUtiliser.add(tab[k]);
						}
			carteUtiliser.sort(comparator);

			System.out.println(carteUtiliser);

			for (int i=0; i<carteUtiliser.size(); i++)
				if (i<3)
					tabMainF[i+1] = carteUtiliser.get(i).getValeur();

			if (carteUtiliser.size()>3)
			{
				carteUtiliser.remove(3);
				carteUtiliser.remove(3);
				carteUtiliser.remove(3);
			}
			System.out.println(carteUtiliser);
		}

		//Remplire le reste de la main

		ArrayList<Carte> temp= new ArrayList<Carte>();
		boolean estDejaJouer;

		for (Carte c : tab)
		{
			estDejaJouer=false;
			for (Carte c2 : carteUtiliser) 
			{
				if (c.equals(c2))
					estDejaJouer=true;
			}
			if (!estDejaJouer)
				temp.add(c);
		}
			
		
		System.out.println(temp);

		temp.sort(comparator);
		System.out.println(temp);
		
		int cptLst=0;
		for (int i=1; i<tabMainF.length;i++)
		{
			if(tabMainF[i]==0)
			{
				tabMainF[i]=temp.get(cptLst).getValeur();
				cptLst++;
			}
			
		}
	
		System.out.print ("[");
		for (int i=0; i<tabMainF.length;i++)
			System.out.print (tabMainF[i]+" : ");
		System.out.print ("]");
		
		return tabMainF;
	}

	public static void main(String[] a)
	{
		Carte[] testTab = {new Carte (15, "Carreau"),
							new Carte (12, "Carreau"),
							new Carte (11, "Carreau"),
							new Carte (10, "Carreau"),
							new Carte (9, "Carreau"),
							new Carte (8, "Carreau"),
							new Carte (6, "Carreau")};

		//Mains.mainFinale(testTab);
		System.out.println (Mains.estSuite(testTab));
	}
}