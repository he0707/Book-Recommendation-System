package com.grape.bookrs.exception;

import com.grape.bookrs.result.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = MMallException.class)
    public ModelAndView handlerException(MMallException e){
        ModelAndView modelAndView = new ModelAndView();
        ResponseEnum responseEnum = e.getResponseEnum();
        switch (responseEnum.getCode()){
            case 301:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("loginNameError", responseEnum.getMsg());
                break;
            case 302:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("loginPwdError", responseEnum.getMsg());
                break;
            case 303:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("loginTypeError", responseEnum.getMsg());
                break;
            case 304:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("registerNameError",responseEnum.getMsg());
                break;
            case 305:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("registerPwdError",responseEnum.getMsg());
                break;
            case 306:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("registerVerifyPwdErrorr",responseEnum.getMsg());
                break;
            case 307:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("registerNameError",responseEnum.getMsg());
                break;
            case 308:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("registerNameError", responseEnum.getMsg());
                break;
            case 309:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("loginNameError", responseEnum.getMsg());
                break;
            case 310:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("loginPwdError", responseEnum.getMsg());
                break;
            case 311:
                modelAndView.setViewName("loginAndRegister");
                modelAndView.addObject("loginTypeError", responseEnum.getMsg());
                break;
        }
        return modelAndView;
    }
}
