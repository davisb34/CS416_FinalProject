/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author Danjo34
 */
@WebServlet(urlPatterns = {"/imgServlet"})
public class imgServlet extends HttpServlet 

{
    
     @Resource(name = "jdbc/HW3DB")
    DataSource dataSource;

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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    /////////Converting IMAGE into byte array////////////////////////////////////////////////////////////////
        try 
        {
                File file = new File("C:/Users/Danjo34/Documents/WebApplication1/src/java/cynthianic0e21.png");
      // File file = new File("C:/Users/Danjo34/Documents/WebApplication1/src/java/cyn.png");
                out.println(file.length());
                 FileInputStream fis = new FileInputStream(file);
                 ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 byte[] buf = new byte[1024];
                try {
                      for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);     
                System.out.println("read " + readNum + " bytes,");
            }
                    } catch (IOException ex) {
                               }
                byte[] bytes = bos.toByteArray();
     /////////////////////////////////////////////////////////////////////////////////
     ////////////////////////// STORING IMAGE IN DATABASE //////////////////////////////////////////////////////
     try
     {
         Connection connection = dataSource.getConnection();
         String sql = "INSERT INTO images VALUES (? , ?)";
         PreparedStatement imageSQL = connection.prepareStatement(sql);
         imageSQL.setInt(1, 3);
         imageSQL.setBytes(2, bytes);
         int yes = imageSQL.executeUpdate();
         System.out.println(yes);
               if (yes == 1)
             {
               out.println("it works");
              }
                  imageSQL.close();
                  connection.close();
      } 
            catch (SQLException e) 
            {
            out.println("This is what happened : " + e.getMessage());
             }
//////////////////////////////////////////////////////////////////
///////////////////////   RETRIEVE IMAGE FROM DATABASE ///////////////////////////////////////////
      byte [] byteImg;
      Connection data = dataSource.getConnection();
      String sql = "SELECT image From images WHERE id = ?";
      PreparedStatement ps = data.prepareStatement(sql);
      ps.setInt(1, 34);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) 
       {
         byteImg = rs.getBytes(1);
                
       }  
     File output = new File("C:/Users/Danjo34/Documents/WebApplication1/src/java/cyn.png");
     FileOutputStream fos = new FileOutputStream(output);
     fos.write(bytes);
                
        }
        catch (Exception e)
        {
            out.println(e.getMessage());
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