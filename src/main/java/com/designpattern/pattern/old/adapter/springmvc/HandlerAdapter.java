package com.designpattern.pattern.old.adapter.springmvc;

/**
 * @author miclefengzss
 */
public interface HandlerAdapter {

    boolean support(Object handler);

    void handle(Object handler);
}

class HttpHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return (handler instanceof HttpController);
    }

    @Override
    public void handle(Object handler) {
        ((HttpController) handler).doSimpleHandler();
    }
}

class SimpleHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return (handler instanceof SimpleController);
    }

    @Override
    public void handle(Object handler) {
        ((SimpleController) handler).doSimpleHandler();
    }
}

class AnnotationHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return (handler instanceof AnnotationController);
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController) handler).doSimpleHandler();
    }
}