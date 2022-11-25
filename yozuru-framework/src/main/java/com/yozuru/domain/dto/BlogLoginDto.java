package com.yozuru.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Yozuru
 */
@Data
@NoArgsConstructor
public class BlogLoginDto {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
