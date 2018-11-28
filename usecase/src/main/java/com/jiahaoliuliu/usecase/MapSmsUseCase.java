package com.jiahaoliuliu.usecase;


import com.jiahaoliuliu.chutoro.entity.Sms;
import com.jiahaoliuliu.chutoro.entity.Transaction;

import java.util.Collections;
import java.util.List;

/**
 * Use case created to map the list of sms to a list of any other data
 */
public class MapSmsUseCase {

    public MapSmsUseCase() {}

    public List<Transaction> mapSmsListToTransactionsList(List<Sms> smsList) {
        return Collections.emptyList();
    }
}
