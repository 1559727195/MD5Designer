package com.massky.domain.interactor.zhihu;

import com.massky.domain.constant.CodeConstant;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.domain.exception.ApiException;
import com.massky.domain.interactor.UseCase;
import com.massky.domain.repository.ZhihuRepository;
import org.reactivestreams.Publisher;
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
        return mZhihuRepository.getZhihuNewsList()
                .flatMap(this::handleException);
    }

    private Publisher<ZhihuNewsEntity> handleException(ZhihuNewsEntity zhihuNewsList) {
        if (zhihuNewsList == null) {
            return Flowable.error(new ApiException(CodeConstant.CODE_EMPTY, "数据为空，请求个毛线！"));
        }
        return Flowable.just(zhihuNewsList);
    }
}
