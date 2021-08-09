package view;

import java.util.Calendar;

import controller.DAO;
import model.UserVO;
import oracle.jdbc.rowset.OracleCachedRowSet;

public class test {

	public static void main(String[] args) {
		DAO dao = new DAO();
//		System.out.println(dao.Join_idcheck("admin"));
//		
//		System.out.println(dao.getRstrId("5000", "10000"));
//		
//		System.out.println(dao.login("jmj","1234"));
//		
//		
		
		UserVO vo = new UserVO("jmj0731", "12345", "w" , 24, "", "", "", 200000, 1, 1, "dd", "d", "");
		
//		System.out.println(dao.Join_idcheck("admn"));
//		System.out.println(dao.insertJoin(vo));
		
//		System.out.println(dao.find_Id("Á¤¹ÎÁö", 1, "dd"));
		
//		System.out.println(dao.find_pw("jmj031", 1, "dd"));
//		System.out.println(dao.update_Pw("jmj0731", "1234"));
		
//		System.out.println(dao.login("jmj0731", "1234"));
//		System.out.println(dao.join_plan("jmj0731", 200000));
		
//		System.out.println(dao.join_plan("jmj0731", 300000));
		
//		System.out.println(dao.join_plan("jmj0731", 400000));
		
//		System.out.println(dao.order_logupdate("jmj0731", "´ë¿Õ±è¹ä", 2000));
//		System.out.println(dao.last_leftover("jmj0731"));
//		System.out.println(dao.Sysdate());
		
//		String thisDay = new java.text.SimpleDateFormat ("yyyy/MM/dd").format(new java.util.Date());
//		System.out.println(thisDay);
		
//		System.out.println(dao.order("jmj0731", "´ë¿Õ±è¹ä", 5000, 33333));
		
//		System.out.println(dao.get_plan("jmj0731"));

		
//		System.out.println(dao.save_sicuser_update("jmj0731"));

		
//		System.out.println(dao.order_logupdate("jmj0731", "´ë¿Õ±è¹ä", 3500));
		
//		System.out.println(dao.logout_loginck("jmj0731"));
	
//		System.out.println(dao.pwcheck("jmj0731"));
//		int i = 12;
//		String str = Integer.toString(i);
//		System.out.println(str);

		
//		System.out.println(dao.getRstrId(0, 5000));
		
//		System.out.println(dao.insertJoin(vo));

		System.out.println(dao.find_pw("jmj", 1, "¼ø"));
		
	}

}
