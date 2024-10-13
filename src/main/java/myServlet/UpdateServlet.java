/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package myServlet;

import common.DatabaseUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //b1.Lấy yêu cầu
            String id = request.getParameter("id");
            String uname = request.getParameter("uname");
            String upass = request.getParameter("upass");
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            //b2.Xu ly yeu cầu và phản hồi kết quả
            Connection conn=DatabaseUtil.getConnection();
            PreparedStatement ps = null;

            try {
           
            //3.Tạo đối tượng thi hành truy vấn
                ps = conn.prepareStatement("update users set name=?, password=?,email=?, country=? where id=?");
            //truyen gia tri tham so cho SQL
                ps.setString(1, uname);
                ps.setString(2, upass);
                ps.setString(3, email);
                ps.setString(4, country);
                ps.setInt(5, Integer.parseInt(id));
            //4.Thi hành truy vấn
                int kq = ps.executeUpdate();
            //5.Xu ly ket qua tra ve
                if (kq > 0) {
                    out.println("<h2>Cập nhật user thành công</h2>");
                } else {
                    out.println("<h2>Cập nhật user thất bại</h2>");
                }
            //6.dong ket noi
                conn.close();
            } catch (Exception e) {
                out.println("<h2>Cập nhật user thất bại</h2>");
            }
            //Chèn nội dung của ViewServlet trong kết quả phản hồi
            request.getRequestDispatcher("ViewServlet").include(request, response);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
