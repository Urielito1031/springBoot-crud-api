package com.firstapi.mi_primer_api_rest.Models.Dto;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto implements Serializable {

    private Long id;

    private String nickname;

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private Date birthDate;

    private Date createdAt;

}
