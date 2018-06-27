package cc.ryanc.halo.web.controller.admin;

import cc.ryanc.halo.model.domain.*;
import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.model.dto.LogsRecord;
import cc.ryanc.halo.service.AttachmentService;
import cc.ryanc.halo.service.LogsService;
import cc.ryanc.halo.service.TimeLineService;
import cc.ryanc.halo.utils.HaloUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static cc.ryanc.halo.model.dto.LogsRecord.PUSH_TIME_LINE;

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


    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private TimeLineService timeLineService;

    @Autowired
    private LogsService logsService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public String TimeLine(){
        return "admin/admin_timeLine";
    }

    /**
     * 新增时间轴
     * @param timeLine
     * @param attachmenIdtList
     * @param session
     */
    @PostMapping(value = "/new/push")
    @ResponseBody
    public void pushPost(@ModelAttribute TimeLine timeLine, @RequestParam("attachmentList") List<String> attachmenIdtList, HttpSession session){
        User user = (User)session.getAttribute(HaloConst.USER_SESSION_KEY);
        try {
            List<Attachment> attachments = attachmentService.strListToAttachmentList(attachmenIdtList);
            timeLine.setImages(attachments);
            timeLine.setPushDate(new Date());
            timeLineService.save(timeLine);
            logsService.saveByLogs(new Logs(PUSH_TIME_LINE,timeLine.getTimeLineContent(),HaloUtils.getIpAddr(request),new Date()));
        }catch (Exception e){
            log.error("未知错误：", e.getMessage());
        }
    }

    /**
     * 处理移除时间轴的请求
     * @param timeLineId
     * @return
     */
    @GetMapping("/throw")
    public void moveToTrash(@RequestParam("timeLineId") Long timeLineId){
        try{
            timeLineService.updateTimeLine(timeLineId,1);
            log.info("编号为"+timeLineId+"的时间轴已被隐藏");
        }catch (Exception e){
            log.error("未知错误：{0}",e.getMessage());
        }
    }


}
