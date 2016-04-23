package demo;

public class Perm {
	public static int[] a={1,2,3};
	public static void main(String[] argc)
	{
		swap(0,2);
		printM();
	}
	private static void swap(int i,int n)
	{
		int tmp = a[i];
		a[i] = a[n];
		a[n] = tmp;
		
	}
	public static void permProcess(int[] a,int n,int m)
	{
		
	}
	private static void printM()
	{
//		for(int b:a)
//		{
//			System.out.println("元素："+b);
//		}
		for(int i=0;i<a.length;i++)
		{
			System.out.println("元素:"+a[i]);
		}
	}
	
}
