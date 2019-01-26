import java.util.*;
import java.util.stream.IntStream;
public class ListTester {

	public static void main(String[] args) {
		String string1 = "hello!";
		int[] N = {10, 100, 1000, 10000, 100000, 1000000};
		String ins = "ms to Ins. ";
		String rmv = "ms to Rmv. ";
		String start = "start,";
		String end = "end,";
		String rnd = "rnd;";
		String rbv = "Rmv. by value";

		Random rand = new Random();
		IntStream N10 = rand.ints(100, 0, N[0] * 10);
		int[] n10array = N10.toArray();
		int[] randArray;
		long[] arrayResults = new long[7];
		long[] fileResults = new long[7];
		
		System.out.println(Arrays.toString(n10array));		
		for(int i = 0; i < N.length; i++) {
			System.out.format("N = %8d:%15s%7s%12s%12s%15s%7s%12s%12s%15s\n",N[i], ins, start, end, rnd, rmv, start, end, rnd, rbv );
			randArray = (rand.ints(N[i], 0, N[0] * 10)).toArray(); 	//initialize random array
			
			// testing ArrayList to insert in beginning
			ArrayList<Number> insertB = new ArrayList<Number>();	
			long beginning = System.currentTimeMillis();
			int k = 0;
			while(k != N[i]) {
				insertB.add(0, randArray[k]);
				k++;
			}
//			System.out.println("Insert Beginning: completed");
			arrayResults[0] = (System.currentTimeMillis() - beginning);
			
			// testing ArrayList to insert at the end 
			ArrayList<Number> insertE = new ArrayList<Number>();		
			beginning = System.currentTimeMillis();
			k = 0;
			while(k != N[i]) {
				insertE.add(randArray[k]);
				k++;
			}
			
			arrayResults[1] = (System.currentTimeMillis() - beginning);
			
//			System.out.println("Insert Ending: completed");
			
			
			// testing ArrayList to insert randomly
			ArrayList<Number> insertR = new ArrayList<Number>();		
			beginning = System.currentTimeMillis();
			k = 0;
			while(k != N[i]) {
				if(k == 0) {
					insertR.add(randArray[k]);
				}
				else {
					insertR.add(Math.abs(rand.nextInt(k)), randArray[k]);
				}
				k++;
			}
			arrayResults[2] = (System.currentTimeMillis() - beginning);
//			System.out.println("Insert Random: completed");
			
			
			
			// testing to remove Arraylist items from the beginning
			beginning = System.currentTimeMillis();
			k=0;
			while(k != N[i]) {
				insertB.remove(0);
				k++;
			}
			arrayResults[3] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove Beginning: completed");
			// testing to remove ArrayList items from the ending
			k = N[i] - 1;	
			beginning = System.currentTimeMillis();
			while(k != -1) {
				insertE.remove(k);
				k--;
			}

			arrayResults[4] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove ending: completed");
			
			
			// testing remove ArrayList items randomly
			ArrayList<Number> removeV = new ArrayList<Number>(insertR);
			beginning = System.currentTimeMillis();
			k = N[i] - 1;
			while(k != 0) {
				insertR.remove(rand.nextInt(k));
				
				k--;
			}
			arrayResults[5] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove Random: completed");
			
			// testing remove by value
			beginning = System.currentTimeMillis();
			k = N[i] - 1;
			while(k != 0) {
				removeV.remove((Object) randArray[k]);
				k--;
			}
			arrayResults[6] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove by Value: completed");
			
			
			// testing ArrayList to insert in beginning
			FileList<Number> fileInsertB = new FileList<Number>();	
			beginning = System.currentTimeMillis();
			k = 0;
			while(k != N[i]) {
				fileInsertB.add(0, randArray[k]);
				k++;
			}
//			System.out.println("Insert Beginning: completed");
			fileResults[0] = (System.currentTimeMillis() - beginning);
			
			// testing ArrayList to insert at the end 
			FileList<Number> fileInsertE = new FileList<Number>();		
			beginning = System.currentTimeMillis();
			k = 0;
			while(k != N[i]) {
				fileInsertE.add(randArray[k]);
				k++;
			}
			
			fileResults[1] = (System.currentTimeMillis() - beginning);
			
//			System.out.println("Insert Ending: completed");
			
			
			// testing ArrayList to insert randomly
			FileList<Number> fileInsertR = new FileList<Number>();		
			beginning = System.currentTimeMillis();
			k = 0;
			while(k != N[i]) {
				if(k == 0) {
					fileInsertR.add(randArray[k]);
				}
				else {
					fileInsertR.add(Math.abs(rand.nextInt(k)), randArray[k]);
				}
				k++;
			}
			fileResults[2] = (System.currentTimeMillis() - beginning);
//			System.out.println("Insert Random: completed");
			
			
			
			// testing to remove Arraylist items from the beginning
			beginning = System.currentTimeMillis();
			k=0;
			while(k != N[i]) {
				fileInsertB.remove(0);
				k++;
			}
			fileResults[3] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove Beginning: completed");
			// testing to remove ArrayList items from the ending
			k = N[i] - 1;	
			beginning = System.currentTimeMillis();
			while(k != -1) {
				fileInsertE.remove(k);
				k--;
			}

			fileResults[4] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove ending: completed");
			
			
			// testing remove ArrayList items randomly
			FileList<Number> fileRemoveV = new FileList<Number>();
			
			k = 0;
			while(k != N[i]) {
				fileRemoveV.add(randArray[k]);
				k++;
			}
			
			beginning = System.currentTimeMillis();
			k = N[i] - 1;
			while(k != 0) {
				fileInsertR.remove(rand.nextInt(k));
				
				k--;
			}
			fileResults[5] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove Random: completed");
			
			// testing remove by value
			beginning = System.currentTimeMillis();
			k = N[i] - 1;
			while(k != 0) {
				fileRemoveV.remove((Object) randArray[k]);
				k--;
			}
			fileResults[6] = (System.currentTimeMillis() - beginning);
//			System.out.println("Remove by Value: completed");
			
			
			System.out.format("ArrayList %24d%13d%11d%21d%13d%11d%15d\n", arrayResults[0], arrayResults[1], arrayResults[2], arrayResults[3], arrayResults[4], arrayResults[5], arrayResults[6]);
			System.out.format("FileList  %24d%13d%11d%21d%13d%11d%15d\n", fileResults[0], fileResults[1], fileResults[2], fileResults[3], fileResults[4], fileResults[5], fileResults[6]);
		}
		//		System.out.format("ArrayList", );
	}

}
