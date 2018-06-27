package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Post;
import cc.ryanc.halo.model.domain.TimeLine;
import cc.ryanc.halo.repository.TimeLineRepository;
import cc.ryanc.halo.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author hwb
 * @create 2018/6/27 13:37
 */
@Service
public class TimeLineServiceImpl implements TimeLineService {


    @Autowired
    private TimeLineRepository timeLineRepository;

    @Override
    public Optional<TimeLine> findByTimeLineId(Long timeLineId) {
        return timeLineRepository.findById(timeLineId);
    }

    /**
     * 时间轴数据记录
     * @param timeLine
     * @return
     */
    @Override
    public TimeLine save(TimeLine timeLine) {
        return timeLineRepository.save(timeLine);
    }

    @Override
    public List<TimeLine> getAllTimeLine() {
        return timeLineRepository.findAll();
    }

    /**
     * 更新状态
     * @param timeLineId
     * @param status
     * @return
     */
    @Override
    public TimeLine updateTimeLine(Long timeLineId, Integer status) {
        Optional<TimeLine> timeLine = this.findByTimeLineId(timeLineId);
        timeLine.get().setTimeLineStatus(status);
        return timeLineRepository.save(timeLine.get());
    }


}
