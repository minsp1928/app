
package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import app.Account;
import app.User;

public class BankDaoImpl implements BankDao{
	static BankDao bd = new BankDaoImpl();
	static Map<Integer, Object> arrMap = new HashMap<Integer, Object>();	
	static List<Account> accounts;
	static List<User> userinfos;
	static User user;
	//static Account[] accounts = new Account[50];
	//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	//	int balance = 20000;//잔고
	//	int accountNum; //계좌번호를 넣을 변수? 배열?  	
		//User user = new User(); -> 여기서만 하면 여기서만 사용됨 놉!
	private static int index = 0;

	@Override
	public User idpwCheck() {//로그인 체크
		Scanner sc = new Scanner(System.in);
		String id, pw;
		boolean check = true;
		while(check) {
			System.out.println("|========================================|");
			System.out.println("|                 ✧로그인✧                |");
			System.out.println("|========================================|");
			System.out.print("| 아이디를 입력해주세요 : ");
			id = sc.next();
			System.out.print("| 패스워드를 입력해주세요 : ");
			pw = sc.next();
			System.out.println("|========================================|");
			System.out.println("");
			//id 혹은 pw가 다르면 반복문이 다시 돌아라
			
			PreparedStatement pstmt = null;
			Connection conn = DBAction.getInstance().getConnection();
			ResultSet rs = null;
			String sql ="select * from user where id= ? and pw = ?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2,pw);
				rs = pstmt.executeQuery();
				
				
				if(rs.next()) {
					user = new User().setId(rs.getString("id"))
								     .setPw(rs.getString("pw"))
								     .setName(rs.getString("name"))
								     .setJoindate(rs.getDate("joindate"))
								     .setStatus(rs.getInt("status"));
				//	System.out.println("id : "+user.getId()+"pw : "+user.getPw());
					System.out.println(user.getName()+"님 환영합니다.");		     
					check = false;
					break;
					}
				System.out.println("|========================================|");
				System.out.println("|       <아이디 혹은 비밀번호가 다릅니다>       |");
				System.out.println("|========================================|");
			
				System.out.println("");
				user = new User().setId("");
				return user;
//				continue;
			} catch (Exception e) {
				System.out.println("BankDaoImpl idpwCheck Exception->");
				e.printStackTrace();
			}finally {
				try {
					if (rs != null)rs.close();
					if (pstmt != null)pstmt.close();
				}catch (Exception e) {
					System.out.println(" allAccount ");
					e.printStackTrace();
				}
			}
			return user;
			
			/*
				for(int i=0; i<BankApp.arr.size(); i++) {//아이디, 패스워드 체크
					if(id.equals(BankApp.arr.get(i).getId()) && pw.equals(BankApp.arr.get(i).getPw())){
						System.out.println("id : "+BankApp.arr.get(i).getId()+" pw : "+BankApp.arr.get(i).getPw());
						System.out.println(BankApp.arr.get(i).getName()+"님 환영합니다.");
						check = false;
						break;
						
					}/*else if(!id.equals(BankApp.arr.get(i).getId()) || !pw.equals(BankApp.arr.get(i).getPw())){
						//이건 그냥 의미가 있나
						// check = true; 의미없음
					}*/
			
		// 메서드로 만들어서 (기능)을 호출하는것으로.
	}
		return user;

		
		
	
	}//로그인 체크 끝-----------------------------
	
	@Override
	public int join() throws initDepositAmountException {//회원가입
		
		Scanner sc = new Scanner(System.in);
		
		String name = null;
		String id = null;
		String pw = null;
		Calendar cal = Calendar.getInstance();
		String year = Integer.toString(cal.get(Calendar.YEAR)); 
		String mon  = Integer.toString((cal.get(Calendar.MONTH)+1));
		if (cal.get(Calendar.MONTH)+1 < 10) {
			mon = 0+ Integer.toString((cal.get(Calendar.MONTH)+1));		
		}
		String date = Integer.toString(cal.get(Calendar.DATE));
		String joindateStr = year+"-"+mon+"-"+date; 
		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-mm-dd");
		Date joindate;
		int status = 1;//회원
		System.out.println("|========================================|");
		System.out.println("|                ✧회원가입✧                ");
		System.out.println("|========================================|");

			System.out.print("| 이름 : ");
			name = sc.next();
			boolean idchk=true;
			while(idchk) {
				System.out.print("| 아이디 :");
				id = sc.next();
				User userIdchk = bd.selectId(id);//아이디 중복검사
				if (userIdchk !=null ) {
					System.out.println("|========================================|");
					System.out.println("| <중복된 아이디입니다>");
					System.out.println("|========================================|");
					System.out.println("");
					continue;
				
				}else {
					System.out.println("|========================================|");
					System.out.println("| <사용가능한 아이디입니다>");
					System.out.println("|========================================|");
					System.out.println("");
				
					break;
				}
			}
			
			boolean check = true;
			while(check) {
				System.out.println("");
				System.out.print("| 비밀번호 :");
				pw = sc.next();
				System.out.print("| 비밀번호 재확인 :");
				String pwChk = sc.next();
				System.out.println("|========================================|");
				if(pw.equals(pwChk)) {
//					System.out.println("<비밀번호 확인>");
					break;
				}else {
					System.out.println("| <비밀번호가 다릅니다>");
				}
			}
	
		
		joindate = java.sql.Date.valueOf(joindateStr);
		System.out.println();
		PreparedStatement pstmt = null;
		Connection conn = DBAction.getInstance().getConnection();
	//	System.out.println("bankseviceImpl 회원가입");
		String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
		int result=0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setDate(4, joindate);
			pstmt.setInt(5, status);
			
			result = pstmt.executeUpdate();
			
			user = new User().setId(id).setPw(pw).setName(name)
					.setJoindate(joindate).setStatus(status);
			
			
		} catch (Exception e) {
			System.out.println("bankDaoImpl join -?>");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)pstmt.close();
				//if (conn != conn)pstmt.close();
			}catch (Exception e) {
				System.out.println("bankDaoImpl join close-?>");
				e.printStackTrace();
			}
		}
		
	//	BankApp.arr.add(new User(accountNum,name,id,pw,balance,joindate));
		System.out.println("| "+name+"님 가입을 환영합니다! ");
		System.out.println("| 가입일 : "+joindateStr);
		System.out.println("|========================================|");
		System.out.println("");
	/*
		for(int i = 0; i<BankApp.arr.size(); i++) {
			arrMap.put(i, BankApp.arr);//유저 정보가 담긴 객체를 list에 넣고 또 map에 넣음.
	
		}
		*/
		 return result;
		
	}//회원가입 끝 -------------
	
	@Override
	public void openAccount() {//계좌개설
		Scanner sc = new Scanner(System.in);
		String id = user.getId();
//		System.out.println("openAccount id = "+id);
		String name = user.getName();
		int balance = 0;
		int accnum1 = (int)((Math.random()*99999)+1);
		int accnum2 = (int)((Math.random()*999999)+1);
		String accountNum=10+"-"+accnum1+"-"+accnum2;
		String accountPw = "";
		
		Calendar cal = Calendar.getInstance();
		String year = Integer.toString(cal.get(Calendar.YEAR)); 
		String mon  = Integer.toString((cal.get(Calendar.MONTH)+1));
		if (cal.get(Calendar.MONTH)+1 < 10) {
			mon = 0+ Integer.toString((cal.get(Calendar.MONTH)+1));		
		}
		String date = Integer.toString(cal.get(Calendar.DATE));
		String opendateStr = year+"-"+mon+"-"+date; 
		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy-mm-dd");
		Date opendate;
		opendate = java.sql.Date.valueOf(opendateStr);
		System.out.println("|========================================|");
		System.out.println("|               ✧신규계좌생성✧              ");
		System.out.println("|========================================|");
		System.out.print("| 계좌비밀번호 : ");
		accountPw = sc.next();
		System.out.print("| 초기입금액   : ");
		try {
//			balance = 0;
			balance = sc.nextInt();
			
		} catch (Exception e) {
			if(balance>0) {
				System.out.println("| "+balance+"원이 입금되었습니다.");
			}else if(balance<=0){
				try {
					throw new initDepositAmountException(
							
							"| "+"0원 이상의 금액을 입력해 주세요");
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}				
			}
		}//catch end
		PreparedStatement pstmt = null;
		Connection conn = DBAction.getInstance().getConnection();
		String sql = "insert into account values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, accountNum);
			pstmt.setString(3, accountPw);
			pstmt.setInt(4, balance);
			pstmt.setDate(5, opendate);
			
			pstmt.executeUpdate();
			Account account = new Account().setId(id).setAccountNum(accountNum)
									.setAccountPw(accountPw).setBalance(balance).setOpendate(opendate);
		} catch (Exception e) {
			System.out.println("bankDaoImpl openAccount -?>");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)pstmt.close();
			//	if (conn != null)conn.close();
			} catch (Exception e2) {
				System.out.println("bankDaoImpl openAccount -?>");
				e2.printStackTrace();
			}
		}

		System.out.println("| "+name+"님의 계좌번호는 "+accountNum+"입니다");
		System.out.println("| 계좌 생성일: "+opendate);
		System.out.println("|========================================|");
		System.out.println("");
	}
	
	@Override
	public void adminLogin() { //관리자로그인 - adminDB.properties
		Map<String, String> map = new HashMap<>();
		Properties properties = getAdminInfo();
		String adminID = properties.getProperty("adminId");//admin
		String adminPW = properties.getProperty("adminPw");//admin
		map.put(adminID, adminPW);
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("|========================================|");
			System.out.println("|               ✧관리자로그인✧              ");
			System.out.println("|========================================|");
			System.out.print("| 관리자아이디를 입력해주세요 : ");
			String id = sc.next();
			System.out.print("| 관리자패스워드를 입력해주세요 : ");
			String pw = sc.next();
			if(map.containsKey(id)) {//containsKey는 boolean형태. key값이 존재하는지 아닌지를 알 수 있다.
				if(map.get(id).equals(pw)) {
					System.out.println("|========================================|");
					System.out.println("|        ✧관리자로 로그인이 되었습니다✧        |");
					System.out.println("|========================================|");
					break;
				}else System.out.println("|           <비밀번호를 확인해주세요>          ");
				break;
			}//else System.out.println("|           <아이디를 확인해주세요>          ");
			break;
		}
		
	}//관리자로그인 끝
	
	public static Properties getAdminInfo() {//프로퍼티안의 관리자 정보가져오기
		//properties 클래스를 사용하여 설정파일을 제어
		Properties properties =null;
		try {
			properties = new Properties();
			String path = BankDaoImpl.class.getResource("adminDB.properties").getPath();
			//class.getResource를 이용하여 해당 클래스의 절대경로를 getpath를 이용하여 파일 객체의 경로를 반환한다.
			path =URLDecoder.decode(path,"UTF-8");
			//대소문자, 숫자, 밑줄을 제외한 url에 있는 문자를 코드화하는 인코딩 결과를 디코딩 = 텍스트화
			properties.load(new FileReader(path));
			//load라는 메서드를 통해 프로퍼티 파일을 읽어옴
			
		} catch (Exception e) { e.printStackTrace();
			System.out.println("에러"+e.getMessage());
		}
		return properties;
		
	}//관리자정보 가져오기 끝

	@Override
	public boolean deposit() {//예금
		Scanner sc = new Scanner(System.in);
		boolean check = true;
//		User user = bd.idpwCheck();
		String id = user.getId();
		
		int deposit =0;
		while(check) {
			System.out.println("|========================================|");
			System.out.println("|                 ✧ 예금 ✧                ");
			System.out.println("|========================================|");
			System.out.print("| 계좌번호를 입력해주세요 : ");
			String accountNum = sc.next();
			System.out.print("| 계좌비밀번호를 입력해 주세요 : ");
			String accountPw = sc.next();
			System.out.println("|========================================|");
			
			Account account = bd.selectAccount(accountNum, accountPw);
			java.util.Date openDate = account.getOpendate();
			int balance = account.getBalance();
		//	System.out.println("balance -> "+balance);
		//	System.out.println("balance account -> "+account.getAccountNum());
			
			if(account.getAccountNum() != null) {
				System.out.print("| 입금할 금액을 입력해주세요: ");
				try {
				    deposit = sc.nextInt();
				//    System.out.println("deposit -> "+deposit);
				    
					balance += deposit;
				//	System.out.println("balance += deposit -> "+balance);
					
					if(deposit>0) {
						Connection conn = DBAction.getInstance().getConnection();
//						PreparedStatement pstmt = null;
						Statement stmt = null;
						String sql="update account set balance="+balance+" where accountnum="+"'"+accountNum+"'";
						try {
							stmt=conn.createStatement();
							stmt.executeUpdate(sql);
						} 
						catch (Exception e3) {
							System.out.println("bankDaoImpl deposit -?>");
							e3.printStackTrace();
						} finally {
							try {
								if (stmt != null)stmt.close();
							//	if (conn != null)conn.close();
							} catch (Exception e2) {
								System.out.println("bankDaoImpl deposit -?>");
								e2.printStackTrace();
							}
						}//finally
						System.out.println("|========================================|");
						System.out.println("|                ✧ 입금완료 ✧              ");
						System.out.println("| "+deposit+"원이 입금되었습니다      ");
						System.out.println("| 잔액은 " + balance + "입니다  ");
						System.out.println("|========================================|");
					}else if(deposit>balance || deposit<0){
						try {
							throw new initDepositAmountException(
									
									"| "+"0원 이상의 금액을 입력해 주세요");
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
						}				
					}
				} catch (Exception e) {
					System.out.println("오류");
				}
					
			
			break;
			}else {
				System.out.println("| 올바른 계좌정보를 입력해 주세요");
				System.out.println("|========================================|");

				break;
			}
		}
		
		return true;


	}//예금 끝

	@Override
	public boolean withDraw() {//출금


		Scanner sc = new Scanner(System.in);
		boolean check = true;
//		User user = bd.idpwCheck();
		String id = user.getId();
		int withDraw =0;
		while(check) {
			System.out.println("|========================================|");
			System.out.println("|                 ✧ 출금 ✧               ");
			System.out.println("|========================================|");
			  System.out.print("| 계좌번호를 입력해주세요 : ");
			String accountNum = sc.next();
			System.out.print("| 계좌비밀번호를 입력해 주세요 : ");
			String accountPw = sc.next();
			
			Account account = bd.selectAccount(accountNum, accountPw);
			
			java.util.Date openDate = account.getOpendate();
			int balance = account.getBalance();
			
			if(account.getAccountNum() != null) {
				System.out.println("| 현재 잔고는 "+balance+"원 입니다.");
				System.out.print("| 출금할 금액을 입력해주세요: ");
				try {
					withDraw = sc.nextInt();
				  //  System.out.println("withDraw -> "+withDraw);
				    
					if(withDraw<=balance) {
						balance -= withDraw;
					//	System.out.println("balance -= withDraw -> "+balance);
						Connection conn = DBAction.getInstance().getConnection();
//						PreparedStatement pstmt = null;
						Statement stmt = null;
						String sql="update account set balance="+balance+" where accountnum="+"'"+accountNum+"'";
						try {
							stmt=conn.createStatement();
							stmt.executeUpdate(sql);
						} 
						catch (Exception e3) {
							System.out.println("bankDaoImpl deposit -?>");
							e3.printStackTrace();
						} finally {
							try {
								if (stmt != null)stmt.close();
							//	if (conn != null)conn.close();
							} catch (Exception e2) {
								System.out.println("bankDaoImpl deposit -?>");
								e2.printStackTrace();
							}
						}//finally
						
						System.out.println("| "+withDraw+"원이 출금되었습니다 ");
						System.out.println("| 잔액은 " + balance + "입니다  ");
						System.out.println("|========================================|");
						System.out.println("|               ✧ 출금완료 ✧               ");
						System.out.println("|========================================|");
						break;
					}else if(withDraw>balance ){
						//System.out.println("|잔액이 부족합니다.");
						try {
							throw new initDepositAmountException(
									
									"| "+"잔액이 부족합니다 ");
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
						}				
					}else if (withDraw<0) {
						try {
							throw new initDepositAmountException(
									
									"| "+"0원 이상의 숫자를 입력해주세요.");
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
						}				
						break;
					}
				} catch (Exception e) {
					System.out.println("출금 "+e.getMessage());
					
						
				}
					}
					else {
						System.out.println("| 올바른 계좌정보를 입력해 주세요");
						break;
					}
			
			
		}
		
		return true;
		///////////////////////
		

	}//출금 끝
	
	
	@Override
	
	public int Balance() {//잔액확인
		Scanner sc = new Scanner(System.in);
		boolean check = true;
	//	User user = bd.idpwCheck();
		String id = user.getId();
		String name = user.getName();
		while(check) {
			System.out.println("|========================================|");
			System.out.println("|                 ✧잔액확인✧               ");
			System.out.println("|========================================|");
			
			System.out.print("| 계좌번호를 입력해주세요 : ");
			String accountNum = sc.next();
			System.out.print("| 계좌비밀번호를 입력해 주세요 : ");
			String accountPw = sc.next();
			
			//System.out.println(" 입력한 accountNum  : "+accountNum);
			
			Account accountNumberChk = bd.selectAccount(accountNum,accountPw);//계좌번호 확인 메서드
			
			int balnce = accountNumberChk.getBalance();
			
			if(accountNumberChk.getAccountNum() == null) {
				System.out.println("");
				System.out.println("|========================================|");
				System.out.println("|           올바른 정보를 입력해주세요         | ");
				System.out.println("|========================================|");
				System.out.println("");
				break;
			}else {
				System.out.println("|========================================|");
				System.out.println("| "+name+"님의 잔액은 "+balnce+"원 입니다    ");
				System.out.println("|========================================|");
				
				break;

			}

		}
		return 0;
			
	}//
					
	@Override
	public void allAccount() throws IOException {//전체계좌목록 조회 및 파일 저장
		//일반 아이디일때 조회 가능
		//if(BankApp.arr.)
	//	User user = bd.idpwCheck();
		File file = new File("C:/MINBank/userinfo.txt");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw,true);
		
		String id = user.getId();
		
		accounts = new ArrayList<>();
		System.out.println("|========================================|");
		System.out.println("|                 ✧계좌목록✧               ");
		System.out.println("|========================================|");
		
		//System.out.println("id -> "+id);
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select * from account where id=?";
		Account account = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				String accountNum = rs.getString("accountNum");
				String accountPw = rs.getString("accountPw");
				int balance = rs.getInt("balance");
				Date opendate = rs.getDate("opendate");
				
				Account account2 = new Account().setId(id)
						   .setAccountNum(accountNum)
						   .setAccountPw(accountPw)
						   .setBalance(balance)
						   .setOpendate(opendate);
				accounts.add(account2);
				
