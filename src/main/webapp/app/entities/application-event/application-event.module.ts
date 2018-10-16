import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NotificationHubSharedModule } from 'app/shared';
import {
    ApplicationEventComponent,
    ApplicationEventDetailComponent,
    ApplicationEventUpdateComponent,
    ApplicationEventDeletePopupComponent,
    ApplicationEventDeleteDialogComponent,
    applicationEventRoute,
    applicationEventPopupRoute
} from './';

const ENTITY_STATES = [...applicationEventRoute, ...applicationEventPopupRoute];

@NgModule({
    imports: [NotificationHubSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApplicationEventComponent,
        ApplicationEventDetailComponent,
        ApplicationEventUpdateComponent,
        ApplicationEventDeleteDialogComponent,
        ApplicationEventDeletePopupComponent
    ],
    entryComponents: [
        ApplicationEventComponent,
        ApplicationEventUpdateComponent,
        ApplicationEventDeleteDialogComponent,
        ApplicationEventDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubApplicationEventModule {}
