package com.saborexpress.saborexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaborExpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaborExpressApplication.class, args);
	}

}
//package br.com.santander.hybcaml.clientintegration.processor;
//
//
//import br.com.santander.bhs.security.token.JwtClaimExtractor;
//import com.jayway.jsonpath.JsonPath;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import org.apache.commons.text.StringEscapeUtils;
//import org.slf4j.MDC;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.UUID;
//import java.util.function.Function;
//
//import static br.com.santander.hybcaml.clientintegration.util.constants.MessagesConstants.ERROR_DECODE_LEGACY_SESSION_DECODER;
//import static br.com.santander.hybcaml.clientintegration.util.constants.MessagesConstants.ERROR_DECODE_LEGACY_SESSION_URL;
//import static br.com.santander.hybcaml.clientintegration.util.constants.MessagesConstants.ERROR_SESSION_INFO;
//import static br.com.santander.hybcaml.clientintegration.util.constants.MessagesConstants.USER_REGISTER;
//
//@Slf4j
//public class SessionExtractor implements Processor {
//	@Qualifier("jwtDecoder0")
//	private JwtDecoder jwtDecoder;
//
//	public SessionExtractor(JwtDecoder jwtDecoder) {
//		this.jwtDecoder = jwtDecoder;
//	}
//
//	@Override
//	public void process(Exchange exchange) throws Exception {
//		String authorizationHeader = exchange.getMessage().getHeader("authorization", String.class);
//		String parameter = "T999999";
//		String userName = "testeViaPostman";
//		String collaboratorType = "Colaborador";
//		String login = "T999999";
//
//		if (authorizationHeader != null) {
//			String jwtToken = authorizationHeader.split(" ")[1];
//			JwtClaimExtractor jwtClaimExtractor = new JwtClaimExtractor(jwtDecoder);
//			String legacySession = jwtClaimExtractor.getClaimAsString("legacy-session", jwtToken);
//
//			if (legacySession != null) {
//				String plainLegacySession;
//				try {
//					plainLegacySession = new String(Base64.getUrlDecoder().decode(legacySession),
//						StandardCharsets.UTF_8);
//				} catch (Exception ex) {
//					log.error(ERROR_DECODE_LEGACY_SESSION_URL, ex.getMessage(), ex.getCause());
//					try {
//						plainLegacySession = new String(Base64.getDecoder().decode(legacySession),
//							StandardCharsets.UTF_8);
//					} catch (Exception e) {
//						plainLegacySession = legacySession;
//						log.error(ERROR_DECODE_LEGACY_SESSION_DECODER, e.getMessage(), e.getCause());
//					}
//				}
//				plainLegacySession = StringEscapeUtils.unescapeJson(plainLegacySession);
//				try {
//					parameter = JsonPath.read(plainLegacySession, "matricula");
//					userName =  JsonPath.read(plainLegacySession, "$.listaUsuario[0].name");
//					collaboratorType = JsonPath.read(plainLegacySession, "contributorType");
//					login =  JsonPath.read(plainLegacySession, "loginAD");
//
//				} catch (Exception ex) {
//					parameter = "BATCH";
//					log.error(ERROR_SESSION_INFO, ex.getMessage(), ex.getCause());
//				}
//			}
//		}
//
//		verifyIfHeadersSentAndFill(exchange, parameter, userName, collaboratorType, login);
//	}
//
//	private void verifyIfHeadersSentAndFill(Exchange exchange, String parameter, String userName, String collaboratorType, String login) {
//		exchange.getMessage().setHeader(USER_REGISTER, exchange.getMessage().getHeader(USER_REGISTER) == null ?
//			parameter : exchange.getMessage().getHeader(USER_REGISTER));
//
//		exchange.setProperty(USER_REGISTER, exchange.getMessage().getHeader(USER_REGISTER));
//
//		exchange.getMessage().setHeader("__userName", exchange.getMessage().getHeader("__userName") == null ?
//			userName : exchange.getMessage().getHeader("__userName"));
//
//		exchange.getMessage().setHeader("__collaboratorType", exchange.getMessage().getHeader("__collaboratorType") == null ?
//			collaboratorType : exchange.getMessage().getHeader("__collaboratorType"));
//
//		exchange.getMessage().setHeader("__login", exchange.getMessage().getHeader("__login") == null ?
//			login : exchange.getMessage().getHeader("__login"));
//
//		String traceId = exchange.getMessage().getHeader("X-TraceId", String.class);
//		MDC.put("X-TraceId", traceId != null ? traceId : getRandomString.apply(null));
//	}
//
//	public Function<Void, String> getRandomString = (nothing) -> UUID.randomUUID().toString();
//}
