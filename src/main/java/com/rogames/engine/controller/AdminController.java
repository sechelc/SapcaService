package com.rogames.engine.controller;

import com.rogames.engine.GameAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private GameAdminService gameAdminService;

    @RequestMapping("admin/createRound")
    public String createGame(@Valid @ModelAttribute RoundForm roundForm, Errors errors, RedirectAttributes ra){
        gameAdminService.createRound(roundForm.getRoundText(), roundForm.getPoints().toString(), roundForm.getPossibleActions());
        return "redirect:/";
    }
}
