package com.todouno.kardex.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * ConfigProperties.
 */
@Component
@ConfigurationProperties(prefix = "kardex")
@Getter
@Setter
public final class ConfigProperties {
  private String productUrl;
  private String userUrl;

}