package com.ternium.core.eventgenerator.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "logging", ignoreUnknownFields = false)
public class LoggingProperties {
	private final Logstash logstash = new Logstash();
	private final Metrics metrics = new Metrics();
	
    public Logstash getLogstash() {
		return logstash;
	}
	public Metrics getMetrics() {
		return metrics;
	}
	
	
	public static class Metrics {

        private final Logs logs = new Logs();

        public Logs getLogs() {
            return logs;
        }

        public static class Logs {

        	@Value("${server.port}")
            private boolean enabled;

            private long reportFrequency;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getReportFrequency() {
                return reportFrequency;
            }

            public void setReportFrequency(long reportFrequency) {
                this.reportFrequency = reportFrequency;
            }
        }
    }
	
	public static class Logstash {

        private boolean enabled;

        private String host;

        private int port;

        private int queueSize;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getQueueSize() {
            return queueSize;
        }

        public void setQueueSize(int queueSize) {
            this.queueSize = queueSize;
        }
    }
}


