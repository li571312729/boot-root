package com.baili.netty.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RpcMessage implements Serializable {

    private static final long serialVersionUID = 430507739718447406L;
    /**
     * interface接口名
     */
    private String name;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parTypes;
    /**
     * 参数
     */
    private Object[] pars;
    /**
     * 结果值
     */
    private Object result;
}
