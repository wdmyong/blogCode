package com.wdm.blogcode.biz.conflict.servies.impl;

import com.wdm.blogcode.biz.conflict.constant.BizType;
import com.wdm.blogcode.biz.conflict.model.Data;
import com.wdm.blogcode.biz.conflict.servies.BizConflictJudge;

/**
 * @author wdmyong
 */
public class MovieConflictJudge extends BizConflictJudge {

    @Override
    public boolean isConflict(BizType bizType, Data data) {
        if (getConflictList(bizType()).contains(bizType.getId()) && data.isMovie()) {
            return true;
        }
        return next() != null && next().isConflict(bizType, data);
    }

    @Override
    public BizType bizType() {
        return BizType.MUSIC;
    }
}
