package com.aprendizaje.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

	// Este método sirve para decidir si se va a ejecutar o no el filtro
	@Override
	public boolean shouldFilter() {
		return true;
	}

	// Aquí se resuelve la lógica de nuestro filtro
	@Override
	public Object run() throws ZuulException {
		// Debemos obtener el objeto httpRequest
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		logger.info("Entrando a post");

		// Tomamos datos del request
		Long tiempoInicio = (Long)request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		logger.info(String.format("Tiempo transcurrido (ms): %s", tiempoTranscurrido));
		return null;
	}

	@Override
	public String filterType() {
		// post es palabra clave, para el filtro post, no puede ir otra palabra
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
