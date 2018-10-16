import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IApplicationEvent } from 'app/shared/model/application-event.model';
import { ApplicationEventService } from './application-event.service';
import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';
import { UserChannelConfigurationService } from 'app/entities/user-channel-configuration';

@Component({
    selector: 'jhi-application-event-update',
    templateUrl: './application-event-update.component.html'
})
export class ApplicationEventUpdateComponent implements OnInit {
    applicationEvent: IApplicationEvent;
    isSaving: boolean;

    userchannelconfigurations: IUserChannelConfiguration[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private applicationEventService: ApplicationEventService,
        private userChannelConfigurationService: UserChannelConfigurationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ applicationEvent }) => {
            this.applicationEvent = applicationEvent;
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
        if (this.applicationEvent.id !== undefined) {
            this.subscribeToSaveResponse(this.applicationEventService.update(this.applicationEvent));
        } else {
            this.subscribeToSaveResponse(this.applicationEventService.create(this.applicationEvent));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IApplicationEvent>>) {
        result.subscribe((res: HttpResponse<IApplicationEvent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
