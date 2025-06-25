package com.minis.core.event;

import com.apple.eawt.ApplicationEvent;

public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
