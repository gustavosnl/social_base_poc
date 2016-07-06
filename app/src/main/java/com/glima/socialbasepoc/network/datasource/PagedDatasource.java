package com.glima.socialbasepoc.network.datasource;

import java.util.List;

import rx.Observable;

/**
 * Created by gustavo on 05/07/16.
 */
public interface PagedDatasource<T> {

    Observable<List<T>> getNext();

    Observable<List<T>> getPaged(Integer page);


}
