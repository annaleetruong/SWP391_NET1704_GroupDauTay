/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Models.BirdDTO;
import Utils.DBHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author hj
 */
public class BirdDAO implements Serializable {

    private List<BirdDTO> birdList;

    public List<BirdDTO> getBirdList() {
        return birdList;
    }

    public List<BirdDTO> getPagingByCreateDateDesc(int index)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select BirdID, Bird_Name, cate.Category_Name, Image, Age, Color, Gender, Quantity_Available, Price, Discount, Quantity_Sold, Status "
                        + "from Birds "
                        + "inner join Category cate on Birds.CategoryID =  cate.CategoryID "
                        + "where Customer_Bird = 'false' "
                        + "Order by Date_created desc "
                        + "OFFSET ? ROWS "
                        + "FETCH FIRST 9 ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setInt(1, (index - 1) * 9);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String birdID = rs.getString("BirdID");
                    String birdName = rs.getString("Bird_Name");
                    String cate_Name = rs.getString("Category_Name");
                    String image = rs.getString("Image");
                    String age = rs.getString("Age");
                    String color = rs.getString("Color");
                    String gender = rs.getString("Gender");
                    int quantity_Available = rs.getInt("Quantity_Available");
                    float price = rs.getFloat("Price");
                    float discount = rs.getFloat("Discount");
                    int quantity_Sold = rs.getInt("Quantity_Sold");
                    String status = rs.getString("Status");
                    BirdDTO result = new BirdDTO(birdID, birdName, cate_Name, age, color, gender, image, quantity_Available, quantity_Sold, price, discount, status);
                    if (this.birdList == null) {
                        this.birdList = new ArrayList<BirdDTO>();
                    }
                    this.birdList.add(result);
                }
                return this.birdList;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public int getNumberPage()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select count(*) "
                        + "From Birds "
                        + "where Customer_Bird = 'false' ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int total = rs.getInt(1);
                    int countPage = total / 9;
                    if (countPage % 9 != 0 && countPage % 2 != 0) {
                        countPage++;
                    }
                    return countPage;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public List<BirdDTO> getBirdByName(String bird_name)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement string
                String sql = "Select * "
                        + "From Birds "
                        + "Where Bird_Name like ? and Customer_Bird = 'false' ";
                //3.Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + bird_name + "%");
                //4.execute-query
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    String birdID = rs.getString("BirdID");
                    String bird_Name = rs.getString("Bird_Name");
                    int categoryID = rs.getInt("CategoryID");
                    String image = rs.getString("Image");
                    String age = rs.getString("Age");
                    String color = rs.getString("Color");
                    String gender = rs.getString("Gender");
                    int quantity = rs.getInt("Quantity_Available");
                    float price = rs.getFloat("Price");
                    float discount = rs.getFloat("Discount");
                    String characteristics = rs.getString("Characteristics");
                    String detail = rs.getString("Detail");
                    Date date_created = rs.getDate("Date_created");
                    String status = rs.getString("Status");

                    BirdDTO dto = new BirdDTO(birdID, bird_Name, categoryID, age, color,
                            gender, image, quantity, price,
                            characteristics, detail, date_created, discount, status);

                    if (this.birdList == null) {
                        this.birdList = new ArrayList<BirdDTO>();

                    }
                    this.birdList.add(dto);
                }
                return this.birdList;
            }//end connection has opened

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void showAllBird()
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement string
                String sql = "Select * " // phai co cach sau username
                        + "From Birds "
                        + "where Customer_Bird = 'false' ";
                //3.Create statement object
                stm = con.prepareStatement(sql);
                //4.execute-query
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    String birdID = rs.getString("BirdID");
                    String bird_Name = rs.getString("Bird_Name");
                    int categoryID = rs.getInt("CategoryID");
                    String image = rs.getString("Image");
                    String age = rs.getString("Age");
                    String color = rs.getString("Color");
                    String gender = rs.getString("Gender");
                    int quantity = rs.getInt("Quantity_Available");
                    float price = rs.getFloat("Price");
                    float discount = rs.getFloat("Discount");
                    String characteristics = rs.getString("Characteristics");
                    String detail = rs.getString("Detail");
                    Date date_created = rs.getDate("Date_created");
                    String status = rs.getString("Status");

                    BirdDTO dto = new BirdDTO(birdID, bird_Name, categoryID, age, color,
                            gender, image, quantity, price,
                            characteristics, detail, date_created, discount, status);

                    if (this.birdList == null) {
                        this.birdList = new ArrayList<BirdDTO>();

                    }
                    this.birdList.add(dto);
                }
            }//end connection has opened

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public BirdDTO getBirdByID(String id)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select *, cate.Category_Name "
                        + "From Birds "
                        + "inner join Category cate on Birds.CategoryID =  cate.CategoryID "
                        + "Where BirdID = ? and Customer_Bird = 'false' ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String birdID = rs.getString("BirdID");
                    String bird_name = rs.getString("Bird_Name");
                    int cateID = rs.getInt("CategoryID");
                    String cate_Name = rs.getString("Category_Name");
                    String image = rs.getString("Image");
                    String age = rs.getString("Age");
                    String color = rs.getString("Color");
                    String gender = rs.getString("Gender");
                    int quantity_Available = rs.getInt("Quantity_Available");
                    float price = rs.getFloat("Price");
                    float discount = rs.getFloat("Discount");
                    String characteristics = rs.getString("Characteristics");
                    String detail = rs.getString("Detail");
                    int quantity_Sold = rs.getInt("Quantity_Sold");
                    Date date_created = rs.getDate("Date_created");
                    String status = rs.getString("Status");

                    BirdDTO bird = new BirdDTO(birdID, bird_name, cate_Name, age, color, gender, image,
                            quantity_Available, quantity_Sold, price, discount, status);

                    return bird;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean updateQuantityAfterBuy(int quantity_available, int quantity_sold, String birdID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create SQL statement string
                String sql = "Update Birds "
                        + "Set Quantity_Available = ?, Quantity_Sold = ? "
                        + "Where BirdID = ? and Customer_Bird = 'false' ";
                //3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity_available);
                stm.setInt(2, quantity_sold);
                stm.setString(3, birdID);
                //4. Excute query
                int effectRows = stm.executeUpdate();
                //5. Process
                if (effectRows > 0) {
                    return true;
                }
            } // end of connection has opend

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
