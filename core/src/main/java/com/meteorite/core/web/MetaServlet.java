package com.meteorite.core.web;

import com.alibaba.fastjson.JSON;
import com.meteorite.core.meta.MetaManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wei_jc
 * @since 1.0.0
 */
public class MetaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        this.doPost(req, res);
        if (req.getRequestURI().endsWith("/meta")) {
            writeJsonObject(res, MetaManager.getMetaList());
        } else {
            String metaName = req.getRequestURI().substring(6);
            writeJsonObject(res, MetaManager.getMeta(metaName));
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String metaName = req.getParameter("name");
        writeJsonObject(res, MetaManager.getMeta(metaName));
    }

    private void writeJsonObject(HttpServletResponse res, Object obj) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(JSON.toJSONString(obj));
    }
}
