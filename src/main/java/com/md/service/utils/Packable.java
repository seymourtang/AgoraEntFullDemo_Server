package com.md.service.utils;


public interface Packable {
    ByteBuf marshal(ByteBuf out);
}