//				accounts.add(new Account().setId(id)
//						   .setAccountNum(accountNum)
//						   .setAccountPw(accountPw)
//						   .setBalance(balance)
//						   .setOpendate(opendate));
//				account = new Account(id, accountNum, accountPw, balance, opendate);
			//	System.out.println(" id  = "+id + " accountNum ="+ accountNum);
				//System.out.println(" accountPw  = "+accountPw + " balance ="+ balance);
				//System.out.println(" opendate  = "+opendate + " account ="+ account);
			
			}
		//	Account accountChk = bd.accountCheck(id);
			//String str="";
			if(accounts.size()>0) {

				for(int i = 0; i<accounts.size(); i++) {
					
				//	System.out.println("------------------>나오는 지 체크"+str);
					System.out.println("| 계좌번호    : "+accounts.get(i).getAccountNum());
					System.out.println("| 계좌비밀번호 : "+accounts.get(i).getAccountPw());
					System.out.println("| 잔      액 : "+accounts.get(i).getBalance()+"원");
					System.out.println("| 계좌생성일   : "+accounts.get(i).getOpendate());
					System.out.println("|========================================|");
				/*
				 str = "| 아이디    : "+accounts.get(i).getId()
							+"\t| 계좌번호 : "+accounts.get(i).getAccountNum()
							+"\t| 계좌비밀번호 : "+accounts.get(i).getAccountPw()
							+"\t| 잔      액 : "+accounts.get(i).getBalance()
							+"\t|      --------------------      |";
					pw.println(str);
				 				
				 */
				}
			}else {
				System.out.println("");
				System.out.println("|========================================|");
				System.out.println("|            계좌정보가 존재하지않습니다       ");
				System.out.println("|========================================|");
				System.out.println("");
			
			}
		

					
