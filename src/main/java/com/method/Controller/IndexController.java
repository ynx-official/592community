package com.method.Controller;
/*
 * @author Yu
 * @date 2019/12/8 18:51
 *
 */

import com.method.mapper.UserMapper;
import com.method.model.User;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
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
        return  "index";
    }
}
