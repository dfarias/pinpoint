/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.navercorp.pinpoint.plugin.undertow;

import com.navercorp.pinpoint.bootstrap.config.Filter;
import com.navercorp.pinpoint.bootstrap.plugin.request.util.ParameterExtractor;
import io.undertow.server.HttpServerExchange;

/**
 * @author jaehong.kim
 */
public class MethodFilterExtractor implements ParameterExtractor<HttpServerExchange> {

    private final Filter<String> excludeProfileMethodFilter;

    private final ParameterExtractor<HttpServerExchange> delegate;

    public MethodFilterExtractor(Filter<String> excludeProfileMethodFilter, ParameterExtractor<HttpServerExchange> delegate) {
        this.excludeProfileMethodFilter = excludeProfileMethodFilter;
        this.delegate = delegate;
    }

    @Override
    public String extractParameter(HttpServerExchange request) {
        if (excludeProfileMethodFilter.filter(request.getRequestMethod().toString())) {
            return null;
        }
        return delegate.extractParameter(request);
    }
}
