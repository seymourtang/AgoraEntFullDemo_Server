package com.md.service.utils;


public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}