import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApplicationEvent } from 'app/shared/model/application-event.model';

@Component({
    selector: 'jhi-application-event-detail',
    templateUrl: './application-event-detail.component.html'
})
export class ApplicationEventDetailComponent implements OnInit {
    applicationEvent: IApplicationEvent;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationEvent }) => {
            this.applicationEvent = applicationEvent;
        });
    }

    previousState() {
        window.history.back();
    }
}
