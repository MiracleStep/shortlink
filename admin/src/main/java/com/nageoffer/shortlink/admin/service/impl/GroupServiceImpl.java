package com.nageoffer.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nageoffer.shortlink.admin.dao.entity.GroupDO;
import com.nageoffer.shortlink.admin.dao.mapper.GroupMapper;
import com.nageoffer.shortlink.admin.service.GroupService;
import com.nageoffer.shortlink.admin.toolkit.RandomCodeGenerator;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 短链接分组接口实现层
 */
@Service
@Slf4j
@RequiredArgsConstructor //构造器传入参数
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomCodeGenerator.generateRandomCode();
        } while (hasGid(gid));

        GroupDO groupDO = GroupDO.builder()
                .name(groupName)
                .gid(gid)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> lambdaQueryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                //TODO设置用户名
                .eq(GroupDO::getUsername, null);
        GroupDO groupDO = baseMapper.selectOne(lambdaQueryWrapper);
        return groupDO != null;
    }
}
