package com.cortinovis.GameMarketPlace.infra.http.Filters;

import com.cortinovis.GameMarketPlace.domain.Exceptions.Filters.RateLimitException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

  private static final int MAX_REQUESTS = 10;
  private static final long WINDOW_TIME = 60_000;

  private final Map<String, RequestCounter> requests = new ConcurrentHashMap<>();

  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain
  ) throws ServletException, IOException {

    String ip = request.getRemoteAddr();

    RequestCounter counter = requests.computeIfAbsent(
            ip,
            key -> new RequestCounter()
    );

    synchronized (counter) {

      long currentTime = System.currentTimeMillis();

      if (currentTime - counter.startTime >= WINDOW_TIME) {
        counter.startTime = currentTime;
        counter.count.set(0);
      }

      if (counter.count.incrementAndGet() > MAX_REQUESTS) {

        RateLimitException exception = new RateLimitException(
                "Too many requests. Try again later."
        );

        response.setStatus(429);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                "{\"message\":\"" + exception.getMessage() + "\"}"
        );

        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  private static class RequestCounter {

    private final AtomicInteger count = new AtomicInteger(0);

    private long startTime = System.currentTimeMillis();
  }
}