package com.aprendizaje.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

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

		logger.info(String.format("%s request enrutado a %s", request.getMethod(),
				request.getRequestURL().toString()));

		// Ahora ya podemos pasar datos al request, por ejemplo el tiempo inicial
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		return null;
	}

	@Override
	public String filterType() {
		// pre es palabra clave, para el filtro pre, no puede ir otra palabra
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
