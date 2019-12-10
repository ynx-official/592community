package com.method.dto;
/*
 * @author Yu
 * @date 2019/12/7 15:17
 *
 */

import lombok.Data;

@Data
public class GithubUser {
    private  Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
