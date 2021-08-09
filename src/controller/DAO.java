package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import model.EventVO;
import model.MenuVO;
import model.RestaurantVO;
import model.RstrName;
import model.UserVO;

public class DAO {

   Connection conn = null;
   PreparedStatement psmt = null;
   ResultSet rs = null;

   private void getConn() {
      // DB 연결
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");

         String url = "jdbc:oracle:thin:@168.131.22.108:1521:xe";
         String dbid = "hr";
         String dbpw = "hr";

         conn = DriverManager.getConnection(url, dbid, dbpw);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   // 비밀번호 가져오는 메소드
   public String pwcheck(String id) {
      String pw = "";
      getConn();
      try {
         String sql = "SELECT PW FROM SIC_USER WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         rs = psmt.executeQuery();
         while (rs.next()) {
            pw = rs.getString(1);

         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return pw;
   }

   // 회원가입 창에서 아이디 중복확인 하기 --> 아이디가 중복 될 시에 TRUE 리턴, 아니면 FALSE리턴
   public boolean Join_idcheck(String inputId) {

      getConn();

      try {

         String sql = "SELECT * FROM SIC_USER WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, inputId);
         rs = psmt.executeQuery();

         while (rs.next()) {
            return true; // 들어올 데이터가 있으면 true 리턴
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return false;
   }

   // id 넣으면 외식 횟수 세는 메소드
   public int get_cntOut(String id) {
      Calendar cal = Calendar.getInstance();
      int month = cal.get(Calendar.MONTH) + 1;
      getConn();
      int numberOfOut = 0;
      String sql = "select count(*) from sic_log where user_id = ? and use_date like ?";
      String date = "19/" + Integer.toString(month) + "/__";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         psmt.setString(2, date);
         rs = psmt.executeQuery();

         while (rs.next()) {
            numberOfOut = rs.getInt(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return numberOfOut;
   }
   
   

   // 회원가입 --> 정보 INSERT --> 회원가입이 성공하면 0 이외의 수 RETURN
   public int insertJoin(UserVO vo) {

      int cnt = 0;
      getConn();

      try {

         String sql = "INSERT INTO SIC_USER(SEQ, ID,PW,SEX,AGE,HP,HINT_TYPE,HINT_ANS,NAME) VALUES(date_sequence.NEXTVAL,?,?,?,?,?,?,?,?)";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, vo.getId());
         psmt.setString(2, vo.getPw());
         psmt.setString(3, vo.getSex());
         psmt.setInt(4, vo.getAge());
         psmt.setString(5, vo.getHp());
         psmt.setInt(6, vo.getHint_type());
         psmt.setString(7, vo.getHint_ans());
         psmt.setString(8, vo.getName());
         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return cnt;
   }

   // 텍스트 필드에 입력 된 id, hint_type, hint_ans 를 매개변수로 받음
   // 그걸로 비교해서 pw값을 리턴해주는 메소드.
   // null이 아닌 pw값이 리턴 된다면 --> 비밀번호 재설정 update_pw 를 사용할 수 있게.
   public String find_pw(String id, int hint_type, String hint_ans) {
      String find_pw = null;
      getConn();

      try {
         String sql = "SELECT PW FROM SIC_USER WHERE ID = ? AND HINT_TYPE = ? AND HINT_ANS = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         psmt.setInt(2, hint_type);
         psmt.setString(3, hint_ans);

         rs = psmt.executeQuery(); // select에만 exeQuery
         while (rs.next()) { // 더 이상 읽어올 데이터가 없을 때까지 데이터를 가지고 옴.
            find_pw = rs.getString(1);

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return find_pw;
   }

   // 비밀번호 찾기 성공 시 비밀번호 재설정 메소드.
   // 성공 시 1을 리턴, 실패 시 0을 리턴
   public int update_Pw(String id, String re_pw) {

      int cnt = 0;
      getConn();

      try {
         // 아이디 비밀번호 입력받은 값으로 sql문.
         String sql = "UPDATE SIC_USER SET PW = ? WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, re_pw);
         psmt.setString(2, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return cnt;
   }

   // 로그인 아이디 비밀번호 확인하는 메소드
   // 로그인 성공 시 그 멤버의 객체정보가 RETURN, 아니면 NULL 리턴.
   public UserVO login(String id, String pw) {

      UserVO loginUser = null;
      int cnt = 0;
      logincheck_update(id);
      getConn();

      try {
         String sql = "SELECT * FROM SIC_USER WHERE ID = ? and PW = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         psmt.setString(2, pw);

         rs = psmt.executeQuery();
         while (rs.next()) {
            String idd = rs.getString(2);
            String pww = rs.getString(3);
            String sex = rs.getString(4);
            int age = rs.getInt(5);
            String create_date = rs.getString(6);
            String update_date = rs.getString(7);
            String login_check = rs.getString(8);
            int plan = rs.getInt(9);
            int oneday = rs.getInt(10);
            int hint_type = rs.getInt(11);
            String hint_ans = rs.getString(12);
            String hp = rs.getString(13);
            String name = rs.getString(14);

            loginUser = new UserVO(idd, pww, sex, age, create_date, update_date, login_check, plan, oneday,
                  hint_type, hint_ans, hp, name);

         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return loginUser;
   }

   // 로그인 성공 시 로그인 체크 속성이 t로 바뀌는 메소드
   public void logincheck_update(String id) {

      getConn();
      int cnt = 0;
      try {
         String sql = "update sic_user set login_check = ? where id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, "t");
         psmt.setString(2, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

   }

   // 회원가입 다음 창에서 한 달 목표 금액 입력받은 것 -> update
   // plan에 따른 하루 적정 금액 리턴해줌.
   public int join_plan(String id, int plan) {

      getConn();
      int cnt = 0;
      try {
         String sql = "update sic_user set plan = ? , firstplan = ? where id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, plan);
         psmt.setInt(2, plan);
         psmt.setString(3, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      cnt = join_oneday(plan, id);
      return cnt;
   }

   // 힌트 타입과 핸드폰 번호가 같은 id 찾기
   public String findId(String hp, String hintans) {

      getConn();
      String id = null;

      try {
         String sql = "SELECT ID FROM SIC_USER WHERE HP = ? AND HINT_ANS = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, hp);
         psmt.setString(2, hintans);
         rs = psmt.executeQuery();
         while (rs.next()) {
            id = rs.getString(1);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return id;
   }

   
   // 힌트 타입 불러오는 메소드
   public int hinttype_get(String hp) {

      getConn();
      int q = 0;

      try {
         String sql = "SELECT HINT_TYPE FROM SIC_USER WHERE HP = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, hp);

         rs = psmt.executeQuery();
         while (rs.next()) {
            q = rs.getInt(1);

         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return q;
   }

   // 한달 식비 수정 성공1 , 실패 0 리턴
   public int update_plan(String id, int plan) {
      getConn();
      int cnt = 0;
      try {
         String sql = "update sic_user set plan = ? where id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, plan);
         psmt.setString(2, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return cnt;
   }

   // 회원가입 시에 insert로 회원가입 다 끝내고 나서 다음 창에서 한 달 목표 식비 입력받음.
   // 그 목표 식비 입력에 따른 oneday 갱신 메소드.
   // 한 달 목표 식비를 일 수로 나눈 값을 리턴해준다.
   public int join_oneday(int plan, String id) {

      Calendar cal = Calendar.getInstance();
      getConn();
      int one = plan / (((cal.getActualMaximum(Calendar.DAY_OF_MONTH)) - (cal.get(Calendar.DAY_OF_MONTH))) + 1);
      int cnt = 0;
      try {
         String sql = "update sic_user set oneday = ? where id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, one);
         psmt.setString(2, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return one;

   }

   // db 업데이트 시 업데이트 날짜 입력
   public int update_date(String id) {
      int cnt = 0;
      getConn();
      try {
         String sql = "update update_date = sysdate where id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return cnt;
   }

   // 주문하기를 누르면 식비 로그가 업데이트 되고 오늘 하루동안 쓴 금액이 리턴되는 메소드
   public int order_logUpdate(String id, String usage, int price) {

      int cnt = 0;
      int lastleft = last_leftover(id);
      int sum = lastleft + price; // price -> total
      getConn();

      try {
         String sql = "INSERT INTO SIC_LOG (SEQ, USER_ID, USE_DATE, USAGE, PRICE, LEFTOVER) VALUES(Up_sequence.NEXTVAL,?, SYSDATE, ?, ?, ?)";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         psmt.setString(2, usage);
         psmt.setInt(3, price);
         psmt.setInt(4, sum);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return sum;
   }

   // 마지막 leftover 가지고 오는 메소드
   public int last_leftover(String id) {

      getConn();
      int lo = 0;
      try {
         String sql = "select max(leftover) from sic_log where user_id = ? and use_date = TO_CHAR(SYSDATE, 'YY/MM/DD') group by use_date, user_id";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         rs = psmt.executeQuery();

         while (rs.next()) { // 더 이상 읽어올 데이터가 없을 때까지 데이터를 가지고 옴.
            lo = rs.getInt(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return lo;
   }

   // 주문하기를 누르면 식비 로그 업데이트, one day 업데이트
   // 주문하기 누를 때 이 메소드만 쓰면 됨.
   // 남은 one day 값을 리턴해줌 --> one day 값이 -이면 초과했다고 화면에 나타내기.
   public int order(String id, int oneday) {

      int cnt = 0;
      getConn();
      try {
         String sql = "update sic_user set oneday = ? where id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, oneday);
         psmt.setString(2, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return cnt;
   }

   // 아이디 별로 plan 값 가져오는 메소드
   public int get_plan(String id) {
      getConn();
      int plan = 0;
      try {
         String sql = "SELECT PLAN FROM SIC_USER WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         rs = psmt.executeQuery();
         while (rs.next()) {
            plan = rs.getInt(1);

         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return plan;
   }
   
   // 아이디 별로 firstplan 가져오기
   public int get_firstplan(String id) {
      getConn();
      int plan = 0;
      try {
         String sql = "SELECT FIRSTPLAN FROM SIC_USER WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         rs = psmt.executeQuery();
         while (rs.next()) {
            plan = rs.getInt(1);

         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return plan;
   }

   // 아이디 별로 oneday값 가져오는 메소드
   public int get_oneday(String id) {
      getConn();
      int plan = 0;
      try {
         String sql = "SELECT ONEDAY FROM SIC_USER WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         rs = psmt.executeQuery();
         while (rs.next()) {
            plan = rs.getInt(1);

         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return plan;
   }

   // save 버튼을 눌렀을 때 sic_user 테이블 갱신
   // 매개변수로 아이디만 받음, plan과 oneday를 갱신함.
   // update한 후의 plan값 리턴
   public int save_sicuser_update(String id) {
      int plan = get_plan(id);
      int lfover = last_leftover(id);
      int cha = plan - lfover;
      int on = join_oneday(cha, id);

      getConn();

      int cnt = 0;
      try {
         String sql = "UPDATE SIC_USER SET PLAN = ? WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, cha);
         psmt.setString(2, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return cha;
   }

   // 로그아웃 버튼 눌렀을 때 로그인 체크 속성 f 로 갱신 메소드
   public int logout_loginck(String id) {
      getConn();

      int cnt = 0;
      try {
         String sql = "UPDATE SIC_USER SET LOGIN_CHECK = 'f' WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return cnt;

   }

   // 가격대에 맞는 식당 id 정보를 주는 메소드
   public ArrayList<RestaurantVO> getRstrId(int minPrice, int maxPrice) {

      ArrayList<RestaurantVO> restaurant = new ArrayList<RestaurantVO>();

      getConn();
      String sql = "select distinct r.id , r.name, r.image from sic_restaurant R, sic_menu M where r.id = m.restaurant_id and m.price in (?, ?)";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setInt(1, minPrice);
         psmt.setInt(2, maxPrice);
         rs = psmt.executeQuery();

         while (rs.next()) {

            String id = rs.getString(1);
            String name = rs.getString(2);
            String image = rs.getString(3);
            System.out.println(image);
            restaurant.add(new RestaurantVO(id, name, image));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return restaurant;
   }

   // 식당 id에 따른 식당 객체 정보를 return 해주는 메소드
   public RestaurantVO getRstrInfo(String id) {
      getConn();
      RestaurantVO rstrInfo = null;
      String sql = "SELECT id, address, tel, name FROM SIC_RESTAURANT WHERE ID = ?";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         rs = psmt.executeQuery();

         while (rs.next()) { // 더 이상 읽어올 데이터가 없을 때까지 데이터를 가지고 옴.
            String rstrID = rs.getString(1);
            String address = rs.getString(2);
            String tel = rs.getString(3);
            String name = rs.getString(4);

            rstrInfo = new RestaurantVO(rstrID, "", address, tel, "", "", "", name);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return rstrInfo;
   }

   // 이벤트 정보 가져오는 메소드
   public EventVO getEventInfo(String id) {
      getConn();
      EventVO eventInfo = null;
      String sql = "SELECT e.RESTAURENT_ID, e.event, e.menu, e.dis_price FROM sic_event e, sic_restaurant r WHERE r.id = ?";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         rs = psmt.executeQuery();

         while (rs.next()) { // 더 이상 읽어올 데이터가 없을 때까지 데이터를 가지고 옴.
            String rstrID = rs.getString(1);
            String event = rs.getString(2);
            String menu = rs.getString(3);
            String disPrice = rs.getString(4);

            eventInfo = new EventVO(rstrID, event, menu, disPrice, "");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return eventInfo;
   }

   // 메뉴 리스트 뽑아내는 메소드
   public ArrayList<MenuVO> MenuList(String id) {
      getConn();
      ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
      String sql = "select m.restaurant_id, m.menu_name, m.price, m.image from sic_menu m, sic_restaurant r where r.id = m.restaurant_id and r.id = ?";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         rs = psmt.executeQuery();

         while (rs.next()) {
            String rstrID = rs.getString(1);
            String list = rs.getString(2);
            int price = rs.getInt(3);
            String image = rs.getString(4);

            menuList.add(new MenuVO(rstrID, list, price, image));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return menuList;
   }

   public int bye(String id, String pw) {

      int cnt = 0;
      getConn();
      String sql = "DELETE FROM SIC_USER WHERE ID = ?";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return cnt;

   }

   private void close() {
      // DB 연결 해제
      try {
         if (psmt != null)
            psmt.close();
         if (conn != null)
            conn.close();
         if (rs != null)
            rs.close();
      } catch (Exception e2) {
         e2.printStackTrace();
      }
   }
   
   
      
   }

