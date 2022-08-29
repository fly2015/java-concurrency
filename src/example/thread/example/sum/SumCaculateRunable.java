package example.thread.example.sum;


public class SumCaculateRunable implements Runnable
{
	/**
	 * SumCaculateRunable will count the sum of the number from 1 to the parameter
	 * countUntil and then write the result to the console.
	 *
	 * SumCaculateRunable is the task which will be performed
	 *
	 * @author HNG
	 *
	 */
	
	private final long countUntil;
	public SumCaculateRunable(long countUntil)
	{
		this.countUntil = countUntil;
	}
	
	public void run() {
		long sum = 0;
		for (int i = 0; i <= countUntil; i++) 
		{
			sum += i;
		}
		System.out.println(sum);
	}
}
