package Daos;

import Models.AccountDTO;
import Utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements Serializable {

    public AccountDTO checkExistEmail(String email) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select AccountID, FullName, RoleName "
                        + "From Account "
                        + "inner join Roles on Account.RoleID = Roles.RoleID "
                        + "Where Email like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + email + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String AccountID = rs.getString("AccountID");
                    String FullName = rs.getString("FullName");
                    String roleName = rs.getString("RoleName");
                    AccountDTO account = new AccountDTO(AccountID, FullName, roleName);
                    return account;
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

    public AccountDTO checkExistFaceBook(String accountID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select AccountID, FullName, RoleName "
                        + "From Account "
                        + "inner join Roles on Account.RoleID = Roles.RoleID "
                        + "Where AccountID like ? and Status = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + accountID + "%");
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String AccountID = rs.getString("AccountID");
                    String FullName = rs.getString("FullName");
                    String roleName = rs.getString("RoleName");
                    AccountDTO account = new AccountDTO(AccountID, FullName, roleName);
                    return account;
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

    public String createAccountID() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select MAX(CAST(SUBSTRING(AccountID,2,LEN(AccountID)) AS INT)) as 'AccountID'  "
                        + "From Account "
                        + "Where AccountID like ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "A" + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int AccountIDMax = rs.getInt("AccountID");
                    if (AccountIDMax == 0) {
                        return "A01";
                    } else {
                        int num = AccountIDMax + 1;
                        String newOrderID;
                        if (num <= 9) {
                            newOrderID = "A0";
                        } else {
                            newOrderID = "A";
                        }

                        return newOrderID.concat(String.valueOf(num));
                    }
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public boolean createAccount(AccountDTO account) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Account ( "
                        + "AccountID, Password, FullName, Email, Date_created, CreateBy, RoleID, Status "
                        + ") values ( "
                        + "?, ?, ?, ?, ?, ?, ?, ? "
                        + ") ";
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getAccountID());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setString(4, account.getEmail());
                stm.setDate(5, account.getDate_created());
                stm.setString(6, account.getCreateBy());
                stm.setInt(7, account.getRoleID());
                stm.setBoolean(8, true);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
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

    public boolean updatePasswordByEmail(String email, String password)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Account "
                        + "Set Password = ? "
                        + "Where Email = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, email);
                int effectRows = stm.executeUpdate();
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

    public AccountDTO getAccountByEmail(String email) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO account = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select AccountID, FullName, RoleName, Password "
                        + "From Account "
                        + "inner join Roles on Account.RoleID = Roles.RoleID "
                        + "Where Email = ? and Status = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setBoolean(2, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String AccountID = rs.getString("AccountID");
                    String FullName = rs.getString("FullName");
                    String roleName = rs.getString("RoleName");
                    String passWord = rs.getString("Password");
                    return account = new AccountDTO(AccountID, FullName, roleName, passWord);
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

    public boolean updatePasswordByAccountID(String id, String password)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Account "
                        + "Set Password = ? "
                        + "Where AccountID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, id);
                int effectRows = stm.executeUpdate();
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

    public AccountDTO getAccountByID(String ID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO account = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select acc.AccountID, acc.FullName, acc.Email "
                        + "from Account acc inner join Roles rol on "
                        + "acc.RoleID = rol.RoleID "
                        + "where acc.AccountID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, ID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String AccountID = rs.getString("AccountID");
                    String FullName = rs.getString("FullName");
                    String Email = rs.getString("Email");
                    return account = new AccountDTO(AccountID, FullName, Email);
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

}
