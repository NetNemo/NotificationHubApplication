import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IEventAttach } from 'app/shared/model/event-attach.model';

@Component({
    selector: 'jhi-event-attach-detail',
    templateUrl: './event-attach-detail.component.html'
})
export class EventAttachDetailComponent implements OnInit {
    eventAttach: IEventAttach;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eventAttach }) => {
            this.eventAttach = eventAttach;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
