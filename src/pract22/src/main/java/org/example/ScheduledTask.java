package org.example;

import org.example.service.DataExportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private final DataExportService dataExportService;

    public ScheduledTask(DataExportService dataExportService) {
        this.dataExportService = dataExportService;
    }

    @Scheduled(fixedRate = 30000)
    public void exportDataPeriodically() {
        dataExportService.exportData();
    }
}
