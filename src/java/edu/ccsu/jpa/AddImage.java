/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.jpa;

import edu.ccsu.beans.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Curtis
 */
@WebServlet(name = "AddImage", urlPatterns = {"/AddImage"})
public class AddImage extends HttpServlet {

    @PersistenceUnit(unitName = "WebApplication1PU")
    private EntityManagerFactory entityManagerFactory;
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
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddImage</title>");      
            out.println("</head>");
            out.println("<body>");
            String title = "meat";//request.getParameter("title");
            File file = new File("C:/Users/Curtis/Pictures/Meat.jpg");
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for(int readNum;  (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
            } catch(IOException ex) {
                out.println(ex.getMessage());
            }
            byte[] bytes = bos.toByteArray();
            Image image = new Image();
            image.setTitle(title);
            //image.setArt(bytes);
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            
            entityManager.getTransaction().begin();
            entityManager.persist(image);
            entityManager.getTransaction().commit();
            
            out.println(image.getTitle() + " added");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e) {
            out.println(e.getMessage());
        } finally {
            out.close();
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
