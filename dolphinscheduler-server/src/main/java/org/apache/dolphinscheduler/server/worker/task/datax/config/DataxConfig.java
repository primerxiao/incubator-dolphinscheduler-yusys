package org.apache.dolphinscheduler.server.worker.task.datax.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "/worker/task/datax/datax.properties")
public class DataxConfig {

    @Value("${datax.python}")
    private String dataxPython;

    @Value("${datax.home.evn}")
    private String dataxHomeEvn;

    public String getDataxPython() {
        return dataxPython;
    }

    public void setDataxPython(String dataxPython) {
        this.dataxPython = dataxPython;
    }

    public String getDataxHomeEvn() {
        return dataxHomeEvn;
    }

    public void setDataxHomeEvn(String dataxHomeEvn) {
        this.dataxHomeEvn = dataxHomeEvn;
    }
}
