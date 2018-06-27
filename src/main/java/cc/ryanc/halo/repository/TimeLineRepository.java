package cc.ryanc.halo.repository;

import cc.ryanc.halo.model.domain.Attachment;
import cc.ryanc.halo.model.domain.TimeLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hwb
 * @create 2018/6/27 13:36
 */
public interface TimeLineRepository extends JpaRepository<TimeLine, Long> {
}
