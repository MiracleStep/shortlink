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

package com.nageoffer.shortlink.project.mq.producer;

import com.alibaba.fastjson2.JSON;
import com.nageoffer.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 延迟消费短链接统计发送者
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayShortLinkStatsProducer {

//    private final RedissonClient redissonClient;/**/
    private final RocketMQTemplate rocketMQTemplate;


    private final String statsDelaySaveTopic = "short-link_project-service_delay-topic";
    /**
     * 发送延迟消费短链接统计
     *
     * @param statsRecord 短链接统计实体参数
     */
    public void send(ShortLinkStatsRecordDTO statsRecord) {
        Message<ShortLinkStatsRecordDTO> build = MessageBuilder
                .withPayload(statsRecord)
                .setHeader(MessageConst.PROPERTY_KEYS, statsRecord.getKeys())
                .build();
        SendResult sendResult;
        try {
            sendResult = rocketMQTemplate.syncSend(statsDelaySaveTopic, build, 2000L, 0);
            log.info("[消息访问统计监控] 消息发送结果：{}，消息ID：{}，消息Keys：{}", sendResult.getSendStatus(), sendResult.getMsgId(), statsRecord.getKeys());
        } catch (Throwable ex) {
            log.error("[消息访问统计监控] 消息发送失败，消息体：{}", JSON.toJSONString(statsRecord), ex);
            // 自定义行为...
        }
    }


}
