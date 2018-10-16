import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NotificationHubSharedModule } from 'app/shared';
import {
    EventDeliveryStatusComponent,
    EventDeliveryStatusDetailComponent,
    EventDeliveryStatusUpdateComponent,
    EventDeliveryStatusDeletePopupComponent,
    EventDeliveryStatusDeleteDialogComponent,
    eventDeliveryStatusRoute,
    eventDeliveryStatusPopupRoute
} from './';

const ENTITY_STATES = [...eventDeliveryStatusRoute, ...eventDeliveryStatusPopupRoute];

@NgModule({
    imports: [NotificationHubSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EventDeliveryStatusComponent,
        EventDeliveryStatusDetailComponent,
        EventDeliveryStatusUpdateComponent,
        EventDeliveryStatusDeleteDialogComponent,
        EventDeliveryStatusDeletePopupComponent
    ],
    entryComponents: [
        EventDeliveryStatusComponent,
        EventDeliveryStatusUpdateComponent,
        EventDeliveryStatusDeleteDialogComponent,
        EventDeliveryStatusDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubEventDeliveryStatusModule {}
