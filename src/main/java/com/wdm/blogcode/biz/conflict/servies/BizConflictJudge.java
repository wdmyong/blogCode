package com.wdm.blogcode.biz.conflict.servies;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.wdm.blogcode.biz.conflict.constant.BizType;
import com.wdm.blogcode.biz.conflict.model.Data;

/**
 * @author wdmyong
 */
public abstract class BizConflictJudge {

    private static Map<Integer, List<Integer>> conflictConfig = Maps.newHashMap();
    static {
        conflictConfig.put(BizType.MUSIC.getId(), Arrays.asList(
                BizType.MOVIE.getId(), BizType.TV.getId()));
        conflictConfig.put(BizType.MOVIE.getId(), Arrays.asList(
                BizType.MUSIC.getId(), BizType.TV.getId()));
        conflictConfig.put(BizType.TV.getId(), Arrays.asList(
                BizType.MUSIC.getId(), BizType.GAME.getId()));
        conflictConfig.put(BizType.GAME.getId(), Arrays.asList(
                BizType.MUSIC.getId(), BizType.MOVIE.getId()));
    }

    private BizConflictJudge next = null;

    public boolean isConflict(BizType bizType, Data data) {
        return false;
    }

    public BizType bizType() {
        return null;
    }

    protected BizConflictJudge next() {
        return next;
    }

    protected List<Integer> getConflictList(BizType bizType) {
        return conflictConfig.getOrDefault(bizType.getId(), Collections.emptyList());
    }
}
