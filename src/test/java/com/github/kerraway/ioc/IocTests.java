package com.github.kerraway.ioc;

import com.github.kerraway.ioc.factory.AutowireBeanFactoryTest;
import com.github.kerraway.ioc.factory.xml.XmlBeanDefinitionReaderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author kerraway
 * @date 2019/09/11
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AutowireBeanFactoryTest.class,
        XmlBeanDefinitionReaderTest.class,
})
public class IocTests {
}