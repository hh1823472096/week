package com.huanghong.week5;
import com.huanghong.dao.UserDao;
import com.huanghong.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(name = "LoginServlet", value = "/Login")
public class LoginServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() {
//        ServletContext context =this.getServletContext();
//        con=null;
//        String driver=context.getInitParameter("driver");
//        String url = context.getInitParameter("url");
//        String username=context.getInitParameter("username");
//        String password=context.getInitParameter("password");
//        try{
//            Class.forName(driver);
//            con = DriverManager.getConnection(url, username, password);
//            System.out.println("connection: "+con);
//        }catch (ClassNotFoundException| SQLException e){
//            e.printStackTrace();
//        }
        con = (Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao=new UserDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        PrintStream writer = null;
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        try {
            User user= userDao.findByUsernamePassword(con,username,password);
            if (user!=null){
//                Cookie c=new Cookie("sessionid",""+user.getId());
//                c.setMaxAge(10*60);response.addCookie(c);
                String rememberMe=request.getParameter("rememberMe");
                if(rememberMe!=null && rememberMe.equals("1")){
                    Cookie usernameCookie=new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie=new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie=new Cookie("cRememberMe",rememberMe);
                    usernameCookie.setMaxAge(5);
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }
                HttpSession session=request.getSession();
                System.out.println("session id-->"+session.getId());
                session.setMaxInactiveInterval(10);
                session.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);

            }else{
                request.setAttribute("message","Username or password is ERROR!!!");
                request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request,response);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String sql = "Select * from usertable where username = ? and password = ?";
//        try {
//            ps = con.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            rse = ps.executeQuery();
//            if (rse.next()){
//                //out.print("Login Success!!! <br>");
//                //out.print("Welcome,"+ username +" <br>");
//
//                request.setAttribute("id", rse.getInt("id"));
//                request.setAttribute("username", username);
//                request.setAttribute("password", password);
//                request.setAttribute("email", rse.getString("email"));
//                request.setAttribute("gender", rse.getString("gender"));
//                request.setAttribute("brithdate", rse.getString("brithdate"));
//
//                request.getRequestDispatcher("userInfo.jsp").forward(request, response);
//
//            }else{
//                request.setAttribute("message", "username or password Error!!!");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

//    @Override
//    public void destroy() {
//        if (rse!=null){
//            try {
//                rse.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (ps!=null){
//            try {
//                ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (con!=null){
//            try {
//                con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//
// }
}
