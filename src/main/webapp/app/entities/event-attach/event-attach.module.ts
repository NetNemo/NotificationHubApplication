import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NotificationHubSharedModule } from 'app/shared';
import {
    EventAttachComponent,
    EventAttachDetailComponent,
    EventAttachUpdateComponent,
    EventAttachDeletePopupComponent,
    EventAttachDeleteDialogComponent,
    eventAttachRoute,
    eventAttachPopupRoute
} from './';

const ENTITY_STATES = [...eventAttachRoute, ...eventAttachPopupRoute];

@NgModule({
    imports: [NotificationHubSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EventAttachComponent,
        EventAttachDetailComponent,
        EventAttachUpdateComponent,
        EventAttachDeleteDialogComponent,
        EventAttachDeletePopupComponent
    ],
    entryComponents: [EventAttachComponent, EventAttachUpdateComponent, EventAttachDeleteDialogComponent, EventAttachDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubEventAttachModule {}
