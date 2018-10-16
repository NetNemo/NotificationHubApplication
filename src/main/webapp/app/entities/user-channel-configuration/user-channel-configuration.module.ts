import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NotificationHubSharedModule } from 'app/shared';
import {
    UserChannelConfigurationComponent,
    UserChannelConfigurationDetailComponent,
    UserChannelConfigurationUpdateComponent,
    UserChannelConfigurationDeletePopupComponent,
    UserChannelConfigurationDeleteDialogComponent,
    userChannelConfigurationRoute,
    userChannelConfigurationPopupRoute
} from './';

const ENTITY_STATES = [...userChannelConfigurationRoute, ...userChannelConfigurationPopupRoute];

@NgModule({
    imports: [NotificationHubSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserChannelConfigurationComponent,
        UserChannelConfigurationDetailComponent,
        UserChannelConfigurationUpdateComponent,
        UserChannelConfigurationDeleteDialogComponent,
        UserChannelConfigurationDeletePopupComponent
    ],
    entryComponents: [
        UserChannelConfigurationComponent,
        UserChannelConfigurationUpdateComponent,
        UserChannelConfigurationDeleteDialogComponent,
        UserChannelConfigurationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NotificationHubUserChannelConfigurationModule {}
