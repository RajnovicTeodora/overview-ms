package com.notbooking.overviewms.mapper;

import com.notbooking.overviewms.dto.DefaultDTO;
import com.notbooking.overviewms.model.DefaultModel;

import java.util.List;

public interface DefaultMapper<D extends DefaultDTO, M extends DefaultModel> {


    M toModel(D dto);

    D toDto(M model);

    List<D> toDto(List<M> models);

}
