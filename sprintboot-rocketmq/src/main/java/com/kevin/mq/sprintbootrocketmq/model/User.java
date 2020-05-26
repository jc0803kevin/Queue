package com.kevin.mq.sprintbootrocketmq.model;

import lombok.*;

import java.io.Serializable;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2020/5/21 17:57
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private String name;

    private Integer age;

}
