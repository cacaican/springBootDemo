package com.xiaocai.springboot.integration.rule.groovy.shells

def sayHello(String name) {
    printf "你好：" + name+"\n";
    name +="新1"
    return name;
}

sayHello(name);