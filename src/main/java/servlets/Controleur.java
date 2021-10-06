package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controleur",urlPatterns = "/authentification/*")
public class Controleur extends HttpServlet {

    private static final String HOME = "home";
    private static final String CONNEXION = "connexion";
    private static final String DECONNEXION = "deconnexion";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String[] parties = uri.split("/");
        String cleNavigation = parties[parties.length-1];
        String destination = "/WEB-INF/pages/accueil.jsp";

        if (HOME.equals(cleNavigation)) {
            destination  = "/WEB-INF/pages/accueil.jsp";
        }


        if (DECONNEXION.equals(cleNavigation)) {
            destination  = "/WEB-INF/pages/accueil.jsp";
        }


        this.getServletContext().getRequestDispatcher(destination).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String[] parties = uri.split("/");
        String cleNavigation = parties[parties.length-1];
        String destination = "/WEB-INF/pages/accueil.jsp";

        if (CONNEXION.equals(cleNavigation)) {
            destination = "/WEB-INF/pages/menu.jsp";
        }

        this.getServletContext().getRequestDispatcher(destination).forward(req,resp);
    }
}
