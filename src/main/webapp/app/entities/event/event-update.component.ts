import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEvent } from 'app/shared/model/event.model';
import { EventService } from './event.service';
import { ISystemEvent } from 'app/shared/model/system-event.model';
import { SystemEventService } from 'app/entities/system-event';
import { IApplicationEvent } from 'app/shared/model/application-event.model';
import { ApplicationEventService } from 'app/entities/application-event';

@Component({
    selector: 'jhi-event-update',
    templateUrl: './event-update.component.html'
})
export class EventUpdateComponent implements OnInit {
    event: IEvent;
    isSaving: boolean;

    systemevents: ISystemEvent[];

    applicationevents: IApplicationEvent[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private eventService: EventService,
        private systemEventService: SystemEventService,
        private applicationEventService: ApplicationEventService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ event }) => {
            this.event = event;
        });
        this.systemEventService.query().subscribe(
            (res: HttpResponse<ISystemEvent[]>) => {
                this.systemevents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.applicationEventService.query().subscribe(
            (res: HttpResponse<IApplicationEvent[]>) => {
                this.applicationevents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.event.id !== undefined) {
            this.subscribeToSaveResponse(this.eventService.update(this.event));
        } else {
            this.subscribeToSaveResponse(this.eventService.create(this.event));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEvent>>) {
        result.subscribe((res: HttpResponse<IEvent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackSystemEventById(index: number, item: ISystemEvent) {
        return item.id;
    }

    trackApplicationEventById(index: number, item: IApplicationEvent) {
        return item.id;
    }
}
