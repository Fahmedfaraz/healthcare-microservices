package com.mediscreen.practitioner_notes;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Service
@RestController
public class LoggingUtil {
	
//	private final Logger LOGGER = LoggerFactory.getLogger(LoggingUtil.class());
	private static final Log logger = LogFactory.getLog(LoggingUtil.class);

	
	@GetMapping("/log")
	public String LoggingUtil(Map<String, Object> model) {

		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");

		model.put("message", "HowToDoInJava Reader !!");
		return "index";
	}
}
