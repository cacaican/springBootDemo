package com.xiaocai.springboot.integration.rules.drools.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @description: 配置Drools的服务类，方便在Rest接口中调用。 该类负责加载具体的drl规则文件， 不再需要kmodule.xml配置文件了。
 * 参考文章： * https://www.jianshu.com/p/de5789b29927
 * @author: xiaocai
 * @time: 2022/3/27 16:08
 */
@Configuration
public class DroolsConfig {
    public static  final String DROOL_PATH="drools/";

    /*KieServices 是一个线程安全的单例，充当集线器，可以访问 Kie 提供的其他服务。作为一般规则，getX() 方法只返回对另一个单例的引用，而 newX() 则创建一个新实例。
    * */
    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }
    private Resource[] getRuleFiles() throws IOException {
        //使用 DefaultResourceLoader 创建一个新的 PathMatchingResourcePatternResolver。
        //ClassLoader 访问将通过线程上下文类加载器进行。
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + DROOL_PATH + "**/*.*");
    }

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


    @Bean
    @ConditionalOnMissingBean(KieBase.class)
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

    // 不能反复被使用，释放资源后需要重新获取。
    // @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    @ConditionalOnMissingBean(KieSession.class)
    public KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }

    @Bean
    @ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
        return new KModuleBeanFactoryPostProcessor();
    }
}
