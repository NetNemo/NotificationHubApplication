import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IEventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';
import { EventDeliveryStatusService } from './event-delivery-status.service';

@Component({
    selector: 'jhi-event-delivery-status-update',
    templateUrl: './event-delivery-status-update.component.html'
})
export class EventDeliveryStatusUpdateComponent implements OnInit {
    eventDeliveryStatus: IEventDeliveryStatus;
    isSaving: boolean;
    dateDp: any;

    constructor(private eventDeliveryStatusService: EventDeliveryStatusService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ eventDeliveryStatus }) => {
            this.eventDeliveryStatus = eventDeliveryStatus;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.eventDeliveryStatus.id !== undefined) {
            this.subscribeToSaveResponse(this.eventDeliveryStatusService.update(this.eventDeliveryStatus));
        } else {
            this.subscribeToSaveResponse(this.eventDeliveryStatusService.create(this.eventDeliveryStatus));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEventDeliveryStatus>>) {
        result.subscribe((res: HttpResponse<IEventDeliveryStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
