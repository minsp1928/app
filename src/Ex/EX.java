package Ex;

import java.util.Scanner;

public class EX {

	public static void main(String[] args) {
		//제어문(미션 관점에서 해보기?)
		//학생들의 정보를 담을 수 있는 변수선언
		boolean run = true;
		int studentNum = 0;
		int[] scores =null;//배열 선언
		Scanner sc = new Scanner(System.in); 
		while(run) {
			//메뉴가 반복적으로 나와야하므로 반복문안에서 사용
			 System.out.println("--------------------------------------------");	
			 System.out.println("1.학생수| 2.점수입력 | 3.점수리스트 | 4.분석 |5.종료");	
			 System.out.println("--------------------------------------------");	
			 System.out.print("선택> ");	
			 int selectNo = sc.nextInt();//스레드를 쓰다가 여기서 입력을 대기하는 상태
			 if(selectNo ==1) { //조건문을 이용 (다중if문, 스위치문등 사용)
				 //switch case ok
				 System.out.println("학생수> ");
				 studentNum = sc.nextInt();//입력받은 숫자(학생수)를 studentNum변수에 넣음.
				 //배열에는 여러값을 담을 수 있음(먼저선언(생성또한), 리사이즈불가) 인덱스를 이용
				 scores = new int[studentNum]; //몇개의 값을 배열의 공간을 만들어줌 <생성>
				 
			 } else if(selectNo == 2) {
				 for(int i=0; i<scores.length; i++) {
					 System.out.print("scores["+i+"]> ");	
					 scores[i] = sc.nextInt();
				 }
				 
			 } else if(selectNo == 3) {
				 for(int i=0; i<scores.length; i++) {
					System.out.println("scores["+ i + "]: "+scores[i]);
				 }
			 } else if(selectNo == 4) {
				 int max =0;
				 int sum = 0;
				 double avg = 0; 
				 for(int i=0; i<scores.length; i++) {
					 max = (max<scores[i])? scores[i] : max;//삼항연산자 
					 						 //참이면   : 거짓이면 재대입
					 sum += scores[i];
				 }
				 avg = (double) sum / studentNum;
				 System.out.println("최고 점수 : "+max);
				 System.out.println("평균 점수 : "+avg);
			 } else if (selectNo == 5) {
				 run = false;
				
			}
		}

	}

}