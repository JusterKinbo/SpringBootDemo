package demo.juster.spboot.error;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

public class ErrorConfig implements ErrorPageRegistrar {

	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		// TODO Auto-generated method stub
		 ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error400Page");
	     ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error401Page");
	     ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error404Page");
	     ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error500Page");
	     registry.addErrorPages(error400Page,error401Page,error404Page,error500Page);
	}
	

}
