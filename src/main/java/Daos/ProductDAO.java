/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Models.ProductDTO;
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
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public List<ProductDTO> getPagingByCreateDateDesc(int index, String product_typeID, String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, Product_Name, cate.Category_Name, Product_TypeID, Image, Age, Color, Gender, "
                        + "Quantity_Available, Quantity_MaleBird, Quantity_FemaleBird, Price, Discount, Quantity_Sold, Status "
                        + "from Products "
                        + "inner join Category cate on Products.CategoryID =  cate.CategoryID "
                        + "where Product_TypeID like ? and Product_Name like ? "
                        + "Order by Date_created desc "
                        + "OFFSET ? ROWS "
                        + "FETCH FIRST 9 ROWS ONLY";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + product_typeID + "%");
                stm.setString(2, "%" + searchValue + "%");
                stm.setInt(3, (index - 1) * 9);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ProductDTO result = new ProductDTO(rs.getString("ProductID"),
                            rs.getString("Product_Name"),
                            rs.getString("Category_Name"),
                            rs.getInt("Product_TypeID"),
                            rs.getString("Age"),
                            rs.getString("Color"),
                            rs.getString("Gender"),
                            rs.getString("Image"),
                            rs.getInt("Quantity_MaleBird"),
                            rs.getInt("Quantity_FemaleBird"),
                            rs.getInt("Quantity_Available"),
                            rs.getInt("Quantity_Sold"),
                            rs.getFloat("Price"),
                            rs.getFloat("Discount"),
                            rs.getString("Status"));
                    if (this.productList == null) {
                        this.productList = new ArrayList<ProductDTO>();
                    }
                    this.productList.add(result);
                }
                return this.productList;
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

    public int getNumberPage(String product_typeID, String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select count(*) "
                        + "From Products "
                        + "where Product_TypeID like ? and  Product_Name like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + product_typeID + "%");
                stm.setString(2, "%" + searchValue + "%");
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

    public List<ProductDTO> getProductByName(String product_name)
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
                        + "From Products "
                        + "Where Product_Name like ? and Product_TypeID = 1 ";
                //3.Create statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + product_name + "%");
                //4.execute-query
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    String productID = rs.getString("ProductID");
                    String product_Name = rs.getString("Product_Name");
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

                    ProductDTO dto = new ProductDTO(productID, product_Name, categoryID, age, color,
                            gender, image, quantity, price,
                            characteristics, detail, date_created, discount, status);

                    if (this.productList == null) {
                        this.productList = new ArrayList<ProductDTO>();

                    }
                    this.productList.add(dto);
                }
                return this.productList;
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

    public ProductDTO getProductByID(String id)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + "From Products "
                        + "inner join Category cate on Products.CategoryID =  cate.CategoryID "
                        + "Where ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ProductDTO product = new ProductDTO(
                            rs.getString("ProductID"),
                            rs.getString("Product_Name"),
                            rs.getInt("CategoryID"),
                            rs.getString("Category_Name"),
                            rs.getInt("Product_TypeID"),
                            rs.getString("Dad_Bird_ID"),
                            rs.getString("Mom_Bird_ID"),
                            rs.getString("Age"),
                            rs.getString("Color"),
                            rs.getString("Gender"),
                            rs.getString("Image"),
                            rs.getInt("Quantity_MaleBird"),
                            rs.getInt("Quantity_FemaleBird"),
                            rs.getInt("Quantity_Available"),
                            rs.getInt("Quantity_Sold"),
                            rs.getFloat("Price"),
                            rs.getString("Characteristics"),
                            rs.getString("Detail"),
                            rs.getDate("Date_created"),
                            rs.getFloat("Discount"),
                            rs.getString("Status"));
                    return product;
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
    
    public ProductDTO getAllQuantityByProductID(String id)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select ProductID, Quantity_Available, Quantity_AreMating, Quantity_Sold "
                        + "From Products "
                        + "Where ProductID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ProductDTO product = new ProductDTO(
                            rs.getString("ProductID"),
                            rs.getInt("Quantity_Available"),
                            rs.getInt("Quantity_AreMating"),
                            rs.getInt("Quantity_Sold"));
                    return product;
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

    public boolean updateQuantityAfterOrder(int quantity_available, int quantity_AreMating, int quantity_sold, String status, String birdID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create SQL statement string
                String sql = "Update Products "
                        + "Set Quantity_Available = ?, Quantity_AreMating = ?, Quantity_Sold = ?, Status = ? "
                        + "Where productID = ? ";
                //3. Create statement object
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity_available);
                stm.setInt(2, quantity_AreMating);
                stm.setInt(3, quantity_sold);
                stm.setString(4, status);
                stm.setString(5, birdID);
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

    public List<ProductDTO> getBirdByGender(String gender, int cateID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.productList = new ArrayList<ProductDTO>();
        try {
            //1.Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2.Create SQL statement string
                String sql = "select ProductID , Product_Name, Image, Age, Color, Gender, Quantity_Available, Quantity_AreMating, Quantity_Sold, Price, Discount "
                        + "from Products "
                        + "where Gender = ? and Age in('Adult', 'Mature', 'Young') and Quantity_Available >= 1 "
                        + "and CategoryID = ? ";
                //3.Create statement object
                stm = con.prepareStatement(sql);
                //4.execute-query
                stm.setString(1, gender);
                stm.setInt(2, cateID);
                rs = stm.executeQuery();
                //5.process
                while (rs.next()) {
                    ProductDTO dto = new ProductDTO(rs.getString("ProductID"),
                            rs.getString("Product_Name"),
                            rs.getString("Age"),
                            rs.getString("Color"),
                            rs.getString("Gender"),
                            rs.getString("Image"),
                            rs.getInt("Quantity_Available"),
                            rs.getInt("Quantity_AreMating"),
                            rs.getInt("Quantity_Sold"),
                            rs.getFloat("Price"),
                            rs.getFloat("Discount"));
                    this.productList.add(dto);
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
        return this.productList;
    }

}
