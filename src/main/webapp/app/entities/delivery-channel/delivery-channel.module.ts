import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NotificationHubSharedModule } from 'app/shared';
import {
    DeliveryChannelComponent,
    DeliveryChannelDetailComponent,
    DeliveryChannelUpdateComponent,
    DeliveryChannelDeletePopupComponent,
    DeliveryChannelDeleteDialogComponent,
    deliveryChannelRoute,
    deliveryChannelPopupRoute
} from './';

const ENTITY_STATES = [...deliveryChannelRoute, ...deliveryChannelPopupRoute];

@NgModule({
    imports: [NotificationHubSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DeliveryChannelComponent,
        DeliveryChannelDetailComponent,
        DeliveryChannelUpdateComponent,
        DeliveryChannelDeleteDialogComponent,
        DeliveryChannelDeletePopupComponent
    ],
    entryComponents: [
        DeliveryChannelComponent,
        DeliveryChannelUpdateComponent,
        DeliveryChannelDeleteDialogComponent,
        DeliveryChannelDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubDeliveryChannelModule {}
