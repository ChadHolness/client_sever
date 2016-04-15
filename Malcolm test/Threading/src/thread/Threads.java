package thread;

public class Threads extends Thread  {
	
	private String name;
	private int increment;
	private int wait;
	
	Threads(String n, int i, int w){
	
		name = n;
		increment= i;
		wait = w;
	
	}
	
	public void run()
	{
		int i;
		
		System.out.println(name + " thread is now running!");
		
		for (i = 0; i < 10; i += increment)
		{
			System.out.println(name + "-" + i);
			
			try
			{
				Thread.sleep(wait);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	

}
