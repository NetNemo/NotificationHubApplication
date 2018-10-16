import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUserChannelConfiguration } from 'app/shared/model/user-channel-configuration.model';
import { UserChannelConfigurationService } from './user-channel-configuration.service';
import { IEventDeliveryStatus } from 'app/shared/model/event-delivery-status.model';
import { EventDeliveryStatusService } from 'app/entities/event-delivery-status';
import { IDeliveryChannel } from 'app/shared/model/delivery-channel.model';
import { DeliveryChannelService } from 'app/entities/delivery-channel';

@Component({
    selector: 'jhi-user-channel-configuration-update',
    templateUrl: './user-channel-configuration-update.component.html'
})
export class UserChannelConfigurationUpdateComponent implements OnInit {
    userChannelConfiguration: IUserChannelConfiguration;
    isSaving: boolean;

    eventdeliverystatuses: IEventDeliveryStatus[];

    deliverychannels: IDeliveryChannel[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private userChannelConfigurationService: UserChannelConfigurationService,
        private eventDeliveryStatusService: EventDeliveryStatusService,
        private deliveryChannelService: DeliveryChannelService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userChannelConfiguration }) => {
            this.userChannelConfiguration = userChannelConfiguration;
        });
        this.eventDeliveryStatusService.query().subscribe(
            (res: HttpResponse<IEventDeliveryStatus[]>) => {
                this.eventdeliverystatuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.deliveryChannelService.query().subscribe(
            (res: HttpResponse<IDeliveryChannel[]>) => {
                this.deliverychannels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.userChannelConfiguration.id !== undefined) {
            this.subscribeToSaveResponse(this.userChannelConfigurationService.update(this.userChannelConfiguration));
        } else {
            this.subscribeToSaveResponse(this.userChannelConfigurationService.create(this.userChannelConfiguration));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUserChannelConfiguration>>) {
        result.subscribe(
            (res: HttpResponse<IUserChannelConfiguration>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackEventDeliveryStatusById(index: number, item: IEventDeliveryStatus) {
        return item.id;
    }

    trackDeliveryChannelById(index: number, item: IDeliveryChannel) {
        return item.id;
    }
}
