package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Attachment;
import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.TimeLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hwb
 * @create 2018/6/27 13:36
 */
public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {


    /**
     * 根据时间轴状态查询
      * @param timeLineStatus
     * @param pageable
     * @return
     */
    Page<TimeLine> findTimeLineByTimeLineStatus(Integer timeLineStatus,Pageable pageable);
}
