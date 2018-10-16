import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISystemEvent } from 'app/shared/model/system-event.model';
import { SystemEventService } from './system-event.service';
import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';
import { UserChannelConfigurationService } from 'app/entities/user-channel-configuration';

@Component({
    selector: 'jhi-system-event-update',
    templateUrl: './system-event-update.component.html'
})
export class SystemEventUpdateComponent implements OnInit {
    systemEvent: ISystemEvent;
    isSaving: boolean;

    userchannelconfigurations: IUserChannelConfiguration[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private systemEventService: SystemEventService,
        private userChannelConfigurationService: UserChannelConfigurationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ systemEvent }) => {
            this.systemEvent = systemEvent;
        });
        this.userChannelConfigurationService.query().subscribe(
            (res: HttpResponse<IUserChannelConfiguration[]>) => {
                this.userchannelconfigurations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.systemEvent.id !== undefined) {
            this.subscribeToSaveResponse(this.systemEventService.update(this.systemEvent));
        } else {
            this.subscribeToSaveResponse(this.systemEventService.create(this.systemEvent));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISystemEvent>>) {
        result.subscribe((res: HttpResponse<ISystemEvent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserChannelConfigurationById(index: number, item: IUserChannelConfiguration) {
        return item.id;
    }
}
