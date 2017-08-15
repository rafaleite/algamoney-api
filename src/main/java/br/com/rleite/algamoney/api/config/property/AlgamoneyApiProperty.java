package br.com.rleite.algamoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {
	
	@Getter
	@Setter
	private String allowedOrigin = "http://localhost:3000";
	
	@Getter
	@Setter
	private boolean enableHttps;
	
}
