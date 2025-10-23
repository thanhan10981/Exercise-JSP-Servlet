package com.codeTeam_3.controller;

import com.codeTeam_3.dao.ProductDao;
import com.codeTeam_3.model.ProductView;
import com.codeTeam_3.web.LangUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "search", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    private final ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        String lang = LangUtil.resolveLang(req);
        if (q == null) q = "";

        List<ProductView> results = productDao.searchByName(lang, q, 100);
        req.setAttribute("products", results);
        req.setAttribute("searchQuery", q);
        req.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(req, resp);
    }
}
