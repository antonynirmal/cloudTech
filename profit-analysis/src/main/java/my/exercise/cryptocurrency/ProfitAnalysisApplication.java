package my.exercise.cryptocurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
@Slf4j
public class ProfitAnalysisApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ProfitAnalysisApplication.class, args);
		log.info("Active Profiles: " + Arrays.toString(applicationContext.getEnvironment()
				.getActiveProfiles()) +
				" ForkJoinPoolSize: " + ForkJoinPool.getCommonPoolParallelism());

	}
}
