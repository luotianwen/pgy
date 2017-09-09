/**
 * Copyright 1999-2014 dangdang.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kokmobi.server;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import com.kokmobi.server.bean.*;
import com.kokmobi.server.protocol.service.*;
import com.kokmobi.server.service.AreaService;
import com.kokmobi.server.service.RedisService;
import com.kokmobi.server.service.SdkService;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This class must be accessible from both the provider and consumer
 *
 * @author lishen
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {

    public Collection<Class> getSerializableClasses() {
        List<Class> classes = new LinkedList<Class>();
        // beans
        classes.add(Country.class);
        classes.add(SdkInfo.class);
        classes.add(GetAdListReq.class);
        classes.add(GetAdListResp.class);
        classes.add(DistReqVO.class);
        classes.add(GetAdListFeebackReq.class);
        classes.add(AdUsersProcessResp.class);
        classes.add(AdUserInfo.class);
        classes.add(AdLogReq.class);
        classes.add(AdProjectInfo.class);
        classes.add(CountryLevel.class);
        classes.add(AdProjectSetting.class);
        classes.add(SilentSetting.class);
        classes.add(ApkAdInfo.class);
        classes.add(AdPluginInfo.class);
        classes.add(SilentPluginResp.class);
        classes.add(UpdateJarResp.class);

        return classes;
    }
}
