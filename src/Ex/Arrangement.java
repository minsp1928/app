package Ex;

import java.util.Random;

public class Arrangement {
	public static void main(String[] args) {
		int[] array = new int [5];
		Random random = new Random();
//		int max;
//		max = array[0];
		int max = array[0];
		
		for(int i=0; i<5; i++) {//5개 랜덤 숫자를 배열에 넣어주는 반복문
			array[i] = random.nextInt(100)+1;
			System.out.printf("%d ",array[i]);
			for(int j=0; j<array.length; j++) {//반복으로 최대값을 구할 수 있을까 일단 그냥 구해보자
				if(array[j]> max) {
					max = array[j];
				}
			}
			
			
		}
		System.out.println(" ");
		System.out.println("최대값 : "+ max);
//		if (array[0]<array[1]) {//값이 이상하게 나옴
//			max = array[1];
//			System.out.println("max1 : "+ max);
//		}
//		1이 0보다 큰 경우의 if조건을 안걸어서 그런것 
//      그렇게 되면 각각 조건을 걸어줘야하므로 이럴 때 반복문을 사용하는게 더 효율적이다.
		
//		if (array[2]<array[3]) {
//			max = array[3];
//			System.out.println("max3 : "+ max);
//		}
//		if (array[3]<array[4]) {
//			max = array[4];
//			System.out.println("max4 : "+ max);
//		}

	}
	
}