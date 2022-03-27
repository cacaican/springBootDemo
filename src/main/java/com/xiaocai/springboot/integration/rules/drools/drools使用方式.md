#一，添加依赖
~~~xml
<dependency>
    <groupId>org.kie</groupId>
    <artifactId>kie-spring</artifactId>
<!--            <version>7.47.0.Final</version>-->
    <version>7.6.0.Final</version>-->
</dependency>
~~~
#二，创建配置类
##2.1首先创建getKieServices
KieServices 是一个线程安全的单例，充当集线器，可以访问 Kie 提供的其他服务。作为一般规则，getX() 方法只返回对另一个单例的引用，而 newX() 则创建一个新实例。
~~~
使用工厂.get方法返回单例
return KieServices.Factory.get();
~~~
##2.2创建getRuleFiles 资源 ，定义资源路径
~~~
  private Resource[] getRuleFiles() throws IOException {
        //使用 DefaultResourceLoader 创建一个新的 PathMatchingResourcePatternResolver。
        //ClassLoader 访问将通过线程上下文类加载器进行。
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + DROOL_PATH + "**/*.*");
    }
~~~
##2.3首先创建kieFileSystem
getKieServices().newKieFileSystem()根据kieService.newFileSystem方法定义一个资源系统
再使用kieFileSystem.write将给定的资源添加到这个 KieFileSystem
~~~java
//1.创建kieSystem,获取kieService ，获取droolsFile 的Resource
    @Bean
    @ConditionalOnMissingBean(KieFileSystem.class)
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        Resource[] ruleFiles = getRuleFiles();
        for (Resource ruleFile:ruleFiles ){
            //将给定的资源添加到这个 KieFileSystem
            kieFileSystem.write(ResourceFactory.newClassPathResource(DROOL_PATH + ruleFile.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }
~~~
##2.4创建KieContainer
内部首先吧所有资源build到了kieBuilder中
然后使用service..newKieContainer(kieRepository.getDefaultReleaseId());定义了一个容器
~~~java
//创建kieContainer
    //KieModule 是定义一组 KieBase 所需的所有资源的容器，例如定义其 ReleaseId 的 pom.xml、声明 KieBase 名称和配置的 kmodule.xml 文件
    // 以及可以从它们创建的所有 KieSession 以及所有自己构建 KieBases 所需的其他文件
    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws IOException {
        //KieRepository 是一个单例，充当所有可用 KieModules 的存储库，无论它们是存储在 maven 存储库中还是由用户以编程方式构建
        final KieRepository kieRepository = getKieServices().getRepository();

        kieRepository.addKieModule(new KieModule() {
            //返回标识此 KieModule 的 ReleaseId
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });

        //KieBuilder 是 KieModule 中包含的资源的构建器
        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        //构建已为其创建此 KieBuilder 的 KieModule 中包含的所有 KieBase
        kieBuilder.buildAll();

        //创建一个新的 KieContainer 包装带有给定 ReleaseId 的 KieModule
        return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }
~~~
##2.5获取kieBase
##2.6获取kieSession
##2.7创建kiePostProcessor
