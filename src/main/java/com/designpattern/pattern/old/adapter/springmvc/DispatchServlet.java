package com.designpattern.pattern.old.adapter.springmvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miclefengzss
 */
public class DispatchServlet {

    public static List<HandlerAdapter> handlerAdapterList = new ArrayList<>();

    public DispatchServlet() {
        handlerAdapterList.add(new HttpHandlerAdapter());
        handlerAdapterList.add(new SimpleHandlerAdapter());
        handlerAdapterList.add(new AnnotationHandlerAdapter());
    }

    public void doDispatch() {
        HttpController httpController = new HttpController();
        // SimpleController simpleController = new SimpleController();
        // AnnotationController annotationController = new AnnotationController();
        HandlerAdapter adapter = getHandler(httpController);
        adapter.handle(httpController);
    }

    private HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter handlerAdapter : handlerAdapterList) {
            if (handlerAdapter.support(controller)) {
                return handlerAdapter;
            }
        }
        return null;
    }
}

class Application {

    public static void main(String[] args) {

        DispatchServlet dispatchServlet = new DispatchServlet();
        dispatchServlet.doDispatch();
    }
}
