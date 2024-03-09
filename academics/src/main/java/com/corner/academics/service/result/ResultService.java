package com.corner.academics.service.result;

import com.corner.academics.dto.ResultRequest;
import com.corner.academics.entity.Result;

import java.util.List;
import java.util.UUID;

public interface ResultService {
    List<Result> getAllResults();

    Result getResultById(UUID resultId);

    Result registerNewResult(ResultRequest resultRequest);

    Boolean toggleStatus(UUID resultId);

    Result updateResult(UUID id, ResultRequest resultRequest);

    Boolean deleteResult(UUID resultId);
}
