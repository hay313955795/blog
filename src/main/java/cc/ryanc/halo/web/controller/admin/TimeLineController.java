package cc.ryanc.halo.web.controller.admin;

import cc.ryanc.halo.model.domain.*;
import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.model.dto.LogsRecord;
import cc.ryanc.halo.service.AttachmentService;
import cc.ryanc.halo.service.LogsService;
import cc.ryanc.halo.service.TimeLineService;
import cc.ryanc.halo.utils.HaloUtils;
import cc.ryanc.halo.utils.ImageUploadToQINIU;
import com.alibaba.fastjson.JSONArray;
import com.qiniu.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static cc.ryanc.halo.model.dto.LogsRecord.PUSH_TIME_LINE;
import static cc.ryanc.halo.utils.Base64.base64ToMultipart;
import static cc.ryanc.halo.utils.ImageUploadToQINIU.GenerateImage;

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

    @GetMapping("/page")
    public String TimeLine(Model model,
                           @RequestParam(value = "status",defaultValue = "0") Integer status,
                           @RequestParam(value = "page",defaultValue = "0") Integer page,
                           @RequestParam(value = "size",defaultValue = "10") Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"pushDate");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<TimeLine> timeLines = timeLineService.findTimeLineByTimeLineStatus(status,pageable);
        model.addAttribute("timeLines",timeLines);
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
    public String pushPost(@ModelAttribute TimeLine timeLine, @RequestParam("attachmentList") String attachmenIdtList , HttpSession session) throws IOException {


        try {
            List<Attachment> attachmentList = attachmentService.Base64ToAttachmentList(attachmenIdtList);
            timeLine.setImages(attachmentList);
            timeLine.setPushDate(new Date());
            timeLineService.save(timeLine);
            logsService.saveByLogs(new Logs(PUSH_TIME_LINE,timeLine.getTimeLineContent(),HaloUtils.getIpAddr(request),new Date()));
            return Json.encode("Success") ;
        }catch (Exception e){
            log.error("未知错误：", e.getMessage());
            return "Failed";
        }
    }

    /**
     * 处理移除时间轴的请求
     * @param timeLineId
     * @return
     */
    @PostMapping("/throw")
    public void moveToTrash(@RequestParam("timeLineId") Long timeLineId){
        try{
            timeLineService.updateTimeLine(timeLineId,1);
            log.info("编号为"+timeLineId+"的时间轴已被隐藏");
        }catch (Exception e){
            log.error("未知错误：{0}",e.getMessage());
        }
    }




}
