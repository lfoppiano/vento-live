package org.vento.service.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 09/06/13
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // CORS "pre-flight" request
        if (request.getHeader("Access-Control-Request-Method") != null
                && "OPTIONS".equals(request.getMethod())) {

            response.addHeader("Access-Control-Allow-Methods",
                    "GET, POST");

            response.addHeader("Access-Control-Allow-Headers",
                    "X-Requested-With, Origin, Content-Type, Accept");
        }

        //response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Origin", "*");

        filterChain.doFilter(request, response);
    }

}
