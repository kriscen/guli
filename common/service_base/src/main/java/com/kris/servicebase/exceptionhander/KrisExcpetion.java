package com.kris.servicebase.exceptionhander;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//生成无参构造
@AllArgsConstructor//生成有参构造
public class KrisExcpetion extends RuntimeException {

    private Integer code;

    private String msg;

}
