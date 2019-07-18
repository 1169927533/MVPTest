package com.example.mvptest.draggerstudy.budaimodule;

import javax.inject.Inject;

public class Factory {
    Project project;

    @Inject
    public Factory(Project project) {
        this.project = project;
    }
}
