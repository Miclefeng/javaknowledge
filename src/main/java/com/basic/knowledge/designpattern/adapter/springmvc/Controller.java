package com.basic.knowledge.designpattern.adapter.springmvc;

/**
 * @author miclefengzss
 */
public interface Controller {

    void doSimpleHandler();
}

class HttpController implements Controller {

    @Override
    public void doSimpleHandler() {
        System.out.println("http ... ");
    }
}

class SimpleController implements Controller {

    @Override
    public void doSimpleHandler() {
        System.out.println("simple ... ");
    }
}

class AnnotationController implements Controller {

    @Override
    public void doSimpleHandler() {
        System.out.println("annotation ... ");
    }
}
