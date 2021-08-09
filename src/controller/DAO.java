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
      // DB ����
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

   // ��й�ȣ �������� �޼ҵ�
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

   // ȸ������ â���� ���̵� �ߺ�Ȯ�� �ϱ� --> ���̵� �ߺ� �� �ÿ� TRUE ����, �ƴϸ� FALSE����
   public boolean Join_idcheck(String inputId) {

      getConn();

      try {

         String sql = "SELECT * FROM SIC_USER WHERE ID = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, inputId);
         rs = psmt.executeQuery();

         while (rs.next()) {
            return true; // ���� �����Ͱ� ������ true ����
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return false;
   }

   // id ������ �ܽ� Ƚ�� ���� �޼ҵ�
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
   
   

   // ȸ������ --> ���� INSERT --> ȸ�������� �����ϸ� 0 �̿��� �� RETURN
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

   // �ؽ�Ʈ �ʵ忡 �Է� �� id, hint_type, hint_ans �� �Ű������� ����
   // �װɷ� ���ؼ� pw���� �������ִ� �޼ҵ�.
   // null�� �ƴ� pw���� ���� �ȴٸ� --> ��й�ȣ �缳�� update_pw �� ����� �� �ְ�.
   public String find_pw(String id, int hint_type, String hint_ans) {
      String find_pw = null;
      getConn();

      try {
         String sql = "SELECT PW FROM SIC_USER WHERE ID = ? AND HINT_TYPE = ? AND HINT_ANS = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         psmt.setInt(2, hint_type);
         psmt.setString(3, hint_ans);

         rs = psmt.executeQuery(); // select���� exeQuery
         while (rs.next()) { // �� �̻� �о�� �����Ͱ� ���� ������ �����͸� ������ ��.
            find_pw = rs.getString(1);

         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return find_pw;
   }

   // ��й�ȣ ã�� ���� �� ��й�ȣ �缳�� �޼ҵ�.
   // ���� �� 1�� ����, ���� �� 0�� ����
   public int update_Pw(String id, String re_pw) {

      int cnt = 0;
      getConn();

      try {
         // ���̵� ��й�ȣ �Է¹��� ������ sql��.
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

   // �α��� ���̵� ��й�ȣ Ȯ���ϴ� �޼ҵ�
   // �α��� ���� �� �� ����� ��ü������ RETURN, �ƴϸ� NULL ����.
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

   // �α��� ���� �� �α��� üũ �Ӽ��� t�� �ٲ�� �޼ҵ�
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

   // ȸ������ ���� â���� �� �� ��ǥ �ݾ� �Է¹��� �� -> update
   // plan�� ���� �Ϸ� ���� �ݾ� ��������.
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

   // ��Ʈ Ÿ�԰� �ڵ��� ��ȣ�� ���� id ã��
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

   
   // ��Ʈ Ÿ�� �ҷ����� �޼ҵ�
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

   // �Ѵ� �ĺ� ���� ����1 , ���� 0 ����
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

   // ȸ������ �ÿ� insert�� ȸ������ �� ������ ���� ���� â���� �� �� ��ǥ �ĺ� �Է¹���.
   // �� ��ǥ �ĺ� �Է¿� ���� oneday ���� �޼ҵ�.
   // �� �� ��ǥ �ĺ� �� ���� ���� ���� �������ش�.
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

   // db ������Ʈ �� ������Ʈ ��¥ �Է�
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

   // �ֹ��ϱ⸦ ������ �ĺ� �αװ� ������Ʈ �ǰ� ���� �Ϸ絿�� �� �ݾ��� ���ϵǴ� �޼ҵ�
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

   // ������ leftover ������ ���� �޼ҵ�
   public int last_leftover(String id) {

      getConn();
      int lo = 0;
      try {
         String sql = "select max(leftover) from sic_log where user_id = ? and use_date = TO_CHAR(SYSDATE, 'YY/MM/DD') group by use_date, user_id";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);

         rs = psmt.executeQuery();

         while (rs.next()) { // �� �̻� �о�� �����Ͱ� ���� ������ �����͸� ������ ��.
            lo = rs.getInt(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return lo;
   }

   // �ֹ��ϱ⸦ ������ �ĺ� �α� ������Ʈ, one day ������Ʈ
   // �ֹ��ϱ� ���� �� �� �޼ҵ常 ���� ��.
   // ���� one day ���� �������� --> one day ���� -�̸� �ʰ��ߴٰ� ȭ�鿡 ��Ÿ����.
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

   // ���̵� ���� plan �� �������� �޼ҵ�
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
   
   // ���̵� ���� firstplan ��������
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

   // ���̵� ���� oneday�� �������� �޼ҵ�
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

   // save ��ư�� ������ �� sic_user ���̺� ����
   // �Ű������� ���̵� ����, plan�� oneday�� ������.
   // update�� ���� plan�� ����
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

   // �α׾ƿ� ��ư ������ �� �α��� üũ �Ӽ� f �� ���� �޼ҵ�
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

   // ���ݴ뿡 �´� �Ĵ� id ������ �ִ� �޼ҵ�
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

   // �Ĵ� id�� ���� �Ĵ� ��ü ������ return ���ִ� �޼ҵ�
   public RestaurantVO getRstrInfo(String id) {
      getConn();
      RestaurantVO rstrInfo = null;
      String sql = "SELECT id, address, tel, name FROM SIC_RESTAURANT WHERE ID = ?";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         rs = psmt.executeQuery();

         while (rs.next()) { // �� �̻� �о�� �����Ͱ� ���� ������ �����͸� ������ ��.
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

   // �̺�Ʈ ���� �������� �޼ҵ�
   public EventVO getEventInfo(String id) {
      getConn();
      EventVO eventInfo = null;
      String sql = "SELECT e.RESTAURENT_ID, e.event, e.menu, e.dis_price FROM sic_event e, sic_restaurant r WHERE r.id = ?";
      try {
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, id);
         rs = psmt.executeQuery();

         while (rs.next()) { // �� �̻� �о�� �����Ͱ� ���� ������ �����͸� ������ ��.
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

   // �޴� ����Ʈ �̾Ƴ��� �޼ҵ�
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
      // DB ���� ����
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

