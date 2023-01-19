package sh.strm.tasker.task;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import sh.strm.tasker.TaskConfiguration;

@Component
@EnableAsync
public class TaskSetup {

	@Autowired
	private TaskConfiguration conf;

	private static final Logger log = LoggerFactory.getLogger(TaskSetup.class);

	public void setConfiguration(TaskConfiguration conf) {
		this.conf = conf;
	}

	@PostConstruct
	public void init() {
		log.info("Task configuration loaded, checking tasks");

		for (Task task : conf.getTasks()) {
			task.check();
		}

		log.info("Task checked, ready to continue");
	}
}
