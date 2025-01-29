package com.hans.petfinderv1.filter;

import com.hans.petfinderv1.utils.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class TokenFilter {

    private final TokenUtil tokenUtil;

    //TODO IMPLEMENT CONTROL FILTER
}
