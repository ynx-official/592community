package com.method.Controller;
/*
 * @author Yu
 * @date 2019/12/8 18:51
 *
 */

import com.method.dto.PaginationDTO;
import com.method.mapper.UserMapper;
import com.method.model.User;
import com.method.service.QuestionService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
            Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "size",defaultValue = "1") Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies!= null &&cookies.length!=0){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user!= null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        PaginationDTO list = questionService.list(page,size);
        model.addAttribute("pagination",list);
        return  "index";
    }
}
