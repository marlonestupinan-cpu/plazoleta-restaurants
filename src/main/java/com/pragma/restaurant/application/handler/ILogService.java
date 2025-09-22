package com.pragma.restaurant.application.handler;

import com.pragma.restaurant.application.dto.request.LogRequest;

public interface ILogService {
    void createLog(LogRequest logRequest);
}
