import java.util.*;

public class Paquet 
{
	private static ArrayList<Carte> lstCartes;

	public static void initPaquet()
	{
		String[] couleurs = {"Carreau", "Pique", "Coeur", "Trefle"};

		this.lstCartes = new ArrayList<Carte>();

		for (int i=1; i<14;i++ )
			for (int j=0; j<4;j++ )
				lstCartes.add(new Carte(i, couleurs[j]));
	}

	public static ArrayList<Carte> getPaquet () {return lstCartes; }

	public static Carte piocherCarte()
	{
		Random rd = new Random();
		int entierA = rd.nextInt(lstCartes.size());

		Carte c = lstCartes.get(entierA);

		lstCartes.remove(entierA);

		return c;
	}


}