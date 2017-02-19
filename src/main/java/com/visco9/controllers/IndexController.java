package com.visco9.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by x on 2/17/2017.
 */
@Controller
public class IndexController implements ErrorController {

    @RequestMapping({"/index", "/"})
    public String index() {
        return "index";
    }

    @Override
    public String getErrorPath() { return "/error"; }

    @RequestMapping(value = "/error")
    public String error() { return "error"; }

}
