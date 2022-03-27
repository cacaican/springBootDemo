package com.xiaocai.springboot.integration.rules.groovy.shells

def sayHello(String name,String sex ,int age) {
    printf "你好：" + name+"性别为：" + sex+"现在你：" + age+"\n";
    name +="新2"
    return name;
}

sayHello(name,sex,age);