//package algorithms.greedy;
//
//import java.util.*;
//
//
//public class MaxMeetings {
//
//	class Pair implements Comparator<Pair>{
//		int start;
//		int end;
//		Pair ( int first , int second ) {
//			this.start = first;
//			this.end = second;
//		}
//		Pair() {
//			start = 0;
//			end = 0 ;
//		}
//		public int compare(Pair s1,Pair s2){
//			if(s1.end==s2.end)
//				return 0;
//			else if(s1.end>s2.end)
//				return 1;
//			else
//				return -1;
//		}
//	}
//
//
//	Object[] MaxMeet( int s[] , int f[] ) {
//	    int start=0 , end =0;
//	    int meetCount = 0 ;
//	    int n=s.length;
//	    Pair[] meet = new Pair[n] ;
//	    for(int i=0 ; i<n ; i++) {
//	    	meet[i].start=s[i];
//	    	meet[i].end=f[i];
//	    }
//	    Arrays.parallelSort(meet , new Pair());
//	    for(int i =0 ; i< n ; i++ ) {
//	       System.out.println(meet);
//	       if(s[i]>end) {
//	    	   end=f[i];
//	           meet.add(i);
//	           meetCount++;
//	       }
//	       if(f[i]<start) {
//	    	   start=s[i];
//	           meet.add(i);
//	           meetCount++;
//	       }
//	       int tempMeet=0,tempInd_k=0 ;
//	       for(int k = 0 ; k<meet.size() ; k++ ) {
//	           if( f[meet.get(k)]>s[i] && s[meet.get(k)]<s[i] || f[meet.get(k)]>f[i] && s[meet.get(k)]<f[i] ) {
//	        	   tempInd_k = k;
//	        	   tempMeet++ ;
//	           }
//
//	       }
//	       if(tempMeet>1) {
//	    	   continue;
//	       }
//	       else if(tempMeet==1) {
//	    	   if(f[meet.get(tempInd_k)]-s[meet.get(tempInd_k)] > f[i]-s[i]) {
//	    		   meet.remove(tempInd_k);
//	    		   if(s[i]<start) {
//	    			   start=s[i];
//	    		   }
//	    		   if(f[i]>end) {
//	    			   end=f[i];
//	    		   }
//	    		   meet.add(i);
//	    	   }
//	       }
//	    }
//	    return meet.toArray();
//	}
//
//	public static void main(String args[]) {
//		Scanner sc = new Scanner(System.in) ;
//		int t = sc.nextInt();
//		while( t-- != 0 ) {
//			int n = sc.nextInt();
//			int[] s = new int[n];
//			int[] f = new int[n];
//			for( int i = 0 ; i< n ; i++ ) {
//				s[i] = sc.nextInt();
//			}
//			for( int i = 0 ; i< n ; i++ ) {
//				f[i] = sc.nextInt();
//			}
//			Object[] temp = MaxMeet( s , f );
//			System.out.println();
//			for(int i = 0 ; i < temp.length ; i++ ) {
//				System.out.print((Integer.parseInt(temp[i].toString())+1)+ " ");
//			}
//
//		}
//
//	}
//}
