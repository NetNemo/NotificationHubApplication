import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';

@Component({
    selector: 'jhi-event-delivery-status-detail',
    templateUrl: './event-delivery-status-detail.component.html'
})
export class EventDeliveryStatusDetailComponent implements OnInit {
    eventDeliveryStatus: IEventDeliveryStatus;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventDeliveryStatus }) => {
            this.eventDeliveryStatus = eventDeliveryStatus;
        });
    }

    previousState() {
        window.history.back();
    }
}
