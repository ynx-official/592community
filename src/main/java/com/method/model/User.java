package com.method.model;
/*
 * @author Yu
 * @date 2019/12/8 17:33
 *
 */

import java.io.Serializable;
import lombok.Data;

@Data
public class User  implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String accountId;
    private String name;
    private String avatarUrl;
    private String username;
    private String password;
    private Long gmtCreate;
    private Long gmtModify;
    private String token;
    private String bio;
}
