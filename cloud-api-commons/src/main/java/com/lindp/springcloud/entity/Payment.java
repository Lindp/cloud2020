package com.lindp.springcloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lindp
 * @since 2020-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String serial;


}
