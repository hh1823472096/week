package com.huanghong.lab3;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CalServlet", urlPatterns = "/CalServlet")
public class CalServlet extends HttpServlet {
    //todo 1:create a method to add two number
    public int add(int firstVal, int secondVal) {
        return firstVal + secondVal;
    }
    //todo 2:create a method to subtract two number
    public int sub(int firstVal, int secondVal) {
        return firstVal - secondVal;
    }
    //todo 3:create a method to multiply two number
    public int mul(int firstVal, int secondVal) {
        return firstVal * secondVal;
    }
    //todo 4:create a method to divide two number
    public int div(int firstVal, int secondVal) {
        return firstVal / secondVal;
    }
    //todo 5:create a method to computeLength of a string
    public int computeLength(String str) {
        return str.length();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo 6:get all request parameters- firstValue , secondValue,name action
        String firstVal = req.getParameter("firstVal");
        String secondVal = req.getParameter("secondVal");
        String cName = req.getParameter("cName");
        String action = req.getParameter("action");
        int result = 0;
        //todo 7:use if else to determine action is add or subtarat or multiply or divide or computerLength
        //todo 8:call method add subtract ,multiply, divide or computeLength on action and get the calculated result
        if (action.equals("add")) {
            result = add(Integer.parseInt(firstVal), Integer.parseInt(secondVal));
        } else if (action.equals("subtarat")) {
            result = sub(Integer.parseInt(firstVal), Integer.parseInt(secondVal));
        } else if (action.equals("multiply")) {
            result = mul(Integer.parseInt(firstVal), Integer.parseInt(secondVal));
        } else if (action.equals("divide")) {
            result = div(Integer.parseInt(firstVal), Integer.parseInt(secondVal));
        } else if (action.equals("computeLength")) {
            result = computeLength(cName);
        }
        //todo 9:if action =add or subtract or multiply or divide
        if(action.equals("add") || action.equals("subtarat") || action.equals("multiply") || action.equals("divide")){
            //todo 10:create 3 cookie name cFirstVal,cSecondVal,cResult and set the value of cookie
            HttpSession session = req.getSession();
            session.setAttribute("cFirstVal", firstVal);
            session.setAttribute("cSecondVal", secondVal);
            session.setAttribute("cResult", result);
            //todo 11:add 3 cookie into response
            resp.addCookie(new Cookie("cFirstVal", firstVal));
            resp.addCookie(new Cookie("cSecondVal", secondVal));
            resp.addCookie(new Cookie("cResult", String.valueOf(result)));
        }
        //todo 12:if action = computerLength
        if(action.equals("computerLength")){
            //todo 13:create 2 cookies name cName,cLength and set the value
            HttpSession session = req.getSession();
            session.setAttribute("cName", cName);
            session.setAttribute("cLength", computeLength(cName));
            //todo 14:add 2 cookie into response
            resp.addCookie(new Cookie("cName", cName));
            resp.addCookie(new Cookie("cLength", String.valueOf(computeLength(cName))));
        }
        //todo 15:send redirect to cal.jsp
        resp.sendRedirect("lab3/cal.jsp");
    }
}