//				for(Account ac : accounts) {
//					System.out.println("| 계좌번호    : "+ac.getAccountNum());
//					System.out.println("| 계좌비밀번호 : "+ac.getAccountPw());
//					System.out.println("| 잔      액 : "+ac.getBalance());
//					System.out.println("|      --------------------      |");
//
//				}
		
			
		} catch (Exception e) {
			System.out.println(" allAccount ");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
			//	if (conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(" allAccount ");
				e.printStackTrace();
			}
		}
		
		/*
		if(BankApp.arr.size()>0) {
			for(int i = 0; i<BankApp.arr.size(); i++) {
				System.out.println("이름 : "+BankApp.arr.get(i).getName()
									+"\t계좌번호 : "+BankApp.arr.get(i).getAccountNum()
									+"\t가입일  :"+BankApp.arr.get(i).getJoindate());
			}
		}else {
			System.out.println("계좌목록이 없습니다.");
		}
	*/
		
	}

	@Override
	public void allUserAdmin() {//관리자용 사용자정보, 계좌목록 조회 /////////////////
		System.out.println();
		System.out.println();
		List<User> userinfo = new ArrayList<>();
		accounts = new ArrayList<>();
		
	
		String name="";
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select * from account a, user u where a.id= u.id order by a.id; ";
		Account account = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		//	User user = new User();
			
			
			while(rs.next()) {
				String accountNum = rs.getString("accountNum");
				String accountPw = rs.getString("accountPw");
				int balance = rs.getInt("balance");
				Date opendate = rs.getDate("opendate");
				String id = rs.getString("id");
				
				
				
				accounts.add(new Account().setId(id)
						   .setAccountNum(accountNum)
						   .setAccountPw(accountPw)
						   .setBalance(balance)
						   .setOpendate(opendate));
			
		
			}//while end
			
			userinfo = bd.allUserinfoAdmin();
		
		//	Account accountChk = bd.accountCheck(id);
			System.out.println("|========================================|");
			System.out.println("|                ✧사용자목록✧              ");
			System.out.println("|========================================|");
			for(int j = 0; j<userinfo.size(); j++) {
				System.out.println("| "+userinfos.get(j).getName()+"님  ");
				System.out.println("| 아 이 디 : "+userinfos.get(j).getId());
			//	System.out.println("| 비밀번호 : "+userinfos.get(j).getPw());//비밀번호는 보안상 x, 계좌개수를 넣었다면 좋았겠다..
				System.out.println("| 가 입 일 : "+userinfos.get(j).getJoindate());
				System.out.println("|========================================|");
				System.out.println("");
			}
			System.out.println("|========================================|");
			System.out.println("|                ✧계좌 목록✧              ");
			System.out.println("|========================================|");
			if(accounts.size()>0) {
			
				for(int i = 0; i<accounts.size(); i++) {
					System.out.println("| "+accounts.get(i).getId()+"님  ");
					System.out.println("| 계좌번호    : "+accounts.get(i).getAccountNum());
					System.out.println("| 계좌비밀번호 : "+accounts.get(i).getAccountPw());
					System.out.println("| 잔      액 : "+accounts.get(i).getBalance());
					System.out.println("| 생  성  일  : "+accounts.get(i).getOpendate());
					System.out.println("|========================================|");
					System.out.println("");
				
				}
				bd.fileInput();
			}else {
				System.out.println("|========================================|");
				System.out.println("|                                        |");
				System.out.println("|계좌정보가 존재하지않습니다. 신규계좌를 개설해주세요|");
				System.out.println("|                                        |");
				System.out.println("|========================================|");
			}
		}catch (Exception e) {
			System.out.println("bankDaoImpl allUserAdmin -?>");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)pstmt.close();
			//	if (conn != null)conn.close();
			} catch (Exception e2) {
				System.out.println("bankDaoImpl allUserAdmin -?>");
				e2.printStackTrace();
			}
		}
		

					
