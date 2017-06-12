/**
 * Copyright 2017 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package webserver;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

/**
 * @author Eunji, Lim
 */
@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    private RequestMapping requestMapping;

    @Override
    public void init() throws ServletException {
        requestMapping = new RequestMapping();
        requestMapping.initMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String requestUri = req.getRequestURI();

        Controller controller = requestMapping.getController(requestUri);

        try {
            String returnUrl = controller.execute(req, resp);
            move(returnUrl, req, resp);
        } catch (Throwable e) {
            throw new ServletException(e.getMessage());
        }
    }

    private void move(String returnUrl, HttpServletRequest req, HttpServletResponse resp) throws
            IOException,
            ServletException {
        if (returnUrl.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            resp.sendRedirect(returnUrl.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(returnUrl);
        requestDispatcher.forward(req, resp);
    }
}
