package sortColors;

public class col1 extends Thread{
	
	public long stallTime=0;
	public int stallCount=0;
	public Doodad[] Col1 = new Doodad[300];
	
	public void run() // starts this thread to sort
	{
		HeapSort();
	}
	
	/*
	 * source https://www.geeksforgeeks.org/heap-sort/
	 */
	public void HeapSort() {
	
		int n = Col1.length; //n= column length

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
		{
			heapify(Col1, n, i);
			stall(2500000);
			stallCount++;
			
		}
		// One by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			// Move current root to end
			
			
			
			double tempx = Col1[0].x; //swaps x and y
			double tempy = Col1[0].y;
			Doodad temp = Col1[0];
			Col1[0].x = Col1[i].x;
			Col1[0].y = Col1[i].y;
			Col1[0] = Col1[i];
			Col1[i].x = tempx;
			Col1[i].y = tempy;
			Col1[i] = temp;

			// call max heapify on the reduced heap
			heapify(Col1, i, 0);
			
		}
	}

	/*
	 * source https://www.geeksforgeeks.org/heap-sort/ 
	 * To heapify a subtree rooted
	 * with node i which is an index in arr[]. n is size of heap
	 */
	void heapify(Doodad arr[], int n, int i) {
		int largest = i; // sets largest known as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		
		// If left child is larger than root
		if (l < n && arr[l].numColor > arr[largest].numColor)
		{
			largest = l; //sets largest to index l
		}
		stall(1000000);
		stallCount++;
		
		// If right child is larger than largest so far
		if (r < n && arr[r].numColor > arr[largest].numColor)
		{
			largest = r; //sets largest to index r
		}
		stall(1000000);
		stallCount++;
		
		// If largest is not root
		if (largest != i) {

			double swapx = arr[i].x;	// swaps the largest and root, so the root in the new largest
			double swapy = arr[i].y;
			Doodad swap = arr[i];
			arr[i].x = arr[largest].x;
			arr[i].y = arr[largest].y;
			arr[i] = arr[largest];
			arr[largest].x = swapx;
			arr[largest].y = swapy;
			arr[largest] = swap;

			// Recursively calls heapify on the current sub-tree
			heapify(arr, n, largest);

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
