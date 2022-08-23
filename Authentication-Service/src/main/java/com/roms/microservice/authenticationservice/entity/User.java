package com.roms.microservice.authenticationservice.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Creating model class for user logging in")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @ApiModelProperty(value = "Username of the Customer")
    private String username;
    @NonNull
    @ApiModelProperty(value = "E-Mail of the Customer")
    private String email;
    @NonNull
    @ApiModelProperty(value = "Password of the Customer")
    private String password;
    @NonNull
    @ApiModelProperty(value = "Credit card number of the Customer")
    private String creditCardNum;
    @NonNull
    @ApiModelProperty(value = "Credit limit of the Customer")
    private BigDecimal creditLimit;
    @NonNull
    private String roles;
}
