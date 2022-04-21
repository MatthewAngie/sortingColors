package sortColors;

public class col4 extends Thread{
	
	Doodad[] Col4 = new Doodad[300];
	
	public long stallTime=0;
	public int stallCount=0;
	
	public void run() // starts thread to run bubble sort
	{
		bubbleSort(Col4);
	}
	
	
	public void bubbleSort(Doodad arr[]) 
	{
		int n = arr.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (arr[j].numColor > arr[j + 1].numColor) //goes through array by doodad, and swaps x and y with next doodad
				{										// continues untill array is fully sorted.
					// swap arr[j+1] and arr[i]
					stall(250000);
					stallCount++;
					Doodad temp = arr[j];
					double tempx = arr[j].x;
					double tempy = arr[j].y;

					arr[j].y = arr[j + 1].y;
					arr[j].x = arr[j + 1].x;
					arr[j] = arr[j + 1];

					arr[j + 1].y = tempy;
					arr[j + 1].x = tempx;
					arr[j + 1] = temp;
				}
	}
	
	
	
	public void stall(long nanos) {
		long start = System.nanoTime();
		while (System.nanoTime() - start < nanos) { // do nothing
		}
		stallTime+=nanos;
		return;
	}
	
	
}
