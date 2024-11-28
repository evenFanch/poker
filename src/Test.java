public class Test
{
	public static void main (String[] a)
	{
		String str = "Relancer : 100";

		System.out.println(Integer.parseInt(str.substring(str.indexOf(":")+2)));
	}
}