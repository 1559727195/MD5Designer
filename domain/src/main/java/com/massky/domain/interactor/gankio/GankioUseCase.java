package com.massky.domain.interactor.gankio;

import com.massky.domain.constant.CodeConstant;
import com.massky.domain.entity.gankio.GankioEntity;
import com.massky.domain.exception.ApiException;
import com.massky.domain.interactor.UseCase;

import org.reactivestreams.Publisher;

import java.security.PrivilegedAction;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GankioUseCase extends UseCase<List<GankioEntity.ResultsEntity>, GankioUseCase.Params> {

    private final GankioRepository mGankioRepository;

    @Inject
    public GankioUseCase(GankioRepository gankioRepository) {
        mGankioRepository = gankioRepository;
    }


    @Override
    protected Flowable<List<GankioEntity.ResultsEntity>> buildUseCaseObservable(Params params) {
        return mGankioRepository.getGankio(params.type,params.count)
                .flatMap(this::handleException);
    }

    private Publisher<List<GankioEntity.ResultsEntity>> handleException(GankioEntity gankioEntity) {
        if (gankioEntity == null) {
            return Flowable.error(new ApiException(CodeConstant.CODE_EMPTY, "数据为空，请求个毛线！"));
        }
        if (gankioEntity.isError()) {
            return Flowable.error(new ApiException(CodeConstant.CODE_DATA_ERROR, "数据错误，没法快乐玩耍！"));
        }
        List<GankioEntity.ResultsEntity> results = gankioEntity.getResults();
        if (results == null || results.isEmpty()) {
            return Flowable.error(new ApiException(CodeConstant.CODE_EMPTY, "数据为空，请求个毛线！"));
        }
        return Flowable.just(results);
    }


    public static final class Params {
        private final String type;
        private final int count;

        public Params(String type, int count) {
            this.type = type;
            this.count = count;
        }

        public static Params get(String type, int count) {
            return new Params(type, count);
        }
    }

}
