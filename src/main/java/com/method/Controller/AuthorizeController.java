package com.method.Controller;
/*
 * @author Yu
 * @date 2019/12/7 14:30
 *
 */

import com.method.dto.AccessTokenDTO;
import com.method.dto.GithubUser;
import com.method.provider.GithubProvider;
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

    @GetMapping("/callback")
    public  String callback(@RequestParam(name="code") String code,
                            @RequestParam(name="state") String state){
        AccessTokenDTO accessToken = new AccessTokenDTO();
        accessToken.setCode(code);
        accessToken.setRedirect_uri(redirectUri);
        accessToken.setState(state);
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(secret);
        String accessToken1 = githubProvider.getAccessToken(accessToken);
        GithubUser user = githubProvider.getUser(accessToken1);
        System.out.println(user.getName()+" "+user.getAvatarurl());
        return "index";
    }
}
