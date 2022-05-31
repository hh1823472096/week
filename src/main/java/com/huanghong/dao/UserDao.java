package com.huanghong.dao;

import com.huanghong.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
public class UserDao implements IUserDao{
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String gender = user.getGender();
        java.sql.Date birthday = user.getBrithdate();
        String sql = "INSERT INTO usertable(username,password,email,gender,brithdate) VALUES(?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,username);
        st.setString(2,password);
        st.setString(3,email);
        st.setLong(4, Long.parseLong(gender));
        st.setDate(5, birthday);
        if(st.executeUpdate()>0){
            return true;
        }else{
            return true;
        }
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        return 0;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        String sql="update usertable set username=?,password=?,email=?,gender=?,brithdate=? where id=?";
        long id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String gender = user.getGender();
        java.sql.Date birthday = user.getBrithdate();
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,username);
        st.setString(2,password);
        st.setString(3,email);
        st.setLong(4, Long.parseLong(gender));
        st.setDate(5,birthday);
        st.setLong(6,id);
        int result = st.executeUpdate();
        return result;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String sql = "select * from usertable where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();
        User user=null;
        if(rs.next()){
            user=new User();
            user.setId(Long.parseLong(rs.getString("id")));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(String.valueOf(rs.getInt("gender")));
            user.setBrithdate(rs.getDate("birthday"));
        }
        return user;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql = "select id,username,password,email,gender,brithdate from usertable where username=? and password=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,username);
        st.setString(2,password);
        ResultSet rs = st.executeQuery();
        User user=null;
        if(rs.next()){
            user=new User();
            user.setId(Long.parseLong(rs.getString("id")));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(String.valueOf(rs.getInt("gender")));
            user.setBrithdate(rs.getDate("birthday"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        return null;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        return null;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        return null;
    }
}