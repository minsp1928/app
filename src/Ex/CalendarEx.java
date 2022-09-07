package Ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
/*		object 생성법
싱클톤 디자인 패턴을 사용하고 있다
캘린더는 추상클래스이므로 new해서 만들 수 없으므로 getInstance()를 이용
1) Calendar cal = Calendar.getInstance();
2) GregorianCalendar cal = new GregorianCalendar(); */
/*
public class CalendarEx implements Runnable{
	public CalendarEx() {
		new Thread(this).start();
	}
*/
public class CalendarEx  {

	//	run();//근데 적다 말았음이러면 멀티스레드가 안되는것. 반복문이 끝나야 다음 작업이 나옴
		//직렬이 아닌 병렬로 실행되는 개념
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		new CalendarEx();//제일 먼저 실행되는것
		Calendar cal = Calendar.getInstance();  //객체 생성
		int year = cal.get(Calendar.YEAR);
		int month = (cal.get(Calendar.MONTH)+1);//월만 +1. 0부터시작하기때문에!
		int date = cal.get(Calendar.DATE);
		//캘린더에서는 get메서드를 통해 상수로하여금 데이터를 받아오는 시스템이 주요 특성이다.
		
		System.out.println("년 : "+year);
		System.out.println("월 : "+month);
		System.out.println("일 : "+date);
		//시, 분, 초를 출력해보세요.(hour, minute, second)
		//요일 출력

		
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		int week = cal.get(Calendar.DAY_OF_WEEK);//1~7까지 나옴
		int week2 = cal.get(Calendar.WEEK_OF_YEAR);//1~7까지 나옴
		
		
		String weekStr="";
		switch (week) {
		case 1: weekStr = "일";	
			break;
		case 2: weekStr = "월";	
		break;
		case 3: weekStr = "화";	
		break;
		case 4: weekStr = "수";	
		break;
		case 5: weekStr = "목";	
		break;
		case 6: weekStr = "금";	
		break;
		case 7: weekStr = "토";	
		break;
		}
		
		String[] yoil = {"일","월","화","수","목","금","토"};
		System.out.println("switch문을 사용"+hour+"시 "+minute+"분 "+second+"초 "+weekStr+"요일");
		System.out.println("배열을 활용한 "+yoil[week-1]+"요일입니다.");
		//week는1~7 배열은 0부터 시작하므로 1을 빼줘야 같은 위치의인덱스 값을 구할 수 있다
		
		//set메서드를 통해 달력을 콘솔에 출력해보자
		//---- 전혀모르니까 일단 구글에 있는것으로 해보는걸루
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int y, m, w;//년, 월, 요일
		
		do {
			System.out.print("연도를 입력하세요 : ");
			y = Integer.parseInt(br.readLine());
		}while(y<1);//이거 왜쓴건지 코드 파악 필요해 일단 적어..
		do {
			System.out.print("월을 입력하세요 : ");
			m = Integer.parseInt(br.readLine());
		}while(m<1||m>12);//입력된 숫자가 달의 범위에 없으면 반복
		cal.set(y, m-1, 1); // 년, 월, 1일의 값.(m-1을 써야한단다) 
		//여기서 cal에 내가입력한 날의 달력이 셋팅
		w = cal.get(Calendar.DAY_OF_WEEK);//가져온 날의 요일을 지정해줌
		System.out.println();
		System.out.println("\t["+y+"년"+m+"월 ]\n");
		System.out.println("  일  월  화   수   목   금  토");
		System.out.println("------------------------------");
		for(int i =1; i<w; i++)
			System.out.print("    ");
		for(int i =1; i<=cal.getActualMaximum(Calendar.DATE); i++) {
			System.out.printf("%4d",i);//%4d를 이용하면 int값왼쪽으로 추가여백4개가 나온다.
			w++;		 //반복문을 수행하며 날짜가 증가할때마다 요일도 함께 증가
			
			if(w%7==1)   //증가한 요일이 일요일이 될때마다 개행(한줄 내림 후 출력)
				System.out.println();
		}
		if(w%7!=1)
			System.out.println();
			
		System.out.println("------------------------------");
		//----
		//--선생님 예제
		year = 2022; //임의의 날을 지정하여 set으로 달력을 지정해줌
		month = 9;
		cal.set(year, month-1, 1);//month는 0부터시작하니까 입력된값은1~12
		int startDay = cal.get(Calendar.DAY_OF_WEEK);//시작일의 요일을 가져오는것(일요일:1)
		int lastDay =cal.getActualMaximum(Calendar.DATE);//설정된 최대 일을 가져옴(2월은 28/9월은 30)
		System.out.println(" ["+year+"년 "+month+"월] ");
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		for(int i = 1; i< lastDay + startDay; i++) {//lastDay + startDay :startday는 1앞의 공백까지 숫자 영역부터 달의 마지막 날의 숫자까지 
			if(i < startDay) {
				System.out.print("\t");//시작 날까지 공백을 찍는것
				continue;//반복문의 증가연산자가 끝날째까지(공백을 다 찍을때까지)
			}
			System.out.print((i - startDay + 1) + "\t");//i는 startDay까지 증가했으므로 i를 1로 사용하기위한 연산
			if( i % 7 == 0) {
				System.out.println();
			}
		}

	}	
	//---
/*
	@Override
	public void run() {
		//--스레드예제
		boolean run =false;
		while(! run) {
			try {
				Thread.sleep(1000);
				System.out.println(Calendar.getInstance().get(Calendar.SECOND));
			} catch (InterruptedException e) {e.printStackTrace();
				// TODO: handle exception
			}
		}
		//--스레드예제 끝
		
	}
*/

}
