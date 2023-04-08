package com.dotcat.servlet;

import com.dotcat.model.Product;
import com.dotcat.service.ProductService;
import com.dotcat.service.impl.ProductServiceImpl;
import com.dotcat.util.RestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = RestUtil.getFromJson(req, Product.class);
        int status = productService.create(product);
        resp.setStatus(status);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String respJson = RestUtil.toJson(productService.getById(Long.parseLong(id)));
        resp.getWriter().write(respJson);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = RestUtil.getFromJson(req, Product.class);
        int status = productService.update(product);
        resp.setStatus(status);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int status = productService.deleteById(Long.parseLong(id));
        resp.setStatus(status);
    }
}
