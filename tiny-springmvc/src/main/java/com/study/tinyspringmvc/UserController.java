package com.study.tinyspringmvc;

import com.study.tinyspringmvc.annotation.Controller;
import com.study.tinyspringmvc.annotation.Qualifier;
import com.study.tinyspringmvc.annotation.RequestMapping;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
@Controller("userController")
@RequestMapping("/user")
public class UserController {

    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping("/insert")
    public void insert() {
        System.out.println("userController insert start...");
        userService.insert();
        System.out.println("userController insert end...");
    }
}
