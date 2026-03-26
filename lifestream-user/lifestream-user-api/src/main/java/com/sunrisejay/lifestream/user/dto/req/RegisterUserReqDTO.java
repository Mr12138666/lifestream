package com.sunrisejay.lifestream.user.dto.req;
import com.sunrisejay.framework.common.validator.MailNumber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserReqDTO {


    @NotBlank(message = "邮箱号不能为空")
    @MailNumber
    private String mail;

}