package com.yunhe.todo.controller;

import com.yunhe.todo.model.Task;
import com.yunhe.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Task> tasks = taskService.getTasks();
        Task task = new Task();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", task);
        model.addAttribute("taskNum", tasks.size());
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute Task task, BindingResult result, Model model) {
        List<Task> tasks = taskService.getTasks();
        if (result.hasErrors()) {
            model.addAttribute("tasks", tasks);
            model.addAttribute("task", task);
            model.addAttribute("taskNum", tasks.size());
            return "index";
        }
        String modName = HtmlUtils.htmlEscape(task.getName());

        for (int i = 0; i < tasks.size(); i++) {
            String tmpName = tasks.get(i).getName();
            if (tmpName.equals(modName)) {
                model.addAttribute("tasks", tasks);
                model.addAttribute("task", task);
                model.addAttribute("taskNum", tasks.size());
                model.addAttribute("errorDupName", true);
                return "index";
            }
        }

        task.setName(modName);
        task.setCreate_date(new Date());
        task.setIs_completed(false);
        taskService.saveTask(task);
        return "redirect:/";
    }

    @RequestMapping(value = "{id}/changeStatus", method = RequestMethod.PUT)
    public String changeStatus(@PathVariable int id) {
        Task task = taskService.getTask(id);
        boolean cur_status = task.getIs_completed();
        task.setIs_completed(!cur_status);
        taskService.saveTask(task);
        return "redirect:/";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model) {
        Task task = taskService.getTask(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(@PathVariable int id, @Valid @ModelAttribute Task task, BindingResult result, Model model) {
//        String id_str = Integer.toString(id);
        if(result.hasErrors()) return "edit";
        List<Task> tasks = taskService.getTasks();
        String modName = HtmlUtils.htmlEscape(task.getName());

        for (int i = 0; i < tasks.size(); i++) {
            String tmpName = tasks.get(i).getName();
            if (tmpName.equals(modName)) {
                model.addAttribute("errorDupName", true);
                return "edit";
            }
        }

        task.setName(modName);
        task.setId(id);
        taskService.saveTask(task);
        return "redirect:/";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam(defaultValue = "") String name) {
        List<Task> tasks = taskService.getTasksByName(name);
        model.addAttribute("taskNumSearched", -1);
        if (name.equals("")) {
            return "search";
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskNumSearched", tasks.size());
        return "search";
    }

}
