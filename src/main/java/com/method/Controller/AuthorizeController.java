package com.method.Controller;
/*
 * @author Yu
 * @date 2019/12/7 14:30
 *
 */

import com.method.dto.AccessTokenDTO;
import com.method.dto.GithubUser;
import com.method.mapper.UserMapper;
import com.method.model.User;
import com.method.provider.GithubProvider;
import java.util.UUID;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String secret;

    @Value("${github.redirecturi}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state, HttpServletRequest request){
        AccessTokenDTO accessToken = new AccessTokenDTO();
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUri);
        accessToken.setState(state);
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(secret);
        String accessToken1 = githubProvider.getAccessToken(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken1);
        System.out.println(githubUser.getName()+" "+githubUser.getAvatarurl());
        if (githubUser!=null){
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setToken((UUID.randomUUID().toString()));
            user.setName(githubUser.getName());
            user.setHeadPic(githubUser.getAvatarurl());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(System.currentTimeMillis());
            userMapper.insert(user);
            //登陆成功,写cookie 和session
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";

        }else {
            //登陆失败,写cookie 和session
            return "redirect:/";
        }
    }
}
