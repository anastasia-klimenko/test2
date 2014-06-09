package com.datajpa;

import com.datajpa.dao.model.User;
import com.datajpa.exception.EntityNotFound;
import com.datajpa.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

/*
        mav.setViewName("redirect:/index.html");
        redirectAttributes.addFlashAttribute("message", message);
        return mav;
        */
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController{

    private final static Logger LOG = Logger.getLogger(HelloController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value={"","/all"}, method = RequestMethod.GET)
    public String list(ModelMap model) {
        List<User> userList = userService.findAll();
        LOG.info("list size = "+userList.size());
        for(int i=0;i<userList.size();i++){
            LOG.info("id="+userList.get(i).getId()+", name="+userList.get(i).getFirstName()+" "+userList.get(i).getLastName());
        }
        model.addAttribute("list", userList);

        return "list";
    }

    @RequestMapping(value={"/create/{firstName}/{lastName}"}, method = RequestMethod.GET)
    public String create(ModelMap model,@PathVariable String firstName,@PathVariable String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userService.create(user);

        return list(model);
    }

    @RequestMapping(value={"/delete/{id}"}, method = RequestMethod.GET)
    public String delete(ModelMap model,@PathVariable Integer id) {
        String msg;
        try {
            userService.delete(id.longValue());
            msg="delete successfully";
        } catch (EntityNotFound entityNotFound) {
            msg="delete fail";
        }
        model.addAttribute("message", msg);

        return "hello";
    }
    @RequestMapping(value={"/one/{id}"}, method = RequestMethod.GET)
       public String one(ModelMap model,@PathVariable Integer id) {
        User user = userService.findById(id.longValue());
        String msg;
        if(user==null){
            msg="user not found";
        }
        else{
            msg="user found";
        }
        model.addAttribute("message", msg);

        return "hello";
    }
    @RequestMapping(value={"/one/first_name/{firstName}"}, method = RequestMethod.GET)
    public String one(ModelMap model,@PathVariable String firstName) {
        User user = userService.findByFirstName(firstName);
        String msg;
        if(user==null){
            msg="user not found";
        }
        else{
            msg="user found";
        }
        model.addAttribute("message", msg);

        return "hello";
    }

    @RequestMapping(value={"/one/name/{firstName}/{lastName}"}, method = RequestMethod.GET)
    public String one(ModelMap model,@PathVariable String firstName,@PathVariable String lastName) {
        List<User>userList = userService.findByName(firstName,lastName);
        String msg;
        if(userList.isEmpty()){
            msg="users not found";
        }
        else{
            msg="found "+userList.size()+" user";
        }
        model.addAttribute("message", msg);

        return "hello";
    }

    @RequestMapping(value={"/native"}, method = RequestMethod.GET)
    public String nativeAll(ModelMap model) {
        List<User> userList = userService.findWithNative();
        LOG.info("list size = "+userList.size());
        for(int i=0;i<userList.size();i++){
            LOG.info("id="+userList.get(i).getId()+", name="+userList.get(i).getFirstName()+" "+userList.get(i).getLastName());
        }
        model.addAttribute("list", userList);

        return "list";
    }

}
