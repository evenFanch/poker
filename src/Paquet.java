import java.util.*;

public class Paquet 
{
	private static ArrayList<Carte> lstCartes;
	private static ArrayList<Carte> plateau;

	public static void initPaquet()
	{
		String[] couleurs = {"Carreau", "Pique", "Coeur", "Trefle"};

		Paquet.lstCartes = new ArrayList<Carte>();
		Paquet.plateau   = new ArrayList<Carte>();

		for (int i=1; i<14;i++ )
			for (int j=0; j<4;j++ )
				lstCartes.add(new Carte(i, couleurs[j]));
	}

	public static ArrayList<Carte> getPaquet () {return lstCartes; }
	public static ArrayList<Carte> getPlateau () {return plateau; }

	public static Carte piocherCarte()
	{
		Random rd = new Random();
		int entierA = rd.nextInt(Paquet.lstCartes.size());

		Carte c = lstCartes.get(entierA);

		lstCartes.remove(entierA);

		return c;
	}

	public static void distribuerPlateau(int tour)
	{
		switch (tour)
		{
			case 1 : Paquet.plateau   = new ArrayList<Carte>();break;
			case 2: for (int i=0; i<3; i++)
						Paquet.plateau.add(Paquet.piocherCarte());
					break;
			case 3 :Paquet.plateau.add(Paquet.piocherCarte());break;
			case 4 :Paquet.plateau.add(Paquet.piocherCarte());break;
		}
		
	}


}