package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Attachment;
import cc.ryanc.halo.model.domain.Category;
import cc.ryanc.halo.repository.AttachmentRepository;
import cc.ryanc.halo.service.AttachmentService;
import cc.ryanc.halo.utils.ImageUploadToQINIU;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static cc.ryanc.halo.utils.Base64.base64ToMultipart;
import static cc.ryanc.halo.utils.HaloUtils.getStringDate;

/**
 * @author : RYAN0UP
 * @version : 1.0
 * @date : 2018/1/10
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    /**
     * 新增附件信息
     *
     * @param attachment attachment
     * @return Attachment
     */
    @Override
    public Attachment saveByAttachment(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    /**
     * 获取所有附件信息
     *
     * @return list
     */
    @Override
    public List<Attachment> findAllAttachments() {
        return attachmentRepository.findAll();
    }

    /**
     * 获取所有附件信息 分页
     *
     * @param pageable pageable
     * @return page
     */
    @Override
    public Page<Attachment> findAllAttachments(Pageable pageable) {
        return attachmentRepository.findAll(pageable);
    }

    /**
     * 根据附件id查询附件
     *
     * @param attachId attachId
     * @return attachment
     */
    @Override
    public Optional<Attachment> findByAttachId(Long attachId) {
        return attachmentRepository.findById(attachId);
    }

    /**
     * 根据编号移除附件
     *
     * @param attachId attachId
     * @return attachment
     */
    @Override
    public Attachment removeByAttachId(Long attachId) {
        Optional<Attachment> attachment = this.findByAttachId(attachId);
        attachmentRepository.delete(attachment.get());
        return attachment.get();
    }

    @Override
    public List<Attachment> strListToAttachmentList(List<String> attachmentList) {

        if (null == attachmentList) {
            return null;
        }
        List<Attachment> attachments = new ArrayList<>();
        Optional<Attachment> attachment = null;
        for (String str : attachmentList) {
            attachment = findByAttachId(Long.parseLong(str));
            attachments.add(attachment.get());
        }
        return attachments;
    }


    @Override
    public List<Attachment> Base64ToAttachmentList(String Base64Str)  {
        List<String> ts = (List<String>) JSONArray.parseArray(Base64Str, String.class);
        List<Attachment> attachmentList = new ArrayList<>();
        for(int index = 0;index<ts.size();index++){
            MultipartFile multipartFile = base64ToMultipart(ts.get(index));
            InputStream inputStream = null;
            try {
                inputStream = multipartFile.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String filePath = ImageUploadToQINIU.upload(inputStream);

            Attachment attachment = new Attachment();
            attachment.setAttachSmallPath(filePath);
            attachment.setAttachCreated(new Date());
            attachment.setAttachName(getStringDate("yyyy-MM-dd HH:mm:ss")+(int)(Math.random()*(9999-1000+1))+1000);
            attachment.setAttachType(".jpg");
            attachment.setAttachSuffix("image/jpeg");
            attachmentList.add(this.saveByAttachment(attachment));
        }
        return attachmentList;
    }
}
