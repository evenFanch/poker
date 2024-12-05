public class Mains 
{
	public static boolean estUnePaire(Carte[] tab)
	{
		int valeur =0;

		for (Carte c: tab)
		{
			valeur = c.getValeur();

			for (Carte c: tab)
			{
				if (valeur= tab.getValeur())
					return true;
			}
		}

		return false;
	}
}