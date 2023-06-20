package com.marivel.marivelshop.controladores;

import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.NestedServletException;

@Controller
public class ControladorError implements ErrorController {

    String carpeta = "error/";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpStatus status = HttpStatus.resolve(response.getStatus());
        if (status != null) {
            switch (status) {
                case FORBIDDEN:
                    return carpeta + "403";
                case NOT_FOUND:
                    return carpeta + "404";
                case INTERNAL_SERVER_ERROR:
                    // TODO: Save url operation in session, then retrieve this and pass to the Model
                    handleInternalServerError(request, model);
                    return carpeta + "500";
                default:
                    model.addAttribute("code", status.value());
                    model.addAttribute("reason", status.getReasonPhrase());
                    return carpeta + "error";
            }
        }
        return carpeta + "desconocido";
    }

    private void handleInternalServerError(HttpServletRequest request, Model model) {
        Throwable ex = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (ex == null) {
            return;
        }
        // TODO: Find the root cause of all nested exceptions
        if (ex instanceof NestedServletException) {
            // ServletException#getRootCause
            // NestedRuntimeException#getRootCause
            ex = ((NestedServletException) ex).getRootCause();
            if (ex instanceof DataIntegrityViolationException) {
                DataIntegrityViolationException dataIntegrityViolationException = (DataIntegrityViolationException) ex;
                ex = Optional.ofNullable(dataIntegrityViolationException.getRootCause())
                        .or(() -> Optional.ofNullable(dataIntegrityViolationException.getCause()))
                        .orElse(dataIntegrityViolationException);
                System.out.println("SQL Cause: "
                        + Optional.ofNullable(ex.getCause()).map(th -> th.getClass().getName()).orElse("null class")
                        + " -> " + ex.getCause());
            }
        }
        model.addAttribute("msg", ex.getLocalizedMessage());
    }
}
