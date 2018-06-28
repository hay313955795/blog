package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.TimeLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author hwb
 * @create 2018/6/27 13:37
 */
public interface TimeLineService {

    /**
     * 根据编号查询时间轴
     * @param timeLineId timeLineId
     * @return Post
     */
    Optional<TimeLine> findByTimeLineId(Long timeLineId);

    /**
     * 时间轴数据记录
     * @param timeLine
     * @return
     */
    TimeLine save(TimeLine timeLine);

    /**
     * 获取所有的时间轴数据
     * @return
     */
    List<TimeLine> getAllTimeLine();

    /**
     * 更新时间轴状态
     * @param timeLineId
     * @param status
     * @return
     */
    TimeLine updateTimeLine(Long timeLineId, Integer status);

    /**
     * 根据时间轴状态查询
     * @param timeLineStatus
     * @param pageable
     * @return
     */
    Page<TimeLine> findTimeLineByTimeLineStatus(Integer timeLineStatus, Pageable pageable);
}
