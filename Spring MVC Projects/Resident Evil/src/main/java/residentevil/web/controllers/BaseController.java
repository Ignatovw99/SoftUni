package residentevil.web.controllers;

import org.springframework.web.servlet.ModelAndView;

abstract class BaseController {

    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/layout");
        modelAndView.addObject("view", view);
        return modelAndView;
    }

    protected ModelAndView view(String view) {
        return this.view(view, new ModelAndView());
    }

    protected ModelAndView redirect(String route) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + route);
        return modelAndView;
    }
}
