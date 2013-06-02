package org.vento.service.common;

import org.easymock.EasyMock;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 02/06/13
 * Time: 09:05
 * To change this template use File | Settings | File Templates.
 */
public class EasyMockFactoryBean<T> implements FactoryBean<T> {
    private Class<T> mockedClass;

    public void setMockedClass(Class mockedClass) {
        this.mockedClass = mockedClass;
    }

    public T getObject() throws Exception {
        return EasyMock.createMock(mockedClass);
    }

    public Class<T> getObjectType() {
        return mockedClass;
    }

    public boolean isSingleton() {
        return true;
    }

}

