import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDeliveryChannel } from 'app/shared/model/delivery-channel.model';
import { DeliveryChannelService } from './delivery-channel.service';

@Component({
    selector: 'jhi-delivery-channel-update',
    templateUrl: './delivery-channel-update.component.html'
})
export class DeliveryChannelUpdateComponent implements OnInit {
    deliveryChannel: IDeliveryChannel;
    isSaving: boolean;

    constructor(private deliveryChannelService: DeliveryChannelService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ deliveryChannel }) => {
            this.deliveryChannel = deliveryChannel;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.deliveryChannel.id !== undefined) {
            this.subscribeToSaveResponse(this.deliveryChannelService.update(this.deliveryChannel));
        } else {
            this.subscribeToSaveResponse(this.deliveryChannelService.create(this.deliveryChannel));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryChannel>>) {
        result.subscribe((res: HttpResponse<IDeliveryChannel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
