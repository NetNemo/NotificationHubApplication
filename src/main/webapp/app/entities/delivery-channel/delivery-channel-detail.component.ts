import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeliveryChannel } from 'app/shared/model/delivery-channel.model';

@Component({
    selector: 'jhi-delivery-channel-detail',
    templateUrl: './delivery-channel-detail.component.html'
})
export class DeliveryChannelDetailComponent implements OnInit {
    deliveryChannel: IDeliveryChannel;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ deliveryChannel }) => {
            this.deliveryChannel = deliveryChannel;
        });
    }

    previousState() {
        window.history.back();
    }
}
