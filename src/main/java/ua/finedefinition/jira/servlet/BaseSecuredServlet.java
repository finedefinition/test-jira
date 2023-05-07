package ua.finedefinition.jira.servlet;

import com.atlassian.sal.api.user.UserKey;
import com.atlassian.sal.api.user.UserManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseSecuredServlet extends HttpServlet {

    private final UserManager userManager;

    BaseSecuredServlet(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAdmin(req)) {
            doGetInternal(req, resp);
        } else {
            String message = "You must be an administrator to access this resource.";
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
        }
    }

    abstract void doGetInternal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    private boolean isAdmin(HttpServletRequest req) {
        UserKey userKey = userManager.getRemoteUserKey(req);
        return userKey != null && userManager.isSystemAdmin(userKey);
    }
}