//				for(Account ac : accounts) {
//					System.out.println("| 계좌번호    : "+ac.getAccountNum());
//					System.out.println("| 계좌비밀번호 : "+ac.getAccountPw());
//					System.out.println("| 잔      액 : "+ac.getBalance());
//					System.out.println("|      --------------------      |");
//
//				}
		
		
		
		/*
		//관리자 아이디일때 조회, 삭제 가능	
		System.out.println("-----------");
		System.out.println("사용자 목록");
		System.out.println("-----------");
		if(BankApp.arr.size()>0) {
			for(int i = 0; i<BankApp.arr.size(); i++) {
				System.out.println("[ 이름 : "+BankApp.arr.get(i).getName()
									+"\t아이디  : "+BankApp.arr.get(i).getId()
									+"\t계좌번호 : "+BankApp.arr.get(i).getAccountNum()
									+"\t잔   액 :"+BankApp.arr.get(i).getBalance()
									+"\t가입일  :"+BankApp.arr.get(i).getJoindate()+"] ");
			}
		}else {
			System.out.println("사용자가 없습니다.");
		}*/
	
	}

	@Override
	public User selectId(String id) {//아이디 중복 검사
		Statement stmt = null;
		Connection conn = DBAction.getInstance().getConnection();
		ResultSet rs = null;
		String sql = "select id from user where id='"+id+"'";
		//User user = new User();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				user = new User().setId(rs.getString("id"));
				//중복된 값이 있는경우
								
			}
			else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("selectId ->");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			
			} catch (Exception e2) {
				System.out.println("selectId close ->");
				e2.printStackTrace();
			}
		}
		
		return user;
	}

	@Override
	public Account selectAccount(String accountNum,String accountPw) {//계좌번호 확인
		//여기서 해야할것이 무엇인가
		//계좌번호와 비밀번호를 입력해, select 해서 account객체에 값이 있는지 확인해라.
		Account account = new Account();
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from account where accountNum=? and accountPw=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,accountNum);
			pstmt.setString(2,accountPw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				account = new Account().setId(rs.getString("id"))
									   .setAccountNum(rs.getString("accountNum"))
									   .setAccountPw(rs.getString("accountPw"))
									   .setBalance(rs.getInt("balance"))
									   .setOpendate(rs.getDate("opendate"));
			}
		} catch (Exception e) {
			System.out.println("bankDaoImpl selectAccount ->?");
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			//	if(conn!=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return account;
	}
	
	public List<User> allUserinfoAdmin() {//관리자용 사용자 정보
		userinfos = new ArrayList<>();
		
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select * from user order by name";
		Account account = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String pw = rs.getString("pw");
				Date joinDate = rs.getDate("joinDate");
				
				user = new User().setId(id)
						   .setName(name)
						   .setPw(pw)
						   .setJoindate(joinDate);
						   
				userinfos.add(user);
				
			}
			for(int j = 0; j<userinfos.size(); j++) {
//				System.out.println("| "+userinfos.get(j).getName()+"님  ");
//				System.out.println("| 아이디    : "+userinfos.get(j).getId());
//				System.out.println("| 비밀 번호 : "+userinfos.get(j).getPw());
//				System.out.println("| 가  입  일  : "+userinfos.get(j).getJoindate());
//				System.out.println("|      --------------------      |");
			}
			
		} catch (Exception e) {
			System.out.println(" allAccount ");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstmt != null)pstmt.close();
			//	if (conn != null)conn.close();
			}catch (Exception e) {
				System.out.println(" allAccount ");
				e.printStackTrace();
			}
		}
		return userinfos;
	
	}
	

	@Override
	public void deleteAccount() {//계좌해지
		Scanner sc =new Scanner(System.in);
		System.out.println("|========================================|");
		System.out.println("|                ✧ 계좌해지 ✧              ");
		System.out.println("|========================================|");
		
		System.out.print("| 해지할 계좌번호를 입력해 주세요 : ");
		String accountNum = sc.next();
		System.out.print("| 계좌비밀번호를 입력해 주세요 : ");
		String accountPw = sc.next();
		Account account = bd.selectAccount(accountNum, accountPw);
		if(account.getAccountNum()!=null) {
			System.out.println("| 선택된 계좌는 '"+accountNum+"'입니다.");
			Connection conn = DBAction.getInstance().getConnection();
			PreparedStatement pstmt = null;
			String sql = "delete from account where accountnum = ?";
			String accountnum = account.getAccountNum();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, accountnum);
				
				pstmt.executeUpdate();
				System.out.println("|========================================|");
				System.out.println("|          ✧ 계좌가 해지되었습니다 ✧          ");
				System.out.println("|========================================|");
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}else {
			System.out.println("| 올바른 계좌정보를 입력해 주세요");
		
		}
	}

	@Override
	public void userDelete() {//회원 탈퇴
		Scanner sc =new Scanner(System.in);
		System.out.println("|========================================|");
		System.out.println("|               ✧ 회원삭제 ✧               ");
		System.out.println("|========================================|");
		System.out.print("| 탈퇴시킬 회원의 아이디를 입력해주세요: ");
		String id = sc.next();
		user = bd.selectId(id);
		if(user.getId() !=null) {//멤버가 있다면
			System.out.print("| 관리자 비밀번호를 입력해 주세요 : ");
			String pw = sc.next();		
			Properties properties = getAdminInfo();
			String adminPW = properties.getProperty("adminPw");//admin
			if(pw.equals(adminPW)) {//관리자 비밀번호와 같다면
				Connection conn = DBAction.getInstance().getConnection();
				PreparedStatement pstmt=null;
				String userSql = "delete from user where id=?";
				String accountSql = "delete from account where id=?";
				try {
					pstmt = conn.prepareStatement(accountSql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();

					pstmt = conn.prepareStatement(userSql);
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					System.out.println("");
					System.out.println("|========================================|");
					System.out.println("|              ✧ 회원삭제 완료 ✧            ");
					System.out.println("|========================================|");
					System.out.println("");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				System.out.println("| 관리자 비밀번호가 틀렸습니다.");
			}
		}else if (user.getId() ==null){
			System.out.println("|  존재하지 않는 아이디입니다.");
		}
	
		
	}

	@Override
	public void fileInput() {//파일저장
		System.out.println("|========================================|");
		System.out.println("|            ✧ 파일에 저장중 . . . ✧        ");
		System.out.println("|            ");
		try {
			 PrintWriter pw = new PrintWriter(new FileWriter(new File("C:/MinBank/userinfo.text"), true));
	        StringBuilder sb = new StringBuilder();
	        for(int i = 0; i<userinfos.size(); i++) {
	        	/*
	        }
	        for(int i = 0; i<userinfos.size(); i++) {
	        	sb.append(userinfos.get(i).getName());
	        	sb.append(userinfos.get(i).getName());
	        	sb.append("#");
	        	sb.append(userinfos.get(i).getId());
	        	sb.append("#");
	        	sb.append(userinfos.get(i).getJoindate());
	        	sb.append("#");
	        }
	        for(int j = 0; j<accounts.size(); j++) {
	        	sb.append(accounts.get(j).getId());
	        	sb.append("#");
	        	sb.append(accounts.get(j).getAccountNum());
	        	sb.append("#");
	        	sb.append(accounts.get(j).getOpendate());
	        	sb.append("#");
	        	*/
	        	
	        	sb.append("|============== 사용자 정보 ==============|");
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append("| 이  름 : "+userinfos.get(i).getName());
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append("| 아이디 "+userinfos.get(i).getId());
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append("| 가입일"+userinfos.get(i).getJoindate());
	        	sb.append(System.getProperty("line.separator"));
	        }
	        for(int j = 0; j<accounts.size(); j++) {
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append("|============== 계좌 정보 ==============|");
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append("| 아이디 : "+accounts.get(j).getId());
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append("| 계좌번호 : "+accounts.get(j).getAccountNum());
	        	sb.append(System.getProperty("line.separator"));
	        	sb.append("| 생성일 : "+accounts.get(j).getOpendate());
	        	sb.append(System.getProperty("line.separator"));
	        	 
	        	 
	        }
	        pw.println(sb.toString());
	        pw.close();
	        System.out.println(". ˚\r\n"
	        		+ "*  * 　　　 ⊹  ˚  .    　　.\r\n"
	        		+ "⊹  ·  ✧ 　　　*    *\r\n"
	        		+ ". 　　  ⊹  ✦  　 ✵  　　　　　*\r\n"
	        		+ "* .　  ·\r\n"
	        		+ ". 　 　  *\r\n"
	        		+ "");
	    	System.out.println("|========================================|");
			System.out.println("|       ✧사용자정보문서가 저장되었습니다✧        ");
			System.out.println("|========================================|");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void remittance() {//계좌이체
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while(run) {
			System.out.println("|========================================|");
			System.out.println("|                 ✧계좌이체✧               ");
			System.out.println("|========================================|");

			System.out.print("| 본인계좌번호를 입력해주세요 : ");
			String fromAccountNum = sc.next();
			System.out.print("| 받는 분 계좌번호를 입력해주세요 : ");
			String toAccountNum = sc.next();
			
			System.out.print("| 이체하실 금액을 입력해주세요 : ");
			int money= sc.nextInt();
			
			System.out.print("| 계좌비밀번호를 입력해 주세요 : ");
			String fromAccountPw = sc.next();
			
			Account accCheck = bd.selectAccount(fromAccountNum, fromAccountPw);
			
			if(accCheck.getAccountNum() != null) {
				Account acc = bd.selectRemittanceAccount(toAccountNum);//받는 계좌존재확인
				if(acc.getAccountNum()!=null) {
					bd.withDrawRun(fromAccountNum, money);//보낸 사람 출금
					boolean runChk = bd.depositRun(toAccountNum, money);//받는 사람 입금
					
					if(runChk = false) {
						
						break;
					}else {
						System.out.println("|========================================|");
						System.out.println("|                ✧ 이체완료 ✧              ");
						System.out.println("| "+money+"원이 "+acc.getId()+"님의 계좌로 이체되었습니다 ");
						System.out.println("|========================================|");
						break;
					}
					
					
					}else {
					System.out.println("| <올바른 계좌번호를 입력해주세요>");
					break;
				}
			}else {
				System.out.println("| <올바른 계좌정보를 입력해주세요>");
				break;
			}
		}
	}
	@Override
	public Account selectRemittanceAccount(String accountNum) {//송금될 계좌확인
		//여기서 해야할것이 무엇인가
		//계좌번호와 비밀번호를 입력해, select 해서 account객체에 값이 있는지 확인해라.
		Account account = new Account();
		Connection conn = DBAction.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from account where accountNum=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,accountNum);
		
			rs = pstmt.executeQuery();
			if(rs.next()) {
				account = new Account().setId(rs.getString("id"))
									   .setAccountNum(rs.getString("accountNum"))
									   .setAccountPw(rs.getString("accountPw"))
									   .setBalance(rs.getInt("balance"))
									   .setOpendate(rs.getDate("opendate"));
			}
		} catch (Exception e) {
			System.out.println("bankDaoImpl selectAccount ->?");
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
			//	if(conn!=null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return account;
	}	

	@Override
	public boolean depositRun(String toAccountNum, int money) {//이체용 입금... 이걸 하나만쓰고 객체지향해서 사용했으면 좋았을텐데...
		boolean check = true;
		String accountNum = toAccountNum;
		int deposit = money;
		while(check) {
			Account acc =  bd.selectRemittanceAccount(accountNum);
			int balance = acc.getBalance();
//			System.out.println("balance -> "+balance);
//			System.out.println("balance account -> "+acc.getAccountNum());
			
			if(acc.getAccountNum() != null) {
				
				try {
//				    System.out.println("deposit -> "+deposit);
				    
					balance += deposit;
				//	System.out.println("balance += deposit -> "+balance);
					
					if(deposit>0) {
						Connection conn = DBAction.getInstance().getConnection();
//						PreparedStatement pstmt = null;
						Statement stmt = null;
						String sql="update account set balance="+balance+" where accountnum="+"'"+accountNum+"'";
						try {
							stmt=conn.createStatement();
							stmt.executeUpdate(sql);
						} 
						catch (Exception e3) {
							System.out.println("bankDaoImpl deposit -?>");
							e3.printStackTrace();
						} finally {
							
							try {
								if (stmt != null)stmt.close();
							//	if (conn != null)conn.close();
							} catch (Exception e2) {
								System.out.println("bankDaoImpl deposit -?>");
								e2.printStackTrace();
							}
						}//finally
					
						
					
					}else if(deposit>balance || deposit<0){
						try {
							throw new initDepositAmountException(
									
									"| <"+"0원 이상의 금액을 입력해 주세요>");
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
						}				
					}
				} catch (Exception e) {
					System.out.println("오류");
				}
					
			
			break;
			}else {
				System.out.println("| <올바른 계좌정보를 입력해 주세요>");
				System.out.println("|========================================|");
				break;
			}
		}
		
		return true;


	}//예금 끝

	@Override
	public boolean withDrawRun(String fromAccountNum, int money) {//이체용출금

		String accountNum = fromAccountNum ;
		boolean check = true;
		String id = user.getId();
		Account acc =  bd.selectRemittanceAccount(accountNum);
		int balance = acc.getBalance();
		while(check) {
			if(acc.getAccountNum() != null) {
			//	System.out.println("| 현재 잔액은 "+balance+"원 입니다.");
				
				try {
					
					if(money<=balance) {
						balance -= money;
					//	System.out.println("balance -= money -> "+balance);
						Connection conn = DBAction.getInstance().getConnection();
//						PreparedStatement pstmt = null;
						Statement stmt = null;
						String sql="update account set balance="+balance+" where accountnum="+"'"+accountNum+"'";
						try {
							stmt=conn.createStatement();
							stmt.executeUpdate(sql);
						} 
						catch (Exception e3) {
						//	System.out.println("bankDaoImpl withDrawRun -?>");
							e3.printStackTrace();
						} finally {
							try {
								if (stmt != null)stmt.close();
							//	if (conn != null)conn.close();
							} catch (Exception e2) {
						//		System.out.println("bankDaoImpl withDrawRun -?>");
								e2.printStackTrace();
							}
						}//finally
			
				
						break;
					}else if(money>balance ){
					//	System.out.println("| <잔액이 부족합니다>");
						
						try {
							throw new initDepositAmountException(
									
									"| <"+"잔액이 부족합니다>");
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
							break;
						}				
					}else if (money<0) {
						try {
							throw new initDepositAmountException(
									
									"| <"+"0원 이상의 숫자를 입력해주세요>");
							
						} catch (Exception e2) {
							System.out.println(e2.getMessage());
						}				
						break;
					}
				} catch (Exception e) {
					
						
				}
					}//end of if acc.getAccountNum() != null)
					else {
						System.out.println("| <올바른 계좌정보를 입력해 주세요>");
						System.out.println("|========================================|");
						break;
					}
			
			
		}
		
		return true;
		///////////////////////
		

	}//출금 끝
	
	
	
	//	@Override
//	public Account accountCheck(String id) {//계좌 유무 확인 -> if문으로 로직을 바꿔서 사용안하는 메서드
//		for(Account accountChk : accounts) {
//			if(accountChk.getId().equals(id)) {
//				return accountChk;
//			}
//		}
//		return null;
//	}


	
	

}//end of class