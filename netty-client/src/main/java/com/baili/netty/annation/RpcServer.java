package com.baili.netty.annation;

import java.lang.annotation.*;

/**
 * @author xiaoqiangli
 * @Date 2021-12-07
 */
@Target(value = {ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RpcServer {
}
