package Ex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.BankApp;

public class Ex7 {

	public static void main(String[] args) {
		int kor, eng, math, total;
		double avg;
		String names[] = {"경여랑","공재현","김민아"};
		int kors[] = {30, 20, 100};
		int engs[] = {100, 70 ,100};
		int maths[] = {20, 50, 100};
		User user = Ex.BankApp.arr.get(0);
		user.getAccountNum();//0번째의 객체정보만 가져올 수 있다.
		List arr = new ArrayList(); // 용량을 주어줄 수 있지만 기본적으로 10개의 공간을 생성. 이후 그냥 추가됨. 다양하게 생성할 수 있도록..?
		for (int i =0; i<names.length; i ++) {
			//다양한 객체 추가 방법
			arr.add(names[i]);
			arr.add(kors[i]);//arr.add(new Integer(kors[i]));
			arr.add(engs[i]);//->오토박싱이 되어서 이렇게 안써도 알아서 객체화됨.
			arr.add(maths[i]);
			
		/*	Integer a = new Integer(10);//동적메모리
			int b = 10; // 정적메모리
			int sum1 = a + b; //  영역이 달라서 불가하지만 언박싱이 자동으로
			int sum2= a.intValue() + b; //
			*/
		}
		 System.out.println("======학교 성적 조회 프로그램======\n");
		 System.out.println(" 이름\t국어\t영어\t수학\t총점\t평균");
		 Iterator iterator = arr.iterator(); //반복자
		 while(iterator.hasNext()) {
			 System.out.print("\n" + iterator.next());
			 Object obj = iterator.next(); //컬렉션은 오브젝트만 받을 수 있으니까?
			 //int형인데 오토박싱으로 인티저로 객체화, 인티저가 오브젝트로 들어감
			//1. Integer kor2 = (Integer)obj; // 런타입 익셉션
			//2. Integer in = (Integer)obj; // 런타입 익셉션
			//2. kor = in.intValue();
			 kor = (Integer)obj;//오토박싱안하는것?
			// String kor2 = (String)obj; // cast 익셉션
			 eng = ((Integer) iterator.next()).intValue();
			 math = ((Integer) iterator.next());
			 total = kor + eng + math;
			 avg = (double) total/3;
			 System.out.print("\t" + kor + "\t" +eng + "\t" +math );
			 System.out.print("\t" +total + "\t" +avg);
			 
		 }
		
	}

}
