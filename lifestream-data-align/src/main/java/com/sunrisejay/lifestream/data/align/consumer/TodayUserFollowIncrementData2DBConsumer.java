package com.sunrisejay.lifestream.data.align.consumer;

import com.sunrisejay.lifestream.data.align.constant.MQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "lifestream_group_data_align_" + MQConstants.TOPIC_COUNT_FOLLOWING, // Group 组
        topic = MQConstants.TOPIC_COUNT_FOLLOWING // 主题 Topic
        )
@Slf4j
public class TodayUserFollowIncrementData2DBConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String body) {

        log.info("## TodayUserFollowIncrementData2DBConsumer 消费到了 MQ: {}", body);

        // ------------------------- 源用户的关注数变更记录 -------------------------
        // TODO: 布隆过滤器判断该日增量数据是否已经记录

        // TODO: 若无，才会落库，减轻数据库压力

        // 将日增量变更数据，分别写入两张表
        // - t_data_align_following_count_temp_日期_分片序号


        // TODO: 数据库写入成功后，再添加布隆过滤器中

        // ------------------------- 目标用户的粉丝数变更记录 -------------------------
        // TODO: 布隆过滤器判断该日增量数据是否已经记录

        // TODO: 若无，才会落库，减轻数据库压力

        // 将日增量变更数据，分别写入两张表
        // - t_data_align_fans_count_temp_日期_分片序号

        // TODO: 数据库写入成功后，再添加布隆过滤器中
    }
}