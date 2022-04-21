package sortColors;

public class col3 extends Thread {

	Doodad[] Col3 = new Doodad[300];
	
	public long stallTime=0;
	public int stallCount=0;
	
	public void run()
	{
		mergeSort(Col3, 0, Col3.length - 1);
	}
	
	
	/**
	 * Source--- Dr. Gurary.... my excellent CS professor
	 * 
	 * Mergesort uses this method to merges two sorted sub-arrays together into a
	 * sorted array
	 * 
	 * @param arr - the array that holds all the sub-arrays
	 * @param l   - start of array 1 (sometimes called "left")
	 * @param m   - end of array 1 (m+1 is start of array 2)
	 * @param r   - end of array 2 (sometimes called "right")
	 */
	void merge(Doodad arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		// Keep track of the size of left and right arrays
		int sizeL = m - l + 1;
		int sizeR = r - m;

		// Temp arrays for left and right
		Doodad L[] = new Doodad[sizeL];
		Doodad R[] = new Doodad[sizeR];

		// Copy data to temps (NOT an effective way if you want to swap doodads during swaps)
		System.arraycopy(arr, l, L, 0, sizeL);
		System.arraycopy(arr, m + 1, R, 0, sizeR);

		// Merge the two subarrays while sorting them
		// Current index of left and right temp array
		int iL = 0, iR = 0;
		int k = l; // initially the first element of left in the original array


		/*
		 * Loop while values remain in BOTH Left and Right sub-arrays. As we loop, we
		 * remove values from the smaller sub-array, so one sub-array may empty out
		 * before the other Also, in some instances, one might be larger than the other
		 * to start (odd lengths)
		 */
		while (iL < sizeL && iR < sizeR) {
			// System.out.println();
			// If the value at the current spot in the left array is smaller than on the
			// right
			if (L[iL].numColor <= R[iR].numColor) 
			{
				stall(1000000);
				stallCount++;
				arr[k] = L[iL]; // put the left array's value in that spot
				iL++; 			// move to next value in left array
			} else {
				stallCount++;
				stall(1000000);	// the right array's value at this spot is smaller
				arr[k] = R[iR]; // put the right array's value in that spot
				iR++; 			// move to next value in right array
			}
			k++;
		}

		// Every remaining element in the arrays must be larger, and should have been
		// sorted in a previous step. Simply copy them.
		while (iL < sizeL) {
			arr[k] = L[iL];
			iL++;
			k++;
			stall(1000000);
			stallCount++;
		}

		while (iR < sizeR) {
			arr[k] = R[iR];
			iR++;
			k++;
			stall(1000000);
			stallCount++;
		}

	}

	/**
	 * Source--- Dr. Gurary.... my excellent CS professor
	 * Recursively sorts the list using merges.
	 * 
	 * The general principle is to split the list into single items, then merge
	 * those into sorted pairs, then merge the pairs into sorted quads, then merge
	 * sorted quads into sorted eights, and so forth.
	 * <p>
	 * At the end of each phase, we know all the sub-arrays (pairs, quads, etc) are
	 * sorted.
	 * <p>
	 * To merge any two sorted sub-arrays with size n (so n*2 items), we need JUST n
	 * comparisons! Consider pair x1, x2, y1, y2. If x1<y1, and both sets were
	 * previously sorted (that is, we know y1<y2 and x1<x2), then we know x1 is the
	 * smallest (x1<y1 && x1<y2 && x1<x2).
	 * <p>
	 * Now we make one more comparison, x2 vs y1, which gives us the final order of
	 * this quad. If x2<y1, the order is x1<x2<y1<y2, and this quad is now ready to
	 * be merged with another quad.
	 * <p>
	 * Thus, for a list that originally starts at size N, we need N + N/2 + N/4 ...
	 * + 1 comparisons. This is roughly O(n * log n)!
	 * 
	 * Note that sometimes, because the array or subarray has odd items, one of the
	 * sub-arrays will be larger.
	 * 
	 * @param arr - array to be sorted
	 * @param l   - first index of the "left" side, generally 0 at the start
	 * @param r   - last index of the "right" side, generally the last element at
	 *            the start
	 */
	void mergeSort(Doodad arr[], int l, int r) {
		// Initially, we recurse as "deep" as possible, then fan out into pairs.
		if (l < r) { // Recurse until the left and right "pointers" touch
			// Midpoint m to split list cleanly in half
			int m = (l + r) / 2;

			/*
			 * recursively called to split the two half-lists (Note: this will continue to
			 * split the list in half recursively, until you get singles, which will get
			 * merged into pairs, and then recursion will move back up the stack) depth 1:
			 * oooo|oooo -> depth 2: oo|oo | oo|oo -> depth 3: o | o | o | o | o | o | o | o
			 * at depth 3, l=r, so do nothing, end recursion, and go back up the stack to
			 * pairs. Now, merge the pairs, then go back up the stack to quads, and so
			 * forth.
			 */
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			// Merges the half-lists, sorting them.
			merge(arr, l, m, r);
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
