package dev.drewboiii.house.service.api.bpp

import mu.KLogging
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Component
class HelloControllerCreationDebuggerBeanPostProcessor : BeanPostProcessor, Ordered {

    override fun getOrder(): Int = Ordered.LOWEST_PRECEDENCE

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        if (beanName.contains("helloController")) {
            logger.info { "BEFORE INIT: $beanName | Class: ${bean::class.simpleName}" }
        }
        return bean
    }

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        if (beanName.contains("helloController")) {
            logger.info { "AFTER INIT: $beanName | Class: ${bean::class.simpleName}" }
            logger.info { "Is Proxy: ${AopUtils.isAopProxy(bean)}" }
            logger.info { "Target Class: ${AopUtils.getTargetClass(bean).simpleName}" }
        }
        return bean
    }

    companion object : KLogging()
}