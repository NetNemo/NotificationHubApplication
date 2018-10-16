import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IEventAttach } from 'app/shared/model/event-attach.model';
import { EventAttachService } from './event-attach.service';
import { IEvent } from 'app/shared/model/event.model';
import { EventService } from 'app/entities/event';

@Component({
    selector: 'jhi-event-attach-update',
    templateUrl: './event-attach-update.component.html'
})
export class EventAttachUpdateComponent implements OnInit {
    eventAttach: IEventAttach;
    isSaving: boolean;

    events: IEvent[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private eventAttachService: EventAttachService,
        private eventService: EventService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ eventAttach }) => {
            this.eventAttach = eventAttach;
        });
        this.eventService.query().subscribe(
            (res: HttpResponse<IEvent[]>) => {
                this.events = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.eventAttach.id !== undefined) {
            this.subscribeToSaveResponse(this.eventAttachService.update(this.eventAttach));
        } else {
            this.subscribeToSaveResponse(this.eventAttachService.create(this.eventAttach));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEventAttach>>) {
        result.subscribe((res: HttpResponse<IEventAttach>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEventById(index: number, item: IEvent) {
        return item.id;
    }
}
