package com.xiaocai.springboot.integration.rules.drools.entity;

import org.drools.core.definitions.rule.impl.RuleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/3/27 16:45
 */
public class PersonRuleAction {

    private static Logger LOG = LoggerFactory.getLogger(PersonRuleAction.class);

    // 目前只实现记录日志功能
    public static void doParse(Person person, RuleImpl rule) {
        LOG.debug("{} is matched Rule[{}]!", person, rule.getName());
    }
}
