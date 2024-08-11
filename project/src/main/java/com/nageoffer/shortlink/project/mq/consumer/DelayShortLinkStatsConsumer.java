/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nageoffer.shortlink.project.mq.consumer;

import com.nageoffer.shortlink.project.common.convention.exception.ServiceException;
import com.nageoffer.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.nageoffer.shortlink.project.mq.idempotent.MessageQueueIdempotentHandler;
import com.nageoffer.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 * 延迟记录短链接统计组件
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = "short-link_project-service_delay-topic",
        consumerGroup = "${rocketmq.consumer.group}"
)
public class DelayShortLinkStatsConsumer implements RocketMQListener<ShortLinkStatsRecordDTO> {

    private final ShortLinkService shortLinkService;
    private final MessageQueueIdempotentHandler messageQueueIdempotentHandler;

    @Override
    public void onMessage(ShortLinkStatsRecordDTO statsRecord) {
        Executors.newSingleThreadExecutor(
                        runnable -> {
                            Thread thread = new Thread(runnable);
                            thread.setName("delay_short-link_stats_consumer");
                            thread.setDaemon(Boolean.TRUE);
                            return thread;
                        })
                .execute(() -> {
                            for (; ; ) {
                                try {
                                    if (statsRecord != null) {
                                        //获取到队列中的数据
                                        if (!messageQueueIdempotentHandler.isMessageProcessed(statsRecord.getKeys())){
                                            //消息消费过
                                            if (messageQueueIdempotentHandler.isAccomplish(statsRecord.getKeys())) {
                                                //消息已经消费完成，不做处理
                                                return;
                                            }
                                            //消息正在消费，且没有消费完成。可能由于宕机
                                            throw new ServiceException("消息未完成流程，需要消息队列重试");//可能中间因为宕机没有处理完成，导致卡在这里抛异常，等10分钟过了就正常执行了。
                                        }
                                        try {
                                            shortLinkService.shortLinkStats(null, null, statsRecord);
                                        } catch (Throwable ex){
                                            //如果执行异常就删除。
                                            messageQueueIdempotentHandler.delMessageProcessed(statsRecord.getKeys());
                                            log.error("延迟消费短链接消费异常", ex);
                                        }
                                        //没有执行异常
                                        //可能执行到delMessageProcessed或delete前就宕机了，因此多个这个步骤和上面二重的判断
                                        messageQueueIdempotentHandler.setAccomplish(statsRecord.getKeys());//设置消息消费完成
                                        continue;
                                    }
                                    LockSupport.parkUntil(500); //延迟等待
                                } catch (Throwable ignored) {
                                }
                            }
                        }
                );
    }
}
