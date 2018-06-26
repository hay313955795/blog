package cc.ryanc.halo.web.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : RYAN0UP
 * @date : 2017/12/16
 * @version : 1.0
 * description: 时间线管理
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/timeline")
public class TimeLineController {


    @GetMapping("")
    public String TimeLine(){
        return "admin/admin_timeLine";
    }
}
