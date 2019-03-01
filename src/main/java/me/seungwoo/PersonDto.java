package me.seungwoo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Leo.
 * User: sonseungwoo
 * Date: 2019-02-26
 * Time: 22:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    @NotNull(message = "{custom.msg}")
    private String firstName;

    private String lastName;

    private int age;
}
