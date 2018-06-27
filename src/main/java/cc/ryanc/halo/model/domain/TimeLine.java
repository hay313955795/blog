package cc.ryanc.halo.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hwb
 * @create 2018/6/27 13:20
 */
@Data
@Entity
@Table(name = "halo_timeline")
public class TimeLine implements Serializable{

    @Id
    @GeneratedValue
    private Long timeLineId;

    private String timeLineContent;

    private Date pushDate;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "halo_timeline_attachment",
            joinColumns = {@JoinColumn(name = "timeline_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "attach_id", nullable = false)})
    private List<Attachment> images = new ArrayList<>();

    /**
     * 0 已发布
     * 1 回收站
     */
    private Integer timeLineStatus = 0;
}
