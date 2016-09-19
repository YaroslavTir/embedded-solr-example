package com.yaroslavtir;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ymolodkov on 17.09.16.
 */
@Controller
public class SolrController {

    @RequestMapping(name = "/*", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
