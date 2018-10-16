import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISystemEvent } from 'app/shared/model/system-event.model';

@Component({
    selector: 'jhi-system-event-detail',
    templateUrl: './system-event-detail.component.html'
})
export class SystemEventDetailComponent implements OnInit {
    systemEvent: ISystemEvent;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ systemEvent }) => {
            this.systemEvent = systemEvent;
        });
    }

    previousState() {
        window.history.back();
    }
}
