package sortColors;

public class col2 extends Thread{

	Doodad[] Col2 = new Doodad[300];
	
	public long stallTime=0;
	public int stallCount=0;
	
	
	public void run() // starts thread which runs sort
	{
		quickSort(Col2, 0, Col2.length - 1);
	}
	
	/*
	 * https://www.baeldung.com/java-quicksort
	 * divids original list into two sub lists divided at pivot
	 */
	public void quickSort(Doodad arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);

			quickSort(arr, begin, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, end);
		}
	}

	/*
	 * https://www.baeldung.com/java-quicksort
	 */
	private int partition(Doodad arr[], int begin, int end) {
		int pivot = arr[end].numColor; //sets pivot(point of sorting) to Color identifier
		int i = (begin - 1);

		for (int j = begin; j < end; j++) //loops through the current sub array
		{
			if (arr[j].numColor <= pivot) // while searching through the array
			{							// reorders elements around the pivot, 
				i++;
				stall(1000000);
				stallCount++;
				
				double tempx = arr[i].x; //swaps x and y of doodads
				double tempy = arr[i].y;
				Doodad swapTemp = arr[i];
				arr[i].x = arr[j].x;
				arr[i].y = arr[j].y;
				arr[i] = arr[j];
				arr[j].x = tempx;
				arr[j].y = tempy;
				arr[j] = swapTemp;
				
			}
		}

		double tempx = arr[i + 1].x; // swaps x and y of start and end
		double tempy = arr[i + 1].y;
		Doodad swapTemp = arr[i + 1];
		arr[i + 1].x = arr[end].x;
		arr[i + 1].y = arr[end].y;
		arr[i + 1] = arr[end];
		arr[end].x = tempx;
		arr[end].y = tempy;
		arr[end] = swapTemp;
		
		stall(1000000);
		stallCount++;
		return i + 1; //effects both sub list on left and right of pivots
	}
	
	
	public void stall(long nanos) {
		long start = System.nanoTime();
		while (System.nanoTime() - start < nanos) { // do nothing
		}
		stallTime+=nanos;
		return;
	}
	
}
