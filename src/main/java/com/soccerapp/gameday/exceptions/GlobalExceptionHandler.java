package com.soccerapp.gameday.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:" + ex.getRedirectUri();
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public String handleDuplicateResource(DuplicateResourceException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:" + ex.getRedirectUri();
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleUnexpectedException(Exception ex) {

        ModelAndView mav =
                new ModelAndView("error/500");

        mav.addObject(
                "message",
                "Ha ocurrido un error inesperado. Inténtalo nuevamente."
        );

        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return mav;
    }
}
