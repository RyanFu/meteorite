package com.meteorite.core.web.rest;

import com.alibaba.fastjson.JSON;
import com.meteorite.core.config.SystemManager;
import com.meteorite.core.ui.config.LayoutConfig;
import com.meteorite.core.ui.model.Layout;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wei_jc
 * @since 1.0.0
 */
public class LayoutRest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.endsWith("/layout")) {
            List<LayoutConfig> list = new ArrayList<>();
            list.add(SystemManager.getInstance().getLayoutConfig());
            writeJsonObject(res, list);
        }
    }

    private void writeJsonObject(HttpServletResponse res, Object obj) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().write(JSON.toJSONString(obj));
    }
}