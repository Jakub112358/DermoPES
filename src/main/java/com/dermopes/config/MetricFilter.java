package com.dermopes.config;

import com.dermopes.service.MetricService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Component
@RequiredArgsConstructor
public class MetricFilter implements Filter {

    private MetricService metricService;

    @Override
    public void init(final FilterConfig config) {
        if (metricService == null) {
            WebApplicationContext appContext = WebApplicationContextUtils
                    .getRequiredWebApplicationContext(config.getServletContext());

            metricService = appContext.getBean(MetricService.class);
        }
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws java.io.IOException, ServletException {
        final HttpServletRequest httpRequest = ((HttpServletRequest) request);
        final String req = httpRequest.getMethod() + " " + httpRequest.getRequestURI();

        chain.doFilter(request, response);

        final int status = ((HttpServletResponse) response).getStatus();
        metricService.increaseCount(req, status);

    }
}
