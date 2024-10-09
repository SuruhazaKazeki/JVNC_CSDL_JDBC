/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "SaveServlet", urlPatterns = {"/SaveServlet"})
public class SaveServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //b1. lấy thông tin
            String uName = request.getParameter("uName");
            String uPassword = request.getParameter("uPassword");
            String Email = request.getParameter("Email");
            String Country = request.getParameter("Country");
            //b2. xử lý yêu cầu
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            Connection conn;
            PreparedStatement ps;
            
            try{
                // nạp trình điều khiển
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                System.out.println("Nap ok");
                // thiết lập kết nối
                conn = DriverManager.getConnection("jdbc:sqlserver://Kazeki;databaseName=demodb","sa","suzaki705");
                System.out.println("ket noi ok");
                // Tạo đối tượng thi hành truy vấn
                ps = conn.prepareStatement("insert into users(name,password,email,country) values(?,?,?,?)");
                ps.setString(1,uName);
                ps.setString(2,uPassword);
                ps.setString(3,Email);
                ps.setString(4,Country);
                // thi hành truy vấn
                int kq = ps.executeUpdate();
                // xử lý kết quả trả về
                if(kq>0){
                    out.println("<h2> lưu thành công</h2>");
                }else{
                    out.println("<h2> lưu thất bại</h2>");
                }
                // đóng kết nối
                conn.close();
                
            }catch (Exception e){
                 System.out.println("lỗi" + e.toString());
                 System.out.println("<h2> lưu thất bại</h2>");
            }
            
            
          
        }
        // chèn nội dung của trang index.html trong trang phản hồi
            request.getRequestDispatcher("index.html").include(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
