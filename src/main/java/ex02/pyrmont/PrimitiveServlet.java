package ex02.pyrmont;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author mazhiqiang
 * @date 14-3-11.
 */
public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("primary servlet init!!!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service method is invoked!!!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("primary servlet destroyed!");
    }
}
