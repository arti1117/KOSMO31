package com.kosmo.test.control;

import java.util.List;
import java.util.Locale;
 
import javax.inject.Inject;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.kosmo.test.dto.HelloVO;
import com.kosmo.test.service.HelloService;
 
/**
 * Handles requests for the application home page.
 */
@Controller
public class HelloController {
    
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    
    @Inject
    private HelloService service;
    
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws Exception{
 
        logger.info("home");
        
        List<HelloVO> memberList = service.selectMember();
        
        model.addAttribute("memberList", memberList);
 
        return "test/home";
    }
    
}
 
