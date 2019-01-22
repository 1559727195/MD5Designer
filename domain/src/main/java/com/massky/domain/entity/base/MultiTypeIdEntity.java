package com.massky.domain.entity.base;

import com.crazysunj.multitypeadapter.entity.MultiTypeEntity;

public abstract class MultiTypeIdEntity implements MultiTypeEntity {
    public abstract String getStringId();

    @Override
    public long getId() {
        return 0;
    }
}
