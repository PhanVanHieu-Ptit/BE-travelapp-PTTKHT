package com.thanhsang.travelapp.model.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schedule_service")
public class ScheduleModel {
    
    @Id
    @Column(name = "id_schedule_service", nullable = false, length = 255)
    private String id;
    @Column(name = "id_service", nullable = false, length = 255)
    private String idService;
    @Column(name = "name_schedule_service", nullable = false, length = 255)
    private String name;
    @Column(name = "activity", nullable = false)
    private boolean activity;

    public ScheduleModel() {}

    public ScheduleModel(String id, String idService, String name, boolean activity) {
        this.id = id;
        this.idService = idService;
        this.name = name;
        this.activity = activity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getId() {
        return id;
    }

    public String getIdService() {
        return idService;
    }

    public String getName() {
        return name;
    }

    public boolean isActivity() {
        return activity;
    }

    public void update(ScheduleModel schedule) {
        this.name = schedule.getName();
        this.activity = schedule.isActivity();
    }
    
}
