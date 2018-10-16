import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NotificationHubSharedModule } from 'app/shared';
import {
    SystemEventComponent,
    SystemEventDetailComponent,
    SystemEventUpdateComponent,
    SystemEventDeletePopupComponent,
    SystemEventDeleteDialogComponent,
    systemEventRoute,
    systemEventPopupRoute
} from './';

const ENTITY_STATES = [...systemEventRoute, ...systemEventPopupRoute];

@NgModule({
    imports: [NotificationHubSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SystemEventComponent,
        SystemEventDetailComponent,
        SystemEventUpdateComponent,
        SystemEventDeleteDialogComponent,
        SystemEventDeletePopupComponent
    ],
    entryComponents: [SystemEventComponent, SystemEventUpdateComponent, SystemEventDeleteDialogComponent, SystemEventDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubSystemEventModule {}
