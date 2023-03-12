package com;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.constant.ViewConstant.HELLO_VIEW;

@Controller
public class Core {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return HELLO_VIEW;
    }
}
