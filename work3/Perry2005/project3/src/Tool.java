import java.sql.*;
import java.util.ArrayList;

public class Tool {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/project3";
    private static final String username = "root";
    private static final String password = "2005127phz";
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,username,password);
    }
    public void addGoods(goods goods) throws SQLException {
        String sql = "INSERT INTO good (id,name,price) VALUES(?,?,?) ";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try{
            conn.setAutoCommit(false);
            pstmt.setInt(1, goods.getId());
            pstmt.setString(2, goods.getName());
            pstmt.setInt(3, goods.getPrice());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            pstmt.close();
            conn.close();
        }
    }
    public ArrayList<String> getAllGoods() throws SQLException {
        ArrayList<String> arr = new ArrayList<>();
        String sql = "SELECT name FROM good";
        Connection conn = getConnection();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            conn.setAutoCommit(false);
            while (rs.next()){
                String str = rs.getString("name");
                arr.add(str);
            }
        }catch (SQLException e){
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            conn.close();
        }

        return arr;
    }
    public void dropGoods(int id) throws SQLException {
        String sql = "DELETE FROM good WHERE id = ?";
        Connection conn = getConnection();
        try(
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            throw  new  RuntimeException(e);
        }finally {
            conn.close();
        }
        
    }
    public void updateGoods(String name ,int price, int id) throws SQLException {
        String sql = "UPDATE good SET name = ?, price = ? WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try{
            conn.setAutoCommit(false);
            pstmt.setString(1,name);
            pstmt.setInt(2,price);
            pstmt.setInt(3,id);
            pstmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            pstmt.close();
            conn.close();
        }
    }
    public void addOrders(orders order) throws SQLException {
        String sql = "INSERT INTO user_order (id,info,time,price) VALUES(?,?,?,?) ";
        Connection conn = getConnection();
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            conn.setAutoCommit(false);
            pstmt.setInt(1, order.getId());
            pstmt.setString(2, order.getName());
            pstmt.setDate(3, order.getDate());
            pstmt.setInt(4, order.getPrice());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            conn.close();
        }
    }
    public void dropOrders(int id) throws SQLException {
        String sql = "DELETE FROM user_order WHERE id = ?";
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try {
            conn.setAutoCommit(false);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            throw  new  RuntimeException(e);
        }finally {
            pstmt.close();
            conn.close();
        }

    }
    public Boolean checKGoodHaving(String name) throws SQLException {
        Tool tool = new Tool();
        ArrayList<String> arr = tool.getAllGoods();
        Boolean flag = true;
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).equals(name)){
                flag = false;
                break;
            }
        }
        return flag;
    }
    public Boolean checkOrderHavingGood(String name) throws SQLException {
        Boolean flag = true;
        String sql = "SELECT info FROM user_order";
        Connection conn = getConnection();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            conn.setAutoCommit(false);
            while (rs.next()){
                String str = rs.getString("info");
                String[] goodInfo = str.split("\\+");
                for (int i = 0; i < goodInfo.length; i++) {
                    String[] goodName = goodInfo[i].split("\\*");
                    if(goodName[1].equals(name)){
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }
    public void selectInfoGood() throws SQLException {
        //String sql1 = "CREATE INDEX idx_price_id_name on good(price,id,name) ";
        String sql = "SELECT id,name,price FROM good ORDER BY price ASC";
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        try(
            ResultSet rs = stmt.executeQuery(sql)){
            conn.setAutoCommit(false);
            while (rs.next()){
                System.out.print("id = "+rs.getInt("id"));
                System.out.print("   name = "+rs.getString("name"));
                System.out.print("   price = "+rs.getInt("price"));
                System.out.println();
            }
        }catch (SQLException e){
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            stmt.close();
            conn.close();
        }
    }
    public void selectInfoUserOrder() throws SQLException {
        //String sql1 = "CREATE INDEX idx_price_id_name on good(price,id,name) ";
        String sql = "SELECT id,info,time,price FROM user_order ORDER BY time ASC";
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        try(
                ResultSet rs = stmt.executeQuery(sql)){
            conn.setAutoCommit(false);
            while (rs.next()){
                System.out.print("id = "+rs.getInt("id"));
                System.out.print("   info = "+rs.getString("info"));
                System.out.print("   price = "+rs.getInt("price"));
                System.out.print("   date = "+rs.getDate("time"));
                System.out.println();
            }
        }catch (SQLException e){
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            stmt.close();
            conn.close();
        }
    }
    public Boolean checkPriceLegal(int price){
        boolean flag = true;
        if (price<=0){
            flag = false;
        }
        return flag;
    }
}
