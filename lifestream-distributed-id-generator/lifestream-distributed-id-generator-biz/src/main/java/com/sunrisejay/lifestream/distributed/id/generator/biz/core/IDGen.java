package com.sunrisejay.lifestream.distributed.id.generator.biz.core;


import com.sunrisejay.lifestream.distributed.id.generator.biz.core.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
