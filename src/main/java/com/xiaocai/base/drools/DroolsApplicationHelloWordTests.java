package com.xiaocai.base.drools;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/1/25 17:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsApplicationHelloWordTests {

    @Test
    public void test() {
        EntityInfoVo actualInfoVo = new EntityInfoVo(210137L, false, "规则", "描述");
        final KieBase kieBase = getKieSession("rule1", "(a>30||<10)");
        KieSession ksession = kieBase.newKieSession();
        ksession.setGlobal("ruleResult", actualInfoVo);
        Map<String, Object> map = new HashMap<>();
        map.put("a", 50);
        ksession.insert(map);
        ksession.fireAllRules();
        //移除session状态
        ksession.destroy();
        ksession.dispose();
        System.out.println("是否命中：" + actualInfoVo.getHit());
    }


    /**
     * @param ruleName 规则名称
     * @param rule     规则信息， 如 a>0||b<30
     * @return
     */
    private KieBase getKieSession(String ruleName, String rule) {
        String myRule = "import java.util.Map;\n" +
                "global com.example.droolsdemo.entity.EntityInfoVo ruleResult;\n" +
                "rule \"%s\"\n" +
                "         dialect \"mvel\"\n" +
                "         no-loop false \n" +
                "      when\n" +
                "        $fact:Map(%s)\n" +
                "      then\n" +
                "\t  ruleResult.setHit(Boolean.TRUE);\n" +
                "end";
        //生成完整的规则字符串
        final String ruleStr = String.format(myRule, ruleName, rule);
        KieHelper helper = new KieHelper();
        helper.addContent(ruleStr, ResourceType.DRL);
        return helper.build();
    }
}

