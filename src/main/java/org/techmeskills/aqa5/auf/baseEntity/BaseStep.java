package org.techmeskills.aqa5.auf.baseEntity;

import org.techmeskills.aqa5.auf.core.BrowsersService;

public abstract class BaseStep {
    public BrowsersService browsersService;

    public BaseStep(BrowsersService browsersService) {
        this.browsersService = browsersService;
    }
}
