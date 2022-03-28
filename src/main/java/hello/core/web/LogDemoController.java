package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //    private final MyLogger myLogger;
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    @RequestMapping("log_demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURI().toString();
        MyLogger myLogger = myLoggerObjectProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass()); // proxy 값 체크
        myLogger.setRequestURL(requestURL);

        myLogger.log("Controller test");
        logDemoService.logic("testid");
        return "OK";
    }
}
