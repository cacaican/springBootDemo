package com.xiaocai.springboot.integration.rules.groovy.shells


def hello(String name) {
    printf "你好：" + name+"\n";
    name +="新3"
    return name;
}

hello(name);




