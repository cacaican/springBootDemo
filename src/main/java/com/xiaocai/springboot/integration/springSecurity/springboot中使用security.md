#在SpringBoot 中使用Security安全框架


##项目的pom.xml中引入SpringSecurity的核心依赖
~~~xml
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
 <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
  </dependency>
~~~
在项目中引入了SpringSecurity的依赖,默认会给你自动配置,默认的用户名是user,密码是每次启动控制台的一串字符串,每次启动这个密码都不一样。并且还会给你生成一个默认login登录页面
关于这个页面,这个页面是SpringSecurity官方提供的,但是没有人会使用这个页面来进行身份认证,都是自定义登录的页面来进行身份认证,但是处理登录逻辑是使用的这个接口

##自定义security的最基本配置
###首先我们创建一个类WebSecurityConfg来继WebSecurityConfigurerAdapter这个适配器

###实现HttpSecurity的configure方法
进行简单的配置才能够让SpringSecurity发挥强大的作用。

authorizeRequests定义那些url需要被保护,那些不需要进行保护,通常这个出来在配置的第一行,其中 antMatchers是设置路径,通常这里设置的是控制器(controller)上的请求路径，后面的permitAll是允许任何人访问,没有任何限制,上面的500、403、404我都设置的是任何人可以访问,通常对公共的页面我们都设置的permitAll来进行访问的。
下面的anyRequest意思除了以上的请求,authenticated的意思是需要身份认证,那么anyRequest和authenticated合起来就是除了上面的500、403、404 请求不需要进行身份认证,其它的请求全部都需要进行身份认证后才能访问。
and()方法类似于xml配置中的结束标签，and()方法返回的对象还是HttpSecurity,当我们配置完成一段配置就要使用and来结束
formLogin是采用的是表单认证方式,还有一个httpBasic认证,你应用应该不会用httpBasic进行认证吧,现在都是采用的formLogin来进行认证的。
csrf.disable是 关闭跨站请求伪造保护
loginProcessingUrl是配置默认的登录入口(这里强调一遍Spring security默认的处理登录接口是/login这个自带的接口)
我已经准备了404、403、500等页面


当我们访问success这个请求的时候,发现被重定向到了login页面要求进行身份认证,这正是 anyRequest和authenticated起了作用,我们在表单中输入用户user和密码(控制台随机生成的)填写后 就访问成功了

##自定义认证策略
###基于内存存储

###基于数据库