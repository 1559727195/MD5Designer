package com.massky.domain.interactor.zhihu;

import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.domain.interactor.UseCase;
import javax.inject.Inject;
import io.reactivex.Flowable;

public class ZhihuNewsListUseCase extends UseCase<ZhihuNewsEntity, Void> {
    private final ZhihuRepository mZhihuRepository;

    @Inject
    public ZhihuNewsListUseCase(ZhihuRepository zhihuRepository) {
        mZhihuRepository = zhihuRepository;
    }

    @Override
    protected Flowable<ZhihuNewsEntity> buildUseCaseObservable(Void aVoid) {
        return null;
    }
}
