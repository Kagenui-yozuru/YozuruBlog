package com.yozuru.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class BlogLoginDto {
    private String userName;
    private String password;
}
