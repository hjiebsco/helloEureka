package com.hji.play;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import com.netflix.appinfo.AmazonInfo;

@SpringBootApplication
@EnableEurekaServer
public class HelloEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloEurekaApplication.class, args);
	}

	@Value("${server.port}")
	private int port;

	@Bean
	public EurekaInstanceConfigBean eurekaInstanceConfig() {
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(new InetUtils(new InetUtilsProperties()));
		AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
		config.setDataCenterInfo(info);
		info.getMetadata().put(AmazonInfo.MetaDataKey.publicHostname.getName(),
				info.get(AmazonInfo.MetaDataKey.publicIpv4));
		config.setHostname(info.get(AmazonInfo.MetaDataKey.publicHostname));
		config.setIpAddress(info.get(AmazonInfo.MetaDataKey.publicIpv4));
		config.setNonSecurePort(port);
		return config;
	}
}
