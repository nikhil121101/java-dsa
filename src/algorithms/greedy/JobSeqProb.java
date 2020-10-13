package algorithms.greedy;

import java.util.*; 
class pairProfit implements Comparator<pairProfit>{
	int index;
	int profit;
	pairProfit() {

	}
	pairProfit(int index , int profit) {
		this.profit = profit;
		this.index = index;
	}
	public int compare(pairProfit p1 , pairProfit p2) {
		return p1.profit - p2.profit;
	}
}

public class JobSeqProb {

	static boolean isFits(int deadline[] , boolean fixed[] , int curDeadline) {
		for(int i = curDeadline - 1 ; i >= 0 ; i--) {
			if(!fixed[i]) {
				return fixed[i] = true;
			}
		}
		return false;
	}

	static int[] maxProfit (pairProfit profit[] , int deadLine[] , int n ) {
		int jobCount = 0;
		int maxProfit = 0;
		int maxDeadline = 0;
		for(int i = 0 ; i < n ; i++) {
			if(deadLine[i] > maxDeadline) {
				maxDeadline = deadLine[i];
			}
		}
		//i am afucking noob who cant type faster than this because i fdont realise what i am typing
		boolean fixed[] = new boolean[maxDeadline];
		Arrays.fill(fixed , false);
		Arrays.parallelSort(profit , new pairProfit());
		for(int i = n - 1 ; i >= 0 ; i--) {
			//System.out.println("deadLine[" + "profit[" + i + "].index = " + profit[i].index + "] = ");
				if(isFits(deadLine , fixed , deadLine[profit[i].index])) {
					//System.out.println("condition true");
					jobCount++;
					maxProfit += profit[i].profit;
				}
		}
//		int maxDeadline = 0;
//		for(int i = 0 ; i < n ; i++) {
//			if(deadLine[i] > maxDeadline) {
//				maxDeadline = deadLine[i];
//			}
//		}
//		int curDeadline = 1;
//		boolean fixed[] = new boolean[n];
//		Arrays.fill(fixed , false);
//		while(curDeadline <= maxDeadline) {
//			int maxProfitDeadline = -1;
//			int maxProfitDeadlineInd = -1;
//			int curMaxProfit = -1;
//			int curMaxProfitInd = -1;
//			for(int i = 0 ; i < n ; i++) {
//				if(deadLine[i] == curDeadline && profit[i].profit > maxProfitDeadline && !fixed[i]) {
//					maxProfitDeadline = profit[i].profit;
//					maxProfitDeadlineInd = i;
//				}
//				if(profit[i].profit > curMaxProfit && !fixed[i]) {
//					curMaxProfit = profit[i].profit;
//					curMaxProfitInd = i;
//				}
//			}
//			if(maxProfitDeadline == -1) {
//				//go with curMaxProfitInd
//				jobCount++;
//				maxProfit += curMaxProfit;
//				fixed[curMaxProfitInd] = true;
//			}
//			else {
//				//go with maxProfitDeadlineInd
//				jobCount++;
//				maxProfit += maxProfitDeadline;
//				fixed[maxProfitDeadlineInd] = true;
//			}
//			curDeadline++;
//		}
		int a[] = new int[]{ jobCount , maxProfit } ;
		return a;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in) ;
		int t = sc.nextInt();
		while( t-- != 0 ) {
			int n = sc.nextInt() ;
			pairProfit profit[] = new pairProfit[n];
			int deadline[] = new int[n];
			for( int i = 0 ; i < n ; i++ ) {
				int index = sc.nextInt() ;
				deadline[i] = sc.nextInt() ;
				int tempProfit = sc.nextInt() ;
				profit[i] = new pairProfit(index - 1 , tempProfit);
			}
			int a[] = maxProfit(profit , deadline , n ) ;
			System.out.print( a[0] + " " + a[1] ) ;
			System.out.println() ;
			
		}
		
	}

}
/*
2
4
1 4 20 2 1 10 3 1 40 4 1 30
5
1 2 100 2 1 19 3 2 27 4 1 25 5 1 15
 */