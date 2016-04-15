package thread;

public class Threaddriver {

	public static void main(String[] args) {
		
         System.out.println("Hi");
		
		Threads t1 = new Threads("thrd1",1,100);
		t1.setPriority(5);
		t1.start();

		Threads t2 = new Threads("thrd2",3,300); 
		t2.setPriority(5);
		t2.start();

		Threads t3 = new Threads("thrd3",5,500); 
		t3.setPriority(5);
		t3.start();

	}

}
