package residentevil.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import residentevil.common.annotations.PreAuthenticate;
import residentevil.domain.entities.enums.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
//An interceptor to authorize and authenticate users with custom annotation @PreAuthenticate
public class PreAuthenticationInterceptor extends HandlerInterceptorAdapter {

    private boolean isResourceHandler(Object handler) {
        return handler instanceof ResourceHttpRequestHandler;
    }

    private boolean isLoggedIn(HttpSession session) {
        if (session == null) {
            return false;
        }

        return session.getAttribute("user-id") != null;
    }

    private boolean isInRole(String role, HttpSession session) {
        if (session == null) {
            return false;
        }

        return this.isLoggedIn(session)
                && ((UserRole) session.getAttribute("user-role")).ordinal() >= UserRole.valueOf(role).ordinal();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!this.isResourceHandler(handler)) {
            HandlerMethod actionHandler = (HandlerMethod) handler;

            if (actionHandler != null) {
                PreAuthenticate actionPreAuthenticateAnnotation = actionHandler.getMethodAnnotation(PreAuthenticate.class);

                if (actionPreAuthenticateAnnotation != null) {
                    boolean shouldBeLoggedIn = actionPreAuthenticateAnnotation.loggedIn();
                    String requiredRole = actionPreAuthenticateAnnotation.inRole();

                    boolean isLoggedIn = this.isLoggedIn(request.getSession(false));
                    boolean isInRole = shouldBeLoggedIn && !this.isInRole(requiredRole, request.getSession(false));

                    if (shouldBeLoggedIn != isLoggedIn || isInRole) {
                        if (isLoggedIn && !isInRole) {
                            response.sendRedirect("/home");
                        } else {
                            response.sendRedirect("/login");
                        }

                        return false;
                    }
                }
            }
        }

        return super.preHandle(request, response, handler);
    }
}
