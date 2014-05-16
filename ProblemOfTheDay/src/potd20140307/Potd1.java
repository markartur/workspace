package potd20140307;

public class Potd1 {
	
	protected static int[][] matriz;
	protected static int[][] matrizclockwise;
	
	public Potd1(){
		
	}
	
	


	public static void main(String[] args) {
		
		
		matriz = new int[5][5];
		matrizclockwise = new int[5][5];
		int z=1;
		
		for(int i=0;i<5;i++)
		{
			for(int y=0;y<5;y++)
			{
				
				matriz[i][y] = z;
				z++;
				}
		}
		String output="";
		for(int i=0;i<5;i++)
		{
			for(int y=0;y<5;y++)
			{
				output=output+matriz[i][y]+" ";
			}
			
			output=output+"\n";
		}
		
		System.out.println(output);
		
		for(int i=0;i<5;i++)
		{
			for (int y=0; y<5;y++)
			{
				matrizclockwise[i][y]=matriz[4-y][i];
			}
		}
		
		System.out.println("\n");
		
		output="";
		
		for(int i=0;i<5;i++)
		{
			for(int y=0;y<5;y++)
			{
				output=output+matrizclockwise[i][y]+" ";
			}
			
			output=output+"\n";
		}
		
		System.out.println(output);

	}

